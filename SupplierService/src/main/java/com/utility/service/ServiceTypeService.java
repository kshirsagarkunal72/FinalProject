package com.utility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utility.entity.ServiceType;
import com.utility.repository.ServiceTypeRepository;

@Service
public class ServiceTypeService {
	@Autowired
	private ServiceTypeRepository str;
	
	public ServiceType save(ServiceType st) {
		return  str.save(st);
	}

	public ServiceType getServiceType(ServiceType serviceid) {
		System.out.println(serviceid);
		return str.findById(serviceid.getId()).get();		 
	}
}
