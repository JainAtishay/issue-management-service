package com.lld.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Agent {
    public String agentId;
    public String email;
    public String name;
    public List<String> IssueTypes;
    private List<Issue> workHistory;
    public boolean isAvailable = true;

    public Agent(String email, String name, List<String> issueTypes) {
        this.email = email;
        this.name = name;
        IssueTypes = issueTypes;
    }
}
