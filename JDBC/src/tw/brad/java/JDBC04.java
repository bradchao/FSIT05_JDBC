package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC04 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/iii";
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String insert = "INSERT INTO cust (name,tel,birthday) " +
						"VALUES (" +
						"'Mary','111','1999-01-03'" + 
						")";
		try {
			Connection conn = DriverManager.getConnection(url,prop);
			Statement stmt = conn.createStatement();
			stmt.execute(insert);
			
			conn.close();
			System.out.println("OK");
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
