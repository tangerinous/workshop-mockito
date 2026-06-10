package ru.yandex.workshop.mockito.reward;

import java.math.BigDecimal;
import java.time.Instant;

public record RewardIssue(
    long customerId,
    BigDecimal purchaseAmount,
    BigDecimal rewardAmount,
    String campaignName,
    RewardStatus status,
    Instant createdAt
) {
}
