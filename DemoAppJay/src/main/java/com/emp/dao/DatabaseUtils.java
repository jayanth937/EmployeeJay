package com.emp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseUtils {
	
	static final String DB_URL = "jdbc:mysql://localhost/FULLSTACK?";
	static final String USER = "root";
	static final String PASS = "J@yanth1";
	
	static Connection con = null;
	
	public static Connection getJdbcConnection() throws SQLException {
		if(con == null) {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		return con;
	}
	
	public static Statement getStatement() throws SQLException {
		if(con == null) {
			getJdbcConnection();
		}
		return con.createStatement();
	}
	
	public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
		if(con == null) {
			getJdbcConnection();
		}
		return con.prepareStatement(sql);
	}
	
	public static void closeResources(Statement stmt, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if(con != null) {
			con.close();
			con = null;
		}

		if(stmt != null) {
			stmt.close();
		}
		
		if(pstmt != null) {
			pstmt.close();
		}
		
		if(rs != null) {
			rs.close();
		}
	}

}
