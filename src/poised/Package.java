/**
 * The <code>poise</code> package contains the source code of the project management program 
 * created for Poised structural engineering firm.
 * <p>
 * Details about a project and its customer, manager, engineer and architect can be created, viewed
 * and edited. Information on current projects and new projects are read from and added to the
 * <code>PoisePMS</code> database.
 * <p>
 * The <code>Person</code> is the abstract parent class of the <code>Customer</code>, 
 * <code>Manager</code>, <code>Engineer</code> and <code>Architect</code> subclasses. 
 * <p>
 * <code>Project</code> is the parent class of the abstract <code>EditAndFinaliseProject</code> 
 * class.
 * <p>
 * The abstract classes <code>ReadAndWriteToDatabase</code> and <code>SearchAndViewProjects</code> 
 * are dependent on object information from the <code>Project</code> and <code>Person</code> classes.
 * <p>
 * The program is run from <code>Mainu</code> where the user is displayed various program operations
 * from the main menu and it's sub menus.
 * Main menu operations:<br>
 * <li> (1) capture a new Poise project
 * <li> (2) search and view projects
 * <li> (3) edit and finalise a project
 * <li> (4) edit the contractors, architects and customers details
 * <li> (5) exit the program 
 * </ul>
 * Sub menu operations from option (2):<br>
 * <li> search for a project
 * <li> view all projects
 * <li> view incomplete projects
 * <li> view overdue projects
 * </ul>
 * Sub menu operations from option (3):<br>
 * <li> edit the projects total fee
 * <li> edit the projects fee paid
 * <li> edit the projects deadline
 * <li> finalise the project
 * 
 * @author Megan Bisschoff
 * @version 2.00, 12 September 2022
 * @see Project.java
 * @see EditAndFinaliseProjects.java
 * @see Person.java
 * @see Manager.java
 * @see Engineer.java
 * @see Architect.java
 * @see Customer.java
 * @see ReadAndWriteToDatabse.java
 * @see SearchAndViewProjects.java
 
 */
package poised;


