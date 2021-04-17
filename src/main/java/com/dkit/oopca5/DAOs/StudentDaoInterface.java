package com.dkit.oopca5.DAOs;
/*
Kamil Jozefowicz
D00229452
 */

import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.DTOs.Student;

public interface StudentDaoInterface {
    public boolean registerStudent(Student s) throws DaoException;
    public Student findStudent(int caoNumber) throws DaoException;

}
