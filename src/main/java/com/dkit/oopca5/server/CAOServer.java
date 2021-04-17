package com.dkit.oopca5.server;
/*
Kamil Jozefowicz
D00229452
 */

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

import java.io.IOException;
import java.util.List;

import com.dkit.oopca5.DAOs.CourseDaoInterface;
import com.dkit.oopca5.DAOs.MySqlCourseDao;
import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.DTOs.*;
import java.net.Socket;
import java.net.ServerSocket;
import com.dkit.oopca5.core.*;
import com.dkit.oopca5.server.CAOClientHandler;

public class CAOServer
{

    public static void main(String[] args) {
        CAOServer server = new CAOServer();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(50025);
            System.out.println("Server: Server started. Listening for connections on port " + CAOService.PORT_NUM + "...");

            int clientNumber = 0;

            while (true)
            {
                Socket socket = ss.accept();
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of server: " + socket.getLocalPort());

                Thread t = new Thread(new CAOClientHandler(socket, clientNumber));
                t.start();

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ".");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting");
    }

//    public static void main(String[] args) {
//
//        CourseDaoInterface ICourseDao = new MySqlCourseDao();
//        try
//        {
//            System.out.println("\nCall findAllCourses()");
//            List<Course> courses = ICourseDao.findAllCourses();
//
//            if (courses.isEmpty())
//            {
//                System.out.println("There are no Courses");
//            }
//            else
//            {
//                displayCourses(courses);
//            }
//        } catch (DaoException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    private static void displayCourses(List<Course> courses)
//    {
//        System.out.printf("%-10s%8s %-50s%-30s\n", "CourseID", "Level", "Title", "Institute");
//        for (Course c : courses)
//            System.out.printf("%-10s%8d %-50s%-30s\n", c.getCourseid(), c.getLevel(), c.getTitle(), c.getInstitution());
//    }


}
