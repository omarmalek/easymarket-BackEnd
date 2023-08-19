package com.smartweb.market.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.smartweb.market.model.Catgory;
@Service
public interface CatgoryService {
	List<Catgory> getallCatgories();
	
	
}
