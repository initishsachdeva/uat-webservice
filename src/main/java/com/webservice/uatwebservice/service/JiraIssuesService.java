package com.webservice.uatwebservice.service;

import com.webservice.uatwebservice.entity.JiraIssues;
import com.webservice.uatwebservice.repo.JiraIssuesRepo;
import com.webservice.uatwebservice.utility.ExcelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JiraIssuesService {

    @Autowired
    private JiraIssuesRepo jiraIssuesRepo;

    public void save(MultipartFile file, String sprintValue) {
        try {
            List<JiraIssues> jiraIssues = new ArrayList<>();
            jiraIssues = ExcelUtility.convertExcelToList(file.getInputStream());
            if (sprintValue.length() > 0) {
                for (JiraIssues jiraItems : jiraIssues) {
                    jiraItems.setSprint(sprintValue);
                }
            }
            this.jiraIssuesRepo.saveAll(jiraIssues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveJiraFile(MultipartFile file) {
        try {
            List<JiraIssues> jiraIssues = new ArrayList<>();
            jiraIssues = ExcelUtility.convertExcelToList(file.getInputStream());
            this.jiraIssuesRepo.saveAll(jiraIssues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<JiraIssues> getAllProducts() {
        return this.jiraIssuesRepo.findAll();
    }

    public List<JiraIssues> getBySprintValue(String sprintValue) {
        return this.jiraIssuesRepo.findAll();
    }
}