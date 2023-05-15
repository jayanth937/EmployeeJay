package com.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.emp.dao.EmpDAOHibernate;
import com.emp.dao.EmployeeDAO;
import com.emp.model.Employee;
import com.emp.model.EmployeeHibernate;


public class EmployeeService2 implements EmpServiceInterface{
	
	@Autowired
	EmpDAOHibernate dao;
    
	
	public boolean isNameValid(String name) {
		return name != null && !name.isEmpty() && name.length() > 3;
	}
	
	public boolean isSalaryValid(Long salary) {
		return salary != null && salary > 0;
	}
	
	public EmployeeHibernate registerEmployee(Integer id, String name, Long salary, String password) {
		EmployeeHibernate emp = null;
		System.out.println("Prod");
		
		if(isSalaryValid(salary)) {
			int count = dao.save(id, name, salary, password);
			
			if(count > 0) {
				emp = dao.getEmployeeById(id);
			}
		}
		
		
		return emp;
	}
	

}
