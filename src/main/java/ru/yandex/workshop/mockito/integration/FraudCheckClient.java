package ru.yandex.workshop.mockito.integration;

import java.math.BigDecimal;

public interface FraudCheckClient {

    FraudVerdict check(long customerId, BigDecimal purchaseAmount);
}
