package com.utility.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utility.config.CustomUserDetailsService;
import com.utility.config.JwtUtil;
import com.utility.entity.User;
import com.utility.entity.VerificationToken;
import com.utility.model.CSignUp;
import com.utility.model.Customer;
import com.utility.model.JwtRequest;
import com.utility.model.JwtResponse;
import com.utility.model.UserOtp;
import com.utility.service.EmailService;
import com.utility.service.UserService;
import com.utility.service.VerificationTokenService;

@RestController
@RequestMapping("/api/secure")
@CrossOrigin
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private EmailService email;
	@Autowired
	private VerificationTokenService vts;
	@PostMapping("/token")
	public ResponseEntity generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		}
		catch (UsernameNotFoundException  | BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}
		
	UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
	String token=	this.jwtUtil.generateToken(userDetails);
	User u=userService.findByUsername(userDetails.getUsername());
	String role=u.getRole();
	HttpHeaders htt=new HttpHeaders();
	htt.set("Authorization" , token);
	JwtResponse res=new JwtResponse(token,role);
	return  ResponseEntity.ok().headers(htt).body(res);
	
	}
	@GetMapping("/getuser")
	public User  getUser(@RequestHeader(value = "Authorization") String auth) {
	String	Username=jwtUtil.getUsernameFromToken(auth.substring(7));
		return userService.findByUsername(Username);
	}
	
	@PostMapping("/signupcustomer")
	public UserOtp signupc(@RequestBody CSignUp cust) {
		Optional<User>  u=userService.saveUserForCustomer(cust);
		Customer c=userService.getCustomerFromSigup(cust);
		if(u.isPresent())
		{c.setUserid((u.get()).getId());
		}
		else {
			throw new RuntimeException();
		}
		boolean res=userService.saveCustomer(c);
		if(res) {
				int otp =this.email.sendMail(cust.getUsername());
				VerificationToken vt=new VerificationToken(0l,otp,u.get());
				Optional<VerificationToken> v=	vts.saveToken(vt);
				if(v.isPresent()) {
					//return u.get().getId();
					UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(u.get().getUsername());
					String token=	this.jwtUtil.generateToken(userDetails);
					return new UserOtp(1,token,"");
				}
					
		}
		else {
			throw new RuntimeException();
		}
		return new UserOtp(0,"","");
	}
	@PostMapping("/verifyotp")
	public String verifyOtp(@RequestBody UserOtp uo) {
		Optional<User> u= userService.getUserFromToken(uo.getOtp());
		String token=uo.getOtp();
		if(u.isPresent()) {
			Long otp= uo.getUserid();
			uo.setOtp(otp.toString());
			uo.setUserid(u.get().getId());
		if(userService.verifyOtp(uo).get() == true)
			return token;
		}
		
			return "";
	}
	@PostMapping ("/savepassword")
	public void saveOrChange(@RequestBody UserOtp uo ) {
		Optional<User> u= userService.getUserFromToken(uo.getToken());
		if(u.isPresent()) {
			u.get().setPassword(uo.getOtp());
			userService.savePassword();
		}
	}
	
}
