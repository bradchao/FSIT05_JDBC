package tw.brad.java;

import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class JDBC19 {

	public static void main(String[] args) {
		Student s1 = new Student(90, 78, 40);
		System.out.println(s1.calScore());
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try(Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/iii", info);
				FileInputStream fin = new FileInputStream("dir1/ball.png");
				) {
			String sql = "UPDATE accounts SET img=?,student=? WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			
			pstmt.setBinaryStream(1, fin);
			pstmt.setObject(2, s1);
			pstmt.setString(3, "17");
			pstmt.executeUpdate();
			System.out.println("Save OK");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
class Student implements Serializable {
	int ch,math,eng;
	Student(int a, int b, int c){
		ch=a; math=b; eng=c;
	}
	int calScore() {return ch+math+eng;}
}
