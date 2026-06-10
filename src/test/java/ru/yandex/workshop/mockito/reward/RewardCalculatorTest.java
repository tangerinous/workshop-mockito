package ru.yandex.workshop.mockito.reward;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ru.yandex.workshop.mockito.customer.Customer;
import ru.yandex.workshop.mockito.integration.Campaign;

class RewardCalculatorTest {

    private final RewardCalculator calculator = new RewardCalculator();

    @Test
    void shouldCalculateRewardWithLoyaltyMultiplier() {
        Customer customer = new Customer(3L, "irina@example.com", "Irina", true, "VIP", 3);
        Campaign campaign = new Campaign("VIP booster", new BigDecimal("0.15"), true);

        BigDecimal result = calculator.calculateReward(new BigDecimal("8000.00"), campaign, customer);

        assertThat(result).isEqualByComparingTo("1440.00");
    }

    @Test
    void shouldReturnBaseMultiplierForFirstLoyaltyLevel() {
        Customer customer = new Customer(2L, "boris@example.com", "Boris", true, "NEW", 1);

        BigDecimal result = calculator.loyaltyMultiplier(customer);

        assertThat(result).isEqualByComparingTo("1.00");
    }
}
