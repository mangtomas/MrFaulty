package com.faultsystem.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.faultsystem.exceptions.DaoException;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
public class PrimaryKeyGeneratorTable {

    private static PrimaryKeyGeneratorTable keyGenerator = null;
    private static int BLOCK_SIZE = 10;
    private static int current = -1;
    private static int getNextAt = -1;

    private PrimaryKeyGeneratorTable() {
    }

    public synchronized static PrimaryKeyGeneratorTable getInstance() {
        if (keyGenerator == null) {
            keyGenerator = new PrimaryKeyGeneratorTable();
        }
        return keyGenerator;
    }

    public synchronized int getNextPK(Connection con) throws DaoException {
        if (current > -1 && current < getNextAt) {
            return current++;  // It is not necessary to access the database
        }
        
        // Retrieve a new block from the database
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT NEXTVAL FROM NEXTPK";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                int nextVal = rs.getInt("NEXTVAL");
                current = nextVal * BLOCK_SIZE;
                getNextAt = current + BLOCK_SIZE;
                String command = "UPDATE NEXTPK SET NEXTVAL = ?";
                ps = con.prepareStatement(command);
                ps.setInt(1, (int) (nextVal + 1));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("ERROR! Unable to retrieve next sequence value: " + e.getMessage());
        }
        return current++;
    }

    public static void main(String[] args) throws SQLException {
    	 String url = "jdbc:mysql://arlia:3306/12mscdb22";
         String username = "12mscadmin22";
         String password = "bbb111";
          Connection con = null;

          MysqlConnectionPoolDataSource m = new MysqlConnectionPoolDataSource();
          m.setURL(url);
          m.setUser(username);
          m.setPassword(password);

          try {
              con = m.getConnection();
          }catch (SQLException e) {
              throw new DaoException("getComnnection(): " + e.getMessage());
          }
        for (int i = 0; i < 25; i++) {
            PrimaryKeyGeneratorTable pkGen = PrimaryKeyGeneratorTable.getInstance();
            System.out.println("Next primary key: " + pkGen.getNextPK(con));
        }
        if (con != null) {
            con.close();
            con = null;
        }
    }
}
