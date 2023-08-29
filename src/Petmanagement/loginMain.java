package Petmanagement;

import java.sql.SQLException;
import java.util.Scanner;

public class loginMain {
	public static void main(String[] args) throws SQLException {
		Scanner scanner=new Scanner(System.in);
		loginDAO daologin = new loginDAO();
		Main op=new Main(); 
		while(true) {
			System.out.println("Whether to login or register. choose any one ");
			System.out.println("1. Register");
			System.out.println("2. Login");
			int choice = scanner.nextInt();
			
		switch(choice) {
		case 1 : //Register
					 System.out.println("Enter name");
					 String name= scanner.next();
					 System.out.println("Enter email");
					 String Email= scanner.next();
					 System.out.println("Create Username ");
					 String username= scanner.next();
					 System.out.println("Create Password ");
					 String password= scanner.next();
					 
					try {
						daologin.insert(name, username, password, Email);
						System.out.println("User registered successfully");
						
					
					try {
						daologin.login(username, password); 
						 System.out.println("login successful");
						 op.MainOperation();
					 }
					 catch(InvalidCredentialsException e) {
						 System.out.println(e.getMessage());
					 }
					}
					catch(SQLException e) {
						System.out.println("Error try again"+e.getMessage());
					}
			 break;
			 
		case 2:
			 System.out.println("Enter Username ");
			 String username2= scanner.next();
			 System.out.println("Enter Password ");
			 String password2= scanner.next();
			 try{
				 daologin.login(username2, password2); 
				 System.out.println("login successful");
				 op.MainOperation();
			 }
			 catch(InvalidCredentialsException e) {
				 System.out.println(e.getMessage());
			 }
			 break;
			
		}
		
		}
}
}
