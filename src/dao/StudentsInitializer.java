package dao;

import adt.*;
import entity.Students;

public class StudentsInitializer {

    public UniversalInterface<Students> initializeStudents() {
        UniversalInterface<Students> pList = new UniversalList<>();
        pList.add(new Students("2300971", "John"));
        pList.add(new Students("2300976", "Ee Mun"));
        pList.add(new Students("2300962", "Zi Qi"));
        pList.add(new Students("2300049", "Eliane"));

        StudentsDAO studentsDAO = new StudentsDAO();
        studentsDAO.saveToFile(pList);
        return pList;
    }

    public static void main(String[] args) {
        // To illustrate how to use the initializeProducts() method
        StudentsInitializer s = new StudentsInitializer();
        UniversalInterface<Students> studentList = s.initializeStudents();
        System.out.println("\nStudents:\n" + studentList);
    }

}
