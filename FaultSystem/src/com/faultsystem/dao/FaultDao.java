package com.faultsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.faultsystem.beans.Fault;
import com.faultsystem.exceptions.DaoException;



public class FaultDao extends Dao {
	public List<Fault> listAllFault()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Fault> faults = new ArrayList<Fault>();
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM fault";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long faultId = rs.getLong("fault_id");
	            String summary = rs.getString("summary");
	            String detail = rs.getString("detail");
	            int sLevel = rs.getInt("severity_level");
	            long reporterId = rs.getLong("reporter_reporter_id");
	            long projectId = rs.getLong("project_project_id");
	            long stateId = rs.getLong("state_state_id");
	            long lifeId = rs.getLong("life_life_id");
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            Fault f = new Fault(faultId,summary,detail,sLevel,reporterId,projectId,stateId,lifeId,createTime,updateTime);
	            faults.add(f);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllFault: " + e.getMessage());
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
	            throw new DaoException("listAllFault(): " + e.getMessage());
	        }
	    }
	    return faults;  // faults may be empty
		
	}
	public List<Fault> listAllFaultWithNameSlow()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Fault> faults = new ArrayList<Fault>();
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM fault";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        ReporterDao rdao = new ReporterDao();
	        ProjectDao pdao = new ProjectDao();
	        StateDao sdao = new StateDao();
	        LifeDao ldao = new LifeDao();
	        while (rs.next()) {
	            long faultId = rs.getLong("fault_id");
	            String summary = rs.getString("summary");
	            String detail = rs.getString("detail");
	            int sLevel = rs.getInt("severity_level");
	            long reporterId = rs.getLong("reporter_reporter_id");
	            
	            String reporterName = rdao.findReorterNameByID(reporterId);
	            long projectId = rs.getLong("project_project_id");
	            String projectName = pdao.findProjectNameById(projectId);
	            long stateId = rs.getLong("state_state_id");
	            String stateName = sdao.findStateNameById(stateId);
	            long lifeId = rs.getLong("life_life_id");
	            String lifeName = ldao.findLifeNameById(lifeId);
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            Fault f = new Fault(faultId,summary,detail,sLevel,reporterName,projectName,stateName,lifeName,createTime,updateTime);
	            faults.add(f);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllFault: " + e.getMessage());
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
	            throw new DaoException("listAllFault(): " + e.getMessage());
	        }
	    }
	    return faults;  // faults may be empty
		
	}
	public List<Fault> listAllFaultWithNameQuick()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Fault> faults = new ArrayList<Fault>();
	    try{
	        con = getConnection();
	        String query = "select fault.fault_id,fault.summary,fault.detail,fault.severity_level,reporter.name, project.project_name, state.state_name, life.life_name,fault.create_time,fault.last_update_time from ((((fault inner join reporter on fault.reporter_reporter_id = reporter.reporter_id)inner join project on fault.project_project_id=project.project_id)inner join state on fault.state_state_id = state.state_id) inner join life on fault.life_life_id = life.life_id)";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long faultId = rs.getLong("fault_id");
	            String summary = rs.getString("summary");
	            String detail = rs.getString("detail");
	            int sLevel = rs.getInt("severity_level");
	            String reporterName = rs.getString("reporter.name");
	            String projectName = rs.getString("project.project_name");;
	            String stateName = rs.getString("state.state_name");
	            String lifeName = rs.getString("life.life_name");
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            Fault f = new Fault(faultId,summary,detail,sLevel,reporterName,projectName,stateName,lifeName,createTime,updateTime);
	            faults.add(f);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllFault: " + e.getMessage());
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
	            throw new DaoException("listAllFault(): " + e.getMessage());
	        }
	    }
	    return faults;  // faults may be empty
		
	}
	public List<Fault> listFaultByProjectId(long proId)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Fault> faults = new ArrayList<Fault>();
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM fault where project_project_id = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setInt(1, (int)proId);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long faultId = rs.getLong("fault_id");
	            String summary = rs.getString("summary");
	            String detail = rs.getString("detail");
	            int sLevel = rs.getInt("severity_level");
	            long reporterId = rs.getLong("reporter_reporter_id");
	            long projectId = rs.getLong("project_project_id");
	            long stateId = rs.getLong("state_state_id");
	            long lifeId = rs.getLong("life_life_id");
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            Fault f = new Fault(faultId,summary,detail,sLevel,reporterId,projectId,stateId,lifeId,createTime,updateTime);
	            faults.add(f);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listFaultByProjectId: " + e.getMessage());
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
	            throw new DaoException("listFaultByProjectId(): " + e.getMessage());
	        }
	    }
	    return faults;  // faults may be empty
		
	}
	public Fault listFaultById(long faultId)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    Fault fault =null;
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM fault where fault_id = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setInt(1, (int)faultId);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            long faultIds = rs.getLong("fault_id");
	            String summary = rs.getString("summary");
	            String detail = rs.getString("detail");
	            int sLevel = rs.getInt("severity_level");
	            long reporterId = rs.getLong("reporter_reporter_id");
	            long projectId = rs.getLong("project_project_id");
	            long stateId = rs.getLong("state_state_id");
	            long lifeId = rs.getLong("life_life_id");
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            fault = new Fault(faultId,summary,detail,sLevel,reporterId,projectId,stateId,lifeId,createTime,updateTime);
	            
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listFaultById: " + e.getMessage());
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
	            throw new DaoException("listFaultById(): " + e.getMessage());
	        }
	    }
	    return fault;  
		
	}
	
	public int addNewFault(Fault fault)throws DaoException{
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;
       
        
        try {
        	 con = getConnection();
             // First check that developerName does not already exist
             String query = "SELECT * FROM fault WHERE summary = ?";
             ps = con.prepareStatement(query);
             ps.setString(1, fault.getSummary());
             rs = ps.executeQuery();
             if (rs.next()) {
                 throw new SQLException("fault with name  " + fault.getSummary() + " already exists");
             }
             rs.close();
             rs = null;

             PrimaryKeyGeneratorTable pkGen = PrimaryKeyGeneratorTable.getInstance();
             long nextPk = pkGen.getNextPK(con);
             //get current time
             java.sql.Timestamp sqlTime =  new Timestamp(System.currentTimeMillis()); 
             String command = "INSERT INTO fault(fault_id,summary,detail,severity_level,reporter_reporter_id,project_project_id,state_state_id,life_life_id,create_time,last_update_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
             ps = con.prepareStatement(command);
             ps.setInt(1, (int) nextPk);
             ps.setString(2, fault.getSummary());
             ps.setString(3, fault.getDetail());
             ps.setInt(4,fault.getSeverityLevel());
             ps.setLong(5, fault.getReporterId());
             ps.setLong(6, fault.getProjectId());
             ps.setLong(7, fault.getStateId());
             ps.setLong(8, fault.getLifeId());
             ps.setTimestamp(9, sqlTime);
             ps.setTimestamp(10, sqlTime);
           
             System.out.println(command);
             rows = ps.executeUpdate();
             if (rows == 1) {
                 fault.setFaultId(nextPk);
                 fault.setCreateTime(sqlTime);
                 fault.setUpdateTime(sqlTime);
                 }
        	
        	
        }catch (SQLException e) {
            throw new DaoException("addNewFault(): " + e.getMessage());
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
                throw new DaoException("addNewFault(): " + e.getMessage());
            }
        }
        return rows;	// The number of rows affected by the update (should be 1)
	}
	
	public int amendFaultById(long faultId, Fault fault)throws DaoException{
		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;
        
        try{
        	 con = getConnection();
             // First check that if does not already exist
        	 String query = "SELECT * FROM fault WHERE fault_id = ?";
             ps = con.prepareStatement(query);
             ps.setInt(1, (int)faultId);
             rs = ps.executeQuery();
             if (rs.next()) {
            	  String command = "UPDATE fault SET summary = ?, detail = ?, severity_level = ?, state_state_id = ?, life_life_id = ?,last_update_time = ? WHERE fault_id = ?";
                  ps = con.prepareStatement(command);
                  ps.setString(1, fault.getSummary());
                  ps.setString(2, fault.getDetail());
                  ps.setInt(3, fault.getSeverityLevel());
                  ps.setLong(4, fault.getStateId());
                  ps.setLong(5, fault.getLifeId());
                  java.sql.Timestamp sqlTime =  new Timestamp(System.currentTimeMillis()); 
                  ps.setTimestamp(6, sqlTime);
                  ps.setLong(7, faultId);
                  System.out.println(command);
                  rows = ps.executeUpdate();
            	 
             }else{
            	 throw new SQLException("fault with id " + faultId + " not exists");
            	 
             }
            
        	 
        	
        }catch (SQLException e) {
            throw new DaoException("amendFaultById(): " + e.getMessage());
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
                throw new DaoException("amendFaultById(): " + e.getMessage());
            }
        }
        return rows;	// The number of rows affected by the update (should be 1)
	}
	
	public int deleteFaultById(long faultId)throws DaoException{
		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;
        
        try{
        	 con = getConnection();
             // First check that if does not already exist
        	 String query = "SELECT * FROM fault WHERE fault_id = ?";
             ps = con.prepareStatement(query);
             ps.setInt(1, (int)faultId);
             rs = ps.executeQuery();
             if (rs.next()) {
            	  String command = "DELETE FROM fault WHERE fault_id = ?";
                  ps = con.prepareStatement(command);
                  ps.setLong(1, faultId);
                  System.out.println(command);
                  rows = ps.executeUpdate();
            	 
             }else{
            	 throw new SQLException("fault with id " + faultId + " not exists");
            	 
             }
            
        	 
        	
        }catch (SQLException e) {
            throw new DaoException("amendFaultById(): " + e.getMessage());
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
                throw new DaoException("amendFaultById(): " + e.getMessage());
            }
        }
        return rows;	// The number of rows affected by the update (should be 1)
	}
	public List<Fault> listAllFaultWithNameByProjectId(long id)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Fault> faults = new ArrayList<Fault>();
	    try{
	        con = getConnection();
	        String query = "select fault.fault_id,fault.summary,fault.detail,fault.severity_level,reporter.name, project.project_name, state.state_name, life.life_name,fault.create_time,fault.last_update_time from ((((fault inner join reporter on fault.reporter_reporter_id = reporter.reporter_id)inner join project on fault.project_project_id=project.project_id)inner join state on fault.state_state_id = state.state_id) inner join life on fault.life_life_id = life.life_id) WHERE fault.project_project_id=?";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, (int)id);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long faultId = rs.getLong("fault_id");
	            String summary = rs.getString("summary");
	            String detail = rs.getString("detail");
	            int sLevel = rs.getInt("severity_level");
	            String reporterName = rs.getString("reporter.name");
	            String projectName = rs.getString("project.project_name");;
	            String stateName = rs.getString("state.state_name");
	            String lifeName = rs.getString("life.life_name");
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            Fault f = new Fault(faultId,summary,detail,sLevel,reporterName,projectName,stateName,lifeName,createTime,updateTime);
	            faults.add(f);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllFault: " + e.getMessage());
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
	            throw new DaoException("listAllFault(): " + e.getMessage());
	        }
	    }
	    return faults;  // faults may be empty
		
	}
	public List<Fault> listAllFaultWithNameByReporterId(long id)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Fault> faults = new ArrayList<Fault>();
	    try{
	        con = getConnection();
	        String query = "select fault.fault_id,fault.summary,fault.detail,fault.severity_level,reporter.name, project.project_name, state.state_name, life.life_name,fault.create_time,fault.last_update_time from ((((fault inner join reporter on fault.reporter_reporter_id = reporter.reporter_id)inner join project on fault.project_project_id=project.project_id)inner join state on fault.state_state_id = state.state_id) inner join life on fault.life_life_id = life.life_id) WHERE fault.reporter_reporter_id=?";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, (int)id);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long faultId = rs.getLong("fault_id");
	            String summary = rs.getString("summary");
	            String detail = rs.getString("detail");
	            int sLevel = rs.getInt("severity_level");
	            String reporterName = rs.getString("reporter.name");
	            String projectName = rs.getString("project.project_name");;
	            String stateName = rs.getString("state.state_name");
	            String lifeName = rs.getString("life.life_name");
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            Fault f = new Fault(faultId,summary,detail,sLevel,reporterName,projectName,stateName,lifeName,createTime,updateTime);
	            faults.add(f);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllFault: " + e.getMessage());
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
	            throw new DaoException("listAllFault(): " + e.getMessage());
	        }
	    }
	    return faults;  // faults may be empty
		
	}
	
	public static void main(String[] args){
		try{
			FaultDao dao = new FaultDao();
			//int a = dao.deleteFaultById(120);
			//System.out.println(a);
			//Fault a = new Fault("faultTest3","summarytest3",4,1,1,3,4);
			//dao.amendFaultById(120, a);
			//Fault a = dao.listFaultById(1);
			//System.out.println(a);
			//Fault a = new Fault("faultTest2","summarytest2",4,1,1,3,4);
			//dao.addNewFault(a);
			List<Fault>faults = new ArrayList<Fault>();
			faults = dao.listAllFaultWithNameByReporterId(1);
			//faults = dao.listAllFaultWithNameByProjectId(1);
			//faults = dao.listFaultByProjectId(1);
			//faults = dao.listAllFault();
			//faults = dao.listAllFaultWithNameSlow();
			faults = dao.listAllFaultWithNameQuick();
			String d = "d"+4;
			System.out.println(d);
			for(int i =0; i < faults.size(); i++){
				System.out.println(faults.get(i).toString());
			}
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
		
		
	}

}
