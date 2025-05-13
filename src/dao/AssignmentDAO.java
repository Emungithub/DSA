package dao;

import adt.*;
import entity.Assignment;
import java.io.*;

public class AssignmentDAO {

    private String fileName = "assignment.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(UniversalInterface<Assignment> assignmentsList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(assignmentsList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public UniversalInterface<Assignment> retrieveFromFile() {
        File file = new File(fileName);
        UniversalInterface<Assignment> assignmentsList = new UniversalList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            assignmentsList = (UniversalList<Assignment>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return assignmentsList;
        }
    }

}
