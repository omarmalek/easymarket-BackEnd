package com.smartweb.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartweb.market.model.OrderDTO;
import com.smartweb.market.model.Ordertobedeleted;
import com.smartweb.market.model.UserOrderDTO;

@Service
public interface OrderService {
	List<Ordertobedeleted> findAll(int pageIndex,int pageSize);

	Ordertobedeleted saveOrder(Ordertobedeleted order);
	
	UserOrderDTO saveUserOrder(UserOrderDTO order);

	List<OrderDTO> getControlOrders(int pageIndex, int pageSize);

	OrderDTO updateOrder(OrderDTO order);

	List<OrderDTO> getSetterOrders(int pageIndex, int pageSize);
	
	List<OrderDTO> getDeliveryOrders(int pageIndex, int pageSize);

	List<OrderDTO> getOldOrders(int pageIndex, int pageSize);
	
	List<OrderDTO> getCustomerOrders(long customerId, int pageIndex, int pageSize);

	List<OrderDTO> getCustomerOldOrders(long customerId, int pageIndex, int pageSize);
}

