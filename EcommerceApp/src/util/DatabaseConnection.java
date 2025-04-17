package util;
import java.sql.*;

public class DatabaseConnection {
	static Connection connection = null;
	public static Connection getConnection() {
		try {		 
			String filePath = "src/resources/database.properties"; 
			String conString = PropertyUtil.getPropertyString(filePath);
			 if (conString == null) {
	                System.out.println("Connection string is null!");
	                return null;
	         }			
			connection = DriverManager.getConnection(conString);
			System.out.println("Database Connected Successfully!");
			return connection;
		}
		catch(Exception e) {
			System.out.println("ERROR: failed to connect!");
			return null;
		}
	}  
}
