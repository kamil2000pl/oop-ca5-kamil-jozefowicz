package com.dkit.oopca5.DAOs;
/*
Kamil Jozefowicz
D00229452
 */

/*
All of the database functionality should be here. You will need a DAO for each table that you are interacting with in the database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.dkit.oopca5.Exceptions.DaoException;

public class MySqlDAO
{
    public Connection getConnection() throws DaoException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/oop_ca5_kamil_jozefowicz";
        String username = "root";
        String password = "";
        Connection conn = null;

        try
        {
            conn = DriverManager.getConnection(url,username,password);
        }
        catch (SQLException e1)
        {
            System.out.println("Connection failed " + e1.getMessage());
            System.exit(2);
        }
        return conn;
    }

    public void freeConnection(Connection con) throws DaoException
    {
        try
        {
            if (con != null)
            {
                con.close();
                con = null;
            }
        }
        catch (SQLException e2)
        {
            System.out.println("Failed to free connection: " + e2.getMessage());
            System.exit(1);
        }
    }
}
