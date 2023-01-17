package com.webservice.uatwebservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JiraIssues {
    private String issueType;
    @Id
    private String issueKey;
    @Lob
    private String summary;
    private String components;
    @Lob
    private String description;
    private String fixVersion;
    private String priority;
    private String sprint;
    private String status;
    private String assignee;
    private String reporter;
    private String created;
}
