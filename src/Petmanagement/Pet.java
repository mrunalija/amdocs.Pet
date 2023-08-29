package Petmanagement;

public class Pet {
	
		private  int petId;
		private  String petName;
		private  String petColor;
		private  double petPrice;
		private String petType;
		private String petVaccination;
		
		
		
		public Pet(int petId,String petName,String petColor, double petPrice,String petType,String petVaccination) {
			this.petId = petId;
			this.petName = petName;
			this.petColor = petColor;
			this.petPrice = petPrice;
			this.petType= petType;
			this.petVaccination=petVaccination; 
		}
		
		
		


		public  int getPetId() {
			return petId;
		}
		public void setPetId(int petId) {
			this.petId = petId;
		}
		public  String getPetName() {
			return petName;
		}
		public void setPetName(String petName) {
			this.petName = petName;
		}
		public  String getPetColor() {
			return petColor;
		}
		public void setPetColor(String petColor) {
			this.petColor = petColor;
		}
		public  double getPetPrice() {
			return petPrice;
		}
		public void setPetPrice(double petPrice) {
			this.petPrice = petPrice;
		}
		public  String getPetType() {
			return petType;
		}
		public void setPetType(String petType) {
			this.petType = petType;
		}

		public String getPetVaccination() {
			return petVaccination;
		}

		public void setPetVaccination(String petVaccination) {
			this.petVaccination = petVaccination;
		}
		
		
		
		}
