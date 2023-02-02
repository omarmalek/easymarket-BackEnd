package com.smartweb.market.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smartweb.market.model.Catgory;
import com.smartweb.market.model.Product;
import com.smartweb.market.service.CatgoryService;
import com.smartweb.market.service.ProductService;

@RestController
@CrossOrigin // (origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CatgoryService catgoryService;

	@GetMapping("/products/bycatgory/{catId}/{pageIndex}/{pageSize}")
	public List<Product> getProductsByCatgoryId(@PathVariable("catId") int catId,
			@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		return productService.findByCatgoryId(catId, pageIndex, pageSize);
	}

	@GetMapping("/product/{id}")//is this used??????
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
	}

	@GetMapping("/products/byname/{str}/{pageIndex}/{pageSize}")
	public List<Product> findProductsByName(@PathVariable("str") String str, @PathVariable("pageIndex") int pageIndex,
			@PathVariable("pageSize") int pageSize) {
		return productService.findByNameContaining(str, pageIndex, pageSize);
	}

	// update product
	@PutMapping("/product/{id}")//is this used??????
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		return new ResponseEntity<Product>(productService.updateProduct(id, product), HttpStatus.OK);
	}

	// Delete product
	@DeleteMapping("/product/{id}")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<String>("product deleted successfully", HttpStatus.OK);
	}

	// save product //is this used??????
	@PostMapping("/product")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
	}

	// save product with image
	@PostMapping(value = { "/productmedia" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@PreAuthorize("hasRole('Admin')")
	public Product saveProductMedia(@RequestPart("product") Product product,
			@RequestPart(name = "imgesFiles", required = false) MultipartFile[] file) {
		System.out.println("*******************************saveProductMedia***********************************************");
		if (file != null) {
			return productService.saveProductWithMedia(product, file);
		}
		return productService.saveProduct(product);
	}
	// for the menu component
	@GetMapping("/catgories")
	public List<Catgory> getAllCatgories() {
		return catgoryService.getallCatgories();
	}

}
