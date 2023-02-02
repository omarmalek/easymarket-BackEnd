package com.smartweb.market.service;

import org.springframework.stereotype.Service;

import com.smartweb.market.entity.Customer;


@Service
public interface CustomerService {
	Customer findById(long customerId);
	
	Customer findByPhoneNumber(String phonNo);

	Customer saveCustomer(Customer customer);

	

}
