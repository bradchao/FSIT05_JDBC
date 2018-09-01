package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC15 {
	public static void main(String[] args) {
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try(Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/iii", info);) {
			String sql = "SELECT * FROM gifts LIMIT 0,4";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			JSONStringer js = new JSONStringer();
			JSONWriter jw = js.array();
			while (rs.next()) {
				String name = rs.getString("name");
				String feature = rs.getString("feature");
				String img = rs.getString("imgurl");
				jw.object();
					jw.key("名稱").value(name);
					jw.key("特色").value(feature);
					//jw.key("圖片").value(img);
				jw.endObject();
			}
			jw.endArray();
			System.out.println(jw);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
