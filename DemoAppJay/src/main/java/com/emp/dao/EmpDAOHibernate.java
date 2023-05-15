package com.emp.dao;

import java.lang.module.Configuration;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.emp.model.EmployeeHibernate;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Repository
public class EmpDAOHibernate {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	EmployeeHibernate emp;
	
	
	@Transactional
	public int save(Integer id, String name, Long salary, String password) {
		emp.setId(id);
		emp.setName(name);
		emp.setPassword(password);
		emp.setSalary(salary);
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		session.save(emp);
		
		return 1;
		
		
	}
	
	public EmployeeHibernate getEmployeeById(Integer id) {
		Session session;
		EmployeeHibernate emp = null;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		emp=(EmployeeHibernate) session.load(EmployeeHibernate.class, id);
		return emp;
	}

	public List<EmployeeHibernate> getAll(){
		List<EmployeeHibernate> empAll=null;
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		Query qr= session.createQuery("select id, name, salary, password from EmployeeHibernate");
		empAll=qr.list();
		
		return empAll;
		
	}

}
