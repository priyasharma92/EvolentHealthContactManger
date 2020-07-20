package com.priya.util;

import java.sql.*;

public class Database {
	
	private volatile static Connection c;
	
	public static Connection getDatabaseConnection() {
		if(c==null) {
			synchronized(Database.class){
				if(c==null) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "username", "password");
					}
					catch (ClassNotFoundException e) {
						System.err.println("Please check your Oracle Libraries.");
						e.printStackTrace();
					} catch (SQLException e) {
						System.err.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		return c;
	}
}
