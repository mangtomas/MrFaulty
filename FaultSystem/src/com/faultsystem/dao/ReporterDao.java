package com.faultsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.faultsystem.beans.Reporter;
import com.faultsystem.encryption.Md5;
import com.faultsystem.exceptions.DaoException;

public class ReporterDao extends Dao{
	
	public List<Reporter> listAllReporter()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Reporter> reporters = new ArrayList<Reporter>();
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM reporter";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long devId = rs.getLong("reporter_id");
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	            String work = rs.getString("work");
	            String selfDesc = rs.getString("self_desc");
	            String emailAdd = rs.getString("email");
	            int credit = rs.getInt("credit");
	            Timestamp time = rs.getTimestamp("reg_time");
	            Reporter d = new Reporter(devId,name,password,work,selfDesc,emailAdd,credit,time);
	            reporters.add(d);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("findAllReporters: " + e.getMessage());
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
	    return reporters;  // reporters may be empty
	}
	
	public Reporter findReporterById(long repId)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    Reporter reporter = null;
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM reporter where reporter_id = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setInt(1, (int)repId);
	        rs = ps.executeQuery();
	     
	        if (rs.next()) {
	            long devId = rs.getLong("reporter_id");
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	            String work = rs.getString("work");
	            String selfDesc = rs.getString("self_desc");
	            String emailAdd = rs.getString("email");
	            int credit = rs.getInt("credit");
	            Timestamp time = rs.getTimestamp("reg_time");
	            reporter= new Reporter(devId,name,password,work,selfDesc,emailAdd,credit,time);
	          
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("findAllReporters: " + e.getMessage());
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
	    return reporter;  // reporters may be empty
	}
	
	public Reporter findReporterByNamePassword(String name,String password)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    Reporter reporter = null;
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM reporter where name = ? AND password = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setString(1, name);
	        Md5 m = new Md5();
	        String enPassword = m.md5s(password);
	        ps.setString(2, enPassword);
	        rs = ps.executeQuery();
	            if (rs.next()) {
	            long devId = rs.getLong("reporter_id");
	            String names = rs.getString("name");
	            String passwords = rs.getString("password");
	            String work = rs.getString("work");
	            String selfDesc = rs.getString("self_desc");
	            String emailAdd = rs.getString("email");
	            int credit = rs.getInt("credit");
	            Timestamp time = rs.getTimestamp("reg_time");
	            reporter= new Reporter(devId,names,passwords,work,selfDesc,emailAdd,credit,time);
	          
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("findReporterByNamePassword: " + e.getMessage());
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
	            throw new DaoException("findReporterByNamePassword(): " + e.getMessage());
	        }
	    }
	    return reporter;  // reporters may be empty
	}

	public boolean checkExist(String name,String password)throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean r = false;
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM reporter where name = ? AND password = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setString(1, name);
	        Md5 m = new Md5();
	        String enPassword = m.md5s(password);
	        ps.setString(2, enPassword);
	        rs = ps.executeQuery();
	            if (rs.next()) {
	            r=true;
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("findReporterByNamePassword: " + e.getMessage());
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
	            throw new DaoException("findReporterByNamePassword(): " + e.getMessage());
	        }
	    }
	    return r;  
	}
	
	public int addNewReporter(Reporter reporter)throws DaoException{
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rows = 0;
       
        
        try {
        	 con = getConnection();
             // First check that developerName does not already exist
             String query = "SELECT * FROM reporter WHERE name = ?";
             ps = con.prepareStatement(query);
             ps.setString(1, reporter.getName());
             rs = ps.executeQuery();
             if (rs.next()) {
                 throw new SQLException("reporter with name  " + reporter.getName() + " already exists");
             }
             rs.close();
             rs = null;

             PrimaryKeyGeneratorTable pkGen = PrimaryKeyGeneratorTable.getInstance();
             long nextPk = pkGen.getNextPK(con);
             //get current time
             java.sql.Timestamp sqlTime =  new Timestamp(System.currentTimeMillis()); 
             String command = "INSERT INTO reporter(reporter_id,name,password,work,self_desc,email,credit,reg_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
             ps = con.prepareStatement(command);
             ps.setInt(1, (int) nextPk);
             ps.setString(2, reporter.getName());
             Md5 m = new Md5();
             //encrypt the password
             String password = m.md5s(reporter.getPassword());
             ps.setString(3, password);
             ps.setString(4, reporter.getWork());
             ps.setString(5, reporter.getSelfDesc());
             ps.setString(6, reporter.getEmailAdd());
             ps.setInt(7, reporter.getCredit());
             ps.setTimestamp(8, sqlTime);
           
             System.out.println(command);
             rows = ps.executeUpdate();
             if (rows == 1) {
                 reporter.setReporterId(nextPk);
                 reporter.setRegTime(sqlTime);
             }
        	
        	
        }catch (SQLException e) {
            throw new DaoException("addNewReporter(): " + e.getMessage());
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
                throw new DaoException("addNewReporter(): " + e.getMessage());
            }
        }
        return rows;	// The number of rows affected by the update (should be 1)
	}
	

	public int deleteReporterById(long repId)throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int rows = 0;
	    try{
	    	con = getConnection();
	        String command = "DELETE FROM reporter WHERE reporter_id = ?";
	        System.out.println(command);

	        ps = con.prepareStatement(command);
	        ps.setInt(1, (int) repId);
	        rows = ps.executeUpdate();
	    }catch (SQLException e) {
	        throw new DaoException("deleteReporterById(): " + e.getMessage());
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
	            throw new DaoException("deleteReporterById(): " + e.getMessage());
	        }
	    }
	    return rows;
		
	}
	public String findReorterNameByID(long repId){
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    String reporterName = "";
	    
	    try{
	        con = getConnection();
	        String query = "SELECT name FROM reporter where reporter_id = ?";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        ps.setInt(1, (int)repId);
	        rs = ps.executeQuery();
	     
	        if (rs.next()) {
	            
	            reporterName = rs.getString("name");
	            
	    	
	    } 
	        }catch (SQLException e) {
	        try {
				throw new DaoException("findAllReporters: " + e.getMessage());
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
					throw new DaoException("findAllUsers(): " + e.getMessage());
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    }
	    return reporterName;  // reporters may be empty
	}


	public long findReporterdByName(String name) throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    long d =0;
	    try{
	    	 con = getConnection();
	         String query = "SELECT reporter_id FROM reporter where name = ?";
	         ps = con.prepareStatement(query);
	         System.out.println(query);
	         ps.setString(1, name);
	         rs = ps.executeQuery();
	        if (rs.next()){
	        	d = rs.getLong("reporter_id");
	        }
	    }catch (SQLException e) {
		        try {
					throw new DaoException("findReporterdByName(): " + e.getMessage());
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
		            throw new DaoException("findReporterdByName(): " + e.getMessage());
		        }
		    }
		    return d;
	        
	}

	public static void main(String[] args){
		try{
			ReporterDao dao = new ReporterDao();
			long a = dao.findReporterdByName("John");
			//String a = dao.findReorterNameByID(1);
			//int a = dao.deleteReporterById(150);
			//Reporter reporter = new Reporter("reporterTest2","test","nowork","i am bad","dad@da.com",1);
			//int a = dao.addNewReporter(reporter);
			//Reporter a = dao.findReporterByNamePassword("Ammy", "asd");
			//Reporter a = dao.findReporterById(1);
			System.out.println(a);
			
			List<Reporter> reporters = new ArrayList<Reporter>();
			 reporters = dao.listAllReporter();
			 for(int i=0;i<reporters.size();i++){
				 System.out.println(reporters.get(i).toString()); 
			 }
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
