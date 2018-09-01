package tw.brad.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JDBC13 {
	public static void main(String[] args) {
		String source = fatchOpendata();
		if (source != null) {
			toMyDB(source);
		}else {
			System.out.println("no data");
		}
	}
	
	static String fatchOpendata() {
		String ret = null;
		try {
			URL url = new URL("http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.connect();
			
			BufferedReader reader = 
					new BufferedReader(
							new InputStreamReader(conn.getInputStream()));
			String line; StringBuffer sb = new StringBuffer();
			while ( (line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			reader.close();
			
			ret = sb.toString();
			
		} catch (Exception e) {
		}
		
		return ret;
	}
	
	static void toMyDB(String json) {
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try(Connection conn = 
				DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/iii", info);) {
			Statement stmt = conn.createStatement();
			stmt.execute("ALTER TABLE gifts AUTO_INCREMENT = 1");
			
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO gifts (name,feature,place,imgurl) VALUES (?,?,?,?)");
			
			JSONArray root = new JSONArray(json);
			//System.out.println(root.length());
			for (int i=0; i<root.length(); i++) {
				try {
					JSONObject row = root.getJSONObject(i);
					String name = row.getString("Name");
					String feature = row.getString("Feature");
					String place = row.getString("SalePlace");
					String imgurl = row.getString("Column1");
					
					pstmt.setString(1, name);
					pstmt.setString(2, feature);
					pstmt.setString(3, place);
					pstmt.setString(4, imgurl);
					pstmt.execute();
				}catch (Exception je) {
					System.out.println("json error: " + i);
				}
			}
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	
}
