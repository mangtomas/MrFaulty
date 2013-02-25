package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faultsystem.beans.Fault;
import com.faultsystem.beans.Reporter;
import com.faultsystem.dao.FaultDao;
import com.faultsystem.dao.ReporterDao;

public class FindFault extends HttpServlet{
	

	public FindFault() {
		super();
	}

	public void init() throws ServletException {
	}

	// req:get the info from browser
	// res:send the info to browser(i am sever)
	public void process(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");  
        PrintWriter pw = res.getWriter();  
        try {  
            // find the username in session  
            HttpSession hs = req.getSession(true);  
            String te = req.getParameter("faultId");
			long a = Long.parseLong(te);
			if(te.length()==0 ){
				res.sendRedirect("error?errorMsg=Empty_Input");
				
			}else{
				  	pw.println("<body><center>");  
		            pw.println("Fault<br>");
		            FaultDao dao = new FaultDao();  
		            Fault p= new Fault();
		            	p=dao.listFaultById(a);  
		            // table head
		            pw.println("<table border=1>");  
		            pw.println("<tr><th>Fault id</th><th>Short Summart</th><th>Detail</th><th>Severity Level</th><th>Project Name</th>" +
            		"<th>Reporter Name</th><th>Current State</th><th>Life Cycle</th><th>create Time</th><th>update time</th></tr>");  
		            System.out.println("fault_id is "+a);
		            pw.println("<td>" + p.getFaultId() + "</td>");  
	                pw.println("<td>" + p.getSummary() + "</td>");  
	                pw.println("<td>" + p.getDetail() + "</td>");  
	                pw.println("<td>" + p.getSeverityLevel() + "</td>");  
	                pw.println("<td>" + p.getProjectName() + "</td>"); 
	                pw.println("<td>" + p.getReporterName() + "</td>");  
	                pw.println("<td>" + p.getStateName() + "</td>");  
	                pw.println("<td>" + p.getLifeName() + "</td>");  
	                pw.println("<td>" + p.getCreateTime() + "</td>"); 
	                pw.println("<td>" + p.getUpdateTime() + "</td>"); 
	                pw.println("<td><a href=\"delfault?faultId="+a+"\" mce_href=\"delfault?faultId=\""+a+ " onclick=/\"return window.confirm('Delete this record?')/\">Delete</a></td>");  
	                System.out.println("at the end of on loop"+p.getProjectId());
	                pw.println("</tr>");    
		            }  
		            pw.println("</table>");  
		            pw.println("<table>");
		            pw.println("<tr>");
		            pw.println("<td><a href=\"allfaults\" mce_href=\"allfaults\">List All Faults</a></td>");  
		            pw.println("<td><a href=\"allreporters\" mce_href=\"allreporters\">List All Reporters</a></td>");
		            pw.println("<td><a href=\"alldevelopers\" mce_href=\"alldevelopers\">List All Developers</a></td>");
		            pw.println("<td><a href=\"alladmins\" mce_href=\"alladmins\">List All Admins</a></td>");
		            pw.println("<td><a href=\"login\" mce_href=\"login\">Back to login</a></td>");
		            pw.println("<tr>");
		            pw.println("</table>");
		              
		            //pw.println("<br><a href=\"logout\" mce_href=\"logout\">Logout</a>"); 
		            pw.println("</center></body>"); 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		process(req, res);
		return;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		process(req, res);
		return;
	}

	public void destroy() {
	}



}
