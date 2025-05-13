package dao;

import adt.*;
import entity.Students;
import java.io.*;

public class StudentsDAO {

    private String fileName = "students.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(UniversalInterface<Students> studentsList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(studentsList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public UniversalInterface<Students> retrieveFromFile() {
        File file = new File(fileName);
        UniversalInterface<Students> studentsList = new UniversalList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            studentsList = (UniversalList<Students>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return studentsList;
        }
    }

}
