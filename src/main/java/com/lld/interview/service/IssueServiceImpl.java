package com.lld.interview.service;

import com.lld.interview.interfaces.AgentManagerService;
import com.lld.interview.interfaces.IssueAssignStrategy;
import com.lld.interview.interfaces.IssueManagerService;
import com.lld.interview.model.Agent;
import com.lld.interview.model.Issue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueServiceImpl implements IssueManagerService {
    public Map<String, Issue> issues = new HashMap<>();
    public IssueAssignStrategy issueAssignStrategy;
    public AgentManagerService agentManagerService;

    public IssueServiceImpl(IssueAssignStrategy issueAssignStrategy, AgentManagerService agentManagerService){
        this.issueAssignStrategy = issueAssignStrategy;
        this.agentManagerService = agentManagerService;
    }

    @Override
    public String createIssue(String transactionId, String issueType, String subject, String description, String email) {
        Issue issue = new Issue(transactionId, issueType, subject, description, email);
        String issueId = "I"+ (issues.size() + 1);
        issues.put(issueId, issue);

        return "Issue " +  issueId + " created against transaction " + transactionId;
    }

    @Override
    public String assignIssue(String issueId) {
        Issue issue = issues.get(issueId);
        if (issue == null || !issue.getStatus().equals("Open")) return "";

        List<Agent> availableAgents = agentManagerService.getAvailableAgents(issue.getIssueType());
        Agent assignedAgent = issueAssignStrategy.getAgent(issue, availableAgents);

        issue.setAssignedAgent(assignedAgent);
        issue.setStatus("In Progress");
        assignedAgent.setAvailable(false);
        //assignedAgent.getWorkHistory().add(issue);

        return "Issue " + issueId+ " assigned to agent " + assignedAgent.getAgentId();
    }

    public List<Issue> getIssues(Map<String, String> filters) {

        List<Issue> filteredIssues = new ArrayList<>();

        for(Map.Entry<String, String> entry : filters.entrySet()){
            String type = entry.getKey();
            String filter = entry.getValue();

            filteredIssues.add(getIssue(type, filter));
        }

        return filteredIssues;
    }

    public Issue getIssue(String type, String filter){
        switch (type){
            case "type":
                return issues.values().stream()
                        .filter(issue -> issue.getIssueType().equalsIgnoreCase(filter))
                        .findFirst().get();
        }

        return null;
    }

    public void updateIssue(String issueId, String status, String remarks) {
        Issue issue = issues.get(issueId);
        if (issue != null) {
            issue.setStatus(status);
            issue.setRemarks(remarks);
        }
    }

    public void resolveIssue(String issueId, String remarks) {
        Issue issue = issues.get(issueId);
        updateIssue(issueId, "Resolved", remarks);
        Agent agent  = issue.getAssignedAgent();
        agent.isAvailable  = true;
        return;
    }

}
