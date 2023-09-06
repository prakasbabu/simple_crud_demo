package com.prakash.springboot.cruddemo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prakash.springboot.cruddemo.entity.Employee;


@Repository
public class EmployeeRepoImpl implements EmployeeRepo {
	
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeRepoImpl(EntityManager theentityManager) {
		this.entityManager= theentityManager;
	}

	@Override

	public List<Employee> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		List<Employee> employees = query.getResultList();	
		
		return employees;
	}

	@Override
	
	public Employee findById(int id) {
		Session currentSession=entityManager.unwrap(Session.class);
		Employee employee = currentSession.get(Employee.class, id);
		return  employee;
	}

	@Override

	public void save(Employee employee) {
		Session currentSession=entityManager.unwrap(Session.class);
		 currentSession.saveOrUpdate(employee);
	}

	@Override
		public void deleteById(int theId) {
		Session currentSession=entityManager.unwrap(Session.class);
		
		Query<Employee> query = currentSession.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", theId);
		query.executeUpdate();
	}

}
