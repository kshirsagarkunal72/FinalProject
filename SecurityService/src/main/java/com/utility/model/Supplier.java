package com.utility.model;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import com.utility.model.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
	
	
	private long serviceproviderid;
	
	private String address;
	
	private int pincode;
	
	private long aadhaar;
	
	private Date dob;
	private int serviceid;	
	private long userid;
 
	
	
}
