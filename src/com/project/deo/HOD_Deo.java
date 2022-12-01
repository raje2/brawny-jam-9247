package com.project.deo;

import java.util.List;

import com.project.beanclass.Engineer;
import com.project.exceptions.EngineerException;
import com.project.exceptions.HodException;
import com.project.exceptions.ProblemException;

public interface HOD_Deo {
	
public String registerEngineer1(String name,String email, String password, String category);
	
	public String registerEngineer2(Engineer engineer);
	
	public List<Engineer> getEngineerlist() throws EngineerException;
	
	public String deleteEngineer(String ename) throws EngineerException;
	
	public String assignProblemWithEngineer(int pid, String name)throws EngineerException,ProblemException ;

	public String loginHod(String username, String password)throws HodException;

}
