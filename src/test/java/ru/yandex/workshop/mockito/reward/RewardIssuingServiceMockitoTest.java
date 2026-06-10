package ru.yandex.workshop.mockito.reward;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.yandex.workshop.mockito.customer.Customer;
import ru.yandex.workshop.mockito.customer.CustomerRepository;
import ru.yandex.workshop.mockito.integration.Campaign;
import ru.yandex.workshop.mockito.integration.CampaignGateway;
import ru.yandex.workshop.mockito.integration.FraudCheckClient;
import ru.yandex.workshop.mockito.integration.FraudVerdict;
import ru.yandex.workshop.mockito.integration.MessageDeliveryException;
import ru.yandex.workshop.mockito.integration.MessageGateway;

@Disabled("Практика для воркшопа: включайте тесты по одному и реализуйте TODO.")
@ExtendWith(MockitoExtension.class)
class RewardIssuingServiceMockitoTest {

    private static final Instant FIXED_NOW = Instant.parse("2026-04-13T10:15:30Z");

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private FraudCheckClient fraudCheckClient;

    @Mock
    private CampaignGateway campaignGateway;

    @Mock
    private RewardCalculator rewardCalculator;

    @Mock
    private RewardPolicy rewardPolicy;

    @Mock
    private RewardIssueRepository rewardIssueRepository;

    @Mock
    private MessageGateway messageGateway;

    @Captor
    private ArgumentCaptor<RewardIssue> rewardIssueCaptor;

    private RewardIssuingService service;

    @BeforeEach
    void setUp() {
        // TODO: создать RewardIssuingService вручную.
        // Подставьте все @Mock-зависимости и Clock.fixed(...), чтобы время в тестах было стабильным.
        service = null;
    }

    @Test
    void shouldThrowWhenCustomerDoesNotExist() {
        // TODO: настройте customerRepository.findById(42L), чтобы он вернул Optional.empty().
        // TODO: вызовите service.issueReward(...) и проверьте IllegalArgumentException.
        // TODO: проверьте, что fraudCheckClient, campaignGateway, repository и messageGateway не вызывались.
    }

    @Test
    void shouldRejectInactiveCustomerAndStopScenario() {
        // TODO: создайте неактивного Customer.
        // TODO: настройте customerRepository.findById(...), чтобы вернуть этого клиента.
        // TODO: вызовите service.issueReward(...).
        // TODO: проверьте статус REJECTED_INACTIVE_CUSTOMER и сообщение "Customer is inactive".
        // TODO: проверьте, что остальные зависимости не вызывались.
    }

    @Test
    void shouldRejectSmallPurchaseWithoutCallingFraudService() {
        // TODO: создайте активного Customer.
        // TODO: настройте customerRepository.findById(...).
        // TODO: вызовите service.issueReward(...) с суммой меньше 3000.00.
        // TODO: проверьте статус REJECTED_SMALL_PURCHASE.
        // TODO: проверьте, что fraudCheckClient и следующие зависимости не вызывались.
    }

    @Test
    void shouldRejectFraudulentPurchaseAndNotRequestCampaign() {
        // TODO: создайте активного Customer и сумму покупки.
        // TODO: настройте customerRepository.findById(...).
        // TODO: настройте fraudCheckClient.check(...) через eq(...), чтобы вернуть blocked=true.
        // TODO: проверьте статус REJECTED_FRAUD и reason из FraudVerdict.
        // TODO: проверьте, что campaignGateway, rewardCalculator, repository и messageGateway не вызывались.
    }

    @Test
    void shouldRejectWhenCampaignIsMissing() {
        // TODO: настройте успешный путь до поиска кампании: customer + fraud OK.
        // TODO: настройте campaignGateway.findCampaignForSegment(...), чтобы вернуть Optional.empty().
        // TODO: проверьте статус REJECTED_NO_CAMPAIGN.
        // TODO: через never() проверьте, что save и sendRewardIssuedMessage не вызывались.
    }

    @Test
    void shouldRejectInactiveCampaign() {
        // TODO: настройте customer, fraud OK и найденную campaign с active=false.
        // TODO: проверьте статус REJECTED_INACTIVE_CAMPAIGN.
        // TODO: проверьте, что rewardCalculator.calculateReward(...) и save(...) не вызывались.
    }

    @Test
    void shouldIssueRewardAndSaveFullRewardIssue() {
        // TODO: настройте успешный сценарий: customer, fraud OK, campaign, rewardCalculator, rewardPolicy.
        // TODO: вызовите service.issueReward(...).
        // TODO: проверьте RewardDecision: APPROVED, rewardAmount, campaignName.
        // TODO: через ArgumentCaptor захватите RewardIssue, переданный в rewardIssueRepository.save(...).
        // TODO: проверьте customerId, purchaseAmount, rewardAmount, campaignName, status и createdAt.
        // TODO: проверьте, что messageGateway получил email клиента и сообщение с суммой награды.
    }

    @Test
    void shouldNotSendMessageForSmallReward() {
        // TODO: настройте успешную выдачу награды, но rewardPolicy.shouldSendMessage(...) должен вернуть false.
        // TODO: проверьте статус APPROVED.
        // TODO: проверьте, что save(...) был вызван.
        // TODO: проверьте, что sendRewardIssuedMessage(...) не вызывался.
    }

    @Test
    void shouldSaveRewardBeforeSendingNotification() {
        // TODO: настройте успешный сценарий с отправкой уведомления.
        // TODO: вызовите service.issueReward(...).
        // TODO: создайте InOrder для rewardIssueRepository и messageGateway.
        // TODO: проверьте порядок: сначала save(...), потом sendRewardIssuedMessage(...).
    }

    @Test
    void shouldPropagateMessageGatewayErrorAfterRewardWasSaved() {
        // TODO: настройте успешный сценарий до отправки уведомления.
        // TODO: через doThrow(...).when(messageGateway) настройте ошибку void-метода.
        // TODO: проверьте, что service.issueReward(...) пробрасывает MessageDeliveryException.
        // TODO: через InOrder проверьте, что save(...) был раньше попытки отправить уведомление.
    }
}
