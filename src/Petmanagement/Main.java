package Petmanagement;
import java.util.Scanner;
import java.util.List;
import java.sql.SQLException;

public class Main {
	public void MainOperation() throws SQLException {
		System.out.println(DatabaseConnection.getConnection());
		Scanner scanner=new Scanner(System.in);
		DAO petDAO = new DAO();
		while(true) {
		System.out.println("choose from below list");
		System.out.println("1. INSERT");
		System.out.println("2. SELECT");
		System.out.println("3. UPDATE");
		System.out.println("4. DELETE");
		System.out.println("5. Search Pet");
		System.out.println("6. Count of Pet Type");
		System.out.println("7. check the Count Pet Vaccinated  ");
		System.out.println("8. Exit");
		int choice = scanner.nextInt();
		
		
		switch(choice) {
		case 1: //INSERT
			try {
			System.out.println("Enter ID");
			 int Id= scanner.nextInt();
			 System.out.println("Enter name");
			 String Name= scanner.next();
			 System.out.println("Enter Color");
			 String Color= scanner.next();
			 System.out.println("Enter Price");
			 double Price= scanner.nextDouble();
			 String TypeOfpet= scanner.next();
			 System.out.println("Enter Type of pet");
			 String petVaccination= scanner.next();
			 System.out.println("Is  pet is vaccinated ");
		
			 Pet pet= new Pet(Id,Name,Color,Price,TypeOfpet, petVaccination);
			 petDAO.insertPet(pet);
			}
			catch(InvalidNameException e) {
				System.out.println("Please provide a valid pet name without special characters");
			}
			 break;
			 
		case 2: //SELECT
			try {
			List<Pet> pets= petDAO.getPet();
				for(Pet Allpet:pets) {
				  System.out.println("ID "   +  Allpet.getPetId()+
			                        ", Name " + Allpet.getPetName()+
					                ", Color "+ Allpet.getPetColor()+
					                "  Price "+ Allpet.getPetPrice()+
					                "Type "   + Allpet.getPetType()+
					             "Vaccination" + Allpet.getPetVaccination());
				}
			}
				catch(PetNotFoundException e) {
					System.out.println(e.getMessage());
			}
			break;
		case 3://update
			try {
				
			
			System.out.println("Enter id");
			int petId=scanner.nextInt();
			if(petId==0)
			{
				throw new UpdateException("The Id cannot be zero");
			}
			System.out.println("Enter new name");
			String petName=scanner.next();
			System.out.println("Enter new color");
			String petColor=scanner.next();
			System.out.println("Enter new price");
			double petPrice=scanner.nextDouble();
			System.out.println("Enter new Pet_Type");
			String petType=scanner.next();
			System.out.println("Is  pet is vaccinated ");
			String petVaccination= scanner.next();
			Pet pet= new Pet(petId,petName,petColor,petPrice,petType,petVaccination);
		    petDAO.updatePet(pet);
			}
			catch(UpdateException e) {
				System.out.println("Id cannot be zero or null");
				
			}
		    
			break;
		case 4: //delete
			System.out.println("Enter Id that want to be deleted");
			try {
			int petId1=scanner.nextInt();
			petDAO.deletePet(petId1);
			}
			catch(DeletionException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		case 5: //search
			System.out.println("Enter Id to search");
			int searchId=scanner.nextInt();
			try {
			Pet searchPet=petDAO.Search(searchId);
			System.out.println(" ID " + searchPet.getPetId()+
                    ", Name " + searchPet.getPetName()+
	                ", Color " + searchPet.getPetColor()+
	                " Price " + searchPet.getPetPrice()+
	                "petType"+searchPet.getPetType());
			}
			catch(PetNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		case 6: //count the type
			try {
					petDAO.countPets();
			}
			catch(PetNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			break;	
		case 7: //count the vaccinated pet by its petType
			System.out.println("check  the type for vaccination");
			String petCat=scanner.next();
			int result = petDAO.checkPet(petCat);
			try{
				if(result==0)
				throw new PetNotFoundException("Pet Not Available");
				else
			 petDAO.petVaccinaton(petCat);
			}catch(PetNotFoundException e)
			{
				System.out.println(e);
				
			}
			
			break;
		
		case 8: 
				
				System.out.println("Exit");
				System.exit(0);
			
			
		default:
			System.out.println("Invalid");
		}
	}
	}
}

