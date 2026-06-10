package ru.yandex.workshop.mockito.reward;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRewardIssueRepository implements RewardIssueRepository {

    private final List<RewardIssue> issues = new ArrayList<>();

    @Override
    public void save(RewardIssue issue) {
        issues.add(issue);
    }

    @Override
    public List<RewardIssue> findAll() {
        return List.copyOf(issues);
    }

    @Override
    public void clear() {
        issues.clear();
    }
}
