package com.utility.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CSignUp {
	private String name;
	private String address;	
	private int pincode;
	private Date dob;
	private long aadhaar;
	private String mobile;
	private String username;
	private String password;
			
}