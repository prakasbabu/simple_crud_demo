package com.prakash.springboot.cruddemo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.springboot.cruddemo.entity.Employee;
import com.prakash.springboot.cruddemo.entity.LoginRequest;
import com.prakash.springboot.cruddemo.utils.JwtTokenUtil;

@RestController
public class LoginController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	 @PostMapping(value = "/user/action/login/customer-login")
	 public ResponseEntity doCustomerLogin(@RequestBody LoginRequest loginRequest) {
		 
		Employee employee = new Employee();
		employee.setPassword(loginRequest.getPassword());
		employee.setEmail(loginRequest.getEmailId());
	     
	    org.springframework.security.core.userdetails.User userDetails
         = new org.springframework.security.core.userdetails.User
         (loginRequest.getEmailId(), loginRequest.getPassword(), new ArrayList<>());
	    
	   // UserDetails userDetails  = new User(loginRequest.getEmailId(), loginRequest.getPassword(), new ArrayList<>());
	        
		
	    employee.setAccessToken(jwtTokenUtil.generateToken(userDetails, "END_USER"));
        return new ResponseEntity(employee, HttpStatus.OK);
	 }
	 
	@PostMapping(value ="/user/action/login/admin-login")
	public ResponseEntity doAdminLogin(@RequestBody LoginRequest loginRequest) {
		
		Employee employee = new Employee();
		employee.setPassword(loginRequest.getPassword());
		employee.setEmail(loginRequest.getEmailId());
		
		org.springframework.security.core.userdetails.User userDetails
        = new org.springframework.security.core.userdetails.User
        (loginRequest.getEmailId(), loginRequest.getPassword(), new ArrayList<>());
		
		 employee.setAccessToken(jwtTokenUtil.generateToken(userDetails, "ADMIN_USER"));
	        return new ResponseEntity(employee, HttpStatus.OK);
		
		
	}

}
