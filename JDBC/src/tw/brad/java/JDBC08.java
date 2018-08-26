package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC08 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/iii";
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String query = "SELECT name,id as `no`,tel,birthday FROM cust";
		
		try (Connection conn = DriverManager.getConnection(url,prop);) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String f1 = rs.getString("no");
				String f2 = rs.getString("name");
				String f3 = rs.getString("tel");
				String f4 = rs.getString("birthday");
				System.out.println(f1 + ":" + f2 + ":" + f3 + ":" + f4);
			}
			
			System.out.println("OK");
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
