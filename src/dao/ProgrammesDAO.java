package dao;

import adt.*;
import entity.Programmes;
import java.io.*;

public class ProgrammesDAO {

    private String fileName = "programmes.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(UniversalInterface<Programmes> programmesList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(programmesList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public UniversalInterface<Programmes> retrieveFromFile() {
        File file = new File(fileName);
        UniversalInterface<Programmes> programmesList = new UniversalList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            programmesList = (UniversalList<Programmes>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return programmesList;
        }
    }

}
