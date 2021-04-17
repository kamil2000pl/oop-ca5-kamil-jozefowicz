package com.dkit.oopca5.DAOs;
/*
Kamil Jozefowicz
D00229452
 */

import java.util.List;
import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.DTOs.Course;

public interface CourseDaoInterface {
    public List<Course> findAllCourses() throws DaoException;


}
