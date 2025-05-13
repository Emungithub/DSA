package dao;

import adt.*;
import entity.Assignment;

public class AssignmentInitializer {

    public UniversalInterface<Assignment> initializeAssignment() {
        UniversalInterface<Assignment> pList = new UniversalList<>();

        AssignmentDAO assignmentDAO = new AssignmentDAO();
        assignmentDAO.saveToFile(pList);

        return pList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        AssignmentInitializer a = new AssignmentInitializer();
        UniversalInterface<Assignment> assignmentsList = a.initializeAssignment();
        System.out.println("\nAssignment teams:\n" + assignmentsList);
    }

}
