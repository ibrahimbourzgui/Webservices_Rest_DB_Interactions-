package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	private Connection conn = null;
	public Connection getConn() {
	   return conn;
	}
	private DbConnection(String driverName, String subprot, String host,
	String port, String db, String uid, String psw) {
	   try {
	   Class.forName(driverName);
	   Properties pp = new Properties();
	   String url = String.format("jdbc:%s://%s:%s/%s&user=%s&password=%s", subprot, host,
	port, db, uid, psw);
	   conn = DriverManager.getConnection(url);
	   } catch (SQLException e) {
	   System.out.println(e);
	   } catch (ClassNotFoundException e) {
	   System.out.println(e);
	   }
	}
	public static DbConnection getInstance(String driverName, String subprot,
	String host,
	   String port, String db, String uid, String psw) {
	   return new DbConnection(driverName, subprot, host, port, db, uid, psw);
	}
	public void close() {
	   try {
	   if (conn != null) {
	   conn.close();
	   conn = null;
	   }
	   } catch (SQLException e) {
	   System.out.println(e);
	   }
	}

}
