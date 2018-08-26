package tw.brad.java;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JDBC12 extends JFrame {
	private JTable jTable;
	private LinkedList<HashMap<String, String>> data;
	
	
	public JDBC12() {
		super("JDBC12");
		setLayout(new BorderLayout());
		
		initData();
		
		jTable = new JTable(new MyTableModel());
		jTable.setFont(new Font("", Font.BOLD, 16));
		JScrollPane jsp = new JScrollPane(jTable);
		add(jsp, BorderLayout.CENTER);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initData() {
		data = new LinkedList<>();
		
		String url = "jdbc:mysql://localhost/iii";
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String query = "SELECT * FROM cust";
		
		try (Connection conn = DriverManager.getConnection(url,prop);) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String f1 = rs.getString("id");
				String f2 = rs.getString("name");
				String f3 = rs.getString("tel");
				String f4 = rs.getString("birthday");
				HashMap<String, String> row = new HashMap<>();
				row.put("id", f1);
				row.put("name", f2);
				row.put("tel", f3);
				row.put("birthday", f4);
				data.add(row);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	private class MyTableModel extends DefaultTableModel {
		@Override
		public int getRowCount() {
			return data.size();
		}
		
		@Override
		public int getColumnCount() {
			return 4;
		}
		
		@Override
		public String getColumnName(int column) {
			String ret = "";
			switch (column) {
				case 0: ret = "id"; break; 
				case 1: ret = "name"; break; 
				case 2: ret = "tel"; break; 
				case 3: ret = "birthday"; break; 
			}
			return ret;
		}
		
		@Override
		public Object getValueAt(int row, int column) {
			String ret = "";
			switch (column) {
				case 0: ret = data.get(row).get("id"); break; 
				case 1: ret = data.get(row).get("name"); break; 
				case 2: ret = data.get(row).get("tel"); break; 
				case 3: ret = data.get(row).get("birthday"); break; 
			}
			return ret;
		}
	}
	
	
	
	public static void main(String[] args) {
		new JDBC12();

	}

}
