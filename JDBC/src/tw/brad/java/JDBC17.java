package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC17 {
	public static void main(String[] args) {
		String account = "tony", passwd ="654321";
		
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try(Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/iii", info);) {
			Member member = null;
			if ((member = checkMember(account,passwd,conn)) != null ) {
				System.out.println("Welcome, " + member.realname);
			}else {
				System.out.println("Login Fail");
			}
		}catch (Exception e) {
			System.out.println("Server Busy");
		}
	}
	
	static Member checkMember(
			String account, String passwd, Connection conn)
			throws SQLException {
		String sql = "SELECT * FROM accounts WHERE account=? and passwd=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, passwd);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return new Member(rs.getString("account"),
					rs.getString("realname"));
		}else {
			// 
			return null;
		}
	}
	
}
class Member {
	String account, realname;
	Member(String account, String realname){
		this.account = account;
		this.realname = realname;
	}
}
