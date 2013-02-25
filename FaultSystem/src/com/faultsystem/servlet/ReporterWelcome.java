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

import com.faultsystem.beans.Project;
import com.faultsystem.dao.ProjectDao;
import com.faultsystem.dao.ReporterDao;

public class ReporterWelcome extends HttpServlet {

	public ReporterWelcome() {  
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
            
            pw.println("Welcom,hello<br>");  
            pw.println("Hello! " + myName);
            ReporterDao rdao = new ReporterDao();
            long rId = rdao.findReporterdByName(myName);
            ProjectDao dao = new ProjectDao();  
            List<Project> al= new ArrayList<Project>();
            	al=dao.listAllProjects();  
            // table head
            pw.println("<table border=1>");  
            pw.println("<tr><th>id</th><th>name</th><th>describtion</th><th>create time</th><th>update time</th></tr>");  
            for(int i=0;i<al.size();i++){  
                Project p=(Project)al.get(i);  
                pw.println("<tr>");  
                long a = p.getProjectId();
                pw.println("<td>" + p.getProjectId() + "</td>");  
                pw.println("<td>" + p.getProjectName() + "</td>");  
                pw.println("<td>" + p.getProjetDesc() + "</td>");  
                pw.println("<td>" + p.getCreateTime() + "</td>");  
                pw.println("<td>" + p.getUpdateTime() + "</td>"); 
                pw.println("<td><a href=\"showfaults?projectId="+a+"\" mce_href=\"showfaults?projectId=\""+a+ " onclick=/\"return window.confirm('Delete this record?')/\">Show faults of this project</a></td>");  
                pw.println("</tr>");  
            }  
            pw.println("</table>");  
            pw.println("<br><a href=\"showmyfaults?reporterId="+rId+"\" mce_href=\"showmyfaults?projectId=\""+rId+ " onclick=/\"return window.confirm('Delete this record?')/\">Show my faults</a></br>");  

            pw.println("<br><a href=\"login\" mce_href=\"login\">Back to login</a>");  
            pw.println("<br><a href=\"logout\" mce_href=\"logout\">Logout</a>"); 
            
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
