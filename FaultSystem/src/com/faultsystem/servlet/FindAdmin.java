package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faultsystem.beans.Admin;
import com.faultsystem.beans.Developer;
import com.faultsystem.dao.AdminDao;
import com.faultsystem.dao.DeveloperDao;
import com.faultsystem.dao.ReporterDao;

public class FindAdmin extends HttpServlet {
	
	public FindAdmin() {
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
            String te = req.getParameter("adminId");
			long t = Long.parseLong(te);
			if(te.length()==0 ){
				res.sendRedirect("error?errorMsg=Empty_Input");
				
			}else{
				  	pw.println("<body><center>");  
		            pw.println("Admin<br>");
		            AdminDao dao = new AdminDao();  
		            Admin l= new Admin();
		            	l=dao.listAdminById(t);  
		            // table head
		            pw.println("<table border=1>");  
		            pw.println("<tr><th>Admin id</th><th>Admin Name</th><th>Password</th><th>Email Address</th></tr>");  
		                pw.println("<td>" + l.getAdminId() + "</td>");  
		                pw.println("<td>" + l.getAdminName() + "</td>");  
		                pw.println("<td>" + l.getPassword() + "</td>");  
		                pw.println("<td>" + l.getEmailAdd()+ "</td>");  
		                pw.println("<td><a href=\"deldeveloper?developerId="+t+"\" mce_href=\"deldeveloper?developerId=\""+t+ " onclick=/\"return window.confirm('Delete this record?')/\">Delete</a></td>");  
		               
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
