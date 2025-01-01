package com.lld.interview.model;

import lombok.Getter;
import lombok.Setter;
I1 -> A1
   -> A2

@Getter
@Setter
public class Issue {
    public String transactionId;
    public String issueType;
    public String subject;
    public String description;
    public String email;
    private String status; //"Open", "In Progress", "Resolved"
    private String remarks;
    private Agent assignedAgent;

    public Issue(String transactionId, String issueType, String subject, String description, String email) {
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = "Open";
    }
}
