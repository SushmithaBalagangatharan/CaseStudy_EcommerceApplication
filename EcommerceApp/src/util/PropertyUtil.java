package util;

import java.io.*;
import java.util.Properties;

public class PropertyUtil {
	public static String getPropertyString(String fileName) {
		
		try {
			Properties p = new Properties();
			FileInputStream input = new FileInputStream(fileName);
			p.load(input);
			
			String hostname = p.getProperty("hostname");
			String dbname = p.getProperty("dbname");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			String portnumber = p.getProperty("port");
			

			String conString = "jdbc:mysql://" + hostname + ":" + portnumber + "/" + dbname +
                    "?user=" + username + "&password=" + password;

			return conString;
		}
		catch(Exception e) {
				System.out.println(e.toString());
				return null;
		} 
	}
}
