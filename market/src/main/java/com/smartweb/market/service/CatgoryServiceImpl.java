package com.smartweb.market.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartweb.market.model.Catgory;
import com.smartweb.market.repository.CatgoryRepository;

@Service
public class CatgoryServiceImpl implements CatgoryService {

	@Autowired
	private CatgoryRepository catgoryRepository ;
	
	@Override
	public List<Catgory> getallCatgories() {
	
		return catgoryRepository.findAll();
	}

	

	

}
