package tw.brad.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JDBC20 {
	public static void main(String[] args) {
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try(Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/iii", info);
				FileOutputStream fout = new FileOutputStream("dir2/ball.png");
				) {
			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "17");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			InputStream in = rs.getBinaryStream("img");
			int len = 0; byte[] buf = new byte[4096];
			while ( (len = in.read(buf)) != -1) {
				fout.write(buf, 0, len);
			}
			fout.flush();
			in.close();
			
			InputStream in2 = rs.getBinaryStream("student");
			ObjectInputStream oin = new ObjectInputStream(in2);
			Student s2 = (Student)oin.readObject();
			System.out.println(s2.calScore());
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
