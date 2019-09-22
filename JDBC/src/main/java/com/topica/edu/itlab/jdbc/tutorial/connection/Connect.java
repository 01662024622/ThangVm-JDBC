package com.topica.edu.itlab.jdbc.tutorial.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	private final String dbName="bt11";
	private final String userName="root";
	private final String password="Thangui0011";
	private final String port="3306";
	private Connection conn;
	private Connect() {}
	private static Connect instanse = new Connect();
	public static Connect getIntanse() {
		return instanse;
	}
	 // Kết nối vào MySQL.
	 public Connection getConnection() throws SQLException,
	         ClassNotFoundException {
		 if (conn==null) {
			return getConnection(this.port, this.dbName, this.userName, this.password);
		}
		 return conn;
	 }
	 public Connection getConnection(String port, String dbName,
	         String userName, String password) throws SQLException,
	         ClassNotFoundException {
	     // Declare class Driver for DB MySQL
	     // need to with Java 5
	     // Java6 auto append Driver suitable.
	     Class.forName("com.mysql.jdbc.Driver");
	     // Cấu trúc URL Connection dành cho Oracle
	     // Ví dụ: jdbc:mysql://localhost:3306/simplehr
	     String connectionURL = "jdbc:mysql://localhost:"+port+"/"+ dbName;
	 
	     this.conn = DriverManager.getConnection(connectionURL, userName,password);
	     System.out.println("connect success!");
	     return this.conn;
	 }
//	close connect db
	public void close() throws SQLException {
	  if(conn != null) {
	    conn.close();
	      conn = null;
	  }
	} 
}
