package ru.yandex.workshop.mockito.integration;

import java.util.Optional;

public interface CampaignGateway {

    Optional<Campaign> findCampaignForSegment(String segment);
}
