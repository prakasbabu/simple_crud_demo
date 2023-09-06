package com.prakash.springboot.cruddemo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.prakash.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeRedishDao {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate template;
	
	
	public Employee save(Employee employee) {
		
		template.opsForHash().put("kamdar", employee.getId(), employee);
		
		return employee;
	}
	
	
	public List <Employee> findAllEmployees(){
		
		return template.opsForHash().values("kamdar");
	}
	
	public Employee findEmployeeById(int id) {
		
		return (Employee) template.opsForHash().get("kamdar", id);
	}
	
	public String deleteEmployee (int id) {
		
		 template.opsForHash().delete("kamdar", id);
		 return "employee deleted";
	}
	
}
