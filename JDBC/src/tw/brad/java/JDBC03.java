package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC03 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/iii";
		String user = "root";
		String passwd = "root";
		String insert = "INSERT INTO cust (name,tel,birthday) " +
						"VALUES (" +
						"'Tony','321','1999-01-02'" + 
						")";
		try {
			Connection conn = DriverManager.getConnection(url,user,passwd);
			Statement stmt = conn.createStatement();
			stmt.execute(insert);
			
			conn.close();
			System.out.println("OK");
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}
}
