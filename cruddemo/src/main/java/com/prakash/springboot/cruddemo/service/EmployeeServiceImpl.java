package com.prakash.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prakash.springboot.cruddemo.entity.Employee;
import com.prakash.springboot.cruddemo.repository.EmployeeRepo;


@Service
public class EmployeeServiceImpl implements EmployeeService {

private EmployeeRepo employeeRepo;
	
	
@Autowired
	public EmployeeServiceImpl(EmployeeRepo theemployeeRepo) {
		
		this.employeeRepo = theemployeeRepo;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
				
		return employeeRepo.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		
		return employeeRepo.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeRepo.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		employeeRepo.deleteById(id);

	}



}
