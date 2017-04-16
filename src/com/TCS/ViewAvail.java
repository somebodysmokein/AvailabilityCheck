package com.TCS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.DBConnectionFactory.DBConnectionFactory;

public class ViewAvail extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject result_obj;
		JSONArray result_array=new JSONArray();
		try
		{
			String message="";
		System.out.println("View Availability Caled");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/json");
		DBConnectionFactory conn1 = DBConnectionFactory.getInstance();
		try{	
						
						Connection conn =  conn1.getConnection();
						String query = "select R1.firstname,R1.lastname,R3.project_end_dt from resource R1,allocation R2,project R3 where R3.project_end_dt>(select  DATE_SUB(CURDATE(),INTERVAL 30 DAY)) and R3.id=R2.project_id"+
								" and R2.Emp_key=R1.Emp_key;";
						java.sql.PreparedStatement empStmt=conn.prepareStatement(query);
						ResultSet rs=empStmt.executeQuery();
						
						while(rs.next())
						{
							result_obj=new JSONObject();
							result_obj.put("fname", rs.getString(1));
							result_obj.put("lname", rs.getString(2));
							result_obj.put("availablefrom", rs.getDate(3));
							result_array.put(result_obj);
							System.out.println("FirstName:"+result_obj.get("fname"));
						}
						
						rs.close();
						conn.close();
						pw.write(result_array.toString());
						pw.flush();
			}
			
					catch (SQLException esql)
					{
						message="Fetch Failed"+esql.getMessage();
						esql.printStackTrace();
					}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
