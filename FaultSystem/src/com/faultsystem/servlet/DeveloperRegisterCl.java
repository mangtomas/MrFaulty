package com.faultsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faultsystem.beans.Developer;
import com.faultsystem.beans.Reporter;
import com.faultsystem.dao.DeveloperDao;
import com.faultsystem.dao.ReporterDao;

public class DeveloperRegisterCl extends HttpServlet {
	

	public DeveloperRegisterCl() {
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
			
			String name = req.getParameter("username");
			String password = req.getParameter("passwd");
			String work = req.getParameter("work");
			String selfdesc = req.getParameter("selfdesc");
			String email = req.getParameter("email");
			Developer a = new Developer();
			a.setName(name);
			a.setPassword(password);
			a.setWork(work);
			a.setSelfDesc(selfdesc);
			a.setEmailAdd(email);
			DeveloperDao dao = new DeveloperDao();
			dao.addDeveloper(a);
		
			
			res.sendRedirect("login");
			


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
