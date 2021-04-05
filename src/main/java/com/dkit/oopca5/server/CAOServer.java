package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

import java.util.List;
import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.DTOs.*;

public class CAOServer
{
    public static void main(String[] args) {

        CourseDaoInterface ICourseDao = new MySqlCourseDao();
        try
        {
            System.out.println("\nCall findAllCourses()");
            List<Course> courses = ICourseDao.findAllCourses();

            if (courses.isEmpty())
            {
                System.out.println("There are no Courses");
            }
            else
            {
                displayCourses(courses);
            }
        } catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    private static void displayCourses(List<Course> courses)
    {
        System.out.printf("%-10s%8s %-50s%-30s\n", "CourseID", "Level", "Title", "Institute");
        for (Course c : courses)
            System.out.printf("%-10s%8d %-50s%-30s\n", c.getCourseid(), c.getLevel(), c.getTitle(), c.getInstitution());
    }
}
