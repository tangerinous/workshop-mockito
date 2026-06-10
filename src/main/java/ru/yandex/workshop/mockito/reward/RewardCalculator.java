package ru.yandex.workshop.mockito.reward;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import ru.yandex.workshop.mockito.customer.Customer;
import ru.yandex.workshop.mockito.integration.Campaign;

@Component
public class RewardCalculator {

    public BigDecimal calculateReward(BigDecimal purchaseAmount, Campaign campaign, Customer customer) {
        return purchaseAmount
            .multiply(campaign.percent())
            .multiply(loyaltyMultiplier(customer))
            .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal loyaltyMultiplier(Customer customer) {
        BigDecimal extra = BigDecimal.valueOf(customer.loyaltyLevel() - 1)
            .divide(BigDecimal.TEN, 2, RoundingMode.HALF_UP);
        return BigDecimal.ONE.add(extra);
    }
}
