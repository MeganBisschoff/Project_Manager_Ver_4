package poised;

import java.sql.*;
import java.util.Scanner;

/**
 * Parent class that captures and edits the information of people set as attributes of the Project 
 * object. <code>Person</code> is the non-instantiable abstract parent class of the instantiable 
 * Manager, Engineer Architect and Customer subclasses.  
 * 
 * @author Megan Bisschoff
 * @Version 2.00
 * @see Manager.java
 * @see Engineer.java
 * @see Architect.java
 * @see Customer.java
 */
// Abstract Person class provides method implementation to all the subclasses.
public abstract class Person { 
			
	// Open scanner object.
	Scanner input = new Scanner(System.in);	
	
	// Protected attributes for Person, accessed by Manager, Engineer, Architect and Customer classes.
	protected String fullName = "none";
	protected String contactNumber = "none";
	protected String email = "none";
	protected String address = "none";
	
	// Attribute to control access to program methods based on whether Person has been captured.
	protected boolean personCaptured = false;
	
	// Constructor to initialise attributes in each subclass extending the Person class.
	protected Person() {
	}
	
	// ----- Getters & Setters for the Person ----- //
	/**
	 * The 'getter' and 'setter' methods gets and sets the values of the <code>fullName</code>,
	 * <code>contactNumber</code>, <code>email</code> and <code>address</code> fields for the 
	 * Manager, Engineer Architect and Customer objects.
	 * 
	 * @return a person's full name, contact number, email and address.
	 */
	public String getFullName () {
		return fullName;
	}
	
