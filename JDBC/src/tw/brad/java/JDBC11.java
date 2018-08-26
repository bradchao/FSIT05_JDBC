package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC11 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/iii";
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String query = "DELETE FROM cust";
		
		try (Connection conn = DriverManager.getConnection(url,prop);) {

			PreparedStatement pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, "20");
			int result = pstmt.executeUpdate();
			System.out.println("OK:" + result);
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
