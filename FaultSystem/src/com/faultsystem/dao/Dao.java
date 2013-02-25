package com.faultsystem.dao;

import java.sql.Connection;
import java.sql.SQLException;


import com.faultsystem.exceptions.DaoException;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class Dao {
	public Connection getConnection() throws DaoException{
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
        return con;
    }

    public void freeConnection(Connection con) throws DaoException {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            throw new DaoException("freeComnnection(): " + e.getMessage());
        }
    }
}
