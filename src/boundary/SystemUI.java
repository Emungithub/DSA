package boundary;

import adt.UniversalInterface;
import entity.Students;
import entity.Courses;
import entity.Programmes;
import entity.TutorialGroups;
import entity.Assignment;
import java.util.Scanner;
import utility.MessageUI;

public class SystemUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\nMAIN MENU");
        System.out.println("(1) Student Registration Management Subsystem");
        System.out.println("(2) Course Management Subsystem");
        System.out.println("(3) Tutorial Group Management Subsystem");
        System.out.println("(4) Assignment Team Management Subsystem");
        System.out.println("(0) Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

// Student Registration Management Subsystem
//------------------------------------------------------------------------------
    public int getStudentMenuChoice() {
        System.out.println("STUDENT MENU");
        System.out.println("(1) Add new students");
        System.out.println("(2) Remove a student");
        System.out.println("(3) Amend student details");
        System.out.println("(4) Search student for registered course");
        System.out.println("(5) Add students to a few courses");
        System.out.println("(6) Remove a student from a course");
        System.out.println("(7) Calculate fee paid for registered courses");
        System.out.println("(8) Filters students for courses based on criteria");
        System.out.println("(9) Generate summary reports");
        System.out.println("(0) Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllStudents(String outputStr) {
        System.out.println("\nList of Students:\n" + outputStr);
    }

    public void printStudentsDetails(Students students) {
        System.out.println("Student Details");
        System.out.println("Student ID:" + students.getID());
        System.out.println("Student name: " + students.getStudentsName());
    }

    public int inputStudentsNumber() {
        System.out.print("Enter student number: ");
        int studentsNumber = scanner.nextInt();
        scanner.nextLine();
        return studentsNumber;
    }

    public String inputStudentsID() {
        System.out.print("Enter student ID: ");
        String ID = scanner.nextLine();
        return ID;
    }

    public String inputStudentsName() {
        String studentName;
        while (true) {
            System.out.print("Enter student name: ");
            studentName = scanner.nextLine();
            // Check if the name contains numbers
            if (!studentName.matches(".*\\d.*")) {
                break; // If no numbers are found, break the loop
            }
            MessageUI.displayInvalidStudentNameInputMessage();
        }
        return studentName;
    }

    public String inputCoursesType() {
        while (true) {
            System.out.print("Enter course type (main / elective / resit / repeat): ");
            String coursesType = scanner.nextLine().trim().toLowerCase(); // Normalize the input to lower case

            // Check if the coursesType is one of the valid options
            if (coursesType.equals("main") || coursesType.equals("elective") || coursesType.equals("resit") || coursesType.equals("repeat")) {
                return coursesType; // Return the valid input
            } else {
                MessageUI.displayInvalidCourseTypeMessage();
            }
        }
    }

    public Students inputSearchStudentsDetails() {
        String studentsID = inputStudentsID();

        // Creating and returning a new Students object with provided details
        return new Students(studentsID);
    }

    public Students inputStudentsDetails() {
        String studentsID = inputStudentsID();
        String studentsName = inputStudentsName();

        // Creating and returning a new Students object with provided details
        return new Students(studentsID, studentsName);
    }
//------------------------------------------------------------------------------

// Course Management Subsystem
//------------------------------------------------------------------------------
    public int getCourseMenuChoice() {
        System.out.println("COURSE MENU");
        System.out.println("(1) Add a programme to courses");
        System.out.println("(2) Remove a programme from a course");
        System.out.println("(3) Add a new course to programmes");
        System.out.println("(4) Remove a course from a programme");
        System.out.println("(5) Search courses offered in a semester");
        System.out.println("(6) Amend course details for a programme");
        System.out.println("(7) List courses taken by different faculties");
        System.out.println("(8) List all coureses for a programme");
        System.out.println("(9) Generate summary reports");
        System.out.println("(0) Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    // Courses
    public void listAllCourses(String outputStr) {
        System.out.println("\nList of Courses:\n" + outputStr);
    }

    public void printCoursesDetails(Courses courses) {
        System.out.println("Course Details");
        System.out.println("Course ID   : " + courses.getCoursesCode());
        System.out.println("Course Name : " + courses.getCoursesName());
        System.out.println("Course Fee  : " + courses.getCoursesFee());
    }

    public int inputCoursesNumber() {
        System.out.print("Enter courses number: ");
        int coursesNumber = scanner.nextInt();
        scanner.nextLine();
        return coursesNumber;
    }

    public String inputCoursesCode() {
        System.out.print("Enter course code: ");
        String coursesCode = scanner.nextLine();
        return coursesCode;
    }

    public String inputCoursesName() {
        System.out.print("Enter course name: ");
        String coursesName = scanner.nextLine();
        return coursesName;
    }

    public double inputCoursesFee() {
        System.out.print("Enter Course Fee: ");
        double courseFee = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        return courseFee;
    }

    public String inputFaculties() {
        System.out.print("Enter faculty: ");
        String faculties = scanner.nextLine();
        return faculties;
    }

    public Courses inputCoursesDetails() {
        String coursesCode = inputCoursesCode();
        String coursesName = inputCoursesName();
        double coursesFee = inputCoursesFee();
        String faculties = inputFaculties();
        return new Courses(coursesCode, coursesName, coursesFee, faculties);
    }

    public int studentSelection() {
        System.out.print("Enter student number (0 to cancel): ");
        int studentSelection = scanner.nextInt();
        scanner.nextLine();
        return studentSelection;
    }

    public int courseSelection() {
        System.out.print("Enter course number (0 to cancel): ");
        int courseSelection = scanner.nextInt();
        scanner.nextLine();
        return courseSelection;
    }
    // Courses

    // Programmes
    public void listAllProgrammes(String outputStr) {
        System.out.println("\nList of Programmes:\n" + outputStr);
    }

    public void printProgrammesDetails(Programmes programmes) {
        System.out.println("Programmes Details");
        System.out.println("Programme name: " + programmes.getProgrammesName());
    }

    public int inputProgrammesNumber() {
        System.out.print("Enter programme number: ");
        int programmesNumber = scanner.nextInt();
        scanner.nextLine();
        return programmesNumber;
    }

    public String inputProgrammesCode() {
        System.out.print("Enter programme code: ");
        String programmesCode = scanner.nextLine();
        return programmesCode;
    }

    public String inputProgrammesName() {
        System.out.print("Enter programme name: ");
        String programmesName = scanner.nextLine();
        return programmesName;
    }

    public Programmes inputSearchProgrammesDetails() {
        String programmesCode = inputProgrammesCode();
        System.out.println();
        return new Programmes(programmesCode);
    }

    public Programmes inputProgrammesDetails() {
        String programmesCode = inputProgrammesCode();
        String programmesName = inputProgrammesName();
        System.out.println();
        return new Programmes(programmesCode, programmesName);
    }

    public int programmeSelection() {
        System.out.print("Enter programme number (0 to cancel): ");
        int programmeSelection = scanner.nextInt();
        scanner.nextLine();
        return programmeSelection;
    }
    // Programmes

//------------------------------------------------------------------------------
// Tutorial Group Management Subsystem
//------------------------------------------------------------------------------
    public int getTutorialGroupsMenuChoice() {
        System.out.println("TUTORIAL GROUP MENU");
        System.out.println("(1) Add a tutorial group to a programme");
        System.out.println("(2) Remove a tutorial group from a programme");
        System.out.println("(3) List all tutorial groups for a programme");
        System.out.println("(4) Adding students to a tutorial group");
        System.out.println("(5) Remove a student from a tutorial group");
        System.out.println("(6) Change the tutorial group for a student");
        System.out.println("(7) List all students in a tutorial group and a programme");
        System.out.println("(8) Merge tutorial groups based on criteria");
        System.out.println("(9) Generate summary reports");
        System.out.println("(0) Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllTutorialGroups(String outputStr) {
        System.out.println("\nList of Tutorial Groups:\n" + outputStr);
    }

    public void printTutorialGroupsDetails(TutorialGroups tutorialGroups) {
        System.out.println("Tutorial Groups Details");
        System.out.println("Programme:" + tutorialGroups.getGroups());
    }

    public int inputTutorialGroupsNumber() {
        System.out.print("Enter tutorial group number: ");
        int tutorialGroupsNumber = scanner.nextInt();
        scanner.nextLine();
        return tutorialGroupsNumber;
    }

    public String inputTutorialGroups() {
        System.out.print("Enter tutorial group: ");
        String tutorialGroups = scanner.nextLine();
        return tutorialGroups;
    }

    public TutorialGroups inputTutorialGroupsDetails() {
        String groups = inputTutorialGroups();
        System.out.println();
        return new TutorialGroups(groups);
    }

    public String getUserInput() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void listAllProgrammes(UniversalInterface<Programmes> programmesList) {
        // Iterate over each programme and print its details
        for (int i = 1; i <= programmesList.getNumberOfEntries(); i++) {
            Programmes programme = programmesList.getEntry(i);
            System.out.println(i + ". " + programme.getProgrammesCode() + "\t" + programme.getProgrammesName());
        }
    }

    public int tutorialGroupSelection() {
        System.out.print("Enter tutorial group number (0 to cancel): ");
        int tutorialGroupSelection = scanner.nextInt();
        scanner.nextLine();
        return tutorialGroupSelection;
    }
//------------------------------------------------------------------------------

// Assignment Team Managment Subsystem
//------------------------------------------------------------------------------
    public int getAssignmentMenuChoice() {
        System.out.println("ASSIGNMENT TEAM MENU");
        System.out.println("(1) Create assignment teams for a tutorial group");
        System.out.println("(2) Remove assignment team");
        System.out.println("(3) Amend assignment team details");
        System.out.println("(4) Add students to assignment teams");
        System.out.println("(5) Remove students from assignment teams");
        System.out.println("(6) Merge assignment team for a tutorial group");
        System.out.println("(7) List assignment teams for a tutorial group");
        System.out.println("(8) List students under an assignment team");
        System.out.println("(9) Generate summary reports");
        System.out.println("(0) Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllAssignment(String outputStr) {
        System.out.println("\nList of Assignment Teams:\n" + outputStr);
    }

    public int assignmentSelection() {
        System.out.print("Enter assignment team number (0 to cancel): ");
        int assignmentSelection = scanner.nextInt();
        scanner.nextLine();
        return assignmentSelection;
    }

    public String inputAssignmentName() {
        System.out.print("Enter assignment team name: ");
        String assingmentName = scanner.nextLine();
        return assingmentName;
    }

    public Assignment inputAssignmentDetails() {
        String assignmentTeam = inputAssignmentName();
        System.out.println();
        return new Assignment(assignmentTeam);
    }
//------------------------------------------------------------------------------
}
