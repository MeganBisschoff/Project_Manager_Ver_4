package poised;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/** 
 * Subclass that edits and sets new information attributed to a Project object from the 
 * <code>projectList</code> selected by the user. 
 * <p>
 * It inherits attributes from the Project parent class to edit the projects total
 * fee <code>editProjFeeTotal</code>, the fee paid <code>editProjFeePaid</code>, the deadline 
 * <code>Project</code>, as well as finalises the project <code>markProjFinalisation</code> and 
 * generates an invoice <code>generateInvoice</code>.
 * 
 * @author Megan Bisschoff
 * @Version 1.00
 * @see Project.java
 */
public abstract class EditAndFinaliseProject extends Project {
		
	// Open scanner object to read user input
	static Scanner userInput  = new Scanner(System.in); 
	
	// Empty constructor.
	private EditAndFinaliseProject() {
	}
	
	/**
	 * Captures, validates and sets the <code>projFeeTotal</code> of the 
	 * <code>selectedProject</code> object pulled from the <code>projectList</code>.
	 * <p>
	 * Input is requested until the user input is validated with a regex pattern and parsed to a 
	 * double type. Once validated and set, the project record that matches the project number in 
	 * the database is updated with the new fee total.
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// ----- Method to EDIT FEE TOTAL ----- //
	public static void editAndUpdateProjFeeTotal(Project selectedProject, List<Project> projectList) 
			throws SQLException { 
		
		while (true) {
			System.out.println("\nEnter the project fee paid: ");
			String inputFeeTotal = userInput.nextLine();
		
			// Validate that input matches any number of digits then parse to a double type. 
			if (inputFeeTotal.matches("\\d+")) {
				Double newFeeTotal = Double.parseDouble(inputFeeTotal);
				selectedProject.setFeeTotal(newFeeTotal);
				System.out.println("\nFee paid successfully updated." + selectedProject);
				break;
			}
			else {
				System.out.println("Invalid fee, enter digits only with no spaces, dots or commas");
			}
		}
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try	(Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
					"otheruser",
					"swordfish");
			Statement updateStatement = connection.createStatement()) {
					
			// Get the new fee total and execute update in the database.
			Double projFeeTotal = selectedProject.getFeeTotal();
			updateStatement.executeUpdate(
					"UPDATE projects SET feeTotal = " + projFeeTotal 
					+ " WHERE projNumber = " + selectedProject.getProjNumber());
			
			System.out.println(projFeeTotal + "successfully updated in database.");	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Captures, validates and sets the <code>projFeePaid</code> of the 
	 * <code>selectedProject</code> object pulled from the <code>projectList</code>.
	 * <p>
	 * Input is requested until the user input is validated with a regex pattern and parsed to a 
	 * double type. Once validated and set, the project record that matches the project number in 
	 * the database is updated with the new paid amount.
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// ----- Method to EDIT FEE PAID ----- //
	public static void editAndUpdateProjFeePaid(Project selectedProject, List<Project> projectList) 
			throws SQLException { 
							
		while (true) {
			System.out.println("\nEnter the project fee paid: ");
			String inputFeePaid = userInput.nextLine();
		
			// Validate that input matches any number of digits then parse to a double type. 
			if (inputFeePaid.matches("\\d+")) {
				Double newFeePaid = Double.parseDouble(inputFeePaid);
				selectedProject.setFeePaid(newFeePaid);
				System.out.println("\nFee paid successfully updated." + selectedProject);
				break;
			}
			else {
				System.out.println("Invalid fee, enter digits only with no spaces, "
						+ "dots or commas");
			}
		}
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try	(Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
					"otheruser",
					"swordfish");
			Statement updateStatement = connection.createStatement()) {
					
			// Get the new fee paid and execute update in the database.
			Double projFeePaid = selectedProject.getFeePaid();
			updateStatement.executeUpdate(
					"UPDATE projects SET feePaid = " + projFeePaid 
					+ " WHERE projNumber = " + selectedProject.getProjNumber());
			
			System.out.println(projFeePaid + "successfully updated in database.");	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Captures, validates and sets the <code>projDeadline</code> of the 
	 * <code>selectedProject</code> object pulled from the <code>projectList</code>.
	 * <p>
	 * Input is requested until the user input is validated by being parsed to a 
	 * <code>LocalDate</code> type and formatted with the <code>DateTimeFormatter</code> class.
	 * Once validated and set, the project record that matches the project number in the database 
	 * is updated with the new deadline.
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */	
	// ----- Method to EDIT PROJECT DEADLINE ----- //
	public static void editAndUpdateProjDeadline(Project selectedProject, List<Project> projectList) 
			throws SQLException {
	
		// Set pattern for date format(carried forward from Project.)  
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		// Get  user input until validated.
		while (true) {
			System.out.println("Enter projects deadline as YYYY-MM-DD: ");
			String strDate = userInput.nextLine();
			
			// Check that date is the correct length of chars and contains "-" symbol.
			if (strDate.length() == 10 && strDate.contains("-")) {
				try {
					// Parse and format user input then set completion date.
					LocalDate formatStrDate = LocalDate.parse(strDate, formatDate);
					selectedProject.setProjDeadline(formatStrDate);
					break;
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(strDate + " is not a valid date, please try again.");
			}
		} 
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try	(Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
					"otheruser",
					"swordfish");
			Statement updateStatement = connection.createStatement()) {
			
			// Get the new deadline and execute update in the database.
			LocalDate projDeadline = selectedProject.getProjDeadline();
			updateStatement.executeUpdate(
					"UPDATE projects SET projDeadline = '" + projDeadline 
					+ "' WHERE projNumber = " + selectedProject.getProjNumber());
			
			System.out.println("Project " + selectedProject.getProjNumber() + " deadline "
					+ "successfully updated in Poise database.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Captures, validates and sets the <code>projCompletionDate</code> and the 
	 * <code>projFinalised</code> status to true of the <code>selectedProject</code> object 
	 * pulled from the <code>projectList</code>. 
	 * <p>
	 * Input for the completion date is requested until the user input is validated and parsed to a 
	 * <code>LocalDate</code> type and formatted with the <code>DateTimeFormatter</code> class. 
	 * Once validated and set, the project record that matches the project number in the database 
	 * is updated with the completion date and and finalisation status. 
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// ----- Method to MARK PROJECT FINALISED ----- //
	public static void markProjFinalisation(Project selectedProject, List<Project> projectList) 
			throws SQLException {

		// Set pattern for date format(carried forward from Project.)  
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		while (true) {
			System.out.println("\nEnter project completion date as YYYY-MM-DD: ");
			String strDate = userInput.nextLine();
			
			if (strDate.length() == 10 && strDate.contains("-")) {
				try {
					// Parse and format user input then set completion date.				
					LocalDate formatStrDate = LocalDate.parse(strDate, formatDate);
					selectedProject.setProjCompletionDate(formatStrDate);
					
					// Set project as finalised.
					selectedProject.setProjFinalised(true);
					break;
				} 
				catch (Exception e) {
					System.out.println(strDate + " is not a valid date");
				}
			}
		}
		
		// Initiliase connection to the PoisePMS database and statement to execute queries.
		try	(Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS_db?useSSL=false",
					"otheruser",
					"swordfish");
			Statement updateStatement = connection.createStatement()) {
			
			// Get the new completion date and execute update in the database.
			LocalDate projCompletionDate = selectedProject.getProjCompletionDate();
			updateStatement.executeUpdate(
					"UPDATE projects SET projCompletionDate = '" + projCompletionDate 
					+ "' WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			
			// Update project finalisation status as 'true' in the database.
			updateStatement.executeUpdate(
					"UPDATE projects SET projFinalised = true"
					+ " WHERE projNumber = '" + selectedProject.getProjNumber() + "'");
			
			System.out.println("\nProject " + selectedProject.getProjNumber() + " completion date "
				+ "and finalisation status successfully updated in Poise database.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Generates an invoice of the <code>selectedProject</code> object pulled from the 
	 * <code>projectList</code>. 
	 * 
	 * The outstanding fee is calculated by subtracting the projects <code>feeTotal</code> 
	 * from the <code>feePaid</code>. Once calculated, a string of the customers details from
	 * the Customer subclasses <code>toString</code> as well as the Projects <code>toString</code> 
	 * method is printed out with oustanding project fees.
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// Method to generate an invoice once the project is finalised
	public static void generateInvoice(Project selectedProject, List<Project> projectList) {

		// Calculate outstanding project fee.
		double outstandingFee = selectedProject.getFeeTotal() - selectedProject.getFeePaid();
		
		// If there is an oustanding fee, print an invoice with the projects balance.
		if (outstandingFee > 0) {
			System.out.println("\n------------------ INVOICE ------------------" 
					+ selectedProject.getCustomer()
					+ "\n\n--- Project Fees ---" 
					+ "\nTotal fee: \t\tR" + selectedProject.getFeeTotal()
					+ "\nTotal paid: \t\tR" + selectedProject.getFeePaid()
					+ "\nOutstanding balance: \tR " + outstandingFee);
		}
	}

}