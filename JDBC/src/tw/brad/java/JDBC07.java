package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC07 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/iii";
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String update = "UPDATE cust SET name='Tony',tel='444' WHERE id=19";
		
		try (Connection conn = DriverManager.getConnection(url,prop);) {
			Statement stmt = conn.createStatement();
			stmt.execute(update);
			
			System.out.println("OK");
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
