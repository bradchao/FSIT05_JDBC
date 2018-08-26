package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC10 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/iii";
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String query = "UPDATE cust set tel=? WHERE name like ?";
		
		try (Connection conn = DriverManager.getConnection(url,prop);) {

			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "0912321456");
			pstmt.setString(2, "Brad%");
			int result = pstmt.executeUpdate();
			System.out.println("OK:" + result);
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
