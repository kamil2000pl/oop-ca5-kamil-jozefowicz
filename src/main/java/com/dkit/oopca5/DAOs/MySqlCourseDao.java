package com.dkit.oopca5.DAOs;
/*
Kamil Jozefowicz
D00229452
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkit.oopca5.DTOs.Course;
import com.dkit.oopca5.Exceptions.DaoException;

public class MySqlCourseDao extends MySqlDAO implements CourseDaoInterface {
    @Override
    public List<Course> findAllCourses() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM COURSE";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                String courseid = rs.getString("COURSEID");
                int level = rs.getInt("LEVEL");
                String title = rs.getString("TITLE");
                String institution = rs.getString("INSTITUTION");
                Course c = new Course(courseid, level, title, institution);
                courses.add(c);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllCourses() " + e.getMessage());
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
                throw new DaoException("findAllCourses() " + e.getMessage());
            }
        }
        return courses;     // may be empty
    }
}
