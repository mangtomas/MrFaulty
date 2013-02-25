package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faultsystem.dao.DeveloperDao;

public class AddProject extends HttpServlet {

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
           DeveloperDao dao = new DeveloperDao(); 
            
           String id=req.getParameter("devId");  
           System.out.println(id);
           pw.println("<h1>Add new Project</h1>");  
           pw.println("<form action=addprojectcl method=post>");  
         
           pw.println("Project Name£º<input type=text name=projectName><br>");  
           pw.println("Project Description£º<input type=text name=projectDesc><br>");  
           pw.println("Developer ID£º<input readonly=\"readonly\" type=text name=devId value="+id+"><br>");
           pw.println("<input type=submit value=Submit><br>"); 
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
