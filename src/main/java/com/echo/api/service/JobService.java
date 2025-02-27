package com.echo.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.echo.api.model.Job;

import java.util.List;

public interface JobService {

    List<Job> getNewestJobs(int number);

    Page<Job> getJobsByPage(Pageable pageable);

    Job validateAndGetJobById(String id);

    Job saveJob(Job job);

    void deleteJob(Job job);

    Page<Job> search(String text, Pageable pageable);
}
