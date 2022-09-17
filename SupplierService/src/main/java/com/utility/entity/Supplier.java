package com.utility.entity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utility.model.*;


@Entity
@Table(name="suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long supplierid;
	@Column(length = 255,nullable = false)
	private String address;
	@Column(length = 6,nullable = false)
	private int pincode;
	@Column(length = 12,nullable = false)
	private long aadhaar;
	@Column
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dob;
	@Column(length = 4,nullable = false)
	private int charge;
	
	@OneToOne
	private ServiceType serviceType;
	@Column(unique = true,nullable = false)
	private long userid;
 
	
	
}
