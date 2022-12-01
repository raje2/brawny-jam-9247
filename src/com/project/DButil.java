package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	
public static Connection provideConnection() {
		
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/powerproject";
		
		try {
			
			connection = DriverManager.getConnection(url,"root","Badalstb1234");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return connection;
	}

}
