package ru.yandex.workshop.mockito.reward;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class RewardPolicy {

    private static final BigDecimal BONUS_MESSAGE_THRESHOLD = new BigDecimal("500.00");

    public boolean shouldSendMessage(BigDecimal rewardAmount) {
        return rewardAmount.compareTo(BONUS_MESSAGE_THRESHOLD) >= 0;
    }
}
