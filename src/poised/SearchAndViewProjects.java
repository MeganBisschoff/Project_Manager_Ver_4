package poised;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/** 
 * Abstract class that allows a user to search and view the details of a Project object, as well 
 * as view all projects, view overdue projects and view incomplete projects retrieved from the 
 * list of projects.
 * 
 * @author Megan Bisschoff
 * @Version 2.00
 * @see Main.java 
 */
public abstract class SearchAndViewProjects { 

	// Open scanner object to read user input.
	static Scanner input = new Scanner(System.in); 
	
	// Constant to format any dates.
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	
	// Empty constructor. 
	private SearchAndViewProjects() {
	}
	
	/**
	 * Method searches for a specified project in the project list retrieved from the DB.
	 * Input is requested until the user inputs a valid <code>searchNameOrNumber</code> that  
	 * matches either the project name or project number, then returns the project information 
	 * along with its Manager, Engineer, Architect and Customer attributes.
	 * 
	 * @param projectList	a list of the current projects.
	 * @return	string details of the selected project objects fields..
	 */
	// ----- Method to SEARCH PROJECTS ----- // 
	public static Project searchProj(List<Project> projectList) {
		
		Project selectedProject = null;
		
		while (selectedProject == null) {
			
			// Get user to input search name or number.
			System.out.println("\nEnter the project name or number to view/edit: ");
			String searchNameOrNumber = input.nextLine();
			
			for (Project projObject : projectList) {
			
				// Check if search term matches the project name or number and display project info.
				if (searchNameOrNumber.equalsIgnoreCase(projObject.getProjName()) 
						|| searchNameOrNumber.equalsIgnoreCase(projObject.getProjNumber())) {
					selectedProject = projObject;
					break;
				}
			} 
		}
		return selectedProject;
	}
	
	/**
	 * Method displays each project object in the project list. It prints its <code>toString</code> 
	 * information along with its  Manager, Engineer, Architect and Customer attributes.
	 * 
	 * @param projectList	a list of the current projects.
	 */
	// ----- Method to VIEW ALL PROJECTS ----- //
	public static void viewAllProj(List<Project> projectList) {
		
		// Iterate through collection and print each object.
		projectList.forEach(System.out::println);
	}
	
	/**
	 * Method displays all project objects in the project list that are incomplete. 
	 * It gets each projects finalisation status <code>projFinalised</code> and if "false"
	 * it prints out its <code>toString</code> information along with Manager, Engineer, Architect 
	 * and Customer attributes.
	 * 
	 * @param projectList	a list of the current projects.
	 */
	// ----- Method to VIEW INCOMPLETE PROJECTS ----- //
	public static void viewIncompleteProj(List<Project> projectList) {
	
		for (Project projObject : projectList) {
			// Check if project is not finalised and display the project information.
			if (!projObject.getProjFinalised()) { 
				System.out.println("\n--- Incomplete Poised Projects ---\n" + projObject);
			}
		}
	}
	
	/**
	 * Method displays all project objects in the project list that are past the set deadline. 
	 * It checks each projects finalisation status <code>projFinalised</code> and whether the  
	 * deadline <code>projDeadline</code> is before the current date.
	 * It prints out its <code>toString</code> information along with its Manager, Customer, 
	 * Engineer and Architect attributes.
	 * 
	 * @param projectList	a list of the current projects.
	 */
	// ----- Method to VIEW OVERDUE PROJECTS ----- //
	public static void viewOverdueProj(List<Project> projectList) {

		for (Project projObject : projectList) {
			// Check if project is not finalised and deadline occurs before currentDate. 
			if (projObject.getProjDeadline().isBefore(LocalDate.now()) 
					&& !projObject.getProjFinalised()) {  
				System.out.println("\n--- Overdue Poised Projects ---\n" + projObject);
			} 
		}
	}
	
}
