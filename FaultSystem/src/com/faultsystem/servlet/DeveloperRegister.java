package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeveloperRegister extends HttpServlet {
	public DeveloperRegister(){  
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
            pw.println("<h1>Developer Register</h1>");  
            pw.println("<form action=devregcl method=post>");  
            pw.println("User Name£º<input type=text name=username><br>");  
            pw.println("Password£º<input type=password name=passwd><br>"); 
            pw.println("Your work£º<input type=text name=work><br>");
            pw.println("<textarea name=selfdesc   style=\"scrollbar-face-color:#aa80ff;border:1px;background-image:url([url]http://expert.csdn.net/images/ad/dby3_database_120.GIF[/url]);color:#000000;font-size:15pt;font-weight:bold\">     </textarea> <br> "); 
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
