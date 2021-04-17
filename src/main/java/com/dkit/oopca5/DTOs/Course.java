package com.dkit.oopca5.DTOs;

import java.util.List;

public class Course {
    private String courseid;
    private int level;
    private String title;
    private String institution;

    public Course(String courseid, int level, String title, String institution)
    {
        this.courseid = courseid;
        this.level = level;
        this.title = title;
        this.institution = institution;
    }

    public Course()
    {
        this.courseid = "";
        this.level = 0;
        this.title = "";
        this.institution = "";
    }

    public String getCourseid() { return courseid; }

    public void setCourseid(String courseid) { this.courseid = courseid; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getInstitution() { return institution; }

    public void setInstitution(String institution) { this.institution = institution; }

    public void displayCourses(List<Course> courses)
    {
        System.out.printf("%-10s%8s %-50s%-30s\n", "CourseID", "Level", "Title", "Institute");
        for (Course c : courses)
            System.out.printf("%-10s%8d %-50s%-30s\n", c.getCourseid(), c.getLevel(), c.getTitle(), c.getInstitution());
    }

    @Override
    public String toString()
    {
        return "Course{" + "courseid=" + courseid + ", level=" + level + ", title=" +
                title + ", institution=" + institution + '}';
    }
}
