package poised;

import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * The main code for the Poised Project Management System that displays various program menu 
 * options and executes operations according to the user's input.
 *
 * @author Megan Bisschoff
 * @version 2.00
 */
public class Main {

	/**
	 * Project objects are read from the PoisePMS database and stored in a list from which further
	 * program operations are performed.
	 * <p>
	 * The main program menu is displayed and a switchblock handles the menu options and executes 
	 * operations on the objects in list selected by the user. Users can choose to capture, search,
	 * view, edit and finalise specific projects from the main program menu and additional submenus. 
	 * Users can edit the details of the manager, engineer, customer or architect of a specific 
	 * project.
	 * 
	 * @param args
	 * @throws SQLException 
	 * @see Project.java
	 * @see Person.java
	 */
	public static void main(String[] args) throws SQLException {
	
		// Open scanner to read user input.
		try (Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH)) {
		 
			// Call method to read database and return a list of projects.
			List<Project> projectList = ReadAndWriteToDatabase.readProjectsFromDatabase();
			
			// Print projects in list.
			System.out.println("------------------ POISE PMS DATABASE ------------------");
			for (Project projObject : projectList) {
					System.out.println(projObject 
							+ "\n\n-------------------------------------------------------" );
			}
			
			// Initiliase variable to hold programs main menu.
			String programMenu = "\n--- Poised Program Menu ---\n"
					+ "\nEnter 1 to capture a new Poise project"
					+ "\nEnter 2 to search and view projects"
					+ "\nEnter 3 to edit and finalise a project"
					+ "\nEnter 4 to edit the manager, engineer, architect or customer details"
					+ "\nEnter 5 to exit the program"
					+ "\nEnter selection: ";
			
			// Initiliase variable to hold sub menu for searching and viewing projects
			String searchAndViewMenu = "\n--- Search and View Projects ---\n"
					+ "\nEnter 1 to search for a project"
					+ "\nEnter 2 to view all projects"
					+ "\nEnter 3 to view incomplete projects"
					+ "\nEnter 4 to view overdue projects"
					+ "\nEnter your selection: ";
			
			// Initiliase variable to hold sub menu for editing projects.
			String editAndFinaliseMenu = "\n--- Edit Project Details ---\n"
					+ "\nEnter 1 to edit the projects total fee"
					+ "\nEnter 2 to edit the projects fee paid"
					+ "\nEnter 3 to edit the projects deadline"
					+ "\nEnter 4 to finalise the project"
					+ "\nEnter your selection: ";
			
			// Initiliase variable to hold sub menu for editing persons.
			String editPersonMenu = "\nWhich persons details would you like to edit?"
					+ "\nEnter 1 to edit the Manager"
					+ "\nEnter 2 to edit the Engineer"
					+ "\nEnter 3 to edit the Architect"
					+ "\nEnter 4 to edit the Customer"
					+ "\nEnter selection: ";
			
			// Display program menu then get and validate user input before running program.
			while (true) {
				
				// Print main program menu and take user input for operation choice.
				System.out.println(programMenu);
				String inputChoice = input.nextLine();
			
				// Check if the inputted choice only has 1 integer only with no letters, 
				// then parse to validate menu choice.
				if (inputChoice.matches("[0-5]") && inputChoice.length() == 1) {
					int menuChoice = Integer.parseInt(inputChoice);
					
					// Switch through each case of the menu and perform selected program operation.
					switch (menuChoice) {
					
						// Capture new Project and Person object information
						case 1 -> {
							// Create and capture new Project object.
							System.out.println("\n--- Capture Project data ---");
							Project newPoiseProject = new Project();
							newPoiseProject.captureProject(newPoiseProject, projectList);
				
							System.out.println("\n--- Capture Manager Details ---");
							Person projectManager = new Manager();
							newPoiseProject.setManager(projectManager);
							projectManager.capturePersonData(projectManager);
							
							System.out.println("\n--- Capture Engineer Details ---");
							Person projectEngineer = new Engineer();
							newPoiseProject.setEngineer(projectEngineer);
							projectEngineer.capturePersonData(projectEngineer);
							
							System.out.println("\n--- Capture Architect Details ---");
							Person projectArchitect = new Architect();
							newPoiseProject.setArchitect(projectArchitect);
							projectArchitect.capturePersonData(projectArchitect);
				
							System.out.println("\n--- Capture Customer Details ---");
							Person projectCustomer = new Customer();
							newPoiseProject.setCustomer(projectCustomer);
							projectCustomer.capturePersonData(projectCustomer);
							
							// Set project name if it was left empty.
							newPoiseProject.createProjectName(newPoiseProject, projectCustomer);
							
							// Display object information captured.
							System.out.println("\n--- Information captured for Project " 
									+ newPoiseProject.getProjNumber() + " ---\n" + newPoiseProject.toString());
							
							// Update project arraylist and write new Poise project to PoisePMS database.
							projectList.add(newPoiseProject);
							ReadAndWriteToDatabase.writeProjectToDatabase(newPoiseProject);
						}
	
						// Search and View Projects.
						case 2 -> {
							// Display sub menu and call corresponding method to execute operation.
							System.out.println(searchAndViewMenu);
							
							if (input.hasNextInt()) {
								int searchOrViewChoice = input.nextInt();
								
								switch (searchOrViewChoice) {
									case 1 -> System.out.println
											(SearchAndViewProjects.searchProj(projectList));
									case 2 -> SearchAndViewProjects.viewAllProj(projectList);
									case 3 -> SearchAndViewProjects.viewIncompleteProj(projectList);
									case 4 -> SearchAndViewProjects.viewOverdueProj(projectList);
									default -> System.out.println("Invalid choice. Please try again");
								}
							} else {
								System.out.println("Please try again by entering number only.");
							}
							input.nextLine();
						}
	
						// Edit and Finalise Project.
						case 3 -> {
							// Call method to get selected project from list to be edited.
							Project selectedProject = SearchAndViewProjects.searchProj(projectList);
							
							// Display sub menu and call corresponding method to execute operation.
							System.out.println(editAndFinaliseMenu);
							
							if (input.hasNextInt()) {
								int editAndFinaliseChoice = input.nextInt();
								
								switch (editAndFinaliseChoice) {
									case 1 -> EditAndFinaliseProject.editAndUpdateProjFeeTotal
										(selectedProject, projectList); 
									case 2 -> EditAndFinaliseProject.editAndUpdateProjFeePaid
										(selectedProject, projectList);
									case 3 -> EditAndFinaliseProject.editAndUpdateProjDeadline
										(selectedProject, projectList);
									case 4 -> { 
										EditAndFinaliseProject.markProjFinalisation
												(selectedProject, projectList);
										EditAndFinaliseProject.generateInvoice
												(selectedProject, projectList);
										projectList.remove(selectedProject);
									}
									default -> System.out.println("Invalid choice. Please try again");
								}
							} else {
								System.out.println("Please try again by entering number only.");
							}
							input.nextLine();
						}
	
						// Edit the Customer, Architects and Contractors details in the database.
						case 4 -> {
							System.out.println("--- Edit Manager, Engineer, Architect "
									+ "and Customer Details ---");
							
							// Call method to get selected project of the person to be edited.
							Project selectedProject = SearchAndViewProjects.searchProj(projectList);
							
							// Display sub menu and call corresponding method to execute operation.
							System.out.println(editPersonMenu);
							
							if (input.hasNextInt()) {
								int editPerson = input.nextInt();
								
								// Call method to edit and set data of the selected projects person.
								// Then, call method to update the persons data in the table.
								switch (editPerson) {
									case 1 -> {
										selectedProject.getManager()
												.editPersonData(selectedProject.getManager());
										selectedProject.getManager()
											.updateManagerInDB(selectedProject);
									}
									case 2 -> {
										selectedProject.getEngineer()
												.editPersonData(selectedProject.getEngineer());
										selectedProject.getEngineer()
											.updateEngineerInDB(selectedProject);
									}
									case 3 -> {
										selectedProject.getArchitect()
												.editPersonData(selectedProject.getArchitect());
										selectedProject.getArchitect()
											.updateArchitectInDB(selectedProject);
									}
									case 4 -> {
										selectedProject.getCustomer()
												.editPersonData(selectedProject.getCustomer());
										selectedProject.getCustomer()
											.updateCustomerInDB(selectedProject);
									}
									default -> 
										System.out.println("Invalid choice. Please try again.");
								}
							} else {
								System.out.println("Please try again by entering number only.");
							}
							input.nextLine();
						}
						// Exit program
						case 5 -> {
							System.out.println("\n--- Thank you for using the Poise project manager ---"); 
							System.exit(0);
						}
						// Default case set to throw exception and notify user of invalid input.
						default -> {
							System.out.println("Invalid menu choice, please try again.");
							throw new IllegalArgumentException();
						}
					} 
				}
				// Else if menu input choice contains letters and no numbers, notify of invalid input.
				else if (inputChoice.matches("[6-9]") 
						|| inputChoice.length() > 1
						|| inputChoice.matches("[a-zA-Z]+")) {
					System.out.println("Invalid entry, ensure choice contains numbers 1-5 only.");
				}
			} 
		}
	}
}

