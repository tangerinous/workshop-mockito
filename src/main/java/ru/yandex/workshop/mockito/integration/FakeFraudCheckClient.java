package ru.yandex.workshop.mockito.integration;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class FakeFraudCheckClient implements FraudCheckClient {

    @Override
    public FraudVerdict check(long customerId, BigDecimal purchaseAmount) {
        if (purchaseAmount.compareTo(new BigDecimal("50000")) > 0) {
            return new FraudVerdict(true, "Purchase requires manual review");
        }
        return new FraudVerdict(false, "OK");
    }
}
