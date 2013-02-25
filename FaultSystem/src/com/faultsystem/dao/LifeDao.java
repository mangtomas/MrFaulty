package com.faultsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.faultsystem.beans.Life;
import com.faultsystem.exceptions.DaoException;

public class LifeDao extends Dao{

	public List<Life> listAllLife()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Life> lifes = new ArrayList<Life>();
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM life";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long lifeId = rs.getLong("life_id");
	            String lifeName = rs.getString("life_name");
	            String lifeDetail = rs.getString("life_detail");
	            Life a = new Life(lifeId,lifeName,lifeDetail);
	            lifes.add(a);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllLife: " + e.getMessage());
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
	            throw new DaoException("listAllLife(): " + e.getMessage());
	        }
	    }
	    return lifes;  // lifes may be empty
	}
	public List<Life> listAllLifeOnlyName()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Life> lifes = new ArrayList<Life>();
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM life";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long lifeId = rs.getLong("life_id");
	            String lifeName = rs.getString("life_name");
	            String lifeDetail = rs.getString("life_detail");
	            Life a = new Life(lifeName);
	            lifes.add(a);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllLifeOnlyName: " + e.getMessage());
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
	            throw new DaoException("listAllLifeOnlyName(): " + e.getMessage());
	        }
	    }
	    return lifes;  // lifes may be empty
	}
	
	public String findLifeNameById(long id){
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    String lifeName = "";
	    
	    try{
	        con = getConnection();
	        String query = "SELECT life_name FROM life WHERE life_id = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setInt(1, (int)id);
	        rs = ps.executeQuery();
	       if(rs.next()) {
	         lifeName = rs.getString("life_name");
	           
	    	
	    } 
	        }catch (SQLException e) {
	        try {
				throw new DaoException("findStateNameById: " + e.getMessage());
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	            try {
					throw new DaoException("findStateNameById(): " + e.getMessage());
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    }
	    return lifeName;  // states may be empty
	}
	
	public static void main(String[] args){
		
		try{

			LifeDao dao = new LifeDao();
			String a = dao.findLifeNameById(1);
			System.out.println(a);
			
			List<Life> lifes = new ArrayList<Life>();
			lifes = dao.listAllLife();
			//lifes = dao.listAllLifeOnlyName();
			for(int i = 0; i< lifes.size();i++){
				System.out.println(lifes.get(i).toString());
			}
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	

}
