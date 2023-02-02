package com.smartweb.market.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartweb.market.entity.Customer;
import com.smartweb.market.model.Product;
@Service
public interface ProductService {

	Product saveProduct(Product product);
	List<Product> getAllProducts();
	Product getProductById(Long id);
	Product updateProduct(long id, Product product);
	void deleteProduct(long id);
	List<Product> findByCatgoryId(int id,int pageIndex, int pageSize );
	
	//pageable
	List<Product> findByNameContaining(String str,int pageIndex, int pageSize );
	

	
	Product saveProductWithMedia(Product product,MultipartFile[] file);
}
