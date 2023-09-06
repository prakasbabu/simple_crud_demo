package com.prakash.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.springboot.cruddemo.entity.Employee;
import com.prakash.springboot.cruddemo.repository.EmployeeRedishDao;

@RestController
@RequestMapping("/redish/")
//@CacheConfig(cacheNames = "manchhe")
public class RedisEmployeeController {

	@Autowired
	private EmployeeRedishDao employeeRedishDao;
	
	@PostMapping("/add")
	
	public Employee save(@RequestBody Employee employee) {
		System.out.println(employee);
		return employeeRedishDao.save(employee);
	}
	
	@GetMapping("/find")
	//@Cacheable(key = "#id")
	public List<Employee> findAllEmployees(){
		
		
		return employeeRedishDao.findAllEmployees();
	}
	
	
	
}
