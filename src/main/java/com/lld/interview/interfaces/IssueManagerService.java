package com.lld.interview.interfaces;

import com.lld.interview.model.Issue;

import java.util.List;
import java.util.Map;

public interface IssueManagerService {
    String createIssue(String transactionId,String issueType,String subject,String description,String email);
    String assignIssue(String issueId);
    List<Issue> getIssues(Map<String, String> filters);
    public void updateIssue(String issueId, String status, String remarks);
}
