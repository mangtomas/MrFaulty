package com.faultsystem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faultsystem.dao.ReporterDao;

public class DelReporter extends HttpServlet{
	
	public void init() throws ServletException{}  
    public void process(HttpServletRequest req,HttpServletResponse res)  
        throws ServletException,IOException{  
        try{                        
           res.setContentType("text/html;charset=UTF-8");  
             
           ReporterDao dao = new ReporterDao(); 
            
           String id=req.getParameter("reporterId");  
           System.out.println(id);
          if( dao.deleteReporterById(Long.parseLong(id))==1){  
              //ɾ���ɹ�  
              res.sendRedirect("ok");  
          }else{  
              //ɾ��ʧ��  
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
