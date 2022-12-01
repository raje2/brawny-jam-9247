package com.project.deo;

import java.util.List;

import com.project.beanclass.Engineer;
import com.project.exceptions.EngineerException;
import com.project.exceptions.HodException;
import com.project.exceptions.ProblemException;

public class HOD_DeoImpl implements HOD_Deo{

	@Override
	public String registerEngineer1(String name, String email, String password, String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String registerEngineer2(Engineer engineer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Engineer> getEngineerlist() throws EngineerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteEngineer(String ename) throws EngineerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignProblemWithEngineer(int pid, String name) throws EngineerException, ProblemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginHod(String username, String password) throws HodException {
		// TODO Auto-generated method stub
		return null;
	}

}
