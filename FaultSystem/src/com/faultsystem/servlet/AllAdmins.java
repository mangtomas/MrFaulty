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

public class AllAdmins extends HttpServlet{



	public AllAdmins() {  
        super();  
    }  
  
    public void init() throws ServletException {  
    }  
  
    public void process(HttpServletRequest req, HttpServletResponse res)  
            throws ServletException, IOException {  
        res.setContentType("text/html;charset=UTF-8");  
        PrintWriter pw = res.getWriter();  
        try {  
            // find the username in session  
            HttpSession hs = req.getSession(true);  
            String myName = (String) hs.getAttribute("name");  
            String name="";  
            String passwd="";
            String userType ="";
  
            if (myName == null) {  
                // check the session first if not then check the cookie
                // get cookies from client
                Cookie[] allCookies = req.getCookies();  
                int i = 0;  
                // if allCookies not null
                if (allCookies != null) {  
                    for (i = 0; i < allCookies.length; i++) {// get cookie  
                        
                        Cookie temp = allCookies[i];  
                        if (temp.getName().equals("myname")) {  
                            // get cookie value
                            name = temp.getValue();  
                        } else if (temp.getName().equals("mypasswd")) {  
                            passwd = temp.getValue();  
                        } else if (temp.getName().equals("usertype")) {  
                            userType = temp.getValue();  
                        }  
                    }  
                   if(!name.equals("")&&!passwd.equals("")){  
                        //check these in longincl
                        res.sendRedirect("logincl?username="+name+"&passwd="+passwd+"&userType="+userType);  
                        return;  
                    }
                     
                }  
                // back to login with info
                res.sendRedirect("login?info=error1");  
                return;  
            }  
            
            pw.println("<body><center>");  
            pw.println("Hello! " + myName+"<br>");
            pw.println("<form action=findadmin method=post>"); 
            pw.println("Find Admin by ID£º<input type=text name=adminId>");
            pw.println("<input type=submit value=search><br>");
            pw.println("</form>"); 
            pw.println("List All Admins<br>");
            AdminDao dao = new AdminDao();  
            List<Admin> al= new ArrayList<Admin>();
            	al=dao.findAllAdmin();  
            // table head
            pw.println("<table border=1>");  
            pw.println("<tr><th>Admin id</th><th>Admin Name</th><th>Password</th><th>Email Address</th></tr>");  
            for(int i=0;i<al.size();i++){  
                Admin p=(Admin)al.get(i);  
                pw.println("<tr>");  
                long a = p.getAdminId();
                System.out.println("id= "+a);
                pw.println("<td>" + p.getAdminId() + "</td>");  
                pw.println("<td>" + p.getAdminName() + "</td>");  
                pw.println("<td>" + p.getPassword() + "</td>");  
                pw.println("<td>" + p.getEmailAdd() + "</td>");  
                pw.println("<td><a href=\"deladmin?adminId="+a+"\" mce_href=\"deladmin?adminId=\""+a+ " onclick=/\"return window.confirm('Delete this record?')/\">Delete</a></td>");  
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
          
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
  
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
            throws ServletException, IOException {  
        process(req, res);  
    }  
  
    public void doPost(HttpServletRequest req, HttpServletResponse res)  
            throws ServletException, IOException {  
        process(req, res);  
    }  
  
    public void destroy() {  
    }  

}
