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

public class AddResource extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{
		System.out.println("AddSource Caled");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/json");
		JSONObject finl_json=new JSONObject();
		finl_json.put("ID", "12456");
		finl_json.put("Name","AIMS");
		finl_json.put("strtdt", "2017-04-16");
		finl_json.put("enddt", "2017-04-30");
		JSONArray json_array=new JSONArray();
		json_array.put(finl_json);
		pw.write(json_array.toString());
		System.out.println(finl_json.get("ID"));
		pw.flush();
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{
			int emp_key=0,proj_Id=0;
			String message="";
		System.out.println("AddResource Called");
		Enumeration<String> recvParams=request.getParameterNames();
		String tmpParams="";
		while(recvParams.hasMoreElements())
		{
			tmpParams=recvParams.nextElement();
		}
		System.out.println(tmpParams);
		JSONObject params=new JSONObject(tmpParams);
		System.out.println(params.length());
		String projName=params.get("projName").toString();
		String firstName=params.get("fname").toString();
		String lastName=params.get("lname").toString();
		System.out.println("projName:"+projName+"FirstName:"+firstName+"LastName:"+lastName);
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/json");
		DBConnectionFactory conn1 = DBConnectionFactory.getInstance();
		try{	
						
						Connection conn =  conn1.getConnection();
						//conn.setAutoCommit(false);
						String query = "insert into resource (firstname,lastname) values (?,?)";
						java.sql.PreparedStatement pStmt = conn.prepareStatement(query);
						pStmt.setString (1,firstName);
						pStmt.setString (2, lastName);
						pStmt.executeUpdate();
						query="select max(Emp_key) from resource where firstname=? and lastname=?";
						java.sql.PreparedStatement empStmt=conn.prepareStatement(query);
						empStmt.setString (1,firstName);
						empStmt.setString (2, lastName);
						ResultSet rs=empStmt.executeQuery();
						while(rs.next())
						{
							emp_key=rs.getInt(1);
						}
						query="select id from project where project_name=?";
						java.sql.PreparedStatement projStmt=conn.prepareStatement(query);
						projStmt.setString (1,projName);
						
						ResultSet projrs=projStmt.executeQuery();
						while(projrs.next())
						{
							proj_Id=projrs.getInt(1);
						}
						System.out.println("Project Id:"+proj_Id);
						query="insert into allocation(Emp_key,project_id) values (?,?)";
						java.sql.PreparedStatement allocStmt=conn.prepareStatement(query);
						allocStmt.setInt(1,emp_key);
						allocStmt.setInt (2,proj_Id);
						allocStmt.executeUpdate();
						message="Insert Successful";
						rs.close();
						projrs.close();
						conn.close();
			}
			
					catch (SQLException esql)
					{
						message="Insert Failed"+esql.getMessage();
						esql.printStackTrace();
					}
		
		JSONObject finl_json=new JSONObject();
		finl_json.put("message",message);
		pw.write(finl_json.toString());
		//System.out.println(finl_json.get("ID"));
		pw.flush();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
