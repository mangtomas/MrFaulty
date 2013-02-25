package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterI extends HttpServlet {
	

	public RegisterI(){  
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
             
            pw.println("<h1>Please choose the type of your account</h1>");  
            pw.println("<form action=registerII method=post>");  
            pw.println("Please select your account type£º");
            pw.println("<select name=reguserType>");
            pw.println("<option value=\"0\">Administrator</option>");
            pw.println("<option value=\"1\">Developer</option>");
            pw.println("<option value=\"2\" selected=\"selected\">Reporter</option>");
            pw.println(" </select><br>");
            pw.println("<input type=submit value=loging>");
          //  pw.println("<td><a href=\"registerI\" mce_href=\"registerI\" onclick=/\"return window.confirm('Delete this record?')/\">Register</a></td>");  

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
