package com.smartweb.market.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartweb.market.entity.Customer;
import com.smartweb.market.model.Catgory;
import com.smartweb.market.model.OrderDTO;
import com.smartweb.market.model.UserOrderDTO;
import com.smartweb.market.service.CatgoryService;
import com.smartweb.market.service.CustomerService;
import com.smartweb.market.service.OrderService;
import com.smartweb.market.service.ProductService;

@RestController
@CrossOrigin // (origins = "http://localhost:3000")
public class CustomerController {

	

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/customer/{id}")
	@PreAuthorize("hasRole('User')")
	public Customer getCustomerById(@PathVariable("id") long id) {
		return customerService.findById(id);
	}
	
	// save order
	@PostMapping("/userorder")
	public ResponseEntity<UserOrderDTO> acceptUserOrder(@RequestBody UserOrderDTO order) {
		return new ResponseEntity<UserOrderDTO>(orderService.saveUserOrder(order), HttpStatus.CREATED);
	}

	// get customer orders
	@GetMapping("/customerorders/{customerid}/{pageIndex}/{pageSize}")
	@PreAuthorize("hasRole('User')")
	public List<OrderDTO> getCustomerOrders(@PathVariable("customerid") long customerId,
			@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		return orderService.getCustomerOrders(customerId, pageIndex, pageSize);
	}

	// get customer old orders
	@GetMapping("/customeroldorders/{customerid}/{pageIndex}/{pageSize}")
	@PreAuthorize("hasRole('User')")
	public List<OrderDTO> getCustomerOldOrders(@PathVariable("customerid") long customerId,
			@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		return orderService.getCustomerOldOrders(customerId, pageIndex, pageSize);
	}

	// get customer by phone
	@GetMapping("/customerbyphone/{phonno}")
	public ResponseEntity<Customer> getCustomerByPhone(@PathVariable("phonno") String phoNo) {
		return new ResponseEntity<Customer>(customerService.findByPhoneNumber(phoNo), HttpStatus.OK);
	}

	// save customer
	@PostMapping("/customer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED);
	}
}
