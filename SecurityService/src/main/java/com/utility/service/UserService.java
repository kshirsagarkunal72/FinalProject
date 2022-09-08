package com.utility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.utility.config.JWTConfiguration;
import com.utility.entity.User;
import com.utility.model.CSignUp;
import com.utility.model.Customer;
import com.utility.repository.UserRepository;


import io.github.resilience4j.retry.annotation.Retry;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTConfiguration jwt;
	@Autowired
	private RestTemplate restTemplate;
	private static final String SECURITY_SERVICE= "SecurityService";
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	public long saveUserForCustomer(CSignUp cust) {
		User u=new User();
		u.setName(cust.getName());
		u.setMobile(cust.getMobile());
		u.setUsername(cust.getUsername());
		u.setPassword(jwt.passwordEncoder().encode(cust.getPassword()));
		u.setRole("CUSTOMER");		
		u.setEnabled(false);
		u.setAccountNonExpired(false);
		u.setAccountNonLocked(false);
		u.setCredentialsNonExpired(false);
		
	Optional<User> o=Optional.of(userRepository.save(u));
	if(o.isPresent()) {
		return ((User)o.get()).getId();
	}
	return 0l;
	}
	public Customer getCustomerFromSigup(CSignUp cust) {
		Customer c=new Customer();
		c.setAddress(cust.getAddress());
		c.setPincode(cust.getPincode());
		c.setAadhaar(cust.getAadhaar());
		c.setDob(cust.getDob());
		c.setUserid(0);
		return c;
	}
	
	@Retry(name = SECURITY_SERVICE,fallbackMethod ="getcFallback" )
	public boolean saveCustomer(Customer  c) {
		HttpHeaders http=new HttpHeaders();
		http.setContentType(MediaType.APPLICATION_JSON);
		String json=null;
		try {
			json=new ObjectMapper().writeValueAsString(c);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		HttpEntity<String> entity=new HttpEntity<String>(json,http); 
		ResponseEntity<HttpEntity> o=restTemplate.exchange("http://CUSTOMER-SERVICE/api/customer/savecustomer", HttpMethod.POST, entity, HttpEntity.class);
		if(o.getStatusCodeValue()==200)
			return true;
		else 
			return false;
	}
	public boolean getcFallback(Exception e) {		
		return false;
	}
}
