package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faultsystem.beans.Reporter;
import com.faultsystem.dao.ReporterDao;

public class FindReporter extends HttpServlet{

	public FindReporter() {
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
            String te = req.getParameter("reporterId");
			long a = Long.parseLong(te);
			if(te.length()==0 ){
				res.sendRedirect("error?errorMsg=Empty_Input");
				
			}else{
				  	pw.println("<body><center>");  
		            pw.println("Reporter<br>");
		            ReporterDao dao = new ReporterDao();  
		            Reporter p= new Reporter();
		            	p=dao.findReporterById(a);  
		            // table head
		            pw.println("<table border=1>");  
		            pw.println("<tr><th>Reporter id</th><th>Reporter Name</th><th>Password</th><th>Work</th><th>Self Description</th>" +
            		"<th>Email address</th><th>Credit</th><th>create Time</th></tr>");  
		            System.out.println("fault_id is "+a);
	                pw.println("<td>" + p.getReporterId() + "</td>");  
	                pw.println("<td>" + p.getName() + "</td>");  
	                pw.println("<td>" + p.getPassword() + "</td>");  
	                pw.println("<td>" + p.getWork() + "</td>");  
	                pw.println("<td>" + p.getSelfDesc() + "</td>"); 
	                pw.println("<td>" + p.getEmailAdd()+ "</td>");  
	                pw.println("<td>" + p.getCredit() + "</td>");  
	                pw.println("<td>" + p.getRegTime() + "</td>");  
	                pw.println("<td><a href=\"delreporter?reporterId="+a+"\" mce_href=\"delreporter?reporterId=\""+a+ " onclick=/\"return window.confirm('Delete this record?')/\">Delete</a></td>");  
	               
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
