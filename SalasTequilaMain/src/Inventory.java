import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventory{
 
		
		private Connection connect = null;
		private Statement statement = null;
		private PreparedStatement ps = null;
		private ResultSet rs = null;
		
		
		public String viewInventory() {
			
			System.out.println("Viewing...");
			String I = "";
			try {
			
			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/salas_tequila", "root", "root");
			statement = connect.createStatement();
			rs = statement.executeQuery("Select * from current_inventory");
			
			while(rs.next()) {
			int productId = rs.getInt("product_id");
			String name = rs.getString("product_name");
			I = I + "Prodcut Id "+ productId + "Prodcut Name " + name + '\n';
			System.out.println("Product ID: " + productId + ", Product Name: " + name);
			}
			
			}
			catch(Exception ex) {System.out.println("Error " + ex.getMessage());}
			
			return I;
			}
		
			}
		
		
		
	

