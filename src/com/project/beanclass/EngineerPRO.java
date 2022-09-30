package com.project.beanclass;

public class EngineerPRO {
	
	
	String pname;
	String pcategory;
	String pstatus;
	int pid;
	
	
	public EngineerPRO(String pname, String pcategory, String pstatus, int pid) {
		super();
		this.pname = pname;
		this.pcategory = pcategory;
		this.pstatus = pstatus;
		this.pid = pid;
	}


	public EngineerPRO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public String getPcategory() {
		return pcategory;
	}


	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}


	public String getPstatus() {
		return pstatus;
	}


	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	@Override
	public String toString() {
		return "EngineerPRO [pname=" + pname + ", pcategory=" + pcategory + ", pstatus=" + pstatus + ", pid=" + pid
				+ "]";
	}
	
	
	
	

}
