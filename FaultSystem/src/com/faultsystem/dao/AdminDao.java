package com.faultsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.faultsystem.beans.Admin;
import com.faultsystem.encryption.Md5;
import com.faultsystem.exceptions.DaoException;



public class AdminDao extends Dao {
	
	
public List<Admin> findAllAdmin()throws DaoException{
	Connection con =null;
	PreparedStatement ps = null;
    ResultSet rs = null;
    List<Admin> admins = new ArrayList<Admin>();
    
    try{
        con = getConnection();
        String query = "SELECT * FROM admin";
        ps = con.prepareStatement(query);
        System.out.println(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            long adminId = rs.getLong("admin_id");
            String adminName = rs.getString("admin_name");
            String password = rs.getString("admin_password");
            String emailAdd = rs.getString("email_add");
            Admin a = new Admin(adminId,adminName,password,emailAdd);
            admins.add(a);
    	
    } 
        }catch (SQLException e) {
        throw new DaoException("findAllAdmins: " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                freeConnection(con);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllUsers(): " + e.getMessage());
        }
    }
    return admins;  // admins may be empty
}
	
public void addAdmin(Admin admin)throws DaoException{
	 Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
    
     try {
    	 PrimaryKeyGeneratorTable pkGen = PrimaryKeyGeneratorTable.getInstance();
    	 con = getConnection();
         long nextPk = pkGen.getNextPK(con);
         System.out.println("pk = "+nextPk);
         String query = "INSERT INTO admin( admin_id, admin_name, admin_password,email_add ) VALUES( ?, ?, ? ,?)";
         ps = con.prepareStatement(query);
         ps.setInt(1, (int)nextPk);
         ps.setString(2, admin.getAdminName());
         Md5 m = new Md5();
         //encrypt the password
         String password = m.md5s(admin.getPassword());
         System.out.println("password = "+password);
         ps.setString(3, password );
         ps.setString(4, admin.getEmailAdd());
         System.out.println(query);
         ps.executeUpdate();

     } catch (SQLException e) {
         throw new DaoException("addAdmin(): " + e.getMessage());
     } finally {
         try {
             if (rs != null) {
                 rs.close();
             }
             if (ps != null) {
                 ps.close();
             }
             if (con != null) {
                 freeConnection(con);
             }
         } catch (SQLException e) {
             throw new DaoException("addAdmin(): " + e.getMessage());
         }
     }
}

public Admin listAdminById(long adminId)throws DaoException{
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Admin admin = null;
    try{
    	 con = getConnection();
         String query = "SELECT * FROM admin where admin_id = ?";
         ps = con.prepareStatement(query);
         System.out.println(query);
         ps.setInt(1, (int)adminId);
         rs = ps.executeQuery();
        if (rs.next()){
        	 long adminIds = rs.getLong("admin_id");
             String adminName = rs.getString("admin_name");
             String password = rs.getString("admin_password");
             String emailAdd = rs.getString("email_add");
             admin = new Admin(adminIds,adminName,password,emailAdd);
        }
    	
    }catch (SQLException e) {
        throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                freeConnection(con);
            }
        } catch (SQLException e) {
            throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
        }
    }
    return admin;
}
public Admin listAdminByNamePassword(String name, String password)throws DaoException{
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Admin admin = null;
    try{
    	 con = getConnection();
         String query = "SELECT * FROM admin where admin_name = ? AND admin_password = ?";
         ps = con.prepareStatement(query);
         System.out.println(query);
         ps.setString(1, name);
         Md5 m = new Md5();
         String enPassword = m.md5s(password);
         ps.setString(2, enPassword);
         
         rs = ps.executeQuery();
        if (rs.next()){
        	 long adminIds = rs.getLong("admin_id");
             String adminName = rs.getString("admin_name");
             String apassword = rs.getString("admin_password");
             String emailAdd = rs.getString("email_add");
             admin = new Admin(adminIds,adminName,apassword,emailAdd);
        }
    	
    }catch (SQLException e) {
        throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                freeConnection(con);
            }
        } catch (SQLException e) {
            throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
        }
    }
    return admin;
}

	public boolean checkExist(String name, String password)throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean userExist = false;
	    try{
	    	 con = getConnection();
	         String query = "SELECT * FROM admin where admin_name = ? AND admin_password = ?";
	         ps = con.prepareStatement(query);
	         System.out.println(query);
	         ps.setString(1, name);
	         Md5 m = new Md5();
	         String enPassword = m.md5s(password);
	         ps.setString(2, enPassword);
	         
	         rs = ps.executeQuery();
	        if (rs.next()){
	        	 userExist = true;
	        }
	    	
	    }catch (SQLException e) {
	        throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
	            if (con != null) {
	                freeConnection(con);
	            }
	        } catch (SQLException e) {
	            throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
	        }
	    }
	    return userExist;
	}

public int deleteAdminById(long adminId)throws DaoException{
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int rows = 0;
    try{
    	con = getConnection();
        String command = "DELETE FROM admin WHERE admin_id = ?";
        System.out.println(command);

        ps = con.prepareStatement(command);
        ps.setInt(1, (int) adminId);
        rows = ps.executeUpdate();
    }catch (SQLException e) {
        throw new DaoException("deleteCustomer(): " + e.getMessage());
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                freeConnection(con);
            }
        } catch (SQLException e) {
            throw new DaoException("deleteCustomer(): " + e.getMessage());
        }
    }
    return rows;
    
}
public static void main(String[] args){
	try{
	AdminDao dao = new AdminDao();
	int a = dao.deleteAdminById(70);
	System.out.println("delete row "+a);
	//Admin a = dao.listAdminByNamePassword("adminTest2", "test");
	//System.out.println("admin with name adminTest2 password test is "+a);
	//Admin a = dao.listAdminById(1);
	//System.out.println("admin with id 1 is "+a);
	//Admin admin = new Admin("adminTest2","test","asda@asdas.com");
	//System.out.println(admin);
	//dao.addAdmin(admin);
	List<Admin> admins = new ArrayList<Admin>();
	 admins = dao.findAllAdmin();
	 for(int i=0;i<admins.size();i++){
		 System.out.println(admins.get(i).toString()); 
	 }
	 
	} catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
}



}
