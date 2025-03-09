package com.echo.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.echo.api.mapper.JobMapper;
import com.echo.api.model.JobServiceEntity;
import com.echo.api.model.OrderServiceEntity;
import com.echo.api.model.User;
import com.echo.api.order.OrderService;
import com.echo.api.rest.dto.CreateJobRequest;
import com.echo.api.rest.dto.CreateOrderRequest;
import com.echo.api.rest.dto.JobResponse;
import com.echo.api.rest.dto.SearchRequest;
import com.echo.api.rest.dto.UpdateJobRequest;
import com.echo.api.service.JobServiceService;
import com.echo.api.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.echo.api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobServiceService jobServiceService;
    private final JobMapper jobMapper;
    private final OrderService orderService;

    @Operation(
            summary = "Get jobs with pagination"
            ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @GetMapping
    public Page<JobResponse> getJobs(
            @ParameterObject @PageableDefault(sort = {"createDate"}, direction = Direction.DESC) Pageable pageable,
            Principal principal) {
        //log.info("Request to get a page of jobs (offset = {}, pageSize = {}) made by {}",
             //   pageable.getOffset(), pageable.getPageSize(), principal.getName());
        return jobServiceService.getJobsByPage(pageable).map(jobMapper::toJobResponse);
    }

    @Operation(summary = "Get the newest jobs")
    @GetMapping("/newest")
    public List<JobResponse> getNewestJobs(@RequestParam(value = "number", required = false, defaultValue = "4") int number) {
        if (number > 10) {
            log.warn("The parameter number cannot be bigger than 10");
            number = 10;
        }
           List<JobResponse> resp= jobServiceService.getNewestJobs(number)
                .stream()
                .map(jobMapper::toJobResponse)
                .collect(Collectors.toList());
           return resp;
      
    }

    @Operation(
            summary = "Get a job by id"
           // security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @GetMapping("/{id}")
    public JobResponse getJobById(@PathVariable Long id, Principal principal) {
       // log.info("Request to get a job with id {} made by {}", id, principal.getName());
    	JobServiceEntity job = jobServiceService.validateAndGetJobById(id);
        return jobMapper.toJobResponse(job);
    }

    @Operation(
            summary = "Create a job"
            , security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public JobResponse createJob(@Valid @RequestBody CreateJobRequest createJobRequest, Principal principal) {
        log.info("Request to create a job made by {}", principal.getName());
        JobServiceEntity job = jobMapper.toJob(createJobRequest);
        job = jobServiceService.saveJob(job);
        return jobMapper.toJobResponse(job);
    }

    @Operation(
            summary = "Delete a job"
            ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @DeleteMapping("/{id}")
    public JobResponse deleteJob(@PathVariable Long id, Principal principal) {
       // log.info("Request to delete a job with id {} made by {}", id, principal.getName());
        JobServiceEntity job = jobServiceService.validateAndGetJobById(id);
        jobServiceService.deleteJob(job);
        return jobMapper.toJobResponse(job);
    }

    @Operation(
            summary = "Update a job"
           // ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @PutMapping("/{id}")
    public JobResponse updateJob(@PathVariable Long id, @Valid @RequestBody UpdateJobRequest updateJobRequest, Principal principal) {
        log.info("Request to update a job with id {} made by {}", id, principal.getName());
        JobServiceEntity job = jobServiceService.validateAndGetJobById(id);
        jobMapper.updateJobFromRequest(updateJobRequest, job);
        jobServiceService.saveJob(job);
        
        return jobMapper.toJobResponse(job);
    }

    @Operation(
            summary = "Search for jobs"
           // ,security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)}
            )
    @PutMapping("/search")
    public Page<JobServiceEntity> searchJobs(@Valid @RequestBody SearchRequest searchRequest,
                                @ParameterObject @PageableDefault(sort = {"createDate"}, direction = Direction.DESC) Pageable pageable,
                                Principal principal) {
       // log.info("Request to search a job with text {} made by {}", searchRequest.getText(), principal.getName());
    	Page<JobServiceEntity> data= jobServiceService.search(searchRequest.getText(), pageable);
        return data;
    }
  /*  
    @GetMapping("/collection/{lookupType}")
    public Page<JobServiceEntity> getLookupType(@PathVariable String lookupType) {
    	Pageable pageable = PageRequest.of(0, 10);
    	Page<JobServiceEntity> data= jobServiceService.getMenuLookupType(lookupType, pageable);
        return data;
    }*/
    
    @GetMapping("/collection/{lookupType}")
    public Page<JobServiceEntity> getLookupType(@Valid @PathVariable String lookupType,@ParameterObject @PageableDefault(sort = {"createDate"}, direction = Direction.DESC) Pageable pageable,Principal principal) {
    	Page<JobServiceEntity> data= jobServiceService.getMenuLookupType(lookupType, pageable);
        return data;
    }
}
