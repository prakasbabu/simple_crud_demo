package com.prakash.springboot.cruddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.springboot.cruddemo.entity.Employee;
import com.prakash.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	
	@Autowired
	public EmployeeController(EmployeeService theemployeeService) {
		employeeService= theemployeeService;
	}

	@GetMapping("/employees/lists")
	public List<Employee> findAll(){
		
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId ) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		
		if (theEmployee==null) {
			
			throw new RuntimeException("Employee Not found");
		}
		return theEmployee;
	}
	
	
		@PostMapping("/employees/create")
		public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
		
	}
		
	@PutMapping("/employees/edit")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		
		return theEmployee;
		
	}
	
	@DeleteMapping("/employees/delete/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		if (tempEmployee == null) {
			
			throw new RuntimeException("Employee Not found");
		}
		
		employeeService.deleteById(employeeId);
			return "Deleted employee with id -"+ employeeId;
	}
	
	
	
	
	
}
