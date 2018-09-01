package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC16 {
	public static void main(String[] args) {
		String account = "tony", passwd="654321", realname = "Brad Chao";
		
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try(Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/iii", info);) {
			
			if (!isDataRepeat(account, conn)) {
				String sql = "INSERT INTO accounts (account,passwd,realname)" +
						" VALUES (?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, account);
				pstmt.setString(2, passwd);
				pstmt.setString(3, realname);
				pstmt.executeUpdate();
				System.out.println("新增成功");
			}else {
				System.out.println("資料重複");
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static boolean isDataRepeat(String account, Connection conn) 
		throws SQLException {
		String sql = "SELECT count(*) AS count FROM accounts WHERE account = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt("count");
		
		return count != 0;
	}
	
	
}
