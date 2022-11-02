package com.project.deo;

import java.util.List;

import com.project.beanclass.Employee;
import com.project.beanclass.EmployeePro;
import com.project.beanclass.Engineer;
import com.project.beanclass.EngineerPRO;
import com.project.beanclass.Problem;
import com.project.exceptions.EmployeeException;
import com.project.exceptions.EngineerException;
import com.project.exceptions.HodException;
import com.project.exceptions.ProblemException;

public interface EngineerDeo {
	
    public String registerEngineer1(String name,String email, String password, String category);
	
	public String registerEngineer2(Engineer engineer);
	
	public List<Engineer> getEngineerlist() throws EngineerException;
	
	public String deleteEngineer(String ename) throws EngineerException;
	
	public String assignProblemWithEngineer(int pid, String name)throws EngineerException,ProblemException ;

	public String loginHod(String username, String password)throws HodException;
	
	public String loginEngineer(String email, String password)throws EngineerException;
	
	public String changePassword(String email, String password, String newpassword)throws EngineerException;
	
	public String registerEmployee(Employee employee);
	
	public String loginEmployee(String username, String password)throws EmployeeException;
	
	public String raiseComplain(String category, String status, String pname);
	
	public List<Problem> getProblemlist() throws ProblemException;
	
	public List<EngineerPRO> getMyProblem(String name)throws ProblemException;
	
	public String updateStatus(String sts,int id) throws ProblemException;
	
	public List<EmployeePro> getEngineer(int pid)throws ProblemException;
	
	public List<Problem> getProlist(int id) throws ProblemException;
	
	public String changeEmpPassword(String user, String password, String newpassword)throws EmployeeException;

}
