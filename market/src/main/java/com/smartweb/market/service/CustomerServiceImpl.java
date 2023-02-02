package com.smartweb.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartweb.market.entity.Customer;
import com.smartweb.market.repository.CustomerRepository;
@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerRepository customerRepository;


//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Customer findById(long customerId) {
		if(customerRepository.existsById(customerId)) {
			return customerRepository.findById(customerId).get();
		}else
			return new Customer(customerId,"notFound");//this is temporary clue for front End in case of there is no
		//user with this id. 
		
	}
	@Override
	public Customer findByPhoneNumber(String phonNo) {
		
		Customer customer = customerRepository.findFirstByPhoneNumber(phonNo);
		//if new customer return new customer
		if(customer == null) {
			Customer newCustomer = new Customer();
			newCustomer.setPhoneNumber(phonNo);
			newCustomer = customerRepository.save(newCustomer);
			return newCustomer;
		}

		return customer;
	}
	@Override
	public Customer saveCustomer(Customer customer) {
		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
		
	}
	
	
	
}
