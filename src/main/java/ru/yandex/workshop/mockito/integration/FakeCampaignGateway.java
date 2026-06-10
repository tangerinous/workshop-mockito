package ru.yandex.workshop.mockito.integration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class FakeCampaignGateway implements CampaignGateway {

    private final Map<String, Campaign> campaigns = Map.of(
        "NEW", new Campaign("Welcome bonus", new BigDecimal("0.05"), true),
        "REGULAR", new Campaign("Regular cashback", new BigDecimal("0.08"), true),
        "VIP", new Campaign("VIP booster", new BigDecimal("0.15"), true)
    );

    @Override
    public Optional<Campaign> findCampaignForSegment(String segment) {
        return Optional.ofNullable(campaigns.get(segment));
    }
}
