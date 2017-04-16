package com.TCS;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.DBConnectionFactory.DBConnectionFactory;
import com.mysql.jdbc.PreparedStatement;

public class AddProject extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{
			String message="";
		System.out.println("AddProject Caled");
		Enumeration<String> recvParams=request.getParameterNames();
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
		
		PrintWriter pw=response.getWriter();
		response.setContentType("text/json");
		DBConnectionFactory conn1 = DBConnectionFactory.getInstance();
		try{	
						
						Connection conn =  conn1.getConnection();
						String query = "insert into project(id,PROJECT_NAME,PROJ_START_DT,PROJECT_END_DT) values (?,?,?,?)";
						java.sql.Date createDate = conn1.getcurrentDate();
						java.sql.PreparedStatement pStmt = conn.prepareStatement(query);
						
						pStmt.setInt (1,Integer.parseInt(projId));
						pStmt.setString (2, projName);
						System.out.println("StartDate:"+conn1.getJavaDBDate(startdt.substring(0,10)));
						System.out.println("EndDate:"+conn1.getJavaDBDate(enddt.substring(0,10)));
						pStmt.setDate (3, conn1.getJavaDBDate(startdt.substring(0,10)));
						pStmt.setDate (4, conn1.getJavaDBDate(enddt.substring(0, 10)));
						pStmt.executeUpdate();	
						message="Update Successful";
			}
			
					catch (SQLException esql)
					{
						message="Update Failed"+esql.getMessage();
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
