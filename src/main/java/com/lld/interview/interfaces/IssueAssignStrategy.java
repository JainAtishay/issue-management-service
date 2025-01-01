package com.lld.interview.interfaces;

import com.lld.interview.model.Agent;
import com.lld.interview.model.Issue;

import java.util.List;

public interface IssueAssignStrategy {
    Agent getAgent(Issue issue, List<Agent> availableAgents);
}
