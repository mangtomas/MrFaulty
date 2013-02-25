package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionValidation extends HttpServlet{
	public QuestionValidation(){  
        super();  
    }  
      
    public void init() throws ServletException{}  
    public void process(HttpServletRequest req,HttpServletResponse res)  
        throws ServletException,IOException{  
        try{              
           //Set the type of page 
           res.setContentType("text/html;charset=UTF-8");  
            //Business logic
            PrintWriter pw=res.getWriter();  
           
            pw.println("<html>");  
            pw.println("<body>");  
            pw.println("<h1>Please enter the security code before regist the admin account.</h1>");  
            pw.println("<form action=adminregcl method=post>");  
            pw.println("The security code is £º<input type=text name=sec><br>");
            pw.println("<input type=submit value=submit>");
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
