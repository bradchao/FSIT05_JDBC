package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBC14 {
	public static void main(String[] args) {
		
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try(Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/iii", info);) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT count(*) as nums from gifts");
			rs.next();
			String nums= rs.getString("nums");
			System.out.println(nums);
			
			int rpp = 7;	// row per page
			int page = 12;	// review page 1
			int start = (page-1)*rpp;
			
			rs = stmt.executeQuery(
					"SELECT id,name FROM gifts LIMIT " + start + "," + rpp);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				System.out.println(id + ":" + name);
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
