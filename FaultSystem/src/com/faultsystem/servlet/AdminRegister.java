package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminRegister extends HttpServlet {
	public AdminRegister(){  
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
            pw.println("<h1>Administrator Register</h1>");  
            pw.println("<form action=adminregistercl method=post>");  
            pw.println("User Name£º<input type=text name=username><br>");  
            pw.println("Password£º<input type=password name=passwd><br>"); 
            pw.println("Your Email Address£º<input type=text name=email><br>");
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
