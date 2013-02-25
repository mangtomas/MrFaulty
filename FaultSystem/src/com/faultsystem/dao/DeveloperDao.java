package com.faultsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.faultsystem.beans.Developer;
import com.faultsystem.encryption.Md5;
import com.faultsystem.exceptions.DaoException;




public class DeveloperDao extends Dao{
	
	public List<Developer> findAllDeveloper()throws DaoException{
		Connection con =null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Developer> developers = new ArrayList<Developer>();
	    
	    try{
	        con = getConnection();
	        String query = "SELECT * FROM developer";
	        ps = con.prepareStatement(query);
	        System.out.println(query);
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            long devId = rs.getLong("developer_id");
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	            String work = rs.getString("work");
	            String selfDesc = rs.getString("self_desc");
	            String emailAdd = rs.getString("email_add");
	            Timestamp time = rs.getTimestamp("reg_time");
	            Developer d = new Developer(devId,name,password,work,selfDesc,emailAdd,time);
	            developers.add(d);
	    	
	    } 
	        }catch (SQLException e) {
	        throw new DaoException("findAllDevelopers: " + e.getMessage());
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
	    return developers;  // developers may be empty
	}
	
	public int addDeveloper(Developer dev)throws DaoException {
			Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        int rows = 0;
	       
	        
	        try {
	        	 con = getConnection();
	             // First check that developerName does not already exist
	             String query = "SELECT * FROM developer WHERE name = ?";
	             ps = con.prepareStatement(query);
	             ps.setString(1, dev.getName());
	             rs = ps.executeQuery();
	             if (rs.next()) {
	                 throw new SQLException("Developername " + dev.getName() + " already exists");
	             }
	             rs.close();
	             rs = null;

	             PrimaryKeyGeneratorTable pkGen = PrimaryKeyGeneratorTable.getInstance();
	             long nextPk = pkGen.getNextPK(con);
	             //get current time
	             java.sql.Timestamp sqlTime =  new Timestamp(System.currentTimeMillis()); 
	             String command = "INSERT INTO developer(developer_id,name,password,work,self_desc,email_add,reg_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
	             ps = con.prepareStatement(command);
	             ps.setInt(1, (int) nextPk);
	             ps.setString(2, dev.getName());
	             Md5 m = new Md5();
	             //encrypt the password
	             String password = m.md5s(dev.getPassword());
	             ps.setString(3, password);
	             ps.setString(4, dev.getWork());
	             ps.setString(5, dev.getSelfDesc());
	             ps.setString(6, dev.getEmailAdd());
	             ps.setTimestamp(7, sqlTime);
	           
	             System.out.println(command);
	             rows = ps.executeUpdate();
	             if (rows == 1) {
	                 dev.setDeveloperId(nextPk);
	                 dev.setRegTime(sqlTime);
	             }
	        	
	        	
	        }catch (SQLException e) {
	            throw new DaoException("addUserPkGen(): " + e.getMessage());
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
	                throw new DaoException("addUserPkGen(): " + e.getMessage());
	            }
	        }
	        return rows;	// The number of rows affected by the update (should be 1)
	}
	
	
	public Developer listDeveloperById(long devId)throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Developer developer = null;
	    try{
	    	 con = getConnection();
	         String query = "SELECT * FROM developer where developer_id = ?";
	         ps = con.prepareStatement(query);
	         System.out.println(query);
	         ps.setInt(1, (int)devId);
	         rs = ps.executeQuery();
	        if (rs.next()){
	        	long devIds = rs.getLong("developer_id");
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	            String work = rs.getString("work");
	            String selfDesc = rs.getString("self_desc");
	            String emailAdd = rs.getString("email_add");
	            Timestamp time = rs.getTimestamp("reg_time");
	            developer = new Developer(devIds,name,password,work,selfDesc,emailAdd,time);
	           
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
	    return developer;
		
	}
	
	public Developer listDeveloperByNamePassword(String name, String password)throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Developer developer = null;
	    try{
	    	 con = getConnection();
	         String query = "SELECT * FROM developer where name = ? AND password = ?";
	         ps = con.prepareStatement(query);
	         System.out.println(query);
	         ps.setString(1, name);
	         Md5 m = new Md5();
	         String enPassword = m.md5s(password);
	         ps.setString(2, enPassword);
	         
	         rs = ps.executeQuery();
	        if (rs.next()){
	        	long devIds = rs.getLong("developer_id");
	            String names = rs.getString("name");
	            String passwords = rs.getString("password");
	            String work = rs.getString("work");
	            String selfDesc = rs.getString("self_desc");
	            String emailAdd = rs.getString("email_add");
	            Timestamp time = rs.getTimestamp("reg_time");
	            developer = new Developer(devIds,names,passwords,work,selfDesc,emailAdd,time);
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
	    return developer;
	}
	public boolean checkExist(String name, String password)throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean d =false;
	    try{
	    	 con = getConnection();
	         String query = "SELECT * FROM developer where name = ? AND password = ?";
	         ps = con.prepareStatement(query);
	         System.out.println(query);
	         ps.setString(1, name);
	         Md5 m = new Md5();
	         String enPassword = m.md5s(password);
	         ps.setString(2, enPassword);
	         
	         rs = ps.executeQuery();
	        if (rs.next()){
	        	d=true;
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
	    return d;
	}
	
	public int deleteDeveloperById(long devId)throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int rows = 0;
	    try{
	    	con = getConnection();
	        String command = "DELETE FROM developer WHERE developer_id = ?";
	        System.out.println(command);

	        ps = con.prepareStatement(command);
	        ps.setInt(1, (int) devId);
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
	
	public long findDeveloperIdByName(String name) throws DaoException{
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    long d =0;
	    try{
	    	 con = getConnection();
	         String query = "SELECT developer_id FROM developer where name = ?";
	         ps = con.prepareStatement(query);
	         System.out.println(query);
	         ps.setString(1, name);
	         rs = ps.executeQuery();
	        if (rs.next()){
	        	d = rs.getLong("developer_id");
	        }
	    }catch (SQLException e) {
		        try {
					throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
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
		            throw new DaoException("findByCustomernamePassword(): " + e.getMessage());
		        }
		    }
		    return d;
	        
	}


	public static void main(String[] args){
		try{
		DeveloperDao dao = new DeveloperDao();
		long a = dao.findDeveloperIdByName("Jack");
		//int a =dao.deleteDeveloperById(80);
		System.out.println(a);
		
		/*
		Developer a = dao.listDeveloperByNamePassword("DevTest1", "test");
		
		System.out.println("developer with name DevTest1 password test is "+a);
		 */
		//Developer a = dao.listDeveloperById(1);
		//System.out.println("developer with id 1 is "+a);
		//Developer dev = new Developer("DevTest1","test","no work","i am bad boy","badboy@hoo.com");
		//dao.addDeveloper(dev);
		List<Developer> developers = new ArrayList<Developer>();
		 developers = dao.findAllDeveloper();
		 for(int i=0;i<developers.size();i++){
			 System.out.println(developers.get(i).toString()); 
		 }
		 /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date(System.currentTimeMillis());
		 String nowDate = format.format(date);
		 System.out.println(nowDate);
		 */
		} catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
