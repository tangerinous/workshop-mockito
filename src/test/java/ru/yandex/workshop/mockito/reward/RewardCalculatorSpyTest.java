package ru.yandex.workshop.mockito.reward;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ru.yandex.workshop.mockito.customer.Customer;
import ru.yandex.workshop.mockito.integration.Campaign;

@Disabled("Практика для воркшопа: включите тест и реализуйте TODO.")
class RewardCalculatorSpyTest {

    @Test
    void shouldAllowPartialOverrideWithSpy() {
        // TODO: создайте spy на базе new RewardCalculator().
        // TODO: создайте Customer и Campaign.
        // TODO: через doReturn(...).when(spy) подмените loyaltyMultiplier(customer).
        // TODO: вызовите настоящий calculateReward(...).
        // TODO: проверьте, что расчет использовал подмененный multiplier.
    }
}
