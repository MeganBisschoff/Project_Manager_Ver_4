package poised;

/**
 * Subclass to create a Manager object with information about the person responsible 
 * for managing the building projects for Poised. 
 * Inherits all attributes and methods from the Person super class. 
 * Contractor objects are set as the <code>manager</code> attribute of the Project object. 
 * 
 * @author Megan Bisschoff 
 * @version 2.0
 * @see Person.java 
 */
public class Manager extends Person {

	/**
	 * An empty constructor without parameters. 
	 * Manager objects are instantiated before attributes are set to provide a valid 
	 * and unique instance of the Contractor object.
	 * 
	 * @return a Manager object of the Person data type.
	 */
	// Empty constructor to provide an instance of a Manager object.
	public Manager() {
	}
	
	/**
	 * Prints all the attributes set for a Manager. 
	 * 
	 * @return output 	a string of the Manager's full name, contact number, email and address.					
	 */
	@Override
	public String toString() {
		String output = "\n\n--- Manager's Information ---\n";
		output += "\nFull Name: \t\t" + fullName;
		output += "\nContact Number: \t" + contactNumber;
		output += "\nEmail: \t\t\t" + email;
		output += "\nAddress: \t\t" + address;
		return output;
	}

}
