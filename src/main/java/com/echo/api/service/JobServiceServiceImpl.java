package com.echo.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.echo.api.exception.JobNotFoundException;
import com.echo.api.model.JobServiceEntity;
import com.echo.api.repository.JobServiceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JobServiceServiceImpl implements JobServiceService {

    private final JobServiceRepository jobServiceRepository;

    @Override
    public List<JobServiceEntity> getNewestJobs(int number) {
        return jobServiceRepository.findAll(PageRequest.of(0, number, Sort.by("createDate").descending())).getContent();
    }

    @Override
    public Page<JobServiceEntity> getJobsByPage(Pageable pageable) {
        return jobServiceRepository.findAll(pageable);
    }

    @Override
    public JobServiceEntity validateAndGetJobById(Long id) {
        return jobServiceRepository.findById(id).orElseThrow(() -> new JobNotFoundException(id+""));
    }

    @Override
    public JobServiceEntity saveJob(JobServiceEntity job) {
        return jobServiceRepository.save(job);
    }

    @Override
    public void deleteJob(JobServiceEntity job) {
    	jobServiceRepository.delete(job);
    }

    @Override
    public Page<JobServiceEntity> search(String text, Pageable pageable) {
        return jobServiceRepository.findJobsUsingQueryStringQuery(text, pageable);
    }

	@Override
	public Page<JobServiceEntity> getMenuLookupType(String lookupType, Pageable pageable) {
		// TODO Auto-generated method stub
		return jobServiceRepository.getMenuLookupType(lookupType,pageable);
	}
}
