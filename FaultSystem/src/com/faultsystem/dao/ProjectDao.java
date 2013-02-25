package com.faultsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.faultsystem.beans.Project;
import com.faultsystem.exceptions.DaoException;
public class ProjectDao extends Dao {
	public List<Project> listAllProjects() throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Project> projects = new ArrayList<Project>();
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM project";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long devId = rs.getLong("project_id");
	            String name = rs.getString("project_name");
	            String password = rs.getString("project_desc");
	            String downloadLink = rs.getString("download_link");
	            long ddevId = rs.getLong("developer_developer_id");
	            Timestamp createTime = rs.getTimestamp("create_time");
	            Timestamp updateTime = rs.getTimestamp("last_update_time");
	            Project p = new Project(devId,name,password,downloadLink,ddevId,createTime,updateTime);
	            projects.add(p);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllProjects: " + e.getMessage());
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
	            throw new DaoException("listAllProjects(): " + e.getMessage());
	        }
	    }
	    return projects;  // projects may be empty
		
	}
	
	public int addNewProject(Project project)throws DaoException{
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;
       
        
        try {
        	 con = getConnection();
             // First check that developerName does not already exist
             String query = "SELECT * FROM project WHERE project_name = ?";
             ps = con.prepareStatement(query);
             ps.setString(1, project.getProjectName());
             rs = ps.executeQuery();
             if (rs.next()) {
                 throw new SQLException("Project with name  " + project.getProjectName() + " already exists");
             }
             rs.close();
             rs = null;

             PrimaryKeyGeneratorTable pkGen = PrimaryKeyGeneratorTable.getInstance();
             long nextPk = pkGen.getNextPK(con);
             //get current time
             java.sql.Timestamp sqlTime =  new Timestamp(System.currentTimeMillis()); 
             String command = "INSERT INTO project(project_id,project_name,project_desc,download_link,developer_developer_id,create_time,last_update_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
             ps = con.prepareStatement(command);
             ps.setInt(1, (int) nextPk);
             ps.setString(2, project.getProjectName());
             ps.setString(3, project.getProjetDesc());
             ps.setString(4,project.getDownloadLink());
             ps.setLong(5, project.getDevId());
             ps.setTimestamp(6, sqlTime);
             ps.setTimestamp(7, sqlTime);
           
             System.out.println(command);
             rows = ps.executeUpdate();
             if (rows == 1) {
                 project.setProjectId(nextPk);
                 project.setCreateTime(sqlTime);
                 project.setUpdateTime(sqlTime);
                 }
        	
        	
        }catch (SQLException e) {
            throw new DaoException("addNewProject(): " + e.getMessage());
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
                throw new DaoException("addNewProject(): " + e.getMessage());
            }
        }
        return rows;	// The number of rows affected by the update (should be 1)
		
	}
	public String findProjectNameById(long id) throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    String projectName = "";
	    try{
	        con = getConnection();
	        String query = "SELECT project_name FROM project WHERE project_id = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setInt(1, (int)id);
	        rs = ps.executeQuery();
	     
	        if (rs.next()) {
	            
	        	projectName = rs.getString("project_name");
	            
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("listAllProjects: " + e.getMessage());
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
	            throw new DaoException("listAllProjects(): " + e.getMessage());
	        }
	    }
	    return projectName;  // projects may be empty
		
	}
	
	public static void main(String[] args){
		try{
			ProjectDao dao = new ProjectDao();
			String a = dao.findProjectNameById(1);
			//Project p = new Project("Protest1","testProject","nolink",1);
			//int a = dao.addNewProject(p);
			System.out.println(a);
			List<Project> projects = new ArrayList<Project>();
			projects = dao.listAllProjects();
			for(int i = 0; i < projects.size();i++){
				System.out.println(projects.get(i).toString());
				}
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

}
