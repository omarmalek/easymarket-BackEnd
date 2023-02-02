package com.smartweb.market.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartweb.market.model.Ordertobedeleted;
@Repository
public interface OrderRepositorytbedeleted extends JpaRepository<Ordertobedeleted, Long> {

	Page<Ordertobedeleted> findAll(Pageable pageRequest);
//	Page<Order> findPage(int pageIndex,int pageSize);
}
