package poised;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that reads and writes project object information to and from a database.
 * <p>
 * A list of Project attribute information is read from the <code>PoisePMS</code> database then 
 * populated into new Project and Person objects and added to the <code>projectList</code>.
 * <p>
 * When a new project object is captured, the formatted string object is appended to the text file.
 * 
 * @author Megan Bisschoff
 * @version 2.0
 */
public abstract class ReadAndWriteToDatabase {

	// Constant to format the dates read from file.
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	private ReadAndWriteToDatabase() {
	}
	
	/**
	 * Method selects all data from each of the <code>project</code>, <code>manager</code>, 
	 * <code>engineer</code>, <code>architect</code> and <code>customer</code> tables.
	 * <p>
	 * <code>Project</code> and <code>Person</code> objects are initialised by getting and setting 
	 * the string values into the respective Project and Person attributes. 
	 * <p>
	 * An array list is inititalised which returns each <code>poiseProjects</code> added to the 
	 * list.
	 * <li>Project number
	 * <li>Project name
	 * <li>Project type
	 * <li>Project addres
	 * <li>Project ERF number
	 * <li>Fee total
	 * <li>Fee paid
	 * <li>Project deadline
	 * <li>Project finalisation status
	 * <li>Customers full name
	 * <li>Customers contact number
	 * <li>Customers email
	 * <li>Customers address
	 * <li>Architects full name
	 * <li>Architects contact number
	 * <li>Architects email
	 * <li>Architects address
	 * <li>Contractors full name
	 * <li>Contractors contact number
	 * <li>Contractors email
	 * <li>Contractors address
	 * 
	 * @return an arraylist of project objects.
	 * @throws SQLException 
	 */
	public static List<Project> readProjectsFromDatabase() throws SQLException {
		
		// Initiliase arraylist to store projects read from file.
		List<Project> projListFromDB = new ArrayList<Project>();
		
		try {
			// Initiliase Connection to the PoisePMS database.
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
					"otheruser",
					"swordfish");
			
			// Initiliase Statement to execute queries.
			Statement projectStatement = connection.createStatement();
			Statement managerStatement = connection.createStatement();
			Statement engineerStatement = connection.createStatement();
			Statement architectStatement = connection.createStatement();
			Statement customerstatement = connection.createStatement();
	
			// Initiliase ResultSet to return query results.
			ResultSet resultsProjectsTable;
			ResultSet resultsManagerTable;
			ResultSet resultsEngineerTable;
			ResultSet resultsArchitectTable;
			ResultSet resultsCustomerTable;
	
			// Retrieve data from each table in the database
			resultsProjectsTable = projectStatement.executeQuery(
					"SELECT * FROM projects"
					);
			resultsManagerTable = managerStatement.executeQuery(
					"SELECT * FROM manager"
					);
			resultsEngineerTable = engineerStatement.executeQuery(
					"SELECT * FROM engineer"
					);
			resultsArchitectTable = architectStatement.executeQuery(
					"SELECT * FROM architect"
					);
			resultsCustomerTable = customerstatement.executeQuery(
					"SELECT * FROM customer"
					);
			
			// Loop through each tables and set the vaues to the corresponding Project and Person objects.
			while (resultsProjectsTable.next() 
					&& resultsManagerTable.next()
					&& resultsEngineerTable.next() 
					&& resultsArchitectTable.next()
					&& resultsCustomerTable.next()) {
	
					// Create new Project and Person objects to assign attributes to. 
					Project poiseProject = new Project();
					Person projectManager = new Manager();
					Person projectEngineer = new Engineer();
					Person projectArchitect = new Architect();
					Person projectCustomer = new Customer();
	
					// Set attributes to the Project object. 
					poiseProject.setProjNumber(resultsProjectsTable.getString("projNumber"));
					poiseProject.setProjName(resultsProjectsTable.getString("projName"));
					poiseProject.setProjType(resultsProjectsTable.getString("projType"));
					poiseProject.setProjAddress(resultsProjectsTable.getString("projAddress"));
					int projErf = Integer.parseInt(resultsProjectsTable.getString("projErf"));
					poiseProject.setProjErf(projErf);
					double feeTotal = Double.parseDouble(resultsProjectsTable.getString("feeTotal"));
					poiseProject.setFeeTotal(feeTotal);
					double feePaid = Double.parseDouble(resultsProjectsTable.getString("feePaid"));
					poiseProject.setFeePaid(feePaid);
					// Convert string date read from sql DB to LOCALDATE type
					LocalDate projDeadline = LocalDate.parse
							(resultsProjectsTable.getString("projDeadline"), formatDate);
					poiseProject.setProjDeadline(projDeadline);
					// Check value of boolean type (1 == true and 0 == false)
					if (resultsProjectsTable.getString("projFinalised").equals("1")) {
						poiseProject.setProjFinalised(true);			
					} else {
						poiseProject.setProjFinalised(false);
					}
					// If date value is not null, convert date read from sql DB to LOCALDATE type.
					if (resultsProjectsTable.getString("projCompletionDate") != null) {
						Date sqlCompletionDate = Date.valueOf
								(resultsProjectsTable.getString("projCompletionDate"));
						LocalDate projCompletionDate = sqlCompletionDate.toLocalDate();
						poiseProject.setProjCompletionDate(projCompletionDate);
					} else {
						poiseProject.setProjCompletionDate(null);
					}
					
					poiseProject.setManager(projectManager);
					poiseProject.setEngineer(projectEngineer);
					poiseProject.setArchitect(projectArchitect);
					poiseProject.setCustomer(projectCustomer);
					
					// Set attributes to the Person objects.
					projectManager.setFullName(resultsManagerTable.getString("fullName"));
					projectManager.setContactNumber(resultsManagerTable.getString("contactNumber"));
					projectManager.setEmail(resultsManagerTable.getString("email"));
					projectManager.setAddress(resultsManagerTable.getString("address"));
					
					projectEngineer.setFullName(resultsEngineerTable.getString("fullName"));
					projectEngineer.setContactNumber(resultsEngineerTable.getString("contactNumber"));
					projectEngineer.setEmail(resultsEngineerTable.getString("email"));
					projectEngineer.setAddress(resultsEngineerTable.getString("address"));
					
					projectArchitect.setFullName(resultsArchitectTable.getString("fullName"));
					projectArchitect.setContactNumber(resultsArchitectTable.getString("contactNumber"));
					projectArchitect.setEmail(resultsArchitectTable.getString("email"));
					projectArchitect.setAddress(resultsArchitectTable.getString("address"));
					
					projectCustomer.setFullName(resultsCustomerTable.getString("fullName"));
					projectCustomer.setContactNumber(resultsCustomerTable.getString("contactNumber"));
					projectCustomer.setEmail(resultsCustomerTable.getString("email"));
					projectCustomer.setAddress(resultsCustomerTable.getString("address"));
					
					// Add projects from DB tables to the list.
					projListFromDB.add(poiseProject);
				}
			
			// Close connections and objects.
			connection.close(); 
			
			resultsManagerTable.close();
			resultsEngineerTable.close();
			resultsArchitectTable.close();
			resultsCustomerTable.close();
			
			projectStatement.close();
			projectStatement.close();
			engineerStatement.close();
			architectStatement.close();
			customerstatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		// Return the list of projects to the main menu for further operations
		return projListFromDB;
	}	
	
