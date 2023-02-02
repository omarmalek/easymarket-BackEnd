package com.smartweb.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartweb.market.entity.OrderDAO;
@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, Long> {
	
	@Query("select s from OrderDAO s where s.isSentDelivery = false")
	Page<OrderDAO> getSetterOrders(Pageable pageRequest);
	
	@Query("select s from OrderDAO s where s.isDelivered = false")
	Page<OrderDAO> getDeliveryOrders(Pageable pageRequest);

	@Query("select s from OrderDAO s where s.customerId = ?1")
	Page<OrderDAO> getCustomerOrders(long customerId,Pageable pageRequest);
	
	
}
