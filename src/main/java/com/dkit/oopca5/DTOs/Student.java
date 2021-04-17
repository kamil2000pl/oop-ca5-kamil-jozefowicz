package com.dkit.oopca5.DTOs;
/*
Kamil Jozefowicz
D00229452
 */

public class Student {
    private int caoNumber;
    private String dateOfBirth;
    private String password;

    public Student(int caoNumber, String dateOfBirth, String password)
    {
        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public Student()
    {
        this.caoNumber = 0;
        this.dateOfBirth = "";
        this.password = "";
    }

    public int getCaoNumber() { return caoNumber; }

    public void setCaoNumber(int caoNumber) { this.caoNumber = caoNumber; }

    public String getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString()
    {
        return "Student{" + "caoNumber=" + caoNumber + ", dateOfBirth=" + dateOfBirth + ", password=" +
                password + '}';
    }
}
