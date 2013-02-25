package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Success extends HttpServlet {
	 public void init() throws ServletException{}  
	    public void process(HttpServletRequest req,HttpServletResponse res)  
	        throws ServletException,IOException{  
	        try{              
	          
	           res.setContentType("text/html;charset=UTF-8");  
	           
	            PrintWriter pw=res.getWriter();  
	          
	            pw.println("<html>");  
	            pw.println("<body bgcolor=#345D6E>");  
	           
	            pw.println("<h1>Operation successed</h1>");  
	            pw.println("<a href=\"adminwelcome\" mce_href=\"adminwelcome\">Back to projects</a>  <a href=\"allfaults\" mce_href=\"allfaults\">View Faults</a>");  
	            
	            pw.println("</body>");  
	            pw.println("</html>");  
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
