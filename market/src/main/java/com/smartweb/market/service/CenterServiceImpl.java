package com.smartweb.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartweb.market.model.Center;
import com.smartweb.market.repository.CenterRepository;

@Service
public class CenterServiceImpl implements CenterService  {// i 'am not going to create interface rite now
	
	@Autowired
	private CenterRepository centerRepository;
	
	public  Center createCenter(Center center,MultipartFile[] multiPartFile) {
		
		try {
			if(multiPartFile.length > 0) {
				center.setLogoAsBytes(multiPartFile[0].getBytes());
			}
			
//			System.out.println("=============================   saving center =====================================");
			return centerRepository.save(center);
		} catch (Exception e) {
			System.out.println("Erorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr in saveCenter");
			return null;
		}

	}


}
