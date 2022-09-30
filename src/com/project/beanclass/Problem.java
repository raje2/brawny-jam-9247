package com.project.beanclass;

public class Problem {
	
	private int pid;
	private String category;
	private String status;
	private String pname;
	
	
	
	
	
	
	public Problem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Problem(int pid, String category, String status, String pname) {
		super();
		this.pid = pid;
		this.category = category;
		this.status = status;
		this.pname = pname;
	}



	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}



	@Override
	public String toString() {
		return "Problem [pid=" + pid + ", category=" + category + ", status=" + status + ", pname=" + pname + "]";
	}
	
	
	
	

}
