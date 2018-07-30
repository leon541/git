package com.leon.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLtester {

	//private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DRIVER =  "net.sourceforge.jtds.jdbc.Driver" ;
	private static final String URL = "jdbc:jtds:sqlserver://10.206.12.101:1433;databaseName=esglogdb76";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "Websense#123";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	/*
	 * get connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		System.out.println("connecting ...");
		try{
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("connection failed!");
		}
		System.out.println("connection OK");
		return conn;
	}
 
	/*
	 * close connection
	 */
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		if(rs!=null){
			try{
				rs.close();
				rs=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("close ResultSet failed");
			}
		}
		if(ps!=null){
			try{
				ps.close();
				ps=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("close PreparedStatement failed");
			}
		}
		if(conn!=null){
			try{
				conn.close();
				conn=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("close Connection failed");
			}
		}
	}
	
	public static void main(String[] argv ) throws Exception {
		SQLtester test = new SQLtester();
		Connection con =  test.getConnection();
		System.out.println("connection  OK");
		
		Statement stmt=con.createStatement();
		System.out.println("statement   OK");
		
         String query= "select * from category";
         ResultSet rs =    stmt.executeQuery(query);
         while(rs.next()) {
        	 String category = rs.getString("category") ;   
        	 String name = rs.getString("name") ;  
        	 System.out.println("category:"+ category + "  name:"+name);
         }
		            
         System.out.println("result  OK");
	}
}