package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faultsystem.beans.Fault;
import com.faultsystem.dao.FaultDao;
import com.faultsystem.dao.ReporterDao;

public class ShowFaultsByProject extends HttpServlet{
	

	public void init() throws ServletException{}  
    public void process(HttpServletRequest req,HttpServletResponse res)  
        throws ServletException,IOException{  
        try{                        
           res.setContentType("text/html;charset=UTF-8");  
          String id=req.getParameter("projectId"); 
          System.out.println("projectID= "+id);
          long d = Long.parseLong(id);
         
          PrintWriter pw = res.getWriter();  
            
              
              pw.println("<body><center>");  
              FaultDao dao = new FaultDao();  
              List<Fault> al= new ArrayList<Fault>();
              HttpSession hs = req.getSession(true);  
              String myName = (String) hs.getAttribute("name");
              ReporterDao rdao = new ReporterDao();
              long r = rdao.findReporterdByName(myName);
              al=dao.listAllFaultWithNameByProjectId(d);  
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
                 
                  pw.println("</tr>");  
              }  
              pw.println("</table>");  
              pw.println("<table>");
              pw.println("<tr>");
             
              pw.println("<td><a href=\"login\" mce_href=\"login\">Back to login</a></td>");
              pw.println("<td><a href=\"reporterwelcome\" mce_href=\"reporterwelcome\">Back to ProjectList</a></td>");
              pw.println("<td><a href=\"addfault?projectId="+d+"&reporterId="+r+"\" mce_href=\"addfault?projectId="+d+"&reporterId="+r+ " onclick=/\"return window.confirm('Delete this record?')/\">Add A New Fault for this Project</a></td>");

              pw.println("<tr>");
              pw.println("</table>");
                
              //pw.println("<br><a href=\"logout\" mce_href=\"logout\">Logout</a>"); 
              pw.println("</center></body>");  
    
           
           
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
    }  
    public void doGet(HttpServletRequest req,HttpServletResponse res)  
        throws ServletException,IOException{  
        process(req,res);  
    }  
    public void doPost(HttpServletRequest req,HttpServletResponse res)  
        throws ServletException,IOException{  
        process(req,res);  
    }  
    public void destroy(){  
        super.destroy();  
    }  



}
