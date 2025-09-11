import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Inventory{
 
		
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement ps = null;
		private ResultSet rs = null;
		
		
		public void viewInventory() throws SQLException  {
			
			System.out.println("Viewing...");
			String I = "";
			try {
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/salas_tequila", "root", "root");
			statement = connect.createStatement();
			rs = statement.executeQuery("Select * From current_inventory");
			
			while (rs.next()) {
			int productId = rs.getInt("product_id");
			String name = rs.getString("product_name");
			String product_Description = rs.getString("product_description");
			Double prize = rs.getDouble("prize");
			Double size_ML = rs.getDouble("size_ML");
			int SKU = rs.getInt("SKU");
			int quantity = rs.getInt("Quantity");
			I = I + "Prodcut Id "+ productId + "Prodcut Name " + name + "Product Description" + product_Description + "Prize" + prize + "Size(mL)" + size_ML + "SKU" + SKU + "Quantity" + quantity + '\n';
			System.out.println("--------------------------");
			
			System.out.println("Product ID: " + productId + ", Product Name: " + name + ", Prodcut Description: " + product_Description + ", Prize: " + prize + ", Size(mL): " + size_ML + ", SKU: " + SKU + ", Quantity: " + quantity);} 
			System.out.println("--------------------------");
			
			
			} catch (InputMismatchException e) {
		        System.out.println("Error: Invalid input type. Please enter text.");}
		
		}
		
		public void addItem() throws SQLException{
			String A = "";
			Scanner scanner = new Scanner(System.in);
			System.out.println("Product ID: ");
			int ID = scanner.nextInt();
			System.out.println("Product Name: ");
			String pName = scanner.next();
			System.out.println("Product Description: ");
			String description = scanner.next();
			System.out.println("Prize: ");
			Double prize = scanner.nextDouble();
			System.out.println("Size: ");
			Double size = scanner.nextDouble();
			System.out.println("SKU: ");
			int SKU = scanner.nextInt();
			System.out.println("Quantity: ");
			int quantity = scanner.nextInt();
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/salas_tequila", "root", "root");
			statement = connect.createStatement();
			
			String query = ("Insert Into current_inventory(product_id, product_name, product_description, prize, size_ML, SKU, Quantity) Values(?, ?, ?, ?, ?, ?, ?);");
			
			PreparedStatement ps = connect.prepareStatement(query);
			
			ps.setInt(1,  ID);
			ps.setString(2, pName);
			ps.setString(3, description);
			ps.setDouble(4, prize);
			ps.setDouble(5, size);
			ps.setInt(6, SKU);
			ps.setInt(7, quantity);
			ps.addBatch();
			ps.executeBatch();
			A = A +  "Prodcut Id "+ ID + "Product Name " + pName + "Product Description" + description + "Prize" + prize + "Size(mL)" + size + "SKU" + SKU + "Quantity" + quantity + '\n';
			System.out.println("--------------------------");
			System.out.println("Product ID: " + ID + ", Product Name: " + pName + ", Product Description: " + description + ", Prize: " + prize + ", Size(mL): " + size + ", SKU: " + SKU + ", Quantity: " + quantity);
			System.out.println("--------------------------");
		}
			
			

			public String update() throws SQLException {
				String U = "";
				
				
				
				System.out.println("What Product ID do you want to update?");
				Scanner scanner = new Scanner(System.in);
				int newId = scanner.nextInt();
				System.out.println("Updating Product ID # " + newId + "....");
				System.out.println("Update to what quantity?");
				int quantity = scanner.nextInt();
				
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/salas_tequila", "root", "root");
				//statement = connect.createStatement();

			     Statement stmt = connect.createStatement();
			        
		            // Updating database
		            String q1 = "UPDATE current_inventory set Quantity = '" + quantity + 
		                    "' WHERE product_id = '" +newId+ "'";
		            int x = stmt.executeUpdate(q1);
		            
		            if (x > 0)            
		                System.out.println("Successfully Updated");            
		            else            
		                System.out.println("ERROR OCCURRED :(");
		            rs = statement.executeQuery("Select * From current_inventory");
		            
			
				while (rs.next()) {
					int productId = rs.getInt("product_id");
					String name = rs.getString("product_name");
					String product_Description = rs.getString("product_description");
					Double prize = rs.getDouble("prize");
					Double size_ML = rs.getDouble("size_ML");
					int SKU = rs.getInt("SKU");
					int Quantity = rs.getInt("Quantity");
					
					
				U = U + "Prodcut Id "+ productId + "Prodcut Name " + name + "Product Description" + product_Description + "Prize" + prize + "Size(mL)" + size_ML + "SKU" + SKU + " Quantity" + quantity + '\n';
				System.out.println("--------------------------");
				System.out.println("Product ID: " + productId + ", Product Name: " + name + ", Prodcut Description: " + product_Description + ", Prize: " + prize + ", Size(mL): " + size_ML + ", SKU: " + SKU + ", Quantity: " + quantity + 'n');}
				System.out.println("--------------------------");
				//rs = statement.executeQuery ("UPDATE current_inventory SET Quantity = '" + quantity + "' WHERE product_id = '" + newId + "'");
				
				return U;
			}
			
		public void totalReport() throws SQLException {
			
			System.out.println("Printing Total Cash Value of Inventory....");
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/salas_tequila", "root", "root");
			statement = connect.createStatement();
			
			String sp = "SELECT SUM(prize) AS Sum1, SUM(Quantity) AS Sum2 FROM current_inventory";
			
			double sum1 = 0;
			double sum2 = 0;
			
			rs = statement.executeQuery(sp);
			 if (rs.next()) {
				 sum1 = rs.getDouble("Sum1");
				 sum2 = rs.getDouble("Sum2");
			
			 System.out.println("Total Cash Value in Inventory" + '\n' + "$" + sum1 * sum2);
			 System.out.println("--------------------------");
			 }
	
		}
		public String search() throws SQLException {
			
			String S = "";
			System.out.println("Search: ");
			Scanner scanner = new Scanner(System.in);
			String name1 = scanner.next();
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/salas_tequila", "root", "root");
			statement = connect.createStatement();
			rs = statement.executeQuery("Select * From current_inventory Where product_description Like '%" + name1 + "%'");
			

			while(rs.next()) {
			int productId = rs.getInt("product_id");
			String productName = rs.getString("product_name");
			String product_Description = rs.getString("product_description");
			Double prize = rs.getDouble("prize");
			Double size_ML = rs.getDouble("size_ML");
			int SKU = rs.getInt("SKU");
			int quantity = rs.getInt("Quantity");
			S = S +  "Prodcut Id "+ productId + "Prodcut Name " + productName + "Product Description" + product_Description + "Prize" + prize + "Size(mL)" + size_ML + "SKU" + SKU +  "Quantity" + quantity +  '\n';
			System.out.println("--------------------------");
			System.out.println("Product ID: " + productId + ", Product Name: " + productName + ", Prodcut Description: " + product_Description + ", Prize: " + prize + ", Size(mL): " + size_ML + ", SKU: " + SKU + ", Quantity: " + quantity);
			System.out.println("--------------------------");
			} 
			
			
			return S;
		}
		
		public void customerService() throws SQLException {
			String c = "";
			System.out.println("Displaying Customer Service Team.....");
			System.out.println("----------------------------------");
			
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/salas_tequila", "root", "root");
			statement = connect.createStatement();
			
			rs = statement.executeQuery("Select * From Customer_Support");
			
			while(rs.next()) {
				
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String position = rs.getString("position");
				String phoneNumber = rs.getString("phone_number");
				String email = rs.getString("email");
			

				c = c + "First Name: " + firstName + '\n' + "Last Name: " + lastName + '\n' + "Position: " + position + '\n' + "Phone Number: " + phoneNumber + '\n' + "Email: " + email + '\n';
				System.out.println("First Name: " + firstName + '\n' + "Last Name: " + lastName + '\n' + "Position: " + position + '\n' + "Phone Number: " + phoneNumber + '\n' + "Email: " + email + '\n');
				
				} 
			
		}
		
			
			

	 }


		
		
		
	

