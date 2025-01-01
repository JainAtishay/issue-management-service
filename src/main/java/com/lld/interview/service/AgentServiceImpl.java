package com.lld.interview.service;

import com.lld.interview.interfaces.AgentManagerService;
import com.lld.interview.interfaces.IssueManagerService;
import com.lld.interview.model.Agent;
import com.lld.interview.model.Issue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgentServiceImpl implements AgentManagerService {
    public Map<String, Agent> agents = new HashMap<>();


    @Override
    public String addAgent(String agentEmail, String agentName, List<String> issueTypeList) {
        Agent agent = new Agent(agentEmail, agentName, issueTypeList);
        String agentId = "A"+ (agents.size() + 1);
        agent.agentId = agentId;
        agents.put(agentId, agent);
        return "Agent " +  agentId + " created";
    }

    @Override
    public List<Agent> getAvailableAgents(String issueType) {
        return agents.values().stream()
                .filter(agent -> agent.isAvailable() && agent.getIssueTypes().contains(issueType))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Issue>> viewAgentsWorkHistory() {
        Map<String, List<Issue>> workHistory = new HashMap<>();
        List<Agent> agentsList = agents.values().stream().toList();

        for(Agent agent : agentsList){
            workHistory.put(agent.getAgentId(), agent.getWorkHistory());
        }

        return workHistory;
    }
}
