package com.lld.interview.service;

import com.lld.interview.interfaces.IssueAssignStrategy;
import com.lld.interview.model.Agent;
import com.lld.interview.model.Issue;

import java.util.List;

public class RoundRobinAssignStrategy implements IssueAssignStrategy {

    @Override
    public Agent getAgent(Issue issue, List<Agent> availableAgents) {
        return availableAgents.get(0);
    }
}
