package com.emp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.emp.model.Employee;

@Component
public class EmployeeDAO {
	public int save(Integer id, String name, Long salary, String password) {
		String sql = "insert into employee values (?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = DatabaseUtils.getPreparedStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setLong(3, salary);
			pstmt.setString(4, password);
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("There is a problem getting a connection or statement or resultset object");
			System.out.println(e);
		} finally {
			try {
				DatabaseUtils.closeResources(null, pstmt, null);
			} catch (SQLException e) {
				System.out.println("There is a problem closing resources");
			} 
		}
		
		return count;
	}
	
	
	public Employee getEmployeeById(Integer id) {
		String sql = "select * from employee where id = " + id;
		Statement stmt = null;
		ResultSet rs = null;
		Employee emp = null;
		
		try {
			stmt = DatabaseUtils.getStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSalary(rs.getLong("salary"));
				emp.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("There is a problem getting a connection or statement or resultset object");
			System.out.println(e);
		} finally {
			try {
				DatabaseUtils.closeResources(stmt, null, rs);
			} catch (SQLException e) {
				System.out.println("There is a problem closing resources");
			} 
		}
		
		return emp;
	}
}
