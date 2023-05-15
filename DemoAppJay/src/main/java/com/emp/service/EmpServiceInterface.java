package com.emp.service;

import com.emp.model.EmployeeHibernate;

public interface EmpServiceInterface {

	public EmployeeHibernate registerEmployee(Integer id, String name, Long salary, String password);
}
