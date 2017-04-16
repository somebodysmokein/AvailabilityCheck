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

public class ViewProject extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		JSONObject result_obj;
		JSONArray result_array=new JSONArray();
		try
		{
			String message="";
		System.out.println("View Project Caled");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/json");
		DBConnectionFactory conn1 = DBConnectionFactory.getInstance();
		try{	
						
						Connection conn =  conn1.getConnection();
						String query = "select * from project R1";
						java.sql.PreparedStatement empStmt=conn.prepareStatement(query);
						ResultSet rs=empStmt.executeQuery();
						
						while(rs.next())
						{
							result_obj=new JSONObject();
							result_obj.put("id", rs.getInt(1));
							result_obj.put("name", rs.getString(2));
							result_obj.put("startdt", rs.getDate(3));
							result_obj.put("enddt", rs.getDate(4));
							result_array.put(result_obj);
						}
						
						rs.close();
						conn.close();
						pw.write(result_array.toString());
						pw.flush();
			}
			
					catch (SQLException esql)
					{
						message="Update Failed"+esql.getMessage();
						esql.printStackTrace();
					}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
