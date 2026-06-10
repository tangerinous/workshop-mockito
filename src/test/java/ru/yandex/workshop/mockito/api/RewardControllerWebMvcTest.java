package ru.yandex.workshop.mockito.api;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ru.yandex.workshop.mockito.reward.RewardDecision;
import ru.yandex.workshop.mockito.reward.RewardIssuingService;

@Disabled("Практика для воркшопа: включайте тесты по одному и реализуйте TODO.")
@WebMvcTest(RewardController.class)
class RewardControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardIssuingService rewardIssuingService;

    @Test
    void shouldReturnRewardDecisionAsJson() throws Exception {
        // TODO: настройте @MockBean rewardIssuingService через when(...).
        // Он должен вернуть RewardDecision.approved(...) для customerId=3 и purchaseAmount=8000.00.
        // TODO: выполните POST /api/rewards/issue через mockMvc.
        // TODO: проверьте HTTP 200.
        // TODO: проверьте JSON-поля: status, rewardAmount, campaignName, message.
        // TODO: через verify(...) проверьте, что контроллер вызвал rewardIssuingService с нужными аргументами.
    }

    @Test
    void shouldReturnBadRequestForInvalidPayload() throws Exception {
        // TODO: выполните POST /api/rewards/issue с purchaseAmount=0.
        // TODO: проверьте HTTP 400.
        // TODO: обратите внимание: здесь мы проверяем validation и Spring MVC, поэтому @WebMvcTest уместен.
    }
}
