import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {
	/* This will be the main menu.  It will welcome
	the user to the inventory program and they can choose the options or decide
	to quit the program.
	*/
	
	//Welcome message
public static void main(String[] args) throws SQLException {
	System.out.println("HELLO. WElCOME TO Salas Tequila !!");
	System.out.println("PLease Select one of the following.");
	
//Set variable for user choice
int choice = '0';

//New Scanner for user input
	Scanner user = new Scanner(System.in);

do{
	//Menu
	System.out.println("1. Enter 1 to view all of current inventory.");
	System.out.println("2. Enter 2 to add new item(s) to inventory");
	System.out.println("3. Enter 3 to update what is in stock.");
	System.out.println("4. Enter 4 to generate a report.");
	System.out.println("5. Enter 5 to search for a specific item.");
	System.out.println("6. Enter 6 to contact customer support.");
	System.out.println("7. quit.");
	
	//declares variable for user's choice
	choice = user.nextInt();
	
	//Switch statements for each option
	switch(choice) {
	case 1:
		
		Inventory I = new Inventory();
		I.viewInventory();
		
	
		break;
	case 2:
		System.out.println("Adding….");
		break;
	case 3:
		System.out.println("Updating….");
		break;
	case 4:
		System.out.println("Generating….");
		break;
	case 5:
		System.out.println("Searching….");
			try {
			Log myLog= new Log("mylog.txt");
			
			myLog.logger.setLevel(Level.INFO);
			myLog.logger.warning("No information found");
		} catch (Exception e) {} 
		break;
	case 6:
		System.out.println("Customer Service");
		break;
	case 7:
		System.out.println("Good Bye!!");
		break;
	default:
		System.out.println("Invalid Choice.  Please try again.");
		try {
			Log myLog= new Log("mylog.txt");
			
			myLog.logger.setLevel(Level.INFO);
			myLog.logger.warning("Wrong Choices");
		} catch (Exception e) {}
		
	}
	
		} while( choice != 7);
//closes scanner
user.close();
}

}
