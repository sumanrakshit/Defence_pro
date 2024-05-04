package com.Innspark.spring.boot.angularlogin.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

private static	Connection connection=null;


//DB connection 
public static Connection getConnection() throws SQLException
{
	if(connection !=null)
	{
		return connection;
	}
	else
	{
//		String driver= "com.mysql.cj.jdbc.Driver";

		String url= "jdbc:mysql://localhost:3306/shadowdb";

		
		
		String user="jack";         //Mysql Username
		String password="root"; 
//
//		String user="root";         //Mysql Username
//		String password="Vivek@2002";    //Mysql password
		   //Mysql password
		
//		String user="root";
//		String password="mohit@123";

		try {
//			Class.forName(driver);
			connection=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return connection;
}

	

}
