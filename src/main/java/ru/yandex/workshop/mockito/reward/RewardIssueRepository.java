package ru.yandex.workshop.mockito.reward;

import java.util.List;

public interface RewardIssueRepository {

    void save(RewardIssue issue);

    List<RewardIssue> findAll();

    void clear();
}