	public void setFullName (String fullName) {
		this.fullName = fullName;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/** 
	 * The abstract <code>toString</code> method is overriden by each of its subclasses 
	 * toString methods.
	 */
	public abstract String toString();

	/**
	 * Captures the fullname, contact number, email and address of an object from the respective 
	 * subclass of the Person type. Prompts the user for input and sets the <code>fullName</code>, 
	 * <code>contactNumber</code>, <code>email</code>, and <code>address</code>.
	 * <p>
	 * Input is requested until the user input is validated for each object field not being equal
	 * to "none" as set in the objects attributes.
	 * 
	 * @param personType	an object from the Manager, Engineer Architect and Customer subclass.
	 */
	// ----- Method to CAPTURE PERSON DATA ----- //
	public void capturePersonData(Person personType) {
			
		// Get input to capture Person's FULL NAME.
		while (fullName.equals("none")) {
			System.out.println("\nEnter first and last name: ");
			String inputFullName = input.nextLine();
			
			// Check that the input contains more than one word (Ie. first and last name).
			if (inputFullName.split(" ").length > 1) {
			    personType.setFullName(inputFullName);
				break;
			}
			else {
				System.out.println("Invalid entry, ensure both first and last names ar entered.");
			}
		}
		    
		// Get input to capture Person's CONTACT NUMBER.
		while (contactNumber.equals("none")) {
			System.out.println("\nEnter contact number as 0826669999, with no spaces or + symbol: ");
			String inputContactNumber = input.nextLine();
			
			// Check that the number contains no letters and has no missing numbers.
			// Number length should be 11 digits with country code or 10 digit without code.
			// Eg: 27821012020 or 0821012020
			if (inputContactNumber.matches("[0-9]+") && 
					(inputContactNumber.length() == 10 || inputContactNumber.length() == 11)) {
					personType.setContactNumber(inputContactNumber);
					break;
			}
			else {
				System.out.println("Invalid entry, enter number without spaces or + symbol.");
			}
		}
		
		// Get input to capture Person's EMAIL.
		while (email.equals("none")) {
			System.out.println("\nEnter email address: ");
			String inputEmail = input.nextLine();
			
			// Validate if the input contains an email '@' symbol and is not blank.
			if (inputEmail.isEmpty() || !inputEmail.contains("@")) {
				System.out.println("Invalid entry, authorised emails require an @ symbol.");
			}
			else {
				personType.setEmail(inputEmail);
			    break;
			}
		}
		
		// Get input to capture Person's ADDRESS.
		while (address.equals("none")) {
			System.out.println("\nEnter physical address: ");
			String inputAddress = input.nextLine();
			
			// Validate that the input contains letters and is not blank.
			if (inputAddress.isBlank() || inputAddress.matches("[^a-zA-Z]+")) {
				System.out.println("Invalid address entry, please try again.");
			}
			else {
				personType.setAddress(inputAddress);
				break;
			}
		}
	}
	
	/**
	 * Edits and recaptures the fullname, contact number, email and address of an object from the 
	 * respective subclass of the Person type. Prompts the user for input and sets the 
	 * <code>fullName</code>, <code>contactNumber</code>, <code>email</code>, and 
	 * <code>address</code>.
	 * <p>
	 * Input is requested until the user input is validated and set for each feild.
	 * 
	 * @param personType 	an object from the Manager, Engineer Architect or Customer subclass.
	 */
	// ----- Method to EDIT PERSON DATA ----- //
	public void editPersonData(Person personType) { 	
				
		// Get input to update the NAME.
		while (true) {
			System.out.println("\nEnter the new first and last name: ");
			String inputNewName = input.nextLine();
			
			// Check that the input contains more than one word (Ie. first and last name).
			if (inputNewName.split(" ").length > 1) {
				personType.setFullName(inputNewName);
			    break;
			}
			else {
				System.out.println("Invalid entry, ensure both first and last names ar entered.");
			}
		}
				
		// Get input to update the CONTACT NUMBER.
		while (true) {
			System.out.println("\nEnter the new contact number as 0826669999, "
					+ "with no spaces or + symbol: ");
			String inputNewContactNumber = input.nextLine();
			
			// Check that the number contains no letters and has no missing numbers.
			// Number length should be 11 digits with country code or 10 digit without code.
			// Eg: 27821012020 or 0821012020
			if (inputNewContactNumber.matches("[0-9]+") && 
					(inputNewContactNumber.length() == 10 || inputNewContactNumber.length() == 11)) {
				personType.setContactNumber(inputNewContactNumber);
				System.out.println("Contact number updated to " + personType.getContactNumber());
			    break;
			}
			else {
				System.out.println("Invalid entry, enter number without spaces or + symbol.");
			}
		}
		
		// Get input to update the EMAIL.
		while (true) {
			System.out.println("\nEnter the new email address: ");
			String inputNewEmail = input.nextLine();
			
			// Validate if the input contains an email '@' symbol and is not blank.
			if (inputNewEmail.isEmpty() || !inputNewEmail.contains("@")) {
				System.out.println("Invalid entry, authorised emails require an @ symbol.");
			} 
			else {
				personType.setEmail(inputNewEmail);
				System.out.println("Email updated to " + personType.getEmail());
				break;
			}
		}
		
		// Get input to update the physical ADDRESS.
		while (true) {
			System.out.println("\nEnter the new address: ");
			String inputNewAddress = input.nextLine();
			
			// Validate that the input contains letters and is not blank.
			if (inputNewAddress.isBlank() || inputNewAddress.matches("[^a-zA-Z]+")) {
				System.out.println("Invalid address entry, please try again.");
			}
			else {
				personType.setAddress(inputNewAddress);
				System.out.println("Address updated to " + personType.getAddress());
				break;
			}
		}
	}
	
	/**
	 * Method gets the Managers fullname, contact number, email and address of the selected project. 
	 * Establishes connection with the DB and executes an sql query to update the Managers table.
	 * 
	 * @param selectedProject 	
	 * @throws SQLException
	 */
	// ----- Method to UPDATE MANAGER TABLE ----- //
	public void updateManagerInDB(Project selectedProject) throws SQLException { 	
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try {
			Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
				"otheruser",
				"swordfish");
			Statement updateStatement = connection.createStatement();
			ResultSet resultsManagerTable;
		
			// Get all the values of the Manager.
			String newFullName = selectedProject.getManager().getFullName();
			String newContactNumber = selectedProject.getManager().getContactNumber();
			String newEmail = selectedProject.getManager().getEmail();
			String newAddress = selectedProject.getManager().getAddress();
			
			// Update the Manager table with the new values.
			updateStatement.executeUpdate(
					"UPDATE manager SET fullName = '" + newFullName 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE manager SET contactNumber = '" + newContactNumber 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE manager SET email = '" + newEmail 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(		
					"UPDATE manager SET address = '" + newAddress 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			
			// Retrieve newly updated data from Managers table and display results.
			resultsManagerTable = updateStatement.executeQuery(
					"SELECT * FROM manager WHERE projNumber = '" 
							+ selectedProject.getProjNumber() + "'");
			
			System.out.println("\nManager updated in Poise PMS database: \n");
			while (resultsManagerTable.next()) {
					System.out.println(
							"Full Name: \t\t" + resultsManagerTable.getString("fullName")
							+ "\nContact Number: \t" + resultsManagerTable.getString("contactNumber")
							+ "\nEmail: \t\t\t" + resultsManagerTable.getString("email")
							+ "\nAddress: \t\t" + resultsManagerTable.getString("address"));
			}
			// Close conenctions & objects
			connection.close();
			updateStatement.close();
			resultsManagerTable.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method gets the Engineers fullname, contact number, email and address of the selected project. 
	 * Establishes connection with the DB and executes an sql query to update the Engineers table.
	 * 
	 * @param selectedProject 	
	 * @throws SQLException
	 */
	// ----- Method to UPDATE ENGINEER TABLE ----- //
	public void updateEngineerInDB(Project selectedProject) throws SQLException { 	
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try {
			Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
				"otheruser",
				"swordfish");
			Statement updateStatement = connection.createStatement();
			ResultSet resultsEngineerTable;
	
			// Get all the values of the Engineer.
			String newFullName = selectedProject.getEngineer().getFullName();
			String newContactNumber = selectedProject.getEngineer().getContactNumber();
			String newEmail = selectedProject.getEngineer().getEmail();
			String newAddress = selectedProject.getEngineer().getAddress();
			
			// Update the Engineer table with the new values.
			updateStatement.executeUpdate(
					"UPDATE engineer SET fullName = '" + newFullName 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE engineer SET contactNumber = '" + newContactNumber 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE engineer SET email = '" + newEmail 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(		
					"UPDATE engineer SET address = '" + newAddress 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			
			// Retrieve newly updated data from Engineers table and display results.
			resultsEngineerTable = updateStatement.executeQuery(
					"SELECT * FROM engineer WHERE projNumber = '" 
							+ selectedProject.getProjNumber() + "'");
			
			System.out.println("\n- Engineer updated in Poise PMS database -\n");
			while (resultsEngineerTable.next()) {
					System.out.println(
							"Full Name: \t\t" + resultsEngineerTable.getString("fullName")
							+ "\nContact Number: \t" + resultsEngineerTable.getString("contactNumber")
							+ "\nEmail: \t\t\t" + resultsEngineerTable.getString("email")
							+ "\nAddress: \t\t" + resultsEngineerTable.getString("address"));
			}
			// Close conenctions & objects
			connection.close();
			updateStatement.close();
			resultsEngineerTable.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method gets the Architect fullname, contact number, email and address of the selected project. 
	 * Establishes connection with the DB and executes an sql query to update the Architects table.
	 * 
	 * @param selectedProject 	
	 * @throws SQLException
	 */
	// ----- Method to UPDATE ARCHITECT TABLE ----- //
	public void updateArchitectInDB(Project selectedProject) throws SQLException { 	
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try {
			Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
				"otheruser",
				"swordfish");
			Statement updateStatement = connection.createStatement();
			ResultSet resultsArchitectTable;
	
			// Get all the values of the Architect.
			String newFullName = selectedProject.getArchitect().getFullName();
			String newContactNumber = selectedProject.getArchitect().getContactNumber();
			String newEmail = selectedProject.getArchitect().getEmail();
			String newAddress = selectedProject.getArchitect().getAddress();
			
			// Update the Architect table with the new values.
			updateStatement.executeUpdate(
					"UPDATE architect SET fullName = '" + newFullName 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE architect SET contactNumber = '" + newContactNumber 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE architect SET email = '" + newEmail 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(		
					"UPDATE architect SET address = '" + newAddress 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			
			// Retrieve newly updated data from Engineers table and display results.
			resultsArchitectTable = updateStatement.executeQuery(
					"SELECT * FROM architect WHERE projNumber = '" 
							+ selectedProject.getProjNumber() + "'");
			
			System.out.println("\n- Architect updated in Poise PMS database -\n");
			while (resultsArchitectTable.next()) {
					System.out.println(
							"Full Name: \t\t" + resultsArchitectTable.getString("fullName")
							+ "\nContact Number: \t" + resultsArchitectTable.getString("contactNumber")
							+ "\nEmail: \t\t\t" + resultsArchitectTable.getString("email")
							+ "\nAddress: \t\t" + resultsArchitectTable.getString("address"));
			}
			// Close conenctions & objects
			connection.close();
			updateStatement.close();
			resultsArchitectTable.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method gets the Customer fullname, contact number, email and address of the selected project. 
	 * Establishes connection with the DB and executes an sql query to update the Customers table.
	 * 
	 * @param selectedProject 	
	 * @throws SQLException
	 */
	// ----- Method to UPDATE CUSTOMER TABLE ----- //
	public void updateCustomerInDB(Project selectedProject) throws SQLException { 	
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try {
			Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
				"otheruser",
				"swordfish");
			Statement updateStatement = connection.createStatement();
			ResultSet resultsCustomerTable;
	
			// Get all the values of the Customer.
			String newFullName = selectedProject.getCustomer().getFullName();
			String newContactNumber = selectedProject.getCustomer().getContactNumber();
			String newEmail = selectedProject.getCustomer().getEmail();
			String newAddress = selectedProject.getCustomer().getAddress();
			
			// Update the Customer table with the new values.
			updateStatement.executeUpdate(
					"UPDATE customer SET fullName = '" + newFullName 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE customer SET contactNumber = '" + newContactNumber 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(
					"UPDATE customer SET email = '" + newEmail 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			updateStatement.executeUpdate(		
					"UPDATE customer SET address = '" + newAddress 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			
			// Retrieve newly updated data from Customers table and display results.
			resultsCustomerTable = updateStatement.executeQuery(
					"SELECT * FROM customer WHERE projNumber = '" 
							+ selectedProject.getProjNumber() + "'");
			
			System.out.println("\n- Customer updated in Poise PMS database -\n");
			while (resultsCustomerTable.next()) {
					System.out.println(
							"Full Name: \t\t" + resultsCustomerTable.getString("fullName")
							+ "\nContact Number: \t" + resultsCustomerTable.getString("contactNumber")
							+ "\nEmail: \t\t\t" + resultsCustomerTable.getString("email")
							+ "\nAddress: \t\t" + resultsCustomerTable.getString("address"));
			}
			// Close conenctions & objects
			connection.close();
			updateStatement.close();
			resultsCustomerTable.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
		

