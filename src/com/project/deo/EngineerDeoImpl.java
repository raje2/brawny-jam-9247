package com.project.deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.project.DButil;
import com.project.beanclass.Employee;
import com.project.beanclass.EmployeePro;
import com.project.beanclass.Engineer;
import com.project.beanclass.EngineerPRO;
import com.project.beanclass.Problem;
import com.project.exceptions.EmployeeException;
import com.project.exceptions.EngineerException;
import com.project.exceptions.HodException;
import com.project.exceptions.ProblemException;

public class EngineerDeoImpl implements EngineerDeo{



	@Override
	public String registerEngineer1(String name, String email, String password, String category) {
		

        String message = "Not Inserted..";
	
		
	
		
		try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into Engineer values(?,?,?,?)");
			
			
			
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
	public String registerEngineer2(Engineer engineer) {
		
         String message = "Not Inserted..";
	
		
	
		
		try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into Engineer(name,email,password,category) values(?,?,?,?)");
			
			
			
			ps.setString(1, engineer.getName());
			ps.setString(2, engineer.getEmail());
			ps.setString(3, engineer.getPassword());
			ps.setString(4, engineer.getCategory());
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Engineer Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		return message;
	
		
		
	}

	@Override
	public List<Engineer> getEngineerlist() throws EngineerException {
	
          List<Engineer> engineers= new ArrayList<>();
		
		
		try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from Engineer");
			
			
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				
				String r= rs.getString("name");
				String e= rs.getString("email");
				String p= rs.getString("password");
				String c= rs.getString("category");
				
			Engineer engineer=new Engineer(r, e, p, c);	
				
			engineers.add(engineer);
			
		}
			
			
		} catch (SQLException e) {
			throw new EngineerException(e.getMessage());
		}
		
		
		if(engineers.size() == 0)
			throw new EngineerException("No Engineer found..");
		
		
		
		
		return engineers;
	}

	@Override
	public String deleteEngineer(String name) throws EngineerException {
		 
		String message = "Engineer assigned with problem so you can't delete !";
		
		
		try(Connection conn = DButil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("delete from Engineer where name = ?");
			
			ps.setString(1, name);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				message = (name+" engineer data is deleted!");
			}
			
		} catch (Exception e) {
			
			
			
		}
		
		
		return message;
	}

	@Override
	public String assignProblemWithEngineer(int pid, String name) throws EngineerException, ProblemException {
		String message = "not assign!";
		
		try(Connection conn= DButil.provideConnection()) {
			
		 	PreparedStatement ps= conn.prepareStatement("select * from Engineer where name =?");
			
		 	
		 	ps.setString(1, name);
		 	
		 	ResultSet rs= ps.executeQuery();
			
		 	if(rs.next()) {
		 		
		 		PreparedStatement ps2= conn.prepareStatement("select * from problem where pid=?");
		 		
		 		ps2.setInt(1, pid);

		 		ResultSet rs2= ps2.executeQuery();
		 		
		 		if(rs2.next()) {
		 			

		 			PreparedStatement ps3= conn.prepareStatement("insert into pro_eng values(?,?)");
		 			
		 			
		 			ps3.setInt(1, pid);
		 			ps3.setString(2, name);
		 			
		 			int x= ps3.executeUpdate();
		 			
		 			if(x > 0)
		 				message = "Enginner assigned to releated problem Sucessfully.. ";
		 			else
		 				throw new EngineerException("Techical error..");
		 			
		 		}
		 		else
		 			throw new ProblemException("Invalid problem...");
		 		
		 		
		 		
		 		
		 	}else
		 		throw new EngineerException("Invalid Engineer...");
			
			
			
			
			
			
		} catch (SQLException e) {
			throw new EngineerException(e.getMessage());
		}
		
		
		
		return message;
		
		
	}

	
	
	
	@Override
	public String loginHod(String username, String password) throws HodException {
		
		String message = "no ligin";
		
		
        try(Connection conn = DButil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("select * from hod where hname = ? AND hpassword = ?");			
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet x = ps.executeQuery();
			
			if(x.next()) {
				
				message = "0011";
			}
			else {
				
				throw new HodException("Invalid Username or password.. ");
				
			}
			
        } catch (SQLException e) {
			throw new HodException(e.getMessage());
		}
		
		
		
		
		
		return message;
		
	}

	@Override
	public String loginEngineer(String email, String password) throws EngineerException {
		
		
         String message = "no login";
		
		
        try(Connection conn = DButil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("select * from Engineer where email = ? AND password = ?");			
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet x = ps.executeQuery();
			
			if(x.next()) {
				
				message = "0011";
			}
			else {
				
				throw new EngineerException("Invalid Username or password.. ");
				
			}
			
        } catch (SQLException e) {
			throw new  EngineerException(e.getMessage());
		}
		
		
		
		
		
		return message;
		
		
		
		
		
		
	}

	@Override
	public String changePassword(String email, String password, String newpassword) throws EngineerException {
		
		String message = "no delete";
		
            try(Connection conn = DButil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("update Engineer set password= ? where email= ? AND password= ? ");			
			
			ps.setString(1, newpassword);
			ps.setString(2, email);
			ps.setString(3, password);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				
				message = "Your password change successfully !";
			}
			else {
				
				throw new EngineerException("Invalid Username or password.. ");
				
			}
			
        } catch (SQLException e) {
			throw new  EngineerException(e.getMessage());
		}
		
		
		
		return message;
	}

	@Override
	public String registerEmployee(Employee employee) {
		String message = "inserted";
		
         try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into employee values(?,?,?)");
			
			
			
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getUsername());
			ps.setString(3, employee.getPassword());
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Employee Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		
		
		
		return message;
	}

	@Override
	public String loginEmployee(String username, String password) throws EmployeeException {
		String message = "not";
		
		
         try(Connection conn = DButil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("select * from employee where username = ? AND password = ?");			
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet x = ps.executeQuery();
			
			if(x.next()) {
				
				message = "0011";
			}
			else {
				
				throw new EmployeeException("Invalid Username or password.. ");
				
			}
			
        } catch (SQLException e) {
			throw new  EmployeeException(e.getMessage());
		}
		
		
		
		return message;
	}

	@Override
	public String raiseComplain(String category, String status, String pname) {
		String message = "not raised";
		
		
          try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into problem(category, status, pname) values(?,?,?)");
			
			
			
			ps.setString(1, category);
			ps.setString(2, status);
			ps.setString(3, pname);
			
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Complain Raised Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
		
		
		return message;
	}

	@Override
	public List<Problem> getProblemlist() throws ProblemException {
		
		List<Problem> list = new ArrayList<>();
		
           try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from problem");
			
			
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				
				int p= rs.getInt("pid");
				String c= rs.getString("category");
				String s= rs.getString("status");
				String n= rs.getString("pname");
				
			Problem problem =new Problem(p, c, s, n);	
				
			list.add(problem);
			
		}
			
			
		} catch (SQLException e) {
			throw new ProblemException(e.getMessage());
		}
		
		
		if(list.size() == 0)
			throw new ProblemException("Problem not found..");
		
		
		
		
		return list;
	}

	@Override
	public List<EngineerPRO> getMyProblem(String name) throws ProblemException {
		
		List<EngineerPRO> list = new ArrayList<>();
		
           try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select p.pname,p.category,p.pid, p.status from problem p INNER JOIN Engineer e INNER JOIN pro_eng pe ON p.pid= pe.id AND e.name = pe.ename AND e.name = ? ;");
			ps.setString(1, name);
			
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				String n= rs.getString("pname");
				String c= rs.getString("category");
				String s= rs.getString("status");
				int p= rs.getInt("pid");
				
			EngineerPRO engineerpro = new EngineerPRO(n, c, s, p);
				
			list.add(engineerpro);
			
		}
			
			
		} catch (SQLException e) {
			throw new ProblemException(e.getMessage());
		}
		
		
		if(list.size() == 0)
			throw new ProblemException("Problem not found..");
		
		
		
		
		
		return list;
		
	}

	@Override
	public String updateStatus(String sts, int id) throws ProblemException {
		String str = "Updated";
		
         try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("update problem set status = ? where pid= ?");
			ps.setString(1, sts);
			ps.setInt(2, id);
			
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				str = "status updated !";
			}
			
			
         }catch (SQLException e) {
        	 
 			throw new ProblemException(e.getMessage());
 		}
		
		
		return str;
	}

	@Override
	public List<EmployeePro> getEngineer(int pid) throws ProblemException {
		
		List<EmployeePro> list = new ArrayList<>();
		
        try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select e.name,p.pname from problem p INNER JOIN Engineer e INNER JOIN pro_eng pe ON p.pid= pe.id AND e.name = pe.ename AND p.pid = ? ");
			ps.setInt(1, pid);
			
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				String n= rs.getString("name");
				String p= rs.getString("pname");
				
			EmployeePro ep = new EmployeePro(n, p);
				
			list.add(ep);
			
		}
			
			
		} catch (SQLException e) {
			throw new ProblemException(e.getMessage());
		}
		
		
		if(list.size() == 0)
			throw new ProblemException("Problem not found..");
		
		
		
		
		
		return list;
		
		
		
		
	}

	@Override
	public List<Problem> getProlist(int id) throws ProblemException {
		
         List<Problem> list = new ArrayList<>();
		
        try(Connection conn= DButil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select p.pid,p.category, p.status, p.pname from problem p INNER JOIN Engineer e INNER JOIN pro_eng pe ON p.pid= pe.id AND e.name = pe.ename AND p.pid = ? ");
			ps.setInt(1, id);
			
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				int pi = rs.getInt("pid");
				String c= rs.getString("category");
				String s= rs.getString("status");
				String pn = rs.getString("pname");
				
				Problem problem = new Problem(pi, c, s, pn); 
				
			list.add(problem);
			
		}
			
			
		} catch (SQLException e) {
			throw new ProblemException(e.getMessage());
		}
		
		
		if(list.size() == 0)
			throw new ProblemException("Problem not found..");
		
		
		
		
		
		return list;
		
		
		
	}

	@Override
	public String changeEmpPassword(String user, String password, String newpassword) throws EmployeeException {
		
		String message = "no delete";
		
        try(Connection conn = DButil.provideConnection()) {
		
		
		PreparedStatement ps= conn.prepareStatement("update employee set password= ? where username= ? AND password= ? ");			
		
		ps.setString(1, newpassword);
		ps.setString(2, user);
		ps.setString(3, password);
		
		int x = ps.executeUpdate();
		
		if(x>0) {
			
			message = "Your password change successfully !";
		}
		else {
			
			throw new EmployeeException("Invalid Username or password.. ");
			
		}
		
    } catch (SQLException e) {
		throw new  EmployeeException(e.getMessage());
	}
	
	
	
	return message;
		
		
	}
		
		
		
		
		
		
		
	
	
	
	

}
