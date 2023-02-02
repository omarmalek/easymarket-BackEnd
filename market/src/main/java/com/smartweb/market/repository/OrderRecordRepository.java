package com.smartweb.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartweb.market.entity.OrderRecordDAO;
@Repository
public interface OrderRecordRepository extends JpaRepository<OrderRecordDAO, Long> {

	public List<OrderRecordDAO> findByOrderId(Long orderId);

	public List<OrderRecordDAO> findByOrderId(long id);

}
