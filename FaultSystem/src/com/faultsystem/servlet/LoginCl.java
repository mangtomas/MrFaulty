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

public class LoginCl extends HttpServlet {
	public LoginCl() {
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
			int t = Integer.parseInt(req.getParameter("userType"));
			String u = req.getParameter("username");
			String p = req.getParameter("passwd");
			System.out.println("");

			switch (t) {
			case 0:{

				// create a object
				AdminDao adao = new AdminDao();

				//check the validation of user
				if (adao.checkExist(u, p)) {
					// validate user
					String keep = req.getParameter("keep");
					if (keep != null) {
						// store the username password and usertype（cookie）
						// create
						Cookie name = new Cookie("myname", u);
						Cookie pass = new Cookie("mypasswd", p);
						Cookie type = new Cookie("usertype",String.valueOf(t));
						// set the validate time
						name.setMaxAge(14 * 24 * 3600);
						pass.setMaxAge(14 * 24 * 3600);
						type.setMaxAge(14 * 24 * 3600);
						// send back to client
						res.addCookie(name);
						res.addCookie(pass);
						res.addCookie(type);
					}

					// 将用户信息存入session中
					HttpSession hs = req.getSession(true);
					hs.setMaxInactiveInterval(30);
					hs.setAttribute("name", u);
					res.sendRedirect("adminwelcome");// jump to AdminWelcome
				} else {
					// 不合法用户
					System.out.println("Wrong username password");
					//res.setHeader("info", "loginfaild");
					res.sendRedirect("login?info=error1");
				}

			}
			case 1: {
				// create a object
				DeveloperDao ddao = new DeveloperDao();

				//check the validation of user
				if (ddao.checkExist(u, p)) {
					// validate user
					String keep = req.getParameter("keep");
					if (keep != null) {
						// store the username password and usertype（cookie）
						// create
						Cookie name = new Cookie("myname", u);
						Cookie pass = new Cookie("mypasswd", p);
						Cookie type = new Cookie("usertype",String.valueOf(t));
						// set the validate time
						name.setMaxAge(14 * 24 * 3600);
						pass.setMaxAge(14 * 24 * 3600);
						type.setMaxAge(14 * 24 * 3600);
						// send back to client
						res.addCookie(name);
						res.addCookie(pass);
						res.addCookie(type);
					}

					// 将用户信息存入session中
					HttpSession hs = req.getSession(true);
					hs.setMaxInactiveInterval(30);
					hs.setAttribute("name", u);
					res.sendRedirect("developerwelcome");// jump to AdminWelcome
				} else {
					// 不合法用户
					System.out.println("Wrong username password");
					//res.setHeader("info", "loginfaild");
					res.sendRedirect("login?info=error1");
					return;
				}
				
			}
			case 2: {
				// create a object
				ReporterDao rdao = new ReporterDao();

				//check the validation of user
				if (rdao.checkExist(u, p)) {
					// validate user
					String keep = req.getParameter("keep");
					if (keep != null) {
						// store the username password and usertype（cookie）
						// create
						Cookie name = new Cookie("myname", u);
						Cookie pass = new Cookie("mypasswd", p);
						Cookie type = new Cookie("usertype",String.valueOf(t));
						// set the validate time
						name.setMaxAge(14 * 24 * 3600);
						pass.setMaxAge(14 * 24 * 3600);
						type.setMaxAge(14 * 24 * 3600);
						// send back to client
						res.addCookie(name);
						res.addCookie(pass);
						res.addCookie(type);
					}

					// 将用户信息存入session中
					HttpSession hs = req.getSession(true);
					hs.setMaxInactiveInterval(30);
					hs.setAttribute("name", u);
					res.sendRedirect("reporterwelcome");// jump to AdminWelcome
				} else {
					// 不合法用户
					System.out.println("Wrong username password");
					//res.setHeader("info", "loginfaild");
					res.sendRedirect("login?info=error1");
					return;
				}
				

			}

			}

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
