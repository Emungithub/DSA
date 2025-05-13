package dao;

import adt.*;
import entity.Courses;
import java.io.*;

public class CoursesDAO {

    private String fileName = "courses.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(UniversalInterface<Courses> coursesList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(coursesList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public UniversalInterface<Courses> retrieveFromFile() {
        File file = new File(fileName);
        UniversalInterface<Courses> coursesList = new UniversalList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            coursesList = (UniversalList<Courses>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return coursesList;
        }
    }

}
