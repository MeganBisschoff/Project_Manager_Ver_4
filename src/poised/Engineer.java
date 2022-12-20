package poised;

/**
 * Subclass to create an Architect object with information about the person responsible
 * for designing the building project for Poised. 
 * Inherits all attributes and methods from the Person super class. 
 * Architect objects are set as the <code>engineer</code> attribute of the Project object. 
 * 
 * @author Megan Bisschoff 
 * @version 2.0
 * @see Person.java 
 */
public class Engineer extends Person {

	/**
	 * An empty constructor without parameters. 
	 * Architect objects are instantiated before attributes are set to provide a valid 
	 * and unique instance of the Architect object.
	 * 
	 * @return an Architect object of the Person data type.
	 */
	// Empty constructor to provide an instance of a Engineer object.
	public Engineer() {
	}

	/**
	 * Prints all the attributes set for an Engineer. 
	 * 
	 * @return output 	a string of the Engineer full name, contact number, email and address.					
	 */
	@Override
	public String toString() {
		String output = "\n\n--- Engineer's Information ---\n";
		output += "\nFull Name: \t\t" + fullName;
		output += "\nContact Number: \t" + contactNumber;
		output += "\nEmail: \t\t\t" + email;
		output += "\nAddress: \t\t" + address;
		return output;
	}

}
