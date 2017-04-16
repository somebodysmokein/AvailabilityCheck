package com.DBConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBConnectionFactory 
{
	private static DBConnectionFactory _dbcf;

	private DBConnectionFactory()
	{
		super();
	}
	
	public static DBConnectionFactory getInstance()
	{
		if (_dbcf != null)
			return _dbcf;
		else
		{
				_dbcf = new DBConnectionFactory();
				return _dbcf;

		}
	}
			
		
	public Connection getConnection() throws Exception
	{
		try
		{
			//String PROD = "jdbc:mysql://10.150.70.114/coreops";
			String DEV = "jdbc:mysql://localhost/allocation_db";
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = DEV;
			Connection newConn = DriverManager.getConnection(dbURL,"root","");
			return newConn;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			throw new Exception("SQL Exception encountered getting new DB Connection");
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
			throw new Exception("ClassNotFoundException encountered getting new DB Connection");
		}
	}	
	
	public void dropConnection(Connection conn) throws Exception
	{
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
				throw new Exception("error closing connection");
			}
		}
		conn = null;
	}
	public Date getJavaDBDate(String sdt) throws Exception
	{
		//String date[] =  sdt.split("-",3);
		//String dt = date[2] + "-" + date[0] + "-" + date[1];		
		java.sql.Date sqltDate= java.sql.Date.valueOf( sdt ); 
		return sqltDate;
	}
	public Date getcurrentDate() throws Exception
	{
		DateFormat df = new SimpleDateFormat("MM/DD/YYYY");
		final Calendar c = Calendar.getInstance();	
		String s=df.format(c.getTime());
		java.util.Date parsedUtilDate = df.parse(s);  
		java.sql.Date sqltDate= new java.sql.Date(parsedUtilDate.getTime());				
		Calendar calendar = Calendar.getInstance();
		java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		return startDate;
	}	
}

