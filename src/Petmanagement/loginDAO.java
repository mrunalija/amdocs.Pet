package Petmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class loginDAO {
	
	private Connection connection;
	
	public loginDAO() 
	{
		connection=DatabaseConnection.getConnection();
	}
	public void insert(String name,String username,String password,String Email ) {
		String query = "INSERT INTO Login_table (name,username,password,Email) values(?,?,?,?)";
		try {
		PreparedStatement statement = connection.prepareStatement(query);	
		statement.setString(1,name);
		statement.setString(2,username);
		statement.setString(3,password);
		statement.setString(4,Email);
		statement.executeUpdate();
		

		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public void login(String username,String password) throws InvalidCredentialsException {
		String query="SELECT * FROM Login_table WHERE username=? AND password=? ";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet=statement.executeQuery();
			if(!resultSet.next()) {
				throw new InvalidCredentialsException("Invalid username or password");
			}
			}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
