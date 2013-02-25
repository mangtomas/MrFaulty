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

import com.faultsystem.beans.Fault;
import com.faultsystem.beans.Project;
import com.faultsystem.dao.FaultDao;
import com.faultsystem.dao.ProjectDao;

public class AllFaults extends HttpServlet {

	public AllFaults() {  
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
            pw.println("<form action=findfault method=post>"); 
            pw.println("Find Reporter by ID£º<input type=text name=faultId>");
            pw.println("<input type=submit value=search><br>");
            pw.println("</form>"); 
            pw.println("List All Faults<br>");
            FaultDao dao = new FaultDao();  
            List<Fault> al= new ArrayList<Fault>();
            	al=dao.listAllFaultWithNameQuick();  
            // table head
            pw.println("<table border=1>");  
            pw.println("<tr><th>Fault id</th><th>Short Summart</th><th>Detail</th><th>Severity Level</th><th>Project Name</th>" +
            		"<th>Reporter Name</th><th>Current State</th><th>Life Cycle</th><th>create Time</th><th>update time</th></tr>");  
            for(int i=0;i<al.size();i++){  
                Fault p=(Fault)al.get(i);  
                pw.println("<tr>");  
                long a = p.getFaultId();
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
    }  
  
    public void doPost(HttpServletRequest req, HttpServletResponse res)  
            throws ServletException, IOException {  
        process(req, res);  
    }  
  
    public void destroy() {  
    }  


}
