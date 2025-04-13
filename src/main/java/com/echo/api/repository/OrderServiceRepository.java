package com.echo.api.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.echo.api.model.OrderServiceEntity;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderServiceEntity, Long> {

	
	@Query("SELECT j FROM OrderServiceEntity j WHERE " +
		       "j.title LIKE %?1% OR " +
		       "j.description LIKE %?1% OR " +
		       "j.company LIKE %?1%")
		Page<OrderServiceEntity> findJobsUsingQueryStringQuery(String text, Pageable pageable);
	
	 List<OrderServiceEntity> findAllByOrderByCreateDateDesc();
	 
	 
	 @Query("SELECT j FROM OrderServiceEntity j WHERE j.user.id =?1")
	Page<OrderServiceEntity> findJobsUsingUserId(Long id,Pageable pageable);
	
   
}
