package com.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emp.model.Employee;
import com.emp.model.EmployeeHibernate;
import com.emp.service.EmpServiceInterface;
import com.emp.service.EmployeeService;


@Controller
public class EmpRegistrationController {
	
	@Autowired
	EmpServiceInterface service;
	
	@RequestMapping("register")
	public ModelAndView register(@RequestParam("id") Integer id, 
			@RequestParam("name")  String name, 
			@RequestParam("salary") Long salary,
			@RequestParam("pwd") String password ) {
		
		EmployeeHibernate employee = service.registerEmployee(id, name, salary, password);
		
		ModelAndView mv = new ModelAndView();
		
		if(employee == null) {
			mv.addObject("failureMsg", "Salary is Invalid");
			mv.setViewName("failure.jsp");
		} else {
			mv.addObject("employee", employee);
			mv.setViewName("result.jsp");
		}

		return mv;
	}

}
