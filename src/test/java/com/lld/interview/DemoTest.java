package com.lld.interview;

import com.lld.interview.model.Issue;
import com.lld.interview.service.AgentServiceImpl;
import com.lld.interview.service.IssueServiceImpl;
import com.lld.interview.service.RoundRobinAssignStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoTest {

    public static void main(String[] args) {
        RoundRobinAssignStrategy roundRobinAssignStrategy = new RoundRobinAssignStrategy();
        AgentServiceImpl agentService = new AgentServiceImpl();

        IssueServiceImpl issueService = new IssueServiceImpl(roundRobinAssignStrategy, agentService);

        String res1 = issueService.createIssue("T1", "Payment Related", "Payment Failed", "My payment failed but money is debited", "testUser1@test.com");
        String res2 = issueService.createIssue("T2", "Mutual Fund Related", "Purchase Failed", "Unable to purchase Mutual Fund", "testUser2@test.com");

        System.out.println(res1);

        String res3 = agentService.addAgent("agent1@test.com", "Agent 1", Arrays.asList("Payment Related", "Gold Related"));

        System.out.println(res3);

        String res4 = issueService.assignIssue("I1");

        System.out.println(res4);

        Map<String, String> filter = new HashMap<>();

        filter.put("type", "Payment Related");
        List<Issue> issueList = issueService.getIssues(filter);

        System.out.println(issueList);

        issueService.updateIssue("I1", "In Progress", "Waiting for payment confirmation");

        issueService.resolveIssue("I1", "PaymentFailed debited amount will get reversed");
        agentService.viewAgentsWorkHistory();
    }
}
