package com.org.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
	
	public static Connection getConnection()
	{
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospatal_management","root","root");
			return con;
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}

}
