package com.smartweb.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.PageRequest;//this was the problem
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartweb.market.entity.OrderOldDAO;

public interface OrderOldRepository extends JpaRepository<OrderOldDAO, Long> {

	@Query("select s from OrderOldDAO s where s.customerId = ?1 ")//we don't really need this;
	Page<OrderOldDAO> findAllByCustomerId(long customerId, Pageable page);

	
	

}
