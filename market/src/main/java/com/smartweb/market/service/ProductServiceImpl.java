package com.smartweb.market.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartweb.market.entity.Customer;
import com.smartweb.market.exception.ResourceNotFoundException;
import com.smartweb.market.model.ImageModel;
import com.smartweb.market.model.Product;
import com.smartweb.market.repository.CustomerRepository;
import com.smartweb.market.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}
	@Override
	public List<Product> getAllProducts() {
		 List<Product> products = productRepository.findAll();
		 products.forEach((product) -> System.out.print(product.getName()));	 
		 return products;
	}
	@Override
	public Product getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}else {
			throw new ResourceNotFoundException("Product","Id",id);
		}	
	}
	@Override
	public Product updateProduct(long id, Product product) {
		Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","Id",id));
		existingProduct.setCatgoryId(product.getCatgoryId());
		existingProduct.setName(product.getName());
		existingProduct.setBackagePrice(product.getBackagePrice());
		existingProduct.setUnitPrice(product.getUnitPrice());
		existingProduct.setDescountPrice(product.getDescountPrice());
		existingProduct.setPackType(product.getPackType());
		existingProduct.setNumberOfPieces(product.getNumberOfPieces());
		existingProduct.setImg(product.getImg());
		existingProduct.setAvilable(product.isAvilable());
		existingProduct.setWeight(product.getWeight());
		existingProduct.setCompany(product.getCompany());
		existingProduct.setCountry(product.getCountry());
		existingProduct.setDescreption(product.getDescreption());
		existingProduct.setBarcode(product.getBarcode());
		existingProduct.setStoreLocation(product.getStoreLocation());
		existingProduct.setSupplayerId(product.getSupplayerId());
		existingProduct.setDateOfProduct(product.getDateOfProduct());
		existingProduct.setDateOfExpery(product.getDateOfExpery());
		existingProduct.setPeriodOfValidity(product.getPeriodOfValidity());
		
		productRepository.save(existingProduct);
		return existingProduct;
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","Id",id));
		productRepository.deleteById(id);
		
		//another way ...
//		if(!productRepository.existsById(id)) {
//			throw new ProductNotFoundException();
//		}
//		productRepository.deleteById(id);
//		return "Product with id "+ id + "has been deleted successfuly";
	}

	@Override
	public List<Product> findByCatgoryId(int id,int pageIndex, int pageSize ) {
		PageRequest page = PageRequest.of(pageIndex, pageSize);
		return productRepository.findByCatgoryId(id,page).getContent();
	}
	
	@Override
	public List<Product> findByNameContaining(String str,int pageIndex, int pageSize ) {
		PageRequest page = PageRequest.of(pageIndex, pageSize);
		return productRepository.findByNameContaining(str,page).getContent();
	}

	@Override
	public Product saveProductWithMedia(Product product,MultipartFile[] multiPartFile) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$---saveProductWithMedia---$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(product);
		System.out.println(multiPartFile);
		
		try {
			if(multiPartFile.length > 0) {
				Set<ImageModel>	images = fileToImages(multiPartFile);
				product.setProductImages(images);
			}
			
			System.out.println("=============================   saving product =====================================");
			return productRepository.save(product);
		} catch (Exception e) {
			System.out.println("Erorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr in saveProductMedia");
			return null;
		}

	}
	
	public Set<ImageModel> fileToImages (MultipartFile[] multiPartfile) throws IOException{
		Set<ImageModel> myset = new HashSet<>();
		for(MultipartFile file : multiPartfile) {
			ImageModel imageModel = new ImageModel(file.getOriginalFilename(),
												   file.getContentType(),
												   file.getBytes());
			myset.add(imageModel);
		}
		return myset;
	}
	

	
	//REMEMBER
//	productRepository.count();
//	productRepository.exists(null);
	//batch saving
//	productRepository.deleteAll(List.of(product1,product2));
	
}
