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
	
		
	
		
		try(Connection connection = DButil.provideConnection()) {
			
			PreparedStatement preparedstatement = connection.prepareStatement
					("insert into Engineer values(?,?,?,?)");
			
			
			
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, email);
			preparedstatement.setString(3, password);
			preparedstatement.setString(4, category);
			
			int x= preparedstatement.executeUpdate();
			
			
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
	
		
	
		
		try(Connection connection = DButil.provideConnection()) {
			
			PreparedStatement preparedstatement = connection.prepareStatement
					("insert into Engineer(name,email,password,category) values(?,?,?,?)");
			
			
			
			preparedstatement.setString(1, engineer.getName());
			preparedstatement.setString(2, engineer.getEmail());
			preparedstatement.setString(3, engineer.getPassword());
			preparedstatement.setString(4, engineer.getCategory());
			
			int x= preparedstatement.executeUpdate();
			
			
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
			
			PreparedStatement preparedstatement = conn.prepareStatement("select * from Engineer");
			
			
			
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				
				String name= resultset.getString("name");
				String email= resultset.getString("email");
				String password= resultset.getString("password");
				String category= resultset.getString("category");
				
			Engineer engineer=new Engineer(name, email, password, category);	
				
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
		
		
		try(Connection connection = DButil.provideConnection()) {
			
			PreparedStatement preparedstatement = connection.prepareStatement("delete from Engineer where name = ?");
			
			preparedstatement.setString(1, name);
			
			int x = preparedstatement.executeUpdate();
			
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
		
		try(Connection connection= DButil.provideConnection()) {
			
		 	PreparedStatement preparedstatement = connection.prepareStatement("select * from Engineer where name =?");
			
		 	
		 	preparedstatement.setString(1, name);
		 	
		 	ResultSet resultset= preparedstatement.executeQuery();
			
		 	if(resultset.next()) {
		 		
		 		PreparedStatement preparedstatement2= connection.prepareStatement("select * from problem where pid=?");
		 		
		 		preparedstatement2.setInt(1, pid);

		 		ResultSet resultset2= preparedstatement2.executeQuery();
		 		
		 		if(resultset2.next()) {
		 			

		 			PreparedStatement preparedstatement3= connection.prepareStatement("insert into pro_eng values(?,?)");
		 			
		 			
		 			preparedstatement3.setInt(1, pid);
		 			preparedstatement3.setString(2, name);
		 			
		 			int x= preparedstatement3.executeUpdate();
		 			
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
		
		
        try(Connection connection = DButil.provideConnection()) {
			
			
			PreparedStatement preparedstatement = connection.prepareStatement("select * from hod where hname = ? AND hpassword = ?");			
			
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			
			ResultSet x = preparedstatement.executeQuery();
			
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
		
		
        try(Connection connection = DButil.provideConnection()) {
			
			
			PreparedStatement preparedstatement = connection.prepareStatement("select * from Engineer where email = ? AND password = ?");			
			
			preparedstatement.setString(1, email);
			preparedstatement.setString(2, password);
			
			ResultSet x = preparedstatement.executeQuery();
			
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
		
            try(Connection connection = DButil.provideConnection()) {
			
			
			PreparedStatement preparedstatement = connection.prepareStatement("update Engineer set password= ? where email= ? AND password= ? ");			
			
			preparedstatement.setString(1, newpassword);
			preparedstatement.setString(2, email);
			preparedstatement.setString(3, password);
			
			int x = preparedstatement.executeUpdate();
			
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
		
         try(Connection connection= DButil.provideConnection()) {
			
			PreparedStatement preparedstatement= connection.prepareStatement
					("insert into employee values(?,?,?)");
			
			
			
			preparedstatement.setString(1, employee.getName());
			preparedstatement.setString(2, employee.getUsername());
			preparedstatement.setString(3, employee.getPassword());
			
			int x= preparedstatement.executeUpdate();
			
			
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
		
		
         try(Connection connection = DButil.provideConnection()) {
			
			
			PreparedStatement preparedstatement= connection.prepareStatement("select * from employee where username = ? AND password = ?");			
			
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			
			ResultSet x = preparedstatement.executeQuery();
			
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
		
		
          try(Connection connection= DButil.provideConnection()) {
			
			PreparedStatement preparedstatement= connection.prepareStatement
					("insert into problem(category, status, pname) values(?,?,?)");
			
			
			
			preparedstatement.setString(1, category);
			preparedstatement.setString(2, status);
			preparedstatement.setString(3, pname);
			
			
			int x= preparedstatement.executeUpdate();
			
			
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
		
           try(Connection connection= DButil.provideConnection()) {
			
			PreparedStatement preparedstatement= connection.prepareStatement("select * from problem");
			
			
			
			ResultSet resultset= preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				
				int p= resultset.getInt("pid");
				String c= resultset.getString("category");
				String s= resultset.getString("status");
				String n= resultset.getString("pname");
				
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
		
           try(Connection connection= DButil.provideConnection()) {
			
			PreparedStatement preparedstatement= connection.prepareStatement("select p.pname,p.category,p.pid, p.status from problem p INNER JOIN Engineer e INNER JOIN pro_eng pe ON p.pid= pe.id AND e.name = pe.ename AND e.name = ? ;");
			preparedstatement.setString(1, name);
			
			
			ResultSet resultset= preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				String n= resultset.getString("pname");
				String c= resultset.getString("category");
				String s= resultset.getString("status");
				int p= resultset.getInt("pid");
				
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
		
         try(Connection connection= DButil.provideConnection()) {
			
			PreparedStatement preparedstatement= connection.prepareStatement("update problem set status = ? where pid= ?");
			preparedstatement.setString(1, sts);
			preparedstatement.setInt(2, id);
			
			
			int x = preparedstatement.executeUpdate();
			
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
		
        try(Connection connection= DButil.provideConnection()) {
			
			PreparedStatement preparedstatement= connection.prepareStatement("select e.name,p.pname from problem p INNER JOIN Engineer e INNER JOIN pro_eng pe ON p.pid= pe.id AND e.name = pe.ename AND p.pid = ? ");
			preparedstatement.setInt(1, pid);
			
			
			ResultSet resultset= preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				String n= resultset.getString("name");
				String p= resultset.getString("pname");
				
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
		
        try(Connection connection= DButil.provideConnection()) {
			
			PreparedStatement preparedstatement= connection.prepareStatement("select p.pid,p.category, p.status, p.pname from problem p INNER JOIN Engineer e INNER JOIN pro_eng pe ON p.pid= pe.id AND e.name = pe.ename AND p.pid = ? ");
			preparedstatement.setInt(1, id);
			
			
			ResultSet resultset= preparedstatement.executeQuery();
			
			while(resultset.next()) {
				int pid = resultset.getInt("pid");
				String cate= resultset.getString("category");
				String status= resultset.getString("status");
				String pname = resultset.getString("pname");
				
				Problem problem = new Problem(pid, cate, status, pname); 
				
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
		
        try(Connection connection = DButil.provideConnection()) {
		
		
		PreparedStatement preparedstatement= connection.prepareStatement("update employee set password= ? where username= ? AND password= ? ");			
		
		preparedstatement.setString(1, newpassword);
		preparedstatement.setString(2, user);
		preparedstatement.setString(3, password);
		
		int x = preparedstatement.executeUpdate();
		
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
