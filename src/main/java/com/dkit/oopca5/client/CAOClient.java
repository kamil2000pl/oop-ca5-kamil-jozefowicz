package com.dkit.oopca5.client;
/*
Kamil Jozefowicz
D00229452
 */


/* The client package should contain all code and classes needed to run the Client
 */

/* The CAOClient offers students a menu and sends messages to the server using TCP Sockets
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import com.dkit.oopca5.core.*;

public class CAOClient
{
    public static void main(String[] args) {
        CAOClient client = new CAOClient();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket(CAOService.HOSTNAME, CAOService.PORT_NUM);
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of server : " + socket.getPort());

            System.out.println("Client message: The client is running and has connected to the server");

            OutputStream os = socket.getOutputStream();
            PrintWriter out = new PrintWriter(os, true);

            int option = -1;

            while(option != 0)
            {
                try
                {
                    Scanner keyboard = new Scanner(System.in);
                    printStartMenu();
                    System.out.print("Please enter option:");
                    option = keyboard.nextInt();
                    String command;
                    boolean loggedIn = false;
                    int loggedInCAONumber = 0;

                    while (option != 0 && !loggedIn)
                    {
                        if (option == 1)
                        {
                            command = startMenuOption1();

                            out.write(command+"\n");  // write command to socket, and newline terminator
                            out.flush();              // flush (force) the command over the socket

                            Scanner inStream = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

                            String RegisteredString = inStream.nextLine();
                            System.out.println("Client message: Response from server: " + RegisteredString);
                            inStream.close();
                        }
                        if (option == 2)
                        {
                            command = startMenuOption2();

                            out.write(command+"\n");  // write command to socket, and newline terminator
                            out.flush();              // flush (force) the command over the socket

                            Scanner inStream = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

                            String RegisteredString = inStream.nextLine();
                            if (RegisteredString.equals(CAOService.SUCCESSFUL_LOGIN))
                            {
                                loggedIn = true;
                            }
                            System.out.println("Client message: Response from server: " + RegisteredString);
                            inStream.close();
                        }
                        System.out.println();
//                        printStartMenu();
//                        System.out.print("\nPlease enter option:");
//                        option = keyboard.nextInt();
                    }
                    while(option != 0 && loggedIn)
                    {
                        try
                        {
                            printLoggedInMenu();
                            System.out.print("Please enter option:");
                            option = keyboard.nextInt();

                        } catch (Exception e) {
                            System.out.println("\nInvalid option chosen(InputMismatchException)");
                        }
                    }
                    out.write("TERMINATE\n");  // write command to socket, and newline terminator
                    out.flush();              // flush (force) the command over the socket
//            Scanner inStream = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
                    os.close();
                    out.close();
                    socket.close();
//                    os.close();
//                    out.close();
//                    socket.close();
                } catch (Exception e) {
                    System.out.println("\nInvalid option chosen(InputMismatchException)");
                }
            }


        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }

    public void printStartMenu()
    {
        System.out.println("0. QUIT_APPLICATION");
        System.out.println("1. REGISTER");
        System.out.println("2. LOGIN");
    }

    public void printLoggedInMenu()
    {
        System.out.println("0. QUIT");
        System.out.println("1. LOGOUT");
        System.out.println("2. DISPLAY_COURSE");
        System.out.println("3. DISPLAY_ALL_COURSES");
        System.out.println("4. DISPLAY_CURRENT_CHOICES");
        System.out.println("5. UPDATE_CURRENT_CHOICES");
    }

    public String startMenuOption1()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("REGISTER option chosen");

        System.out.print("Enter your CAO Number: ");
        int caoNumber = keyboard.nextInt();

        System.out.print("Enter your date of birth in the format yyyy-mm-dd: ");
        String dateOfBirth = keyboard.next();

        System.out.print("Enter your password: ");
        String password = keyboard.next();

        return (CAOService.REGISTER_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dateOfBirth + CAOService.BREAKING_CHARACTER + password);
    }

    public String startMenuOption2()
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("LOGIN option chosen");

        System.out.print("Enter your CAO Number: ");
        int caoNumber = keyboard.nextInt();

        System.out.print("Enter your date of birth in the format yyyy-mm-dd: ");
        String dateOfBirth = keyboard.next();

        System.out.print("Enter your password: ");
        String password = keyboard.next();

        return (CAOService.LOGIN_COMMAND + CAOService.BREAKING_CHARACTER + caoNumber + CAOService.BREAKING_CHARACTER + dateOfBirth + CAOService.BREAKING_CHARACTER + password);
    }


    public void sendCommand(String command)
    {

    }
}
