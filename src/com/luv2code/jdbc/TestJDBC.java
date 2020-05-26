package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
	
	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try {
			
			Connection conn = DriverManager.getConnection(jdbcURL, user, pass);
			
			System.out.println("Connection successful");
			
			
			conn.close();
			
		} catch (SQLException sqlExc) {
			System.out.println("Exceptions is thrown " + sqlExc.getMessage());
		}
		
		
	}

}
