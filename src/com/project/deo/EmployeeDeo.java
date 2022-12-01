package com.project.deo;

import java.util.List;

import com.project.beanclass.Employee;
import com.project.beanclass.EngineerPRO;
import com.project.beanclass.Problem;
import com.project.exceptions.EmployeeException;
import com.project.exceptions.ProblemException;

public interface EmployeeDeo {

public String registerEmployee(Employee employee);
	
	public String loginEmployee(String username, String password)throws EmployeeException;
	
	public String raiseComplain(String category, String status, String pname);
	
	public List<Problem> getProblemlist() throws ProblemException;
	
	public List<EngineerPRO> getMyProblem(String name)throws ProblemException;
	
}
