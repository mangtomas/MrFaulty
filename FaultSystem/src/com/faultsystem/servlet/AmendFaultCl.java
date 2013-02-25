package com.faultsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faultsystem.beans.Fault;
import com.faultsystem.dao.FaultDao;

public class AmendFaultCl extends HttpServlet {

	public AmendFaultCl() {
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
			String faultId = req.getParameter("faultId");
			String summary = req.getParameter("summary");
			String detail = req.getParameter("detail");
			String slevelInt = req.getParameter("serverity_level");
			String stateId = req.getParameter("state");
			String lifeId = req.getParameter("life");
			Fault a = new Fault();
			a.setSummary(summary);
			a.setDetail(detail);
			a.setSeverityLevel(Integer.parseInt(slevelInt));
			a.setStateId(Long.parseLong(stateId));
			a.setLifeId(Long.parseLong(lifeId));
			FaultDao fdao = new FaultDao();
			fdao.amendFaultById(Long.parseLong(faultId), a);
			
			res.sendRedirect("reporterwelcome");
			


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
