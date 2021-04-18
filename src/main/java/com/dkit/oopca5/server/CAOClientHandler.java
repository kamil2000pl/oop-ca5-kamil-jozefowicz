package com.dkit.oopca5.server;
/*
Kamil Jozefowicz
D00229452
 */

/*
The CAOClientHandler will run as a thread. It should listen for messages from the Client and respond to them.There should be one CAOClientHandler per Client.
 */

import com.dkit.oopca5.DAOs.MySqlStudentDao;
import com.dkit.oopca5.DAOs.StudentDaoInterface;
import com.dkit.oopca5.DTOs.Student;
import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.core.CAOService;

import java.io.*;
import java.net.Socket;

public class CAOClientHandler implements Runnable
{
    BufferedReader socketReader;
    PrintWriter socketWriter;
    Socket socket;
    int clientNumber;

    public CAOClientHandler(Socket clientSocket, int clientNumber)
    {
        try {
            InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
            this.socketReader = new BufferedReader(isReader);

            OutputStream os = clientSocket.getOutputStream();
            this.socketWriter = new PrintWriter(os, true);

            this.clientNumber = clientNumber;

            this.socket = clientSocket;

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        boolean terminate = false;
        StudentDaoInterface IStudentDao = new MySqlStudentDao();
        try {
            while (!terminate) {
                message = socketReader.readLine();
                    if (message != null) {
                        String[] component = message.split(CAOService.BREAKING_CHARACTER, -1);

                        if (component[0].equals(CAOService.REGISTER_COMMAND)) {
                            int caoNumber = Integer.parseInt(component[1]);
                            String dateOfBirth = component[2];
                            String password = component[3];

                            Student student = new Student(caoNumber, dateOfBirth, password);

                            boolean success = IStudentDao.registerStudent(student);

                            if (success) {
                                socketWriter.println(CAOService.SUCCESSFUL_REGISTER);
                                System.out.println("success");
                            } else {
                                socketWriter.println(CAOService.FAILED_REGISTER);
                            }
                        }
                        else if (component[0].equals(CAOService.LOGIN_COMMAND))
                        {
                            int caoNumber = Integer.parseInt(component[1]);
                            String dateOfBirth = component[2];
                            String password = component[3];

                            Student student = IStudentDao.findStudent(caoNumber);

                            if (student == null)
                            {
                                socketWriter.println(CAOService.FAILED_LOGIN);
                            }
                            else if (caoNumber == student.getCaoNumber() && dateOfBirth.equals(student.getDateOfBirth()) && password.equals(student.getPassword()))
                            {
                                socketWriter.println(CAOService.SUCCESSFUL_LOGIN);
                            } else {
                                socketWriter.println(CAOService.FAILED_LOGIN);
                            }
                        }
                        else if (message.equals("TERMINATE"))
                        {
                            terminate = true;
                        }
                    }

            }
            socket.close();
        } catch (IOException | DaoException ex)
        {
            ex.printStackTrace();
        }
        System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
    }
}
