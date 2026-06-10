package ru.yandex.workshop.mockito.bad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.yandex.workshop.mockito.customer.Customer;
import ru.yandex.workshop.mockito.customer.CustomerRepository;
import ru.yandex.workshop.mockito.integration.Campaign;
import ru.yandex.workshop.mockito.integration.CampaignGateway;
import ru.yandex.workshop.mockito.integration.FraudCheckClient;
import ru.yandex.workshop.mockito.integration.FraudVerdict;
import ru.yandex.workshop.mockito.integration.MessageGateway;
import ru.yandex.workshop.mockito.reward.RewardCalculator;
import ru.yandex.workshop.mockito.reward.RewardDecision;
import ru.yandex.workshop.mockito.reward.RewardIssueRepository;
import ru.yandex.workshop.mockito.reward.RewardIssuingService;
import ru.yandex.workshop.mockito.reward.RewardPolicy;
import ru.yandex.workshop.mockito.reward.RewardStatus;

@Disabled("Практика для воркшопа: исправляем плохие тесты из BadMockitoExamplesTest.")
@ExtendWith(MockitoExtension.class)
class RefactoredBadMockitoExamplesTest {

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

    private RewardIssuingService service;

    @BeforeEach
    void setUp() {
        // TODO: создать настоящий RewardIssuingService, а не mock тестируемого класса.
        // Используйте @Mock-зависимости и Clock.fixed(...).
        service = null;
    }

    @Test
    void shouldUseSpecificMatchersForImportantArguments() {
        // TODO: перепишите плохой тест, где везде использовался any().
        // TODO: для customerId, purchaseAmount и segment используйте eq(...), потому что эти аргументы важны.
        // TODO: проверьте результат APPROVED.
        // TODO: проверьте, что уведомление ушло на правильный email с правильным текстом.
    }

    @Test
    void shouldVerifyOnlyMeaningfulExternalEffects() {
        // TODO: перепишите плохой тест, который проверял слишком много внутренних вызовов.
        // TODO: настройте сценарий "кампания не найдена".
        // TODO: проверьте статус REJECTED_NO_CAMPAIGN.
        // TODO: проверьте только важные внешние эффекты: save и notification не должны вызываться.
    }
}
