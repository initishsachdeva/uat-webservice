package com.webservice.uatwebservice.restcontrollers;

import com.webservice.uatwebservice.entity.JiraIssues;
import com.webservice.uatwebservice.service.JiraIssuesService;
import com.webservice.uatwebservice.utility.ExcelUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class JiraIssuesRestController {
    @Autowired
    private JiraIssuesService jiraService;

    @PostMapping(value = "/jiraIssues/upload/{sprintValue}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> fileUploadWithSprintValue(@PathVariable String sprintValue, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Please select an excel file"));
        if (sprintValue.isEmpty())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error", "Please provide the sprint value"));
        if (ExcelUtility.checkExcelFormat(file)) {
            this.jiraService.save(file, sprintValue);
            return ResponseEntity.ok(Map.of("message", "Excel file uploaded successfully and data is stored in DB"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", "Please upload a excel file with .xlsx format"));
    }

    @GetMapping(value = "/jiraIssues", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<JiraIssues> getAllProducts() {
        return this.jiraService.getAllProducts();
    }

    @GetMapping(value = "/jiraIssues/sprint", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<JiraIssues> getBySprintNumber(@RequestParam("sprintValue") String sprintValue) {
        return this.jiraService.getBySprintValue(sprintValue);
    }

}
