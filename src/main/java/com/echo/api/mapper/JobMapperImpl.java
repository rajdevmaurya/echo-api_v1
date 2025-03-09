package com.echo.api.mapper;

import org.springframework.stereotype.Service;

import com.echo.api.model.JobServiceEntity;
import com.echo.api.rest.dto.CreateJobRequest;
import com.echo.api.rest.dto.JobResponse;
import com.echo.api.rest.dto.UpdateJobRequest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
@Service
public class JobMapperImpl implements JobMapper {
	 private static final DateTimeFormatter DATE_TIME_FORMATTER =
	            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
    @Override
    public JobResponse toJobResponse(JobServiceEntity job) {
        if (job == null) {
            return null;
        }
       // return new JobResponse(job.getId(), job.getTitle(), job.getCompany(), job.getLogoUrl(), job.getDescription(), job.getCreateDate());
        return  new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                job.getLogoUrl(),
                job.getDescription(),
                DATE_TIME_FORMATTER.format(job.getCreateDate()),
        		job.getLookupType(),
        		job.getBrand(),
        		job.getFeatureDescription());
    }

    @Override
    public JobServiceEntity toJob(CreateJobRequest createJobRequest) {
        if (createJobRequest == null) {
            return null;
        }
        return new JobServiceEntity(createJobRequest.getTitle(), createJobRequest.getCompany(), createJobRequest.getLogoUrl(), createJobRequest.getDescription(),createJobRequest.getLookupType(),
        		createJobRequest.getBrand(),createJobRequest.getFeatureDescription());
    }

    @Override
    public void updateJobFromRequest(UpdateJobRequest updateJobRequest, JobServiceEntity job) {
        if (updateJobRequest == null) {
            return;
        }

        if (updateJobRequest.getTitle() != null) {
            job.setTitle(updateJobRequest.getTitle());
        }
        if (updateJobRequest.getCompany() != null) {
            job.setCompany(updateJobRequest.getCompany());
        }
        if (updateJobRequest.getLogoUrl() != null) {
            job.setLogoUrl(updateJobRequest.getLogoUrl());
        }
        if (updateJobRequest.getDescription() != null) {
            job.setDescription(updateJobRequest.getDescription());
        }
        if (updateJobRequest.getLookupType() != null) {
            job.setLookupType(updateJobRequest.getLookupType());
        }
        if (updateJobRequest.getBrand() != null) {
            job.setBrand(updateJobRequest.getBrand());
        }
        if (updateJobRequest.getFeatureDescription() != null) {
            job.setFeatureDescription(updateJobRequest.getFeatureDescription());
        }
    }
}
