package com.TCS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.DBConnectionFactory.DBConnectionFactory;

public class ViewResource extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		JSONObject result_obj;
		JSONArray result_array=new JSONArray();
		try
		{
			String message="";
		System.out.println("View Resource Caled");
		/*Enumeration<String> recvParams=request.getParameterNames();
		String tmpParams="";
		while(recvParams.hasMoreElements())
		{
			tmpParams=recvParams.nextElement();
		}
		System.out.println(tmpParams);
		JSONObject params=new JSONObject(tmpParams);
		System.out.println(params.length());
		String projName=params.get("projectname").toString();
		String projId=params.get("projectid").toString();
		String startdt=params.get("startdt").toString().substring(0, 10);
		String enddt=params.get("enddt").toString().substring(0,10);
		System.out.println("projName:"+projName+"projId:"+projId+"startdt:"+startdt+"enddt:"+enddt);
		
		*/
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/json");
		DBConnectionFactory conn1 = DBConnectionFactory.getInstance();
		try{	
						
						Connection conn =  conn1.getConnection();
						String query = "select * from resource R1";
						java.sql.PreparedStatement empStmt=conn.prepareStatement(query);
						ResultSet rs=empStmt.executeQuery();
						
						while(rs.next())
						{
							result_obj=new JSONObject();
							result_obj.put("firstname", rs.getString(1));
							result_obj.put("lastname", rs.getString(2));
							result_obj.put("Employee_Id", rs.getInt(3));
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
