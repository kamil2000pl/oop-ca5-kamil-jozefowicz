package com.dkit.oopca5.server;

import java.util.List;
import com.dkit.oopca5.Exceptions.DaoException;
import com.dkit.oopca5.DTOs.Course;

public interface CourseDaoInterface {
    public List<Course> findAllCourses() throws DaoException;


}
