package com.smartweb.market.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smartweb.market.model.Center;
import com.smartweb.market.model.OrderDTO;
import com.smartweb.market.model.Product;
import com.smartweb.market.service.CenterService;
import com.smartweb.market.service.CenterServiceImpl;
import com.smartweb.market.service.OrderService;

@RestController
@CrossOrigin//(origins = "*") // (origins = "http://localhost:3000")
@RequestMapping("/admin") 

public class AdminController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	CenterService centerService;

	// get control orders
	@GetMapping("/controlorders/{pageIndex}/{pageSize}")
	@PreAuthorize("hasRole('Admin')")
	public List<OrderDTO> getControlOrders(@PathVariable("pageIndex") int pageIndex,
			@PathVariable("pageSize") int pageSize) {
		
		return orderService.getControlOrders(pageIndex, pageSize);
	}

	// get control old orders
	@GetMapping("/controloldorders/{pageIndex}/{pageSize}")
	@PreAuthorize("hasRole('Admin')")
	public List<OrderDTO> getControlOldOrders(@PathVariable("pageIndex") int pageIndex,
			@PathVariable("pageSize") int pageSize) {
	
		return orderService.getOldOrders(pageIndex, pageSize);
	}

	// get setter orders
	@GetMapping("/setterorders/{pageIndex}/{pageSize}")
	@PreAuthorize("hasAnyRole('Admin','Setter')")
	public List<OrderDTO> getSetterorders(@PathVariable("pageIndex") int pageIndex,
			@PathVariable("pageSize") int pageSize) {

		return orderService.getSetterOrders(pageIndex, pageSize);
	}
	// get setter orders
		@GetMapping("/deliveryorders/{pageIndex}/{pageSize}")
		@PreAuthorize("hasAnyRole('Admin','Delivery')")
		public List<OrderDTO> getDeliveryorders(@PathVariable("pageIndex") int pageIndex,
				@PathVariable("pageSize") int pageSize) {

			return orderService.getDeliveryOrders(pageIndex, pageSize);
		}

	// update order
	@PutMapping("/order")
	@PreAuthorize("hasAnyRole('Admin','Setter','Delivery')")
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO order) {
		return new ResponseEntity<OrderDTO>(orderService.updateOrder(order), HttpStatus.ACCEPTED);
	}
	
	// create new center by rootAddmin
		@PostMapping(value = { "/centers-managment/create" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	//	@PreAuthorize("hasRole('Admin')")//TODO: change to > hasRole('rootAdmin')
		public Center createCenter(@RequestPart("center") Center center,
				@RequestPart(name = "imgeFile", required = false) MultipartFile[] file) {
			System.out.println("---------------create new center--------------------");
			if (file != null) {
				return centerService.createCenter(center, file);
			}
			return null;
		}
}