	/**
	 * Method gets Project and Person values of the given project object and inserts the data into 
	 * their respective <code>project</code>, <code>manager</code>, <code>engineer</code>, 
	 * <code>architect</code> and <code>customer</code> tables.
	 * 
	 * @param poiseProject 	a newly created project object.
	 * @throws SQLException 
	 */
	public static void writeProjectToDatabase (Project poiseProject) throws SQLException {
		
		try {
			// Initiliase connection to the PoisePMS database and statement to execute queries.
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
					"otheruser",
					"swordfish");
			
			Statement projectStatement = connection.createStatement();
			Statement managerStatement = connection.createStatement();
			Statement engineerStatement = connection.createStatement();
			Statement architectStatement = connection.createStatement();
			Statement customerstatement = connection.createStatement();
			
			// Initialise and get the projects values.
			String projNumber = poiseProject.getProjNumber();
			String projName = poiseProject.getProjName();
			String projType = poiseProject.getProjType();
			String projAddress = poiseProject.getProjAddress();
			int projErf = poiseProject.getProjErf();
			double feeTotal = poiseProject.getFeeTotal();
			double feePaid = poiseProject.getFeePaid();
			LocalDate projDeadline = poiseProject.getProjDeadline();
			boolean projFinalised = poiseProject.getProjFinalised();
			// Completion date set to null, as there is a specific method for updating field.
			LocalDate projCompletionDate = null;
			
			// Get managers values.
			String mangFullName = poiseProject.getManager().getFullName();
			String mangContactNumber = poiseProject.getManager().getContactNumber();
			String mangEmail = poiseProject.getManager().getEmail();
			String mangAddress = poiseProject.getManager().getAddress();
			
			// Get engineers values.
			String engFullName = poiseProject.getEngineer().getFullName();
			String engContactNumber = poiseProject.getEngineer().getContactNumber();
			String engEmail = poiseProject.getEngineer().getEmail();
			String engAddress = poiseProject.getEngineer().getAddress();
			
			// Get architects values.
			String archFullName = poiseProject.getArchitect().getFullName();
			String archContactNumber = poiseProject.getArchitect().getContactNumber();
			String archEmail = poiseProject.getArchitect().getEmail();
			String archAddress = poiseProject.getArchitect().getAddress();
			
			// Get customers values.
			String custFullName = poiseProject.getCustomer().getFullName();
			String custContactNumber = poiseProject.getCustomer().getContactNumber();
			String custEmail = poiseProject.getCustomer().getEmail();
			String custAddress = poiseProject.getCustomer().getAddress();
			
			// Update the project values.
			projectStatement.executeUpdate(
					"INSERT INTO projects VALUES ("
							+ "'" + projNumber + "', "
							+ "'" + projName + "', "
							+ "'" + projType + "', "
							+ "'" + projAddress + "', "
							+ projErf + ", "
							+ feeTotal + ", "
							+ feePaid + ", "
							+ "'" + projDeadline + "', " 
							+ projFinalised + ", "	
							+ projCompletionDate + ");");
			
			// Update the managers values.
			managerStatement.executeUpdate(
					"INSERT INTO manager VALUES (" 
							+ "'" + projNumber + "', "
							+ "'" + mangFullName + "', "
							+ "'" + mangContactNumber + "', "
							+ "'" + mangEmail + "', "
							+ "'" + mangAddress + "'" + ");"); 
			
			// Update the engineers values.
			engineerStatement.executeUpdate(
					"INSERT INTO engineer VALUES (" 
							+ "'" + projNumber + "', "
							+ "'" + engFullName + "', "
							+ "'" + engContactNumber + "', "
							+ "'" + engEmail + "', "
							+ "'" + engAddress + "'" + ");");
			
			// Update the architects values.
			architectStatement.executeUpdate(
					"INSERT INTO architect VALUES (" 
							+ "'" + projNumber + "', "
							+ "'" + archFullName + "', "
							+ "'" + archContactNumber + "', "
							+ "'" + archEmail + "', "
							+ "'" + archAddress + "'" + ");");
			
			// Update the customers values.
			customerstatement.executeUpdate(
					"INSERT INTO customer VALUES (" 
							+ "'" + projNumber + "', "
							+ "'" + custFullName + "', "
							+ "'" + custContactNumber + "', "
							+ "'" + custEmail + "', "
							+ "'" + custAddress + "'" + ");");
			
			// Close connection and objects.
			connection.close();
			projectStatement.close();
			projectStatement.close();
			engineerStatement.close();
			architectStatement.close();
			customerstatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
