package com.lld.interview.interfaces;

import com.lld.interview.model.Agent;
import com.lld.interview.model.Issue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface AgentManagerService {
    String addAgent(String agentEmail, String agentName , List<String> issueTypeList);
    List<Agent> getAvailableAgents(String issueType);
    Map<String, List<Issue>> viewAgentsWorkHistory();
}
