package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	public Login(){  
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
            //get the error message 
            String info=(String)req.getParameter("info");  
            if(info!=null){  
                pw.println("<h1>Your username and password is wrong!</h1><br>");  
            }  
            pw.println("<h1>Login please</h1>");  
            pw.println("<form action=logincl method=post>");  
            pw.println("Please select your account type£º");
            pw.println("<select name=userType>");
            pw.println("<option value=\"0\">Administrator</option>");
            pw.println("<option value=\"1\">Developer</option>");
            pw.println("<option value=\"2\" selected=\"selected\">Reporter</option>");
            pw.println(" </select><br>");
            pw.println("User Name£º<input type=text name=username><br>");  
            pw.println("Password£º<input type=password name=passwd><br>");  
            pw.println("<input type=checkbox name=keep value=2>Remember you in 2 weeks<br>");  
            pw.println("<input type=submit value=loging>");
            pw.println("<td><a href=\"registerI\" mce_href=\"registerI\" onclick=/\"return window.confirm('Delete this record?')/\">Register</a></td>");  

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
