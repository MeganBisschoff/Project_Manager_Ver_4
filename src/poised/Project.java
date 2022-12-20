package poised;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/** 
 * Parent class that captures and edits the information to create an instance of a Project. 
 * It is inherited by the <code>EditAndFinaliseProject</code> subclass which contains the methods
 * associated with editing a field of a Project object and finalising the project.
 * Project attributes include an object of each of the Manager, Engineer, Architect and Customer 
 * subclasses. 
 * 
 * @author Megan Bisschoff
 * @Version 2.00
 * @see EditAndFinaliseProject.java 
 * @see SearchAndViewProjects.java 
 * @see ReadAndWriteToDatabase.java 
 */
public class Project { 
	
	// Open scanner object to read user input.
	Scanner input = new Scanner(System.in);
	
	// Constant to format any dates.
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
		
	// Attributes for the Project initialised for validating fields when capturing project data.
	private String projNumber = "none";
	private String projName = "none";
	private String projType = "none";
	private String projAddress = "none";
	private int projErf = 0;
	private double feeTotal = 0;
	private double feePaid = 0;
	private LocalDate projDeadline = null;	
	private boolean projFinalised;	
	private LocalDate projCompletionDate = null;
		
	// Attributes for the Person class, excluded from constructor
	private Person customer;
	private Person architect;
	private Person engineer;
	private Person manager;
	
	// Empty Project constructor to create valid instance of the Project object.
	public Project() {
	}
	
	// ----- Getters & Setters for the PROJECT ----- //
	/**
	 * The 'getter' and 'setter' methods gets and sets the values of the <code>projNumber</code>,
	 * <code>projName</code>, <code>projType</code>, <code>projAddress</code>, <code>projErf</code>,
	 * <code>feeTotal</code>, <code>feePaid</code>, <code>projDeadline</code>, 
	 * <code>projFinalised</code> and <code>projCompletionDate</code> fields for the Project objects.
	 * 
	 * @return the projects number, name, building type, address, ERF number, fee total, fee paid,
	 * deadline, finalisation status and completion date.
	 */
	public String getProjNumber() {
		return projNumber;
	}
	
	public void setProjNumber(String newProjNumber) {
		this.projNumber = newProjNumber;
	}
	
	public String getProjName() {
		return projName;
	}
	
	public void setProjName(String newProjName) {
		this.projName = newProjName;
	}
	
	public String getProjType() {
		return projType;
	}
	
	public void setProjType(String newProjType) {
		this.projType = newProjType;
	}
	
	public String getProjAddress() {
		return projAddress;
	}
	
	public void setProjAddress(String newProjAddress) {
		this.projAddress = newProjAddress;
	}
	
	public int getProjErf() {
		return projErf;
	}
	
	public void setProjErf(int newProjErf) {
		this.projErf = newProjErf;
	}
	
	public double getFeeTotal() {
		return feeTotal;
	}
	
	public void setFeeTotal(double newfeeTotal) {
		this.feeTotal = newfeeTotal;
	}
	
	public double getFeePaid() {
		return feePaid;
	}
	
	public void setFeePaid(double newfeePaid) {
		this.feePaid = newfeePaid;
	}
	
	public LocalDate getProjDeadline() {
		return projDeadline;
	}
	
	public void setProjDeadline(LocalDate newProjDeadline) {
		this.projDeadline = newProjDeadline;
	}
	
	public boolean getProjFinalised() {	
		return projFinalised;
	}
	
	public void setProjFinalised (boolean newProjFinalised) {			
		this.projFinalised = newProjFinalised;	
	}
	
	public LocalDate getProjCompletionDate() {	 
		return projCompletionDate;
	}
	
	public void setProjCompletionDate(LocalDate newProjCompletionDate) {	
		this.projCompletionDate = newProjCompletionDate;
	}
	
	// ----- Getters & Setters for the PERSON's of the project ----- //
	/**
	 * The 'getter' and 'setter' methods gets and sets the values of the <code>customer</code>,
	 * <code>architect</code>, <code>engineer</code> and <code>engineer</code> fields for the 
	 * {@link Architect}, {@link Customer}, {@link Engineer} and {@link Manager} subclass objects.
	 * 
	 * @return the full name, contact number, email and address for each subclass object.
	 */
	public Person getCustomer() {
		return customer;
	}

	public void setCustomer(Person customer) {
		this.customer = customer;
	}
	
	public Person getArchitect() {
		return architect;
	}

