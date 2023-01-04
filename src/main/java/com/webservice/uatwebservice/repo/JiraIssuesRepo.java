package com.webservice.uatwebservice.repo;

import com.webservice.uatwebservice.entity.JiraIssues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraIssuesRepo extends JpaRepository<JiraIssues,String> {

}

