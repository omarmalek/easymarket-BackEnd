package com.smartweb.market.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartweb.market.model.Product;
@Repository 
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByCatgoryId(int id);
	
	//JPQL
//	@Query("SELECT s FROM Product WHERE s.name like ?1")
//	Page<Product> getProdutByName(String str,Pageable pageRequest);
	Page<Product> findByCatgoryId(int id,Pageable page);
	Page<Product> findByNameContaining(String str,Pageable pageRequest);
	
}
