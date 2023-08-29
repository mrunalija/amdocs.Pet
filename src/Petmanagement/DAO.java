package Petmanagement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

 	public class DAO {
 		private Connection connection;
	
	public void insertPet(Pet pet) throws InvalidNameException {
		
		String query = "INSERT INTO pet_table(petId,petName,petColor,petPrice) values(?,?,?,?)";
		String petName=pet.getPetName();
		if(!petName.matches("&*$%#@!^-_+=")) {
			throw new InvalidNameException("Invalid pet name.name can ony contain letters ,numbers");
		}
		try (PreparedStatement statement = connection.prepareStatement(query))
		{	
		statement.setInt(1,pet.getPetId());
		statement.setString(2,pet.getPetName());
		statement.setString(3,pet.getPetColor());
		statement.setDouble(4,pet.getPetPrice());
		statement.setString(5,pet.getPetType());
		statement.setString(6,pet.getPetVaccination());
		statement.executeUpdate();

		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public List<Pet> getPet() throws PetNotFoundException {
		List<Pet> pets=new ArrayList<>();
		String query= "SELECT * FROM pet_table";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet=statement.executeQuery(query);			
			if(resultSet.next()) {
				int petId=resultSet.getInt("petId");
				String petName=resultSet.getString("petName");
				String petColor=resultSet.getString("petColor");
		        double petPrice=resultSet.getDouble("petPrice");
		        String petType=resultSet.getString("petType");
		        String Vaccination =resultSet.getString("petVaccination");
				pets.add(new Pet(petId,petName,petColor,petPrice,petType,Vaccination));
			}
			else {
				throw new PetNotFoundException("pet  Id  not found");
			}
			
		} 
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		return pets;
	}
	public void updatePet(Pet pet) throws UpdateException {
		String query="UPDATE pet_table SET petName=?,petColor=?,petPrice=? where petId=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setString(1,pet.getPetName());
			statement.setString(2,pet.getPetColor());
			statement.setDouble(3,pet.getPetPrice());
			statement.setInt(4,pet.getPetId());
			statement.setString(5,pet.getPetType());
			statement.setString(6,pet.getPetVaccination());
			statement.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		

	public void deletePet(int petId1) throws DeletionException {
		try {
			String query="DELETE FROM pet_table where petId=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, petId1);
			int rowsDeleted=statement.executeUpdate();
			if(rowsDeleted!=1) {
				throw new DeletionException("Failed to delete pet:No pet with id "+petId1+" found");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
	}

	public Pet Search(int petId) throws PetNotFoundException {
		String query = "SELECT * from pet_table where petId=?";
		try(PreparedStatement statement = connection.prepareStatement(query))
		{
		statement.setInt(1, petId);
		ResultSet resultSet=statement.executeQuery();
		
		if(resultSet.next())
			{
			int Id= resultSet.getInt("petId");
		    String name=resultSet.getString("petName");
			String color=resultSet.getString("petColor");
			double price=resultSet.getDouble("petPrice");
			String Type=resultSet.getString("petType");
			String Vaccination =resultSet.getString("petVaccination");
			return new Pet(Id,name,color,price,Type,Vaccination);
			
			}
		else {
			throw new PetNotFoundException("pet with Id "+ petId +" not found");
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		}
	
	public void countPets() throws PetNotFoundException{
		
		String query="SELECT petType,COUNT(*) AS pet_count FROM pet_table GROUP BY petType";
		try {
			Statement statement= connection.createStatement();
			ResultSet resultSet=statement.executeQuery(query);
			if(resultSet.next()) {
				String Category=resultSet.getString("petType");
				int count=resultSet.getInt("pet_count");
				System.out.println("Category= "+Category+"  count= "+count);
				
			}
			else {
				throw new PetNotFoundException("pet Id not found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	
	public int checkPet(String pet) throws SQLException
	{
		String query="SELECT distinct petType from pet_Table;";
			PreparedStatement PreparedStatement = connection.prepareStatement(query);
			ResultSet resultSet=PreparedStatement.executeQuery(query);
			HashSet<String> Hs=new HashSet<String>();
			while(resultSet.next()) {
				String Category=resultSet.getString("petType");
				Hs.add(Category);
			}
			if(Hs.contains(pet))
			return 1;
			return 0;
		}
	
	public void petVaccinaton(String petCat)  {
		String query="SELECT petVaccination,COUNT(*) AS count FROM pet_Table where petType='"+petCat+ "'  GROUP BY petVaccination";
		try {
			PreparedStatement PreparedStatement = connection.prepareStatement(query);
			ResultSet resultSet=PreparedStatement.executeQuery();
			while(resultSet.next()) {
				
				String vaccine=resultSet.getString("petVaccination");
				int count=resultSet.getInt("count");
				System.out.println("Vaccination= "+vaccine+"  count= "+count);
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
