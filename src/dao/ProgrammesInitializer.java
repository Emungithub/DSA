package dao;

import adt.*;
import entity.Programmes;

public class ProgrammesInitializer {

    public UniversalInterface<Programmes> initializeProgrammes() {
        UniversalInterface<Programmes> pList = new UniversalList<>(); 
        pList.add(new Programmes("BSE", "Bachelor of Software Engineering"));
        pList.add(new Programmes("BISE", "Bachelor of Information Systems in Enterprise Information Systems"));
        pList.add(new Programmes("BIT", "Bachelor of Information Technology in Information Security"));
        pList.add(new Programmes("BCSI", "Bachelor of Computer Sceince in Interactive Software Technology"));
        pList.add(new Programmes("BCSD", "Bachelor of Computer Science in Data Science"));

        ProgrammesDAO programmesDAO = new ProgrammesDAO();
        programmesDAO.saveToFile(pList);
        
        return pList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        ProgrammesInitializer p = new ProgrammesInitializer();
        UniversalInterface<Programmes> programmesList = p.initializeProgrammes();
        System.out.println("\nProgrammes:\n" + programmesList);
    }

}
