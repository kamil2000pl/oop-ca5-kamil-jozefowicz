package com.dkit.oopca5.DAOs;
/*
Kamil Jozefowicz
D00229452
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dkit.oopca5.DTOs.Student;
import com.dkit.oopca5.Exceptions.DaoException;

public class MySqlStudentDao extends MySqlDAO implements StudentDaoInterface {
    @Override
    public boolean registerStudent(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;


        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "INSERT INTO student VALUES(?,?,?)";
            ps = con.prepareStatement(query);

            ps.setInt(1, s.getCaoNumber());
            ps.setString(2, s.getDateOfBirth());
            ps.setString(3, s.getPassword());

            success = (ps.executeUpdate() == 1);

        } catch (SQLException e)
        {
            throw new DaoException("registerStudent() " + e.getMessage());
        } finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("registerStudent() " + e.getMessage());
            }
        }
        return success;
    }
    @Override
    public Student findStudent(int caoNumber) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student s = null;

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM Student WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);

            rs = ps.executeQuery();
            if (rs.next())
            {
                caoNumber = rs.getInt("CAONUMBER");
                String dateOfBirth = rs.getString("DATEOFBIRTH");
                String password = rs.getString("PASSWORD");

                s = new Student(caoNumber, dateOfBirth, password);
            }
        } catch (SQLException e) {
            throw new DaoException("findStudent() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return s;     // s may be null
    }

}
