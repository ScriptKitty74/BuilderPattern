//**********************************************************
//Programmer: Jeff Sherwood
//Homework assignment 6 - 04/03/2019 ~ 300 minutes I honestly quite counting
//
//Description: Reads values from a database and makes JSON 
//Extra Credit: Wanted to populate the database with user input, I may or may not have failed at this miserably.
//Integrity Statement: I pledge that this program represents my own
//unique programming code. I further pledge that this 
//program was created specifically to satisfy the requirements
//specified in the assignment listed above. 
//*********************************************************
package hw6Revised;
import java.sql.ResultSet;
import java.sql.SQLException;
import uca.mis3339.OracleWrapper;
import java.util.ArrayList;
import uca.jetty.Container;
public class Driver {
	public static void main(String[] args) {
		
		Container.startServer(81);
		Container.addHandler(new BookJSON(),"/json");
		Container.addHandler(new AddedBooks(), "/html");
		Container.addHandler(new BookHTML(), "/html2");
	
	} // end main
} // end Driver