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

import java.sql.ResultSetMetaData;

public class JDBC12 extends JFrame {
	private JTable jTable;
	private int dataCount;
	private String[] fields;
	private ResultSet rs;
	private Connection conn;
	
	public JDBC12() {
		super("JDBC12");
		setLayout(new BorderLayout());
		
		initData();
		
		jTable = new JTable(new MyTableModel());
		jTable.setFont(new Font("", Font.PLAIN, 16));
		JScrollPane jsp = new JScrollPane(jTable);
		add(jsp, BorderLayout.CENTER);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initData() {
		String url = "jdbc:mysql://localhost/iii";
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String query = 
				"SELECT id, name as `名稱`, feature as `特色`, place as `購買地址` FROM gifts";
		
		try {
			conn = DriverManager.getConnection(url,prop);
			PreparedStatement pstmt0 = conn.prepareStatement("SELECT count(*) as count FROM gifts");
			ResultSet rs0 = pstmt0.executeQuery();
			rs0.next(); dataCount = rs0.getInt("count"); 
			
			PreparedStatement pstmt = conn.prepareStatement(query,
					ResultSet.TYPE_FORWARD_ONLY, 
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metadata = rs.getMetaData();
			fields = new String[metadata.getColumnCount()];
			for (int i=0; i<fields.length; i++) {
				fields[i] = metadata.getColumnLabel(i+1);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	private class MyTableModel extends DefaultTableModel {
		@Override
		public int getRowCount() {
			return dataCount;
		}
		
		@Override
		public int getColumnCount() {
			return fields.length;
		}
		
		@Override
		public String getColumnName(int column) {
			return fields[column];
		}
		
		@Override
		public Object getValueAt(int row, int column) {
			try {
				rs.absolute(row+1);
				return rs.getString(fields[column]);
			}catch(Exception e) {
				return "---";
			}
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return fields[column].equals("id")?false:true;
			//return true;
		}

		@Override
		public void setValueAt(Object aValue, int row, int column) {
			super.setValueAt(aValue, row, column);
			try {
				rs.absolute(row+1);
				rs.updateString(fields[column], (String)aValue);
				rs.updateRow();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new JDBC12();

	}

}
