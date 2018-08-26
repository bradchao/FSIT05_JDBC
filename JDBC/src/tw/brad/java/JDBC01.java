package tw.brad.java;

import java.lang.reflect.Method;

public class JDBC01 {
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("OK");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found");
		}
		
		
	}
}
