package com.project.usecase;

import java.util.List;
import java.util.Scanner;

import com.project.beanclass.Employee;
import com.project.beanclass.EmployeePro;
import com.project.beanclass.Engineer;
import com.project.beanclass.EngineerPRO;
import com.project.beanclass.Problem;
import com.project.deo.EngineerDeo;
import com.project.deo.EngineerDeoImpl;
import com.project.exceptions.EmployeeException;
import com.project.exceptions.EngineerException;
import com.project.exceptions.HodException;
import com.project.exceptions.ProblemException;

public class Demo {

	public static void main(String[] args) {
		

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to PowerProject page Who you are ");
		
		System.out.println("1.HOD");
		System.out.println("2.Engineer");
		System.out.println("3.Employee");
		int ans = sc.nextInt();
		
		
		//here hod page start
		if(ans==1) {
			
			System.out.println("Login as HOD");
			System.out.println("Submit a username");
			String user = sc.next();
			System.out.println("Submit a password");
			String pass = sc.next();
			
			try {
				
				EngineerDeo deo1 = new EngineerDeoImpl();
				
				String code = deo1.loginHod(user, pass);
				
				if(code.equalsIgnoreCase("0011")) {
					System.out.println("Welcome to HOD Official Page");
					System.out.println("1.register enginner ");
					System.out.println("2.to get enginner list ");
					System.out.println("3.Delet enginner from table ");
					System.out.println("4.See all raised problem ");
					System.out.println("5.Assign problem to Engineer ");
					int command = sc.nextInt();
					
					
					//here we insert value in engineer table
					if(command==1) {
						
						while(true) {
							
							
							System.out.println("Enter Engineer Name:");
							String ename= sc.next();
							
							System.out.println("Enter Engineer Email:");
							String email= sc.next();
							
							System.out.println("Enter Engineer Password:");
							String password= sc.next();
							
							System.out.println("Enter Engineer Category");
							String category= sc.next();
							
							
							EngineerDeo deo = new EngineerDeoImpl();
							
							Engineer engineer = new Engineer(ename,email,password,category);
							
							
							String result = deo.registerEngineer2(engineer);
							
							System.out.println(result);
							
							System.out.println("wants to add more ? yes/no");
							String ans2 = sc.next();
							
							if(ans2.equalsIgnoreCase("no")) {
								break;
							}
							
						}
						
					}
					
					//here we get engineer list
					
					else if(command == 2) {
						
						
						EngineerDeo deo = new EngineerDeoImpl();
						
						try {
							
							List<Engineer> list = deo.getEngineerlist();
							
							list.forEach(e -> {
								
								System.out.println("Engineer name is : "+e.getName());
								System.out.println("Engineer email is : "+e.getEmail());
								System.out.println("Engineer password is : "+e.getPassword());
								System.out.println("Engineer category is : "+e.getCategory());
								
								
								System.out.println("=================================");
								
							});
							
						} catch (EngineerException e) {
							
							e.printStackTrace();
						}
						
					
					}
					
					
					//here we Delete engineer from list
					
					else if(command == 3) {
						
						System.out.println("Enter here engineer name to delete ");
						String name = sc.next();
					
					   EngineerDeo deo = new EngineerDeoImpl();
					   
					   try {
						   
						String result = deo.deleteEngineer(name);
						System.out.println(result);
						
						} catch (EngineerException e) {
							
							e.printStackTrace();
						}
						
						
					}
					
					//here we see all raised problem
					else if(command==4) {
						
						EngineerDeo deo = new EngineerDeoImpl();
						
						try {
							
							List<Problem> list = deo.getProblemlist();
							
                              list.forEach(e -> {
								
								System.out.println("Problem pid is : "+e.getPid());
								System.out.println("Problem category is : "+e.getCategory());
								System.out.println("Problem status is : "+e.getStatus());
								System.out.println("Problem name is : "+e.getPname());
								
								
								System.out.println("=================================");
								
							});
							
							
						} catch (ProblemException e) {
							
							
							e.printStackTrace();
						}
						
						
					}
					
					//here we assign problem to engineer
					else if(command==5) {
					
						EngineerDeo deo = new EngineerDeoImpl();
						
						System.out.println("Enter problem ID ");
						int pid = sc.nextInt();
						
						System.out.println("Enter Engineer name ");
						String name = sc.next();
						
						try {
							String result3 = deo.assignProblemWithEngineer(pid, name);
							
							System.out.println(result3);
						} catch (EngineerException e) {
						
							e.printStackTrace();
						} catch (ProblemException e) {
							
							e.printStackTrace();
						}
						
						
					}
					
					
					
				}
				
			} catch (HodException he) {
				System.out.println(he.getMessage());
			}
			
			
		}
		
		//here engineer page start
		else if(ans==2) {
			
			System.out.println("Login as Engineer");
			System.out.println("Submit HOD provided email here");
			String email = sc.next();
			System.out.println("Submit HOD provided password here");
			String pass = sc.next();
			
			EngineerDeo deo1 = new EngineerDeoImpl();
			
			try {
				
				String code = deo1.loginEngineer(email, pass);
				
				if(code.equalsIgnoreCase("0011")) {
					
					System.out.println("Welcome to Engineer Official Page");
					System.out.println("1.See the assign problem");
					System.out.println("2.Update problem status");
					System.out.println("3.See the list of problem");
					System.out.println("4.Change the password");
					
					int command = sc.nextInt();
					
					if(command==4) {
						System.out.println("submit your old email");
						String oldemail = sc.next();
						System.out.println("submit your old password");
						String oldpass = sc.next();
						System.out.println("submit a new password");
						String newpass = sc.next();
						
						EngineerDeo deo = new EngineerDeoImpl();
						String result = deo.changePassword(oldemail, oldpass, newpass);
						
						System.out.println(result);
						
					}
					//here engineer see his assign problem
					else if(command==1 || command == 3) {
						System.out.println("Please enter your name to see assign problem");
						String eng_name = sc.next();
						EngineerDeo deo = new EngineerDeoImpl();
						
						try {
							
							List<EngineerPRO> list =  deo.getMyProblem(eng_name);
							
                                list.forEach(e -> {
								
								System.out.println("Problem id is : "+e.getPid());
								System.out.println("Problem category is : "+e.getPcategory());
								System.out.println("Problem status is : "+e.getPstatus());
								System.out.println("Problem name is : "+e.getPname());
								
								
								System.out.println("=================================");
								
							});
							
						} catch (ProblemException e) {
							
							e.printStackTrace();
						}
						
						
					}
					//here engineer update his/her status
					else if(command==2) {
						System.out.println("Please enter your name to update status");
						int pro_id = sc.nextInt();
						System.out.println("Please enter your status (resolved/pending) ");
						String stsname = sc.next();
						
						EngineerDeo deo = new EngineerDeoImpl();
						
						try {
							String result4 = deo.updateStatus(stsname, pro_id);
							
							System.out.println(result4);
							
						} catch (ProblemException e) {
							
							e.printStackTrace();
						}
						
					}
					
				}
				
				
			} catch (EngineerException e) {
				
				e.printStackTrace();
			}
			
			
			
		}
		
		//here employee page start
				else if(ans==3) {

					
					System.out.println("1. To register new employee account press( 1 )");
					System.out.println("2. To Login employee account press( 2 )");
					int com = sc.nextInt();
					
					if(com==1) {
						
						System.out.println("Submit your name here");
						String empname = sc.next();
						System.out.println("Submit username here");
						String empuser = sc.next();
						System.out.println("Submit password here");
						String emppass = sc.next();
						
						EngineerDeo deo1 = new EngineerDeoImpl();
						
						Employee emp = new Employee(empname, empuser, emppass);
						
						String result = deo1.registerEmployee(emp);
						
						System.out.println(result);
						
						
						
					}
					else if(com==2) {
						
						System.out.println("Submit username here");
						String empuser = sc.next();
						System.out.println("Submit password here");
						String emppass = sc.next();
						
						EngineerDeo deo1 = new EngineerDeoImpl();
						
						try {
							
							String result = deo1.loginEmployee(empuser, emppass);
							if(result.equalsIgnoreCase("0011")) {
								
								System.out.println("Welcome to employee official page");
								System.out.println("1.For Register a complain press ( 1 )");
								System.out.println("2.See the assign engineer to your problem press ( 2 )");
								System.out.println("3.List of complain press ( 3 )");
								System.out.println("4.Change your password press ( 4 )");
								
								int command = sc.nextInt();
								
								if(command==1) {
									
									while(true) {
									
									EngineerDeo deo = new EngineerDeoImpl();
									
									System.out.println("Submit problem category (hardware/software)");
									String catego = sc.next();
									System.out.println("Submit problem status ");
									String status = sc.next();
									System.out.println("Submit problem name ");
									String pname = sc.next();
									
									String result2 = deo.raiseComplain(catego, status, pname);
									
									System.out.println(result2);
									
									System.out.println("Register more complain ? (yes/no)");
									String ka = sc.next();
									
									if(ka.equalsIgnoreCase("no")) {
										break;
									}
									
								 }
									
								}
								//here employee see engineer name assign to his problem
								else if(command==2) {	
									
									System.out.println("Please enter pid");
									int pro_id = sc.nextInt();
									EngineerDeo deo = new EngineerDeoImpl();
									
									try {
										
										List<EmployeePro> list =  deo.getEngineer(pro_id);
										
			                                list.forEach(e -> {
											
											System.out.println("Assign Engineer is : "+e.getEname());
											System.out.println("Problem name is : "+e.getPname());
											
											
											System.out.println("=================================");
											
										});
										
									} catch (ProblemException e) {
										
										e.printStackTrace();
									}
									
									
									
								}
								
								//here employee see problem list assigned by him
								else if(command==3) {	
								   
									System.out.println("Please enter pid");
									int pro_id = sc.nextInt();
									EngineerDeo deo = new EngineerDeoImpl();
									
									try {
										
										List<Problem> list =  deo.getProlist(pro_id);
										
			                                list.forEach(e -> {
											
											System.out.println("Problem pid is : "+e.getPid());
											System.out.println("Problem category is : "+e.getPname());
											System.out.println("Problem status is : "+e.getStatus());
											System.out.println("Problem name is : "+e.getPname());
											
											System.out.println("=================================");
											
										});
										
									} catch (ProblemException e) {
										
										e.printStackTrace();
									}
									
									
								
								}
								
								//here employee change his/her password
								else if(command==4) {
								
									System.out.println("submit your old username");
									String oldemail = sc.next();
									System.out.println("submit your old password");
									String oldpass = sc.next();
									System.out.println("submit a new password");
									String newpass = sc.next();
									
									EngineerDeo deo = new EngineerDeoImpl();
									String result6;
									try {
										
										result6 = deo.changeEmpPassword(oldemail, oldpass, newpass);
										
										System.out.println(result6);
										
										
									} catch (EmployeeException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
								
									
								}
								
								
								
								
							}
							
						} catch (EmployeeException e) {
							
							e.printStackTrace();
						}
						
						
					}
					
					
					
		
			}
	}
	

}
