package com.echo.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.echo.api.model.JobServiceEntity;

import java.util.List;

public interface JobServiceService {

    List<JobServiceEntity> getNewestJobs(int number);

    Page<JobServiceEntity> getJobsByPage(Pageable pageable);

    JobServiceEntity validateAndGetJobById(Long id);

    JobServiceEntity saveJob(JobServiceEntity job);

    void deleteJob(JobServiceEntity job);

    Page<JobServiceEntity> search(String text, Pageable pageable);
    
    Page<JobServiceEntity> getMenuLookupType(String lookupType, Pageable pageable);
}
