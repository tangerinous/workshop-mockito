package ru.yandex.workshop.mockito.api;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.yandex.workshop.mockito.dto.IssueRewardRequest;
import ru.yandex.workshop.mockito.dto.RewardResponse;
import ru.yandex.workshop.mockito.reward.RewardDecision;
import ru.yandex.workshop.mockito.reward.RewardIssuingService;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardIssuingService rewardIssuingService;

    public RewardController(RewardIssuingService rewardIssuingService) {
        this.rewardIssuingService = rewardIssuingService;
    }

    @PostMapping("/issue")
    public RewardResponse issueReward(@Valid @RequestBody IssueRewardRequest request) {
        RewardDecision decision = rewardIssuingService.issueReward(request.customerId(), request.purchaseAmount());
        return new RewardResponse(
            decision.status().name(),
            decision.rewardAmount(),
            decision.campaignName(),
            decision.message()
        );
    }
}
