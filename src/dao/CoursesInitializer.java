package dao;

import adt.*;
import entity.Courses;

public class CoursesInitializer {

    public UniversalInterface<Courses> initializeCourses() {
        UniversalInterface<Courses> pList = new UniversalList<>();
        pList.add(new Courses("BACS2163", "Software Engineering", 100, "FOCS"));
        pList.add(new Courses("BACS2063", "Data Structure And Algorithms", 200, "FOET"));
        pList.add(new Courses("BACS2173", "Graphics Programming", 300, "FOCS"));
        pList.add(new Courses("MPU-3232", "Entrepreneurship", 400, "FCCI"));
        pList.add(new Courses("BACS2042", "Research Methods", 500, "FCCI"));

        CoursesDAO coursesDAO = new CoursesDAO();
        coursesDAO.saveToFile(pList);

        return pList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        CoursesInitializer c = new CoursesInitializer();
        UniversalInterface<Courses> coursesList = c.initializeCourses();
        System.out.println("\nCourses:\n" + coursesList);
    }

}
