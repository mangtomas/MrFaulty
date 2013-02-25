package com.faultsystem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faultsystem.beans.Fault;
import com.faultsystem.dao.FaultDao;

public class AmendFault extends HttpServlet{

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
           
            
           String faultId=req.getParameter("faultId");  
        
           
           pw.println("<h1>Add a new Fault</h1>");  
           pw.println("<form action=amendfaultcl method=post>");  
           pw.println("Fault ID£º<input readonly=\"readonly\" type=text name=faultId value="+faultId+"><br>");
           pw.println("Fault Summary£º<br>");  
           pw.println("<textarea name=summary   style=\"scrollbar-face-color:#aa80ff;border:1px;background-image:url([url]http://expert.csdn.net/images/ad/dby3_database_120.GIF[/url]);color:#000000;font-size:15pt;font-weight:bold\">     </textarea> <br> "); 
          
           pw.println("Fault Detail£º<br>");  
           pw.println("<textarea name=detail   style=\"scrollbar-face-color:#aa80ff;border:1px;background-image:url([url]http://expert.csdn.net/images/ad/dby3_database_120.GIF[/url]);color:#000000;font-size:15pt;font-weight:bold\">     </textarea> <br> "); 
         
           pw.println("Severity Level£º<br>");
           pw.println("<select name=serverity_level>");
           pw.println("<option value=\"1\">1</option>");
           pw.println("<option value=\"2\">2</option>");
           pw.println("<option value=\"3\" selected=\"selected\">3</option>");
           pw.println(" </select><br>");
          
         //  pw.println("Reporter ID£º<input readonly=\"readonly\" type=text name=reporterId value="+reporterid+"><br>");
          
         //pw.println("Project ID£º<input readonly=\"readonly\" type=text name=projectId value="+projectid+"><br>");
          
           pw.println("Fault state£º<br>");
           pw.println("<select name=state>");
           pw.println("<option value=\"1\"selected=\"selected\">None</option>");
           pw.println("<option value=\"2\">Can Not Reproduce</option>");
           pw.println("<option value=\"3\">As Intended</option>");
           pw.println("<option value=\"4\">Fixed</option>");
           pw.println("<option value=\"5\">Duplicate</option>");
           pw.println("<option value=\"6\">3rd party problem</option>");
           pw.println("<option value=\"7\">Awaiting info</option>");
           pw.println(" </select><br>");
          
           pw.println("Current life level£º<br>");
           pw.println("<select name=life>");
           pw.println("<option value=\"1\"selected=\"selected\">New</option>");
           pw.println("<option value=\"2\">In progress</option>");
           pw.println("<option value=\"3\">In Test</option>");
           pw.println("<option value=\"4\">Deferred</option>");
           pw.println("<option value=\"5\">Closed</option>");
           pw.println(" </select><br>");
          
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
