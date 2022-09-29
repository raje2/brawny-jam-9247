package com.project.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.project.DButil;
import com.project.beanclass.Engineer;

public class EngineerDeoImpl implements EngineerDeo{

	@Override
	public String registerStudent(String name, String email, String password, String category) {
		
		
          String message = "Not Inserted..";
	
		
	
		
		try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into student(name,email,password,category) values(?,?,?,?)");
			
			
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, category);
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Engineer Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
	
	
		
		return message;
		
		
		
	}

	@Override
	public String registerStudent2(Engineer engineer) {
		
		
          String message = "Not Inserted..";
	
		
	
		
		try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into student(name,email,password,category) values(?,?,?,?)");
			
			
			
			ps.setString(1, engineer.getName());
			ps.setString(2, engineer.getEmail());
			ps.setString(3, engineer.getPassword());
			ps.setString(4, engineer.getCategory());
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Student Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
	
	
	}
	
	
	

}
