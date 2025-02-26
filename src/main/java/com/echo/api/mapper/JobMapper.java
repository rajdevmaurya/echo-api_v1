package com.echo.api.mapper;

import com.echo.api.model.JobServiceEntity;
import com.echo.api.rest.dto.CreateJobRequest;
import com.echo.api.rest.dto.JobResponse;
import com.echo.api.rest.dto.UpdateJobRequest;

public interface JobMapper {

    JobResponse toJobResponse(JobServiceEntity job);

    JobServiceEntity toJob(CreateJobRequest createJobRequest);

    void updateJobFromRequest(UpdateJobRequest updateJobRequest, JobServiceEntity job);
}