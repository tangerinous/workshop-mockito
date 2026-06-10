package ru.yandex.workshop.mockito.reward;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;

import org.springframework.stereotype.Service;

import ru.yandex.workshop.mockito.customer.Customer;
import ru.yandex.workshop.mockito.customer.CustomerRepository;
import ru.yandex.workshop.mockito.integration.Campaign;
import ru.yandex.workshop.mockito.integration.CampaignGateway;
import ru.yandex.workshop.mockito.integration.FraudCheckClient;
import ru.yandex.workshop.mockito.integration.FraudVerdict;
import ru.yandex.workshop.mockito.integration.MessageGateway;

@Service
public class RewardIssuingService {

    private static final BigDecimal MIN_PURCHASE = new BigDecimal("3000.00");

    private final CustomerRepository customerRepository;
    private final FraudCheckClient fraudCheckClient;
    private final CampaignGateway campaignGateway;
    private final RewardCalculator rewardCalculator;
    private final RewardPolicy rewardPolicy;
    private final RewardIssueRepository rewardIssueRepository;
    private final MessageGateway messageGateway;
    private final Clock clock;

    public RewardIssuingService(
        CustomerRepository customerRepository,
        FraudCheckClient fraudCheckClient,
        CampaignGateway campaignGateway,
        RewardCalculator rewardCalculator,
        RewardPolicy rewardPolicy,
        RewardIssueRepository rewardIssueRepository,
        MessageGateway messageGateway,
        Clock clock
    ) {
        this.customerRepository = customerRepository;
        this.fraudCheckClient = fraudCheckClient;
        this.campaignGateway = campaignGateway;
        this.rewardCalculator = rewardCalculator;
        this.rewardPolicy = rewardPolicy;
        this.rewardIssueRepository = rewardIssueRepository;
        this.messageGateway = messageGateway;
        this.clock = clock;
    }

    public RewardDecision issueReward(long customerId, BigDecimal purchaseAmount) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));

        if (!customer.active()) {
            return RewardDecision.rejected(RewardStatus.REJECTED_INACTIVE_CUSTOMER, "Customer is inactive");
        }

        if (purchaseAmount.compareTo(MIN_PURCHASE) < 0) {
            return RewardDecision.rejected(RewardStatus.REJECTED_SMALL_PURCHASE, "Purchase is too small");
        }

        FraudVerdict fraudVerdict = fraudCheckClient.check(customerId, purchaseAmount);
        if (fraudVerdict.blocked()) {
            return RewardDecision.rejected(RewardStatus.REJECTED_FRAUD, fraudVerdict.reason());
        }

        Campaign campaign = campaignGateway.findCampaignForSegment(customer.segment())
            .orElse(null);
        if (campaign == null) {
            return RewardDecision.rejected(RewardStatus.REJECTED_NO_CAMPAIGN, "No campaign for segment");
        }
        if (!campaign.active()) {
            return RewardDecision.rejected(RewardStatus.REJECTED_INACTIVE_CAMPAIGN, "Campaign is inactive");
        }

        BigDecimal rewardAmount = rewardCalculator.calculateReward(purchaseAmount, campaign, customer);
        RewardIssue issue = new RewardIssue(
            customer.id(),
            purchaseAmount,
            rewardAmount,
            campaign.name(),
            RewardStatus.APPROVED,
            Instant.now(clock)
        );
        rewardIssueRepository.save(issue);

        if (rewardPolicy.shouldSendMessage(rewardAmount)) {
            messageGateway.sendRewardIssuedMessage(
                customer.email(),
                "You have received " + rewardAmount + " bonus points in campaign " + campaign.name()
            );
        }

        return RewardDecision.approved(rewardAmount, campaign.name());
    }
}
