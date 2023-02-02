package com.smartweb.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartweb.market.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByPhoneNumber(String customerPhone);
	
//	@Query(value="select e from Employee e where empName = ?1 limit 1", nativeQuery=true)
	
	
	Customer findFirstByPhoneNumber(String customerPhone);

}
