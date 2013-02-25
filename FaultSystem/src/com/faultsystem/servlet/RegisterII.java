package com.faultsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faultsystem.dao.AdminDao;
import com.faultsystem.dao.DeveloperDao;
import com.faultsystem.dao.ReporterDao;

public class RegisterII extends HttpServlet{
	
	public RegisterII() {
		super();
	}

	public void init() throws ServletException {
	}

	// req:get the info from browser
	// res:send the info to browser(i am sever)
	public void process(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");// page type

		try {
			// get the user type username and password
			int t = Integer.parseInt(req.getParameter("reguserType"));
			switch (t) {
			case 0:{
				//Admin
				res.sendRedirect("questionval");
			

			}
			case 1: {
				//Developer
				res.sendRedirect("developerreg");
				
				
			}
			case 2: {
				//Reporter
				res.sendRedirect("reporterreg");
			

		
				}
			}
		}catch(Exception ex){  
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
