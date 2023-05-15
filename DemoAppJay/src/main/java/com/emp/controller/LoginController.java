package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emp.dao.EmpDAOHibernate;
import com.emp.dao.EmployeeDAO;
import com.emp.model.Employee;
import com.emp.model.EmployeeHibernate;



@Controller
//@RequestMapping("/SpringMVCApplication/login")
public class LoginController {
	
	@Autowired
	EmpDAOHibernate dao;
	
	@RequestMapping("/login")
	//@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(@RequestParam("empId") Integer empId, 
			@RequestParam("pwd")  String pwd) {
		
		ModelAndView mv = new ModelAndView();
		
 		EmployeeHibernate employee = dao.getEmployeeById(empId);
 		List<EmployeeHibernate> empList = dao.getAll();
		
		if(employee != null) {
			System.out.println("Found an employee with the given ID");
			
			if(employee.getPassword().equals(pwd)) {
				System.out.println("Login Successful");
			
				mv.addObject("employee", employee);
				
				mv.setViewName("success.jsp");
			} else {
				mv.addObject("failureMsg", "Username/Password Mismatch. Login failed. Please try again");
				mv.setViewName("failure.jsp");
			}
		} else {
			System.out.println("Employee with the given ID not found");
			mv.addObject("failureMsg", "No Employee found with given Employee ID. Please try again");
			mv.setViewName("failure.jsp");
		}
		
		return mv;
	}
	
}
