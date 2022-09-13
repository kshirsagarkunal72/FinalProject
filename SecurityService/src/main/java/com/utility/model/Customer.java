package com.utility.model;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.utility.entity.User;
import com.utility.model.*;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	private long customerid;	
	private String address;	
	private int pincode;	
	private long aadhaar;	
	private Date dob;	
	private long userid;	
	private List<Order>	orders;
	
}
