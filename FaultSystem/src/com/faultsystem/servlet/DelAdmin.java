package com.faultsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faultsystem.dao.AdminDao;

public class DelAdmin extends HttpServlet {
	

	public void init() throws ServletException{}  
    public void process(HttpServletRequest req,HttpServletResponse res)  
        throws ServletException,IOException{  
        try{                        
           res.setContentType("text/html;charset=UTF-8");  
             
           AdminDao dao = new AdminDao(); 
            
           String id=req.getParameter("adminId");  
           System.out.println(id);
          if( dao.deleteAdminById(Long.parseLong(id))==1){  
              //É¾³ý³É¹¦  
              res.sendRedirect("ok");  
          }else{  
              //É¾³ýÊ§°Ü  
              res.sendRedirect("erro");  
              return;
          }  
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
