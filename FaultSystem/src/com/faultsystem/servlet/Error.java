package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Error extends HttpServlet {

	
	  public void init() throws ServletException{}  
	    public void process(HttpServletRequest req,HttpServletResponse res)  
	        throws ServletException,IOException{  
	        try{              
	           
	           res.setContentType("text/html;charset=UTF-8");  
	           String errorMessage=req.getParameter("errorMsg");  
	            PrintWriter pw=res.getWriter();  
	            
	            pw.println("<html>");  
	            pw.println("<body bgcolor=#345D6E>");  
	            
	            pw.println("<h1>Operation faild!"+errorMessage+" </h1>");  
	            pw.println("<a href=\"adminwelcome\" mce_href=\"adminwelcome\">Back to projects</a>  <a href=\"delfaults\" mce_href=\"delfaults\">View Faults</a>");  
	            
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
