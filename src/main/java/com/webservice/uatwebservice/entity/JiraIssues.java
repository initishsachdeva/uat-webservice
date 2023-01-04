package com.webservice.uatwebservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JiraIssues {
    private String type;
    @Id
    private String key;
    private String summary;
    private String assignee;
    private String component;
    private String description;
    private String sprintValue;
}
