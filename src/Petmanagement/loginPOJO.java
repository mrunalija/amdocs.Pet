package Petmanagement;

public class loginPOJO {
	String username;
	String name;
	String Email;
	String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public loginPOJO(String username, String name, String email, String password) {
		
		this.username = username;
		this.name = name;
		Email = email;
		this.password = password;
	}
	public loginPOJO(String username2, String password2) {
		this.username=username2;
		this.password=password2;
	}
	
	
	
	

}
