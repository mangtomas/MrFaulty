package com.faultsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faultsystem.beans.Project;
import com.faultsystem.dao.ProjectDao;

public class AddProjectCl extends HttpServlet {
	public AddProjectCl() {
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
			
			String u = req.getParameter("projectName");
			String p = req.getParameter("projectDesc");
			String id = req.getParameter("devId");
			Project pro = new Project();
			pro.setProjectName(u);
			pro.setProjetDesc(p);
			pro.setDevId(Long.parseLong(id));
			ProjectDao pdao = new ProjectDao();
			pdao.addNewProject(pro);
			res.sendRedirect("developerwelcome");
			System.out.println("");


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