	public void setArchitect(Person architect) {
		this.architect = architect;
	}
			
	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}
	
	public Person getEngineer() {
		return engineer;
	}

	public void setEngineer(Person engineer) {
		this.engineer = engineer;
	}
	
	/**
	 * Prints string of each <code>Project</code> attribute. Method includes the Manager, Engineer,
	 * Architect and Customer subclasses <code>toString</code> method.
	 * 
	 * @return output 	A formatted string of the projects's number, name, type, address, ERF
	 * 					number, fee total, fee paid, deadline and completion date together with
	 * 					the customer, architect and contractors details about the project.					
	 */
	@Override
	public String toString() {
		String output = "\n\n--- Project Information ---\n";
		output += "\nProject Number: \t" + projNumber;
		output += "\nProject Name: \t\t" + projName;
		output += "\nBuilding Type: \t\t" + projType;
		output += "\nPhysical  Address: \t" + projAddress;
		output += "\nERF Number: \t\t" + projErf;
		output += "\nTotal Fee: \t\tR " + feeTotal;
		output += "\nTotal Paid: \t\tR " + feePaid;
		output += "\nDeadline: \t\t" + projDeadline;
		output += "\nFinalised: \t\t" + projFinalised;
		output += "\nCompletion Date: \t" + projCompletionDate;
		output += manager.toString();
		output += engineer.toString();
		output += architect.toString();
		output += customer.toString();

		return output;
	}
	
	/**
	 * Captures, validates and sets information to create a new Project object.
	 * Prompts the user for input and sets the project number <code>setProjNumber</code>, 
	 * building type <code>setProjType</code>, project address <code>setProjAddress</code>, 
	 * ERF number <code>setProjErf</code>, total fee <code>setFeeTotal</code>, fee paid 
	 * <code>setFeePaid</code>, and project deadline <code>setProjDeadline</code>.
	 * <p>  
	 * Input is requested until the user input is validated for each object field not being equal
	 * to "none", 0 or null as set in the objects attributes.
	 * 
	 * @param newPoiseProject	a new project object created by the empty constructor.
	 * @throws SQLException 
	 */
	// ----- Method to CAPTURE PROJECT information ----- //
	public void captureProject(Project newPoiseProject, List<Project> projectList) {
				
		// --- Capture, validate and set the project NUMBER --- 
		while (newPoiseProject.getProjNumber().equals("none")) { 			
			System.out.println("\nEnter the project number: ");
			String inputProjNumber = input.nextLine();
			
			// Check if projNumber already exists in the list pulled from the DB.
			boolean projNumberExists = false;
			for (Project poiseProject : projectList) {
				if (inputProjNumber.equals(poiseProject.getProjNumber())) {
					projNumberExists = true;
				}
			}
			// Validate that the input is not empty.
			if (inputProjNumber.isBlank()) {
				System.out.println("Invalid entry, enter project number without spaces or letters.");
			} 
			// Validate that the project number does not already exist.
			else if (projNumberExists) {
				System.out.println("Project number already exists. Input a unique number.");
			}
			// Validate that the input contains characters or numbers, then set project number.
			else if (inputProjNumber.matches("[0-9]+") || inputProjNumber.matches("[a-zA-Z]+")){
				newPoiseProject.setProjNumber(inputProjNumber);
				System.out.println("Project number captured as " + newPoiseProject.getProjNumber());
				break;
			}
		}
		
		// --- Capture, validate and set the project building TYPE --- 
		while (newPoiseProject.getProjType().equals("none")) { 
			System.out.println("\nEnter the building type (house, apartment, etc.): ");
			String inputProjType = input.nextLine();
			
			// Validate that the input is not empty and that it has letters in the word.
			if (inputProjType.isBlank() || inputProjType.matches("[^a-zA-Z]+")) {
				System.out.println("Invalid building type, please try again."); 
			} else {
				newPoiseProject.setProjType(inputProjType);
				System.out.println("Building type captured as " + newPoiseProject.getProjType());
				break;
			}
		}	
		
		// --- Capture and set the project NAME ---
		// No validation check required as project name is created afterwards if empty.
		System.out.println("\nEnter the projects name: ");	
		String inputProjName = input.nextLine() ;
		newPoiseProject.setProjName(inputProjName);		
		
		// --- Capture, validate and set the projects physical ADDRESS --- 
		while (newPoiseProject.getProjAddress().equals("none")) { 
			System.out.println("\nEnter projects physical address: ");
			String inputProjAddress = input.nextLine();
			
			// Validate that the input is not blank and that it has letters in the word.
			if (inputProjAddress.isBlank() || inputProjAddress.matches("[^a-zA-Z]+")) {
				System.out.println("Invalid address entry, please try again."); 
			} else {
				newPoiseProject.setProjAddress(inputProjAddress);
				System.out.println("Project address captured as " + newPoiseProject.getProjAddress());
				break;
			}
		}
		
		// --- Capture, validate and set the project ERF number  --- 
		while (newPoiseProject.getProjErf() == 0) { 
			System.out.println("\nEnter projects ERF number: ");
			
			// Validate that the input has an integer present as the ERF number.
			if (input.hasNextInt()) {
				int inputProjErf = input.nextInt();
				newPoiseProject.setProjErf(inputProjErf);
				System.out.println("ERF number captured as " + newPoiseProject.getProjErf());
				break;
			} else {
				System.out.println("Invalid entry, enter ERF number without spaces or letters."); 
				input.nextLine();
			}
		}
		input.nextLine();

		// --- Capture, validate and set the projects FEE TOTAL --- 
		while (newPoiseProject.getFeeTotal() == 0) { 
			System.out.println("\nEnter projects total fee: R");
			
			// Validate that the input contains a double data type and set the fee paid.
			if (input.hasNextDouble()) {
				Double inputFeeTotal = input.nextDouble();
				newPoiseProject.setFeeTotal(inputFeeTotal);
				System.out.println("Fee total captured as " + newPoiseProject.getFeeTotal());
				break;
			} else {
				System.out.println("Invalid fee, enter digits only with no spaces, dots or commas"); 
			}
		}
		
		// --- Capture, validate and set the projects FEE PAID --- 
		while (newPoiseProject.getFeePaid() >= 0) { 
			System.out.println("\nEnter projects fee paid: R");
			
			// Validate that the input contains a double data type and set the fee paid.
			if (input.hasNextDouble()) {
				Double inputFeePaid = input.nextDouble();
				newPoiseProject.setFeePaid(inputFeePaid);
				System.out.println("Fee paid captured as " + newPoiseProject.getFeePaid());
				break;
			} else {
				System.out.println("Invalid fee, enter digits only with no spaces, dots or commas"); 
			}
		}
		input.nextLine();
		
		// --- Capture, validate and set the projects DEADLINE --- 
		// Set pattern for date format.  
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
				
		while (newPoiseProject.getProjDeadline() == null) {
			System.out.println("\nEnter projects deadline as YYYY-MM-DD: ");
			String strDate = input.nextLine();
			
			// Check that date is the correct length of chars and contains "-" symbol.
			if (strDate.length() == 10 && strDate.contains("-")) {
				try {
					// Validate by trying to parse string date to date format, then set the deadline.
					LocalDate formatStrDate = LocalDate.parse(strDate, formatDate);
					newPoiseProject.setProjDeadline(formatStrDate);
					System.out.println("Deadline captured as " + newPoiseProject.getProjDeadline());
					break;
				} catch (Exception e) { 
					e.printStackTrace();
				}
			} else {
				System.out.println(strDate + " is not a valid date, please try again.");
			}
		}
		
		// --- Set the projects FINALISATION STATUS and COMPLETION DATE --- 
		// As they are newly captured projects, the values are set to false an null respectively.
		newPoiseProject.setProjFinalised(false);
		newPoiseProject.setProjCompletionDate(null);
	}
	
	/**
	 * Creates and sets a project name <code>projName</code> if the object feild is empty or 
	 * equals to the "none" value initialised in the objects attributes.
	 * It combines the property type with the customer last name, retreived from the second 
	 * word/index of the customers fullname. 
	 * 
	 * @param newPoiseProject   Project object
	 * @param projectCustomer	Customer object
	 */
	// ----- Method to create PROJECT NAME ----- //
	public void createProjectName(Project newPoiseProject, Person projectCustomer) {
		
		// Set a new project name with the building type + customer lastname if one doesn't exist.
		if (newPoiseProject.getProjName().equals("none") || newPoiseProject.getProjName().isEmpty()) {
			String alternativeProjName = newPoiseProject.getProjType() 
					+ " " + projectCustomer.getFullName().split(" ")[1];
			newPoiseProject.setProjName(alternativeProjName);
			System.out.println("\nProject name set as " + alternativeProjName);
		}
	}

}

