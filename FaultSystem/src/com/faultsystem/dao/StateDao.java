package com.faultsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.faultsystem.beans.State;
import com.faultsystem.exceptions.DaoException;

public class StateDao extends Dao{
	
	public List<State> listAllState()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<State> states = new ArrayList<State>();
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM state";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long stateId = rs.getLong("state_id");
	            String stateName = rs.getString("state_name");
	            String stateDetail = rs.getString("state_detail");
	            State a = new State(stateId,stateName,stateDetail);
	            states.add(a);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllState: " + e.getMessage());
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
	            throw new DaoException("listAllState(): " + e.getMessage());
	        }
	    }
	    return states;  // states may be empty
	}
	public List<State> listAllStateOnlyName()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<State> states = new ArrayList<State>();
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM state";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long stateId = rs.getLong("state_id");
	            String stateName = rs.getString("state_name");
	            String stateDetail = rs.getString("state_detail");
	            State a = new State(stateName);
	            states.add(a);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllStateOnlyName: " + e.getMessage());
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
	            throw new DaoException("listAllStateOnlyName(): " + e.getMessage());
	        }
	    }
	    return states;  // states may be empty
	}
	public String findStateNameById(long id)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    String stateName = "";
	    
	    try{
	        con = getConnection();
	        String query = "SELECT state_name FROM state WHERE state_id = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setInt(1, (int)id);
	        rs = ps.executeQuery();
	       if(rs.next()) {
	         stateName = rs.getString("state_name");
	           
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("findStateNameById: " + e.getMessage());
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
	            throw new DaoException("findStateNameById(): " + e.getMessage());
	        }
	    }
	    return stateName;  // states may be empty
	}
	
	
	
	public static void main(String[] args){
		
		try{

			StateDao dao = new StateDao();
			String a = dao.findStateNameById(1);
			System.out.println(a);
			List<State> states = new ArrayList<State>();
			//states = dao.listAllState();
			states = dao.listAllStateOnlyName();
			for(int i = 0; i< states.size();i++){
				System.out.println(states.get(i).toString());
			}
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	

}
