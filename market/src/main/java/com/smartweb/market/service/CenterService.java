package com.smartweb.market.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartweb.market.model.Center;

@Service
public interface CenterService {
	Center createCenter(Center center,MultipartFile[] multiPartFile);

}
