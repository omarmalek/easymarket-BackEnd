package com.smartweb.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartweb.market.entity.OrderRecordDAO;
import com.smartweb.market.entity.OrderRecordOldDAO;

public interface OrderRecordOldRepository extends JpaRepository<OrderRecordOldDAO, Long> {

	List<OrderRecordOldDAO> findByOrderId(long id);

}
