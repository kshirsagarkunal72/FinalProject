package com.utility.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.utility.config.JWTConfiguration;
import com.utility.config.JwtUtil;
import com.utility.entity.User;
import com.utility.entity.VerificationToken;
import com.utility.model.CSignUp;
import com.utility.model.Customer;
import com.utility.model.UserOtp;
import com.utility.repository.UserRepository;
import com.utility.repository.VerificationTokenRepository;

import io.github.resilience4j.retry.annotation.Retry;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTConfiguration jwt;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private VerificationTokenRepository vtr;
	@Autowired
	private UserService userService;
	private static final String SECURITY_SERVICE= "SecurityService";
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	
	
	
	
	
	public Optional<User> getUserFromToken(String token){
		String mail=jwtUtil.getUsernameFromToken(token);
		return Optional.ofNullable(userService.findByUsername(mail));
	}
	
	
	public Optional<Boolean> verifyOtp(UserOtp uo){
	Optional<List<VerificationToken>> o =	Optional.ofNullable(vtr.findAll().stream().filter(
				v->v.getUser().getId()==uo.getUserid())
				.collect(Collectors.toList()));	
	List<VerificationToken> l=(List)o.get();	
	if(!l.isEmpty()) {
		
		VerificationToken vt=l.get(0);
		if(vt.getOtp()==Integer.valueOf(uo.getOtp()))
			return Optional.ofNullable(true);
	}	
		return Optional.ofNullable(false);	
	}
	
	
	public Optional<User> saveUserForCustomer(CSignUp cust) {
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
		
	Optional<User> o=Optional.ofNullable(userRepository.save(u));	
	return o;
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
