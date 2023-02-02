package com.smartweb.market.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartweb.market.model.Ordertobedeleted;
import com.smartweb.market.model.Product;
import com.smartweb.market.service.OrderService;
import com.smartweb.market.service.ProductService;

@RestController
@CrossOrigin // (origins = "http://localhost:3000")

public class MainController {

	


	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;	
	

	//i well stop this temporary for debugging purposes.
	 //@GetMapping("/error")
	public String replayWithError() { return
	  "Somthing gos wrong! please check your URL path!  Admin -omar ma'eleq-"; }
	 
	
	
	
	
	// ============================= not for use
	// =====================================
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/product-primary/{id}")
	public Product getProductById2(@PathVariable("id") long id) {
		return productService.getProductById(id);
	}

	@PostMapping("/product-primary")
	public Product saveProduct2(@RequestBody Product product) {
		Product savedProduct = productService.saveProduct(product);

		return savedProduct;
	}
	

//	extra features: an alarm of expired products 
//	save method for bulk insertion >> saveAll(List<Product>)
	// insertion by barcode scanner
	
	// get orders
	@GetMapping("/orders/{pageIndex}/{pageSize}") // to be deleted
	public List<Ordertobedeleted> getOrders(@PathVariable("pageIndex") int pageIndex,
			@PathVariable("pageSize") int pageSize) {
		return orderService.findAll(pageIndex, pageSize);
	}

}
