package ru.yandex.workshop.mockito.bad;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ru.yandex.workshop.mockito.customer.Customer;
import ru.yandex.workshop.mockito.customer.CustomerRepository;
import ru.yandex.workshop.mockito.integration.Campaign;
import ru.yandex.workshop.mockito.integration.CampaignGateway;
import ru.yandex.workshop.mockito.integration.FraudCheckClient;
import ru.yandex.workshop.mockito.integration.FraudVerdict;
import ru.yandex.workshop.mockito.integration.MessageGateway;
import ru.yandex.workshop.mockito.reward.RewardCalculator;
import ru.yandex.workshop.mockito.reward.RewardIssueRepository;
import ru.yandex.workshop.mockito.reward.RewardIssuingService;
import ru.yandex.workshop.mockito.reward.RewardPolicy;

@Disabled("Examples for workshop discussion. They are intentionally bad and should not be executed.")
class BadMockitoExamplesTest {

    @Test
    void badTestMocksDomainObjectAndVerifiesImplementationRoute() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        FraudCheckClient fraudCheckClient = mock(FraudCheckClient.class);
        CampaignGateway campaignGateway = mock(CampaignGateway.class);
        RewardCalculator rewardCalculator = mock(RewardCalculator.class);
        RewardPolicy rewardPolicy = mock(RewardPolicy.class);
        RewardIssueRepository rewardIssueRepository = mock(RewardIssueRepository.class);
        MessageGateway messageGateway = mock(MessageGateway.class);
        RewardIssuingService service = mock(RewardIssuingService.class);

        when(service.issueReward(3L, new BigDecimal("8000.00"))).thenCallRealMethod();

        service.issueReward(3L, new BigDecimal("8000.00"));

        verify(customerRepository).findById(3L);
        verify(fraudCheckClient).check(any(), any());
        verify(campaignGateway).findCampaignForSegment(any());
        verify(rewardCalculator).calculateReward(any(), any(), any());
        verify(rewardPolicy).shouldSendMessage(any());
    }

    @Test
    void badTestUsesAnyEverywhereAndWouldMissWrongArguments() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        CampaignGateway campaignGateway = mock(CampaignGateway.class);

        when(customerRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Customer(3L, "irina@example.com", "Irina", true, "VIP", 3)));
        when(campaignGateway.findCampaignForSegment(any()))
            .thenReturn(Optional.of(new Campaign("VIP booster", new BigDecimal("0.15"), true)));
    }
}
