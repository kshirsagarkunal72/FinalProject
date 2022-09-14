package com.utility.aopconfig;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.utility.entity.ServiceType;
import com.utility.entity.Supplier;
import com.utility.model.Customer;
import com.utility.model.Order;
import com.utility.model.User;
import com.utility.service.SupplierService;
import com.utility.valueobjects.ALL;
import com.utility.valueobjects.SDashboard;



@Aspect
@Component
@CrossOrigin
public class AspectConfiguration {
	@Autowired
	private SupplierService supplierService;
	
	@Pointcut ("execution(Object  com.utility.contoller.*.*(..))")
	public void logging() {}
	
	@Around("logging()")	
	public Object supplierAOP(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Before");
		Object[] args=pjp.getArgs();
		System.out.println(pjp.getSignature());
		String token=(String)args[0];		
		User user=supplierService.getUser(token);
		System.out.println(user);
		int i=0;
		for(Object arg:args) {
			if(arg instanceof User)
				args[i]=user;
			i++;
		}
		Object object=pjp.proceed(args);
		List list=null;
		Customer cust=null;
		Supplier supp=null;
		ServiceType service=null;
		Order order=null;
		List<Supplier> supplierslist=null;
		List <Customer> customerslist=null;
		List<ServiceType> servicelist=null;
		List<Order> orderlist=null;
		ALL all =new ALL();
		
		if(object instanceof List) {
			list=(List)object;
			if(list.get(0) instanceof Supplier) {
				all.setSupplierslist(list);
			}
			if(list.get(0) instanceof Customer) {
				all.setCustomerslist(list);
			}
			if(list.get(0) instanceof Order) {
				all.setOrderlist(list);
			}
			if(list.get(0) instanceof ServiceType) {
				all.setServicelist(list);
			}
		}
		if(object instanceof Supplier)
			all.setSupplier((Supplier)object);
		if(object instanceof Customer)
			all.setCustomer((Customer)object);
		if(object instanceof Order)
			all.setOrder((Order)object);
		if(object instanceof ServiceType)
			all.setService((ServiceType)object);
		if(object instanceof SDashboard)
			all.setSDashboard((SDashboard)object);
		all.setUser(user);		
		System.out.println("After");
		if(user !=null)
			return (Object)all;
		else 
			return new Object();
	}
	
	
	
}
