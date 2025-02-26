package com.echo.api.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.echo.api.model.JobServiceEntity;

@Repository
public interface JobServiceRepository extends JpaRepository<JobServiceEntity, Long> {

	
	@Query("SELECT j FROM JobServiceEntity j WHERE " +
		       "j.title LIKE %?1% OR " +
		       "j.description LIKE %?1% OR " +
		       "j.company LIKE %?1%")
		Page<JobServiceEntity> findJobsUsingQueryStringQuery(String text, Pageable pageable);
	
	
	@Query("SELECT j FROM JobServiceEntity j WHERE " +
		       "j.lookupType LIKE %?1%")
		Page<JobServiceEntity> getMenuLookupType(String lookupType, Pageable pageable);
   
}
