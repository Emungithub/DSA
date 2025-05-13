package dao;

import adt.*;
import entity.TutorialGroups;
import java.io.*;

public class TutorialGroupsDAO {

    private String fileName = "tutorialGroups.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToFile(UniversalInterface<TutorialGroups> tutorialGroupsList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(tutorialGroupsList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public UniversalInterface<TutorialGroups> retrieveFromFile() {
        File file = new File(fileName);
        UniversalInterface<TutorialGroups> tutorialGroupsList = new UniversalList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            tutorialGroupsList = (UniversalList<TutorialGroups>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return tutorialGroupsList;
        }
    }

}
