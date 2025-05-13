package control;

import adt.*;
import boundary.SystemUI;
import dao.StudentsDAO;
import dao.CoursesDAO;
import dao.ProgrammesDAO;
import dao.TutorialGroupsDAO;
import dao.AssignmentDAO;
import entity.*;
import utility.MessageUI;

import java.util.Scanner;

public class Main {

    private UniversalInterface<Students> studentsList = new UniversalList<>();
    private final StudentsDAO studentsDAO = new StudentsDAO();

    private UniversalInterface<Courses> coursesList = new UniversalList<>();
    private final CoursesDAO coursesDAO = new CoursesDAO();

    private UniversalInterface<Programmes> programmesList = new UniversalList<>();
    private final ProgrammesDAO programmesDAO = new ProgrammesDAO();

    private UniversalInterface<TutorialGroups> tutorialGroupsList = new UniversalList<>();
    private final TutorialGroupsDAO tutorialGroupsDAO = new TutorialGroupsDAO();

    private UniversalInterface<Assignment> assignmentsList = new UniversalList<>();
    private final AssignmentDAO assignmentDAO = new AssignmentDAO();

    private final SystemUI systemUI = new SystemUI();

    private final HashMapInterface<String, UniversalInterface<Students>> studentsCourseMap = new HashMap<>();
    private final HashMapInterface<String, UniversalInterface<Programmes>> programmesCourseMap = new HashMap<>();
    private final HashMapInterface<String, UniversalInterface<Courses>> coursesProgrammeMap = new HashMap<>();
    private final HashMapInterface<String, UniversalInterface<TutorialGroups>> tutorialGroupsToProgrammeMap = new HashMap<>();
    private final HashMapInterface<String, UniversalInterface<Students>> studentToTutorialGroupsMap = new HashMap<>();
    private final HashMapInterface<String, UniversalInterface<Assignment>> assignmentTutorialGroupMap = new HashMap<>();
    private final HashMapInterface<String, UniversalInterface<Students>> studentsAssignmentMap = new HashMap<>();

    public Main() {
        studentsList = studentsDAO.retrieveFromFile();
        coursesList = coursesDAO.retrieveFromFile();
        programmesList = programmesDAO.retrieveFromFile();
        tutorialGroupsList = tutorialGroupsDAO.retrieveFromFile();
        assignmentsList = assignmentDAO.retrieveFromFile();
    }

    public void runSystem(Scanner scanner) {
        int choice = 0;
        do {
            choice = systemUI.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                // Student Registration Management Subsystem (Leong Ee Mun) ----
                case 1:
                    int studentChoice = 0;
                    do {
                        // Display Student Registration Management Subsystem Menu
                        studentChoice = systemUI.getStudentMenuChoice();
                        switch (studentChoice) {
                            case 0:
                                // Exit Student Registration Management Subsystem
                                MessageUI.displayExitMessage();
                                break;
                            case 1:
                                // Add new students
                                addNewStudents();
                                break;
                            case 2:
                                // Remove a student
                                if (studentsList.isEmpty()) {
                                    MessageUI.displayStudentListEmptyMessage();
                                    break;
                                } else {
                                    removeStudents();
                                    break;
                                }
                            case 3:
                                // Amend student details
                                systemUI.listAllStudents(getAllStudents());
                                amendStudentDetails();
                                break;
                            case 4:
                                // Search students for registered courses
                                if (studentsList.isEmpty()) {
                                    MessageUI.displayStudentListEmptyMessage();
                                    break;
                                } else {
                                    searchStudentsCourses();
                                }
                                break;
                            case 5:
                                // Add students to a few courses
                                if (studentsList.isEmpty() || coursesList.isEmpty()) {
                                    System.out.println("\nStudents or course list is empty\n");
                                    break;
                                } else {
                                    systemUI.listAllStudents(getAllStudents());
                                    addStudentToCourse();
                                    break;
                                }
                            case 6:
                                // Remove a student from a course
                                if (studentsList.isEmpty()) {
                                    MessageUI.displayStudentListEmptyMessage();
                                    break;
                                } else {
                                    removeStudentFromCourse();
                                    break;
                                }
                            case 7:
                                // Calculate fee paid for registered courses
                                systemUI.listAllStudents(getAllStudents());
                                int chosenStudentIndex = systemUI.studentSelection();
                                if (chosenStudentIndex > 0 && chosenStudentIndex <= studentsList.getNumberOfEntries()) {
                                    Students student = studentsList.getEntry(chosenStudentIndex);
                                    double totalFeePaid = calculateTotalFeePaid(student);
                                    System.out.println("\nTotal Fee Paid for " + student.getStudentsName() + ": RM" + totalFeePaid + "\n");
                                } else if (chosenStudentIndex == 0) {
                                    // Handle cancel action
                                } else {
                                    MessageUI.displayInvalidChoiceMessage();
                                }
                                break;
                            case 8:
                                // Filters students for courses based on criteria (Course Type)
                                filterStudentsByCourseType();
                                break;
                            case 9:
                                // Generate summary reports
                                generateStudentManagementReport();
                                break;
                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                break;
                        }
                    } while (studentChoice != 0);
                    break;
                // Student Registration Management Subsystem (Leong Ee Mun) ----

                // Course Management Subsystem (Eliane Chong Yuet Lian) --------
                case 2:
                    int courseChoice = 0;
                    do {
                        // Display Course Management Subsystem Menu
                        courseChoice = systemUI.getCourseMenuChoice();
                        switch (courseChoice) {
                            case 0:
                                MessageUI.displayExitMessage();
                                break;
                            case 1:
                                // Add a programme to courses
                                if (programmesList.isEmpty() || coursesList.isEmpty()) {
                                    System.out.println("\nProgramme or course list is empty\n");
                                    break;
                                } else {
                                    systemUI.listAllProgrammes(getAllProgrammes());
                                    addProgrammeToCourse();
                                    break;
                                }
                            case 2:
                                // Remove a programme froma course
                                if (programmesList.isEmpty()) {
                                    MessageUI.displayProgrammeListEmptyMessage();
                                    break;
                                } else {
                                    removeProgrammeFromCourse();
                                    break;
                                }
                            case 3:
                                // Add a new course to programems
                                if (programmesList.isEmpty() || coursesList.isEmpty()) {
                                    System.out.println("\nProgramme or course list is empty\n");
                                    break;
                                } else {
                                    systemUI.listAllCourses(getAllCourses());
                                    addCourseToProgramme();
                                    break;
                                }
                            case 4:
                                // Remove a course from a programme
                                if (coursesList.isEmpty()) {
                                    MessageUI.displayCourseListEmptyMessage();
                                    break;
                                } else {
                                    removeCourseFromProgramme();
                                    break;
                                }
                            case 5:
                                // Search courses offered in a semester
                                if (coursesList.isEmpty()) {
                                    MessageUI.displayCourseListEmptyMessage();
                                    break;
                                } else {
                                    searchCourseOffered();
                                    break;
                                }
                            case 6:
                                // Amend course details for a programme
                                systemUI.listAllCourses(getAllCourses());
                                amendCourseDetails();
                                break;
                            case 7:
                                // List courses taken by different faculties
                                listCourseTakenFaculties();
                                break;
                            case 8:
                                // List all courses for a programme
                                if (programmesList.isEmpty()) {
                                    MessageUI.displayProgrammeListEmptyMessage();
                                    break;
                                } else {
                                    listCoursesForProgramme();
                                }
                                break;
                            case 9:
                                // Generate summary reports
                                generateCourseManagementReport();
                                break;
                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                break;
                        }
                    } while (courseChoice != 0);
                    break;
                // Course Management Subsystem (Eliane Chong Yuet Lian) --------

                // Tutorial Group Management Susbsystem (John Lee Xing) --------
                case 3:
                    int tutorialChoice = 0;
                    do {
                        // Display Tutorial Group Management Subsystem Menu
                        tutorialChoice = systemUI.getTutorialGroupsMenuChoice();
                        switch (tutorialChoice) {
                            case 0:
                                MessageUI.displayExitMessage();
                                break;
                            case 1:
                                // Add a tutorial group to a programme
                                if (tutorialGroupsList.isEmpty() || programmesList.isEmpty()) {
                                    System.out.println("\nTutorial group or course list is empty\n");
                                    break;
                                } else {
                                    systemUI.listAllTutorialGroups(getAllTutorialGroups());
                                    addTutorialGroupsToProgramme();
                                    break;
                                }
                            case 2:
                                // Remove a tutorial group from a programme
                                if (tutorialGroupsList.isEmpty()) {
                                    MessageUI.displayTutorialGroupListEmptyMessage();
                                    break;
                                } else {
                                    systemUI.listAllTutorialGroups(getAllTutorialGroups());
                                    removeTutorialGroupFromProgramme();
                                    break;
                                }
                            case 3:
                                // List all tutorial groups for a programme
                                listTutorialGroupsForProgramme();
                                break;
                            case 4:
                                // Adding students to a tutorial group
                                if (tutorialGroupsList.isEmpty() || studentsList.isEmpty()) {
                                    System.out.println("\nTutorial group or student list is empty\n");
                                    break;
                                } else {
                                    systemUI.listAllStudents(getAllStudents());
                                    addStudentsToTutorialGroup();
                                    break;
                                }
                            case 5:
                                // Remove a student from a tutorial group
                                if (studentsList.isEmpty()) {
                                    MessageUI.displayStudentListEmptyMessage();
                                    break;
                                } else {
                                    systemUI.listAllStudents(getAllStudents());
                                    removeStudentFromTutorialGroup();
                                    break;
                                }
                            case 6:
                                // Change the tutorial group for a student
                                changeStudentTutorialGroup();
                                break;
                            case 7:
                                // List all students in a tutorial group and a programme
                                listProgrammesAndAssociatedTutorialGroups();
                                break;
                            case 8:
                                // Merge tutorial groups based on criteria
                                mergeTutorialGroups();
                                break;
                            case 9:
                                // Generate summary reports
                                generateTutorialGroupManagementReport();
                                break;
                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                break;
                        }
                    } while (tutorialChoice != 0);
                    break;
                // Tutorial Group Management Susbsystem (John Lee Xing) --------

                // Assignment Team Management Subsystem (Ang Zi Qi) ------------
                case 4:
                    int assignmentChoice = 0;
                    do {
                        // Display Assignment Team Management Subsystem Menu
                        assignmentChoice = systemUI.getAssignmentMenuChoice();
                        switch (assignmentChoice) {
                            case 0:
                                MessageUI.displayExitMessage();
                                break;
                            case 1:
                                // Create assignment teams for a tutorial group
                                createAssignmentTeamForTutorialGroup();
                                break;
                            case 2:
                                // Remove assignment team
                                removeAssignmentTeam();
                                break;
                            case 3:
                                // Amend assignment team details
                                amendAssingmentTeamDetails();
                                break;
                            case 4:
                                // Add students to assignment teams
                                addStudentsToAssignmentTeam();
                                break;
                            case 5:
                                // Remove students from assignment teams
                                removeStudentFromAssignmentTeam();
                                break;
                            case 6:
                                // Merge assginment teams based on criteria
                                mergeAssignmentTeams();
                                break;
                            case 7:
                                // List assignment teams for a tutorial group
                                listAssignmentTeamForTutorialGroup();
                                break;
                            case 8:
                                // List students under an assignment team
                                listStudentsUnderAnAssignmentTeam();
                                break;
                            case 9:
                                // Generate summary reports
                                generateAssignmentReport();
                                break;
                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                break;
                        }
                    } while (assignmentChoice != 0);
                    break;
                // Assignment Team Management Subsystem (Ang Zi Qi) ------------

                default:
                    MessageUI.displayInvalidChoiceMessage();
                    break;
            }
        } while (choice != 0);
    }

    // Student Registration Management Subsystem (Leong Ee Mun) ----------------
    public void addNewStudents() {
        Students newStudents = systemUI.inputStudentsDetails();

        boolean studentExists = false;
        for (int i = 1; i <= studentsList.getNumberOfEntries(); i++) {
            Students existingStudent = studentsList.getEntry(i);
            if (newStudents.equals(existingStudent)) {
                System.out.println("\nStudent already exists\n");
                studentExists = true;
                break;
            }
        }
        if (!studentExists) {
            studentsList.add(newStudents);
            studentsDAO.saveToFile(studentsList);
            System.out.println("\nStudent successfully added\n");
        }
    }

    public void removeStudents() {
        systemUI.listAllStudents(getAllStudents());
        int removeStudents = systemUI.inputStudentsNumber();
        if (removeStudents > studentsList.getNumberOfEntries() || removeStudents < 0) {
            MessageUI.displayInvalidChoiceMessage();
        } else if (removeStudents == 0) {
            MessageUI.displayExitMessage();
        } else {
            studentsList.remove(removeStudents);
            studentsDAO.saveToFile(studentsList);
            System.out.println("\nStudent successfully removed\n");
            systemUI.listAllStudents(getAllStudents());
        }
    }

    private void amendStudentDetails() {
        int studentSelection = systemUI.studentSelection();
        if (studentSelection == 0) {
            return;
        }

        if (studentSelection > 0 && studentSelection <= studentsList.getNumberOfEntries()) {
            Students student = studentsList.getEntry(studentSelection);
            Students updatedStudent = systemUI.inputStudentsDetails();
            student.setID(updatedStudent.getID());
            student.setStudentsName(updatedStudent.getStudentsName());
            studentsDAO.saveToFile(studentsList);
            System.out.println("\nStudent details updated successfully\n");
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void searchStudentsCourses() {
        Students student = systemUI.inputSearchStudentsDetails();
        UniversalInterface<Students> studentCoursesInterface = studentsCourseMap.get(student.getID());

        if (studentCoursesInterface != null && !studentCoursesInterface.isEmpty()) {
            System.out.println("\nCourse taken by Student ID: " + student.getID());

            // Iterate through the courses using getEntry
            System.out.printf("%-15s %-20s %-15s %-10s\n", "Course Type", "Course Fee", "Course Code", "Course Name");
            for (int i = 1; i <= studentCoursesInterface.getNumberOfEntries(); i++) {
                Students studentCourse = studentCoursesInterface.getEntry(i);
                System.out.printf("%-15s %-20s %-15s %-10s\n", studentCourse.getCourseType(), studentCourse.getCourseFee(),
                        studentCourse.getCourseCode(), studentCourse.getCourseName());
            }

            System.out.println("");

        } else {
            System.out.println("\nStudent not found or student is not enrolled in any courses\n");
        }
    }

    public void addStudentToCourse() {
        int chosenStudentIndex = systemUI.studentSelection();
        if (chosenStudentIndex > 0 && chosenStudentIndex <= studentsList.getNumberOfEntries()) {
            Students student = studentsList.getEntry(chosenStudentIndex);
            UniversalInterface<Students> studentCourses = studentsCourseMap.getOrDefault(student.getID(), new UniversalList<>());

            systemUI.listAllCourses(getAllCourses());
            int chosenCourseIndex = systemUI.courseSelection();
            if (chosenCourseIndex > 0 && chosenCourseIndex <= coursesList.getNumberOfEntries()) {
                Courses course = coursesList.getEntry(chosenCourseIndex);

                boolean courseExists = false;
                for (int i = 1; i <= studentCourses.getNumberOfEntries(); i++) {
                    Students studentCourse = studentCourses.getEntry(i);
                    if (studentCourse.getCourseCode().equals(course.getCoursesCode())) {
                        courseExists = true;
                        break;
                    }
                }

                if (courseExists) {
                    System.out.println("\nStudent is already enrolled in this course\n");
                } else {
                    // Create a new Students object for this course
                    Students newStudentCourse = new Students(student.getID(), student.getStudentsName());
                    newStudentCourse.setCourseCode(course.getCoursesCode());
                    newStudentCourse.setCourseType(systemUI.inputCoursesType());
                    newStudentCourse.setCourseName(course.getCoursesName());
                    newStudentCourse.setCourseFee(course.getCoursesFee());

                    // Add the new student course to the list
                    studentCourses.add(newStudentCourse);
                    studentsCourseMap.put(student.getID(), studentCourses);

                    System.out.println("\nStudent successfully added into course\n");
                }

            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } else if (chosenStudentIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeStudentFromCourse() {
        String studentID = systemUI.inputStudentsID(); // Get student ID
        String courseCode = systemUI.inputCoursesCode(); // Get course code

        // Check if the student is in the HashMap
        if (studentsCourseMap.containsKey(studentID)) {
            UniversalInterface<Students> studentCourses = studentsCourseMap.get(studentID);
            // Find the course to remove
            for (int i = 1; i <= studentCourses.getNumberOfEntries(); i++) {
                Students student = studentCourses.getEntry(i);
                if (student.getCourseCode().equals(courseCode)) {
                    // Remove the course
                    studentCourses.remove(i);
                    System.out.println("\nCourse successfully removed for student " + studentID + "\n");
                    return; // Exit the method once the course is removed
                }
            }
            // If the course wasn't found
            System.out.println("\nCourse not found for student " + studentID + "\n");
        } else {
            // If the student is not in the HashMap
            System.out.println("\nStudent not found within the course\n");
        }
    }

    public double calculateTotalFeePaid(Students student) {
        double totalFeePaid = 0.0;
        UniversalInterface<Students> studentCourses = studentsCourseMap.get(student.getID());
        if (studentCourses != null && !studentCourses.isEmpty()) {
            for (int i = 1; i <= studentCourses.getNumberOfEntries(); i++) {
                Students studentCourse = studentCourses.getEntry(i);
                totalFeePaid += studentCourse.getCourseFee();
            }
        }
        return totalFeePaid;
    }

    public void filterStudentsByCourseType() {
        // Get the course type from user input
        String courseType = systemUI.inputCoursesType();

        // Create a new StudentsList to store filtered students
        UniversalInterface<Students> filteredStudents = new UniversalList<>();

        // Iterate through students to find matching course type
        for (int i = 1; i <= studentsList.getNumberOfEntries(); i++) {
            Students student = studentsList.getEntry(i);
            UniversalInterface<Students> studentCourses = studentsCourseMap.get(student.getID());
            if (studentCourses != null && !studentCourses.isEmpty()) {
                for (int j = 1; j <= studentCourses.getNumberOfEntries(); j++) {
                    Students studentCourse = studentCourses.getEntry(j);
                    if (studentCourse.getCourseType().equalsIgnoreCase(courseType)) {
                        // Add only the specific course details, not the student
                        Students filteredStudentCourse = new Students(student.getID(), student.getStudentsName());
                        filteredStudentCourse.setCourseCode(studentCourse.getCourseCode());
                        filteredStudentCourse.setCourseName(studentCourse.getCourseName());
                        filteredStudentCourse.setCourseType(studentCourse.getCourseType());
                        filteredStudentCourse.setCourseFee(studentCourse.getCourseFee());
                        filteredStudents.add(filteredStudentCourse);
                    }
                }
            }
        }

        // Display filtered students
        if (!filteredStudents.isEmpty()) {
            System.out.println("\nStudents enrolled in courses of type '" + courseType + "':");
            System.out.printf("%-15s %-20s %-15s %-10s\n", "Student ID", "Student Name", "Course Code", "Course Name");
            for (int i = 1; i <= filteredStudents.getNumberOfEntries(); i++) {
                Students filteredStudent = filteredStudents.getEntry(i);
                System.out.printf("%-15s %-20s %-15s %-10s\n", filteredStudent.getID(), filteredStudent.getStudentsName(),
                        filteredStudent.getCourseCode(), filteredStudent.getCourseName());
            }
            System.out.println("");
        } else {
            System.out.println("\nNo students found enrolled in courses of type '" + courseType + "'\n");
        }
    }

    public void generateStudentManagementReport() {
        // Print header
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("                          STUDENT ENROLLMENT REPORT                          ");
        System.out.println("-----------------------------------------------------------------------------");

        // Print total number of students
        System.out.println("Total Number of Students: " + studentsList.getNumberOfEntries());

        // Print details of each student and their enrolled courses
        for (int i = 1; i <= studentsList.getNumberOfEntries(); i++) {
            Students student = studentsList.getEntry(i);
            UniversalInterface<Students> studentCourses = studentsCourseMap.get(student.getID());
            if (studentCourses != null && !studentCourses.isEmpty()) {
                // Print student details
                System.out.println("\nStudent ID: " + student.getID());
                System.out.println("Student Name: " + student.getStudentsName());
                System.out.println("Total Fee Paid: RM" + calculateTotalFeePaid(student));
                System.out.println("\nEnrolled Courses:");
                System.out.printf("%-15s %-30s %-15s %-15s\n", "Course Code", "Course Name", "Course Type", "Course Fee");
                double totalFeePaid = 0.0;
                for (int j = 1; j <= studentCourses.getNumberOfEntries(); j++) {
                    Students studentCourse = studentCourses.getEntry(j);
                    System.out.printf("%-15s %-30s %-15s %-15.2f\n", studentCourse.getCourseCode(), studentCourse.getCourseName(), studentCourse.getCourseType(), studentCourse.getCourseFee());
                    totalFeePaid += studentCourse.getCourseFee();
                }
                System.out.println("Total Fee Paid by Student: RM" + totalFeePaid);
                System.out.println("-----------------------------------------------------------------------------");
            }
        }
        System.out.println("                                End of Report                                ");
        System.out.println("-----------------------------------------------------------------------------");
    }

    public String getAllStudents() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= studentsList.getNumberOfEntries(); i++) {
            outputStr.append(i)
                    .append(". ")
                    .append(studentsList.getEntry(i))
                    .append("\n");
        }
        return outputStr.toString();
    }
    // Student Registration Management Subsystem (Leong Ee Mun) ----------------

    // Course Management Subsystem (Eliane Chong Yuet Lian) --------------------
    public void addProgrammeToCourse() {
        int chosenProgrammeIndex = systemUI.programmeSelection();
        if (chosenProgrammeIndex > 0 && chosenProgrammeIndex <= programmesList.getNumberOfEntries()) {
            Programmes programme = programmesList.getEntry(chosenProgrammeIndex);
            UniversalInterface<Programmes> programmeCourses = programmesCourseMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

            systemUI.listAllCourses(getAllCourses());
            int chosenCourseIndex = systemUI.courseSelection();
            if (chosenCourseIndex > 0 && chosenCourseIndex <= coursesList.getNumberOfEntries()) {
                Courses course = coursesList.getEntry(chosenCourseIndex);
                UniversalInterface<Courses> courseProgrammes = coursesProgrammeMap.getOrDefault(course.getCoursesCode(), new UniversalList<>());

                // Check if the course is already added to this programme
                boolean courseExistsInProgramme = false;
                for (int i = 1; i <= programmeCourses.getNumberOfEntries(); i++) {
                    Programmes programmeCourse = programmeCourses.getEntry(i);
                    if (programmeCourse.getCourseCode().equals(course.getCoursesCode())) {
                        courseExistsInProgramme = true;
                        break;
                    }
                }

                // Check if the programme is already added to this course
                boolean programmeExistsInCourse = false;
                for (int j = 1; j <= courseProgrammes.getNumberOfEntries(); j++) {
                    Courses courseProgramme = courseProgrammes.getEntry(j);
                    if (courseProgramme.getProgrammesCode().equals(programme.getProgrammesCode())) {
                        programmeExistsInCourse = true;
                        break;
                    }
                }

                if (courseExistsInProgramme || programmeExistsInCourse) {
                    System.out.println("\nProgramme or course is already added to this course or programme\n");
                } else {
                    // Create a new Programmes object for this course
                    Programmes newProgrammeCourse = new Programmes(programme.getProgrammesCode(), programme.getProgrammesName());
                    newProgrammeCourse.setCourseCode(course.getCoursesCode());
                    newProgrammeCourse.setCourseName(course.getCoursesName());
                    newProgrammeCourse.setCourseFee(course.getCoursesFee());

                    // Add the new programme course to the list
                    programmeCourses.add(newProgrammeCourse);
                    programmesCourseMap.put(programme.getProgrammesCode(), programmeCourses);

                    System.out.println("\nProgramme successfully added into course\n");
                }

            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } else if (chosenProgrammeIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeProgrammeFromCourse() {
        // Display list of programmes
        systemUI.listAllCourses(getAllCourses());

        int chosenCourseIndex = systemUI.courseSelection();
        if (chosenCourseIndex > 0 && chosenCourseIndex <= coursesList.getNumberOfEntries()) {
            Courses course = coursesList.getEntry(chosenCourseIndex);
            String courseCode = course.getCoursesCode();

            // Check if the programme code is valid
            if (coursesProgrammeMap.containsKey(courseCode)) {
                UniversalInterface<Courses> coursesProgramme = coursesProgrammeMap.get(courseCode);

                // Display list of courses for the selected programme
                System.out.println("\nProgrammes for Course " + courseCode + ":");
                for (int i = 1; i <= coursesProgramme.getNumberOfEntries(); i++) {
                    Courses programme = coursesProgramme.getEntry(i);
                    System.out.println(i + ". " + "Programme Code: " + programme.getProgrammesCode() + ", Programme Name: " + programme.getProgrammesName());
                }

                System.out.println("");

                int chosenProgrammeIndex = systemUI.programmeSelection();
                if (chosenProgrammeIndex > 0 && chosenProgrammeIndex <= coursesProgramme.getNumberOfEntries()) {
                    Courses chosenCourses = coursesProgramme.getEntry(chosenProgrammeIndex);
                    String programmeCode = chosenCourses.getProgrammesCode();

                    // Find and remove the course
                    for (int i = 1; i <= coursesProgramme.getNumberOfEntries(); i++) {
                        Courses programme = coursesProgramme.getEntry(i);
                        if (programme.getProgrammesCode().equals(programmeCode)) {
                            coursesProgramme.remove(i);
                            System.out.println("\nProgramme successfully removed for course " + courseCode + "\n");
                            return; // Exit the method once the course is removed
                        }
                    }
                    // If the course wasn't found
                    System.out.println("\nProgramme not found for course " + courseCode + "\n");
                } else if (chosenCourseIndex == 0) {
                    // Handle cancel action
                } else {
                    MessageUI.displayInvalidChoiceMessage();
                }
            } else {
                // If the programme is not in the HashMap
                System.out.println("\nCourse not found\n");
            }
        } else if (chosenCourseIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void addCourseToProgramme() {
        int chosenCourseIndex = systemUI.courseSelection();
        if (chosenCourseIndex > 0 && chosenCourseIndex <= coursesList.getNumberOfEntries()) {
            Courses course = coursesList.getEntry(chosenCourseIndex);
            UniversalInterface<Courses> courseProgrammes = coursesProgrammeMap.getOrDefault(course.getCoursesCode(), new UniversalList<>());

            System.out.print("Enter the faculty for this course: ");
            String faculty = systemUI.getUserInput().trim();
            course.setFaculties(faculty);

            systemUI.listAllProgrammes(getAllProgrammes());
            int chosenProgrammeIndex = systemUI.programmeSelection();
            if (chosenProgrammeIndex > 0 && chosenProgrammeIndex <= programmesList.getNumberOfEntries()) {
                Programmes programme = programmesList.getEntry(chosenProgrammeIndex);
                UniversalInterface<Programmes> programmeCourses = programmesCourseMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

                // Check if the course is already added to this programme
                boolean courseExistsInProgramme = false;
                for (int i = 1; i <= programmeCourses.getNumberOfEntries(); i++) {
                    Programmes programmeCourse = programmeCourses.getEntry(i);
                    if (programmeCourse.getCourseCode().equals(course.getCoursesCode())) {
                        courseExistsInProgramme = true;
                        break;
                    }
                }

                // Check if the programme is already added to this course
                boolean programmeExistsInCourse = false;
                for (int j = 1; j <= courseProgrammes.getNumberOfEntries(); j++) {
                    Courses courseProgramme = courseProgrammes.getEntry(j);
                    if (courseProgramme.getProgrammesCode().equals(programme.getProgrammesCode())) {
                        programmeExistsInCourse = true;
                        break;
                    }
                }

                if (courseExistsInProgramme || programmeExistsInCourse) {
                    System.out.println("\nCourse or programme is already added to this programme or course\n");
                } else {
                    // Create a new Courses object for this programme
                    Courses newCourseProgramme = new Courses(course.getCoursesCode(), course.getCoursesName());
                    newCourseProgramme.setProgrammesCode(programme.getProgrammesCode());
                    newCourseProgramme.setProgrammesName(programme.getProgrammesName());

                    // Add the new course to the list of courses for this programme
                    courseProgrammes.add(newCourseProgramme);
                    coursesProgrammeMap.put(course.getCoursesCode(), courseProgrammes);

                    System.out.println("\nCourse successfully added to programme\n");
                }

            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } else if (chosenCourseIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeCourseFromProgramme() {
        // Display list of programmes
        systemUI.listAllProgrammes(getAllProgrammes());

        int chosenProgrammeIndex = systemUI.programmeSelection();
        if (chosenProgrammeIndex > 0 && chosenProgrammeIndex <= programmesList.getNumberOfEntries()) {
            Programmes programme = programmesList.getEntry(chosenProgrammeIndex);
            String programmeCode = programme.getProgrammesCode();

            // Check if the programme code is valid
            if (programmesCourseMap.containsKey(programmeCode)) {
                UniversalInterface<Programmes> programmeCourses = programmesCourseMap.get(programmeCode);

                // Display list of courses for the selected programme
                System.out.println("\nCourses for Programme " + programmeCode + ":");
                System.out.println("   Course Code     Course Name");
                for (int i = 1; i <= programmeCourses.getNumberOfEntries(); i++) {
                    Programmes course = programmeCourses.getEntry(i);
                    System.out.println(i + ". " + course.getCourseCode() + "        " + course.getCourseName());
                }

                System.out.println("");

                int chosenCourseIndex = systemUI.courseSelection();
                if (chosenCourseIndex > 0 && chosenCourseIndex <= programmeCourses.getNumberOfEntries()) {
                    Programmes chosenCourse = programmeCourses.getEntry(chosenCourseIndex);
                    String courseCode = chosenCourse.getCourseCode();

                    // Find and remove the course
                    for (int i = 1; i <= programmeCourses.getNumberOfEntries(); i++) {
                        Programmes course = programmeCourses.getEntry(i);
                        if (course.getCourseCode().equals(courseCode)) {
                            programmeCourses.remove(i);
                            System.out.println("\nCourse successfully removed for programme " + programmeCode + "\n");
                            return; // Exit the method once the course is removed
                        }
                    }
                    // If the course wasn't found
                    System.out.println("\nCourse not found for programme " + programmeCode + "\n");
                } else if (chosenCourseIndex == 0) {
                    // Handle cancel action
                } else {
                    MessageUI.displayInvalidChoiceMessage();
                }
            } else {
                // If the programme is not in the HashMap
                System.out.println("\nProgramme not found\n");
            }
        } else if (chosenProgrammeIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void searchCourseOffered() {
        systemUI.listAllCourses(getAllCourses());
    }

    public void amendCourseDetails() {
        int courseSelection = systemUI.courseSelection();
        if (courseSelection == 0) {
            return;
        }

        if (courseSelection > 0 && courseSelection <= coursesList.getNumberOfEntries()) {
            Courses course = coursesList.getEntry(courseSelection);
            Courses updatedCourse = systemUI.inputCoursesDetails();
            course.setCoursesCode(updatedCourse.getCoursesCode());
            course.setCoursesName(updatedCourse.getCoursesName());
            course.setCoursesFee(updatedCourse.getCoursesFee());
            course.setFaculties(updatedCourse.getFaculties());
            coursesDAO.saveToFile(coursesList);
            System.out.println("\nCourse details updated successfully\n");
        } else {
            System.out.println("\nInvalid course selection\n");
        }
    }

    public void listCourseTakenFaculties() {
        System.out.print("\nEnter the faculty to list courses: ");
        String inputFaculty = systemUI.getUserInput().trim();

        System.out.println("\nCourses offered by the Faculty of " + inputFaculty + ":");
        boolean found = false;
        for (int i = 1; i <= coursesList.getNumberOfEntries(); i++) {
            Courses course = coursesList.getEntry(i);
            if (course.getFaculties().equalsIgnoreCase(inputFaculty)) {
                System.out.println(course);
                found = true;
            }
        }

        System.out.println("");

        if (!found) {
            System.out.println("\nNo courses found for this faculty\n");
        }
    }

    public void listCoursesForProgramme() {
        Programmes programme = systemUI.inputSearchProgrammesDetails();
        UniversalInterface<Programmes> programmeCoursesInterface = programmesCourseMap.get(programme.getProgrammesCode());

        if (programmeCoursesInterface != null && !programmeCoursesInterface.isEmpty()) {
            System.out.println("\nCourses for Programme Code: " + programme.getProgrammesCode());

            // Iterate through the courses using getEntry
            System.out.println("Course Code   Course Fee     Course Name");
            for (int i = 1; i <= programmeCoursesInterface.getNumberOfEntries(); i++) {
                Programmes programmeCourse = programmeCoursesInterface.getEntry(i);
                System.out.println(programmeCourse.getCourseCode()
                        + "      RM" + programmeCourse.getCourseFee()
                        + "        " + programmeCourse.getCourseName());
            }
            System.out.println("");
        } else {
            System.out.println("\nProgramme not found or programme is not enrolled in any courses\n");
        }
    }

    public String getAllCourses() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= coursesList.getNumberOfEntries(); i++) {
            outputStr.append(i)
                    .append(". ")
                    .append(coursesList.getEntry(i))
                    .append("\n");
        }
        return outputStr.toString();
    }

    public void removeCourses() {
        int removeCourses = systemUI.inputCoursesNumber();
        if (removeCourses > coursesList.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {
            coursesList.remove(removeCourses);
            coursesDAO.saveToFile(coursesList);
            System.out.println("\nCourse successfully removed\n");
        }
    }

    public String getAllProgrammes() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= programmesList.getNumberOfEntries(); i++) {
            outputStr.append(i)
                    .append(". ")
                    .append(programmesList.getEntry(i))
                    .append("\n");
        }
        return outputStr.toString();
    }

    public void generateCourseManagementReport() {
        StringBuilder report = new StringBuilder();

        // Append header
        report.append("-------------------------------------------------------------------------------\n");
        report.append("                         Course Management Report\n");
        report.append("-------------------------------------------------------------------------------\n\n");

        // Append list of programmes
        report.append("Programmes:\n");
        report.append(getAllProgrammes());

        // Append list of courses
        report.append("\nCourses:\n");
        report.append(getAllCourses());

        // Append list of courses for each programme
        report.append("\nCourses Offered for Each Programme:\n");
        for (int i = 1; i <= programmesList.getNumberOfEntries(); i++) {
            Programmes programme = programmesList.getEntry(i);
            report.append("Programme: ").append(programme.getProgrammesCode()).append(" - ").append(programme.getProgrammesName()).append("\n");
            UniversalInterface<Programmes> programmeCoursesInterface = programmesCourseMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

            if (!programmeCoursesInterface.isEmpty()) {
                report.append("Courses:\n");
                for (int j = 1; j <= programmeCoursesInterface.getNumberOfEntries(); j++) {
                    Programmes programmeCourse = programmeCoursesInterface.getEntry(j);
                    report.append("- ").append(programmeCourse.getCourseCode()).append(" - ").append(programmeCourse.getCourseName()).append("\n");
                }
            } else {
                report.append("No courses offered for this programme.\n");
            }
            report.append("\n");
        }

        // Display report
        System.out.println(report.toString());
    }

    public void removeProgrammes() {
        int removeProgrammes = systemUI.inputProgrammesNumber();
        if (removeProgrammes > programmesList.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {
            programmesList.remove(removeProgrammes);
            programmesDAO.saveToFile(programmesList);
            System.out.println("\nProgramme successfully removed\n");
        }
    }
    // Course Management Subsystem (Eliane Chong Yuet Lian) --------------------

    // Tutorial Group Management Subsystem (John Lee Xing) ---------------------
    public void addTutorialGroupsToProgramme() {
        // Get the selected tutorial group
        int chosenTutorialGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenTutorialGroupIndex > 0 && chosenTutorialGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups tutorialGroup = tutorialGroupsList.getEntry(chosenTutorialGroupIndex);

            // List all available programmes
            systemUI.listAllProgrammes(getAllProgrammes());

            // Get the selected programme
            int chosenProgrammeIndex = systemUI.programmeSelection();
            if (chosenProgrammeIndex > 0 && chosenProgrammeIndex <= programmesList.getNumberOfEntries()) {
                Programmes programme = programmesList.getEntry(chosenProgrammeIndex);

                // Get the associated tutorial groups for the selected programme
                UniversalInterface<TutorialGroups> associatedTutorialGroups = tutorialGroupsToProgrammeMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

                // Check if the selected tutorial group is already enrolled in the programme
                boolean tutorialGroupExists = false;
                for (int i = 1; i <= associatedTutorialGroups.getNumberOfEntries(); i++) {
                    TutorialGroups associatedTutorialGroup = associatedTutorialGroups.getEntry(i);
                    if (associatedTutorialGroup.getGroups().equals(tutorialGroup.getGroups())) {
                        tutorialGroupExists = true;
                        break;
                    }
                }

                if (tutorialGroupExists) {
                    System.out.println("\nTutorial group is already enrolled in this programme\n");
                } else {
                    // Create a new TutorialGroups object for this programme
                    TutorialGroups newTutorialGroupProgramme = new TutorialGroups(tutorialGroup.getGroups());
                    newTutorialGroupProgramme.setProgrammesCode(programme.getProgrammesCode());

                    // Add the new tutorial group programme to the list
                    associatedTutorialGroups.add(newTutorialGroupProgramme);
                    tutorialGroupsToProgrammeMap.put(programme.getProgrammesCode(), associatedTutorialGroups);

                    System.out.println("\nTutorial group successfully added into programme\n");
                }
            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } else if (chosenTutorialGroupIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeTutorialGroupFromProgramme() {
        // Get the selected tutorial group
        int chosenTutorialGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenTutorialGroupIndex > 0 && chosenTutorialGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups tutorialGroup = tutorialGroupsList.getEntry(chosenTutorialGroupIndex);

            // List all available programmes
            systemUI.listAllProgrammes(getAllProgrammes());

            // Get the selected programme
            int chosenProgrammeIndex = systemUI.programmeSelection();
            if (chosenProgrammeIndex > 0 && chosenProgrammeIndex <= programmesList.getNumberOfEntries()) {
                Programmes programme = programmesList.getEntry(chosenProgrammeIndex);

                // Get the associated tutorial groups for the selected programme
                UniversalInterface<TutorialGroups> associatedTutorialGroups = tutorialGroupsToProgrammeMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

                // Check if the selected tutorial group is already enrolled in the programme
                boolean tutorialGroupExists = false;
                for (int i = 1; i <= associatedTutorialGroups.getNumberOfEntries(); i++) {
                    TutorialGroups associatedTutorialGroup = associatedTutorialGroups.getEntry(i);
                    if (associatedTutorialGroup.getGroups().equals(tutorialGroup.getGroups())) {
                        tutorialGroupExists = true;
                        break;
                    }
                }

                if (tutorialGroupExists) {
                    // Add the new tutorial group programme to the list
                    associatedTutorialGroups.remove(chosenTutorialGroupIndex);
                    tutorialGroupsToProgrammeMap.put(programme.getProgrammesCode(), associatedTutorialGroups);

                    System.out.println("\nTutorial group successfully removed from programme\n");
                } else {
                    System.out.println("\nTutorial group not found from this programme\n");
                }
            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } else if (chosenTutorialGroupIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void listTutorialGroupsForProgramme() {
        // Get the programme details
        Programmes programme = systemUI.inputSearchProgrammesDetails();

        // Get the tutorial groups for the given programme
        UniversalInterface<TutorialGroups> programmeTutorialGroups = tutorialGroupsToProgrammeMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

        if (!programmeTutorialGroups.isEmpty()) {
            System.out.println("\nTutorial Groups for Programme Code: " + programme.getProgrammesCode());

            // Iterate through the tutorial groups using getEntry
            System.out.println("\nTutorial Groups: ");
            for (int i = 1; i <= programmeTutorialGroups.getNumberOfEntries(); i++) {
                TutorialGroups tutorialGroup = programmeTutorialGroups.getEntry(i);
                System.out.println("- " + tutorialGroup.getGroups());
            }

            System.out.println("");

        } else {
            System.out.println("\nProgramme not found or programme does not have any tutorial groups\n");
        }
    }

    public void addStudentsToTutorialGroup() {
        int chosenStudentIndex = systemUI.studentSelection();
        if (chosenStudentIndex > 0 && chosenStudentIndex <= studentsList.getNumberOfEntries()) {
            Students student = studentsList.getEntry(chosenStudentIndex);

            // List all available tutorial groups
            systemUI.listAllTutorialGroups(getAllTutorialGroups());

            // Get the selected tutorial group
            int chosenTutorialGroupIndex = systemUI.tutorialGroupSelection();
            if (chosenTutorialGroupIndex > 0 && chosenTutorialGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
                TutorialGroups tutorialGroup = tutorialGroupsList.getEntry(chosenTutorialGroupIndex);

                // Get the associated students for the selected tutorial group
                UniversalInterface<Students> associatedStudents = studentToTutorialGroupsMap.getOrDefault(tutorialGroup.getGroups(), new UniversalList<>());

                // Check if the student is already enrolled in this tutorial group
                boolean studentExists = false;
                for (int i = 1; i <= associatedStudents.getNumberOfEntries(); i++) {
                    Students associatedStudent = associatedStudents.getEntry(i);
                    if (associatedStudent.getID().equals(student.getID())) {
                        studentExists = true;
                        break;
                    }
                }

                if (studentExists) {
                    System.out.println("\nStudent is already enrolled in this tutorial group\n");
                } else {
                    // Create a new Students object for this tutorial group
                    Students newStudentTutorialGroup = new Students(student.getID(), student.getStudentsName());
                    newStudentTutorialGroup.setGroups(tutorialGroup.getGroups());

                    // Add the new student tutorial group to the list
                    associatedStudents.add(newStudentTutorialGroup);
                    studentToTutorialGroupsMap.put(tutorialGroup.getGroups(), associatedStudents);

                    System.out.println("\nStudent successfully added into tutorial group\n");
                }
            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } else if (chosenStudentIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeStudentFromTutorialGroup() {
        int chosenStudentIndex = systemUI.studentSelection();
        if (chosenStudentIndex > 0 && chosenStudentIndex <= studentsList.getNumberOfEntries()) {
            Students student = studentsList.getEntry(chosenStudentIndex);

            // List all available tutorial groups
            systemUI.listAllTutorialGroups(getAllTutorialGroups());

            // Get the selected tutorial group
            int chosenTutorialGroupIndex = systemUI.tutorialGroupSelection();
            if (chosenTutorialGroupIndex > 0 && chosenTutorialGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
                TutorialGroups tutorialGroup = tutorialGroupsList.getEntry(chosenTutorialGroupIndex);

                // Get the associated students for the selected tutorial group
                UniversalInterface<Students> associatedStudents = studentToTutorialGroupsMap.getOrDefault(tutorialGroup.getGroups(), new UniversalList<>());

                // Check if the student is already enrolled in this tutorial group
                boolean studentExists = false;
                for (int i = 1; i <= associatedStudents.getNumberOfEntries(); i++) {
                    Students associatedStudent = associatedStudents.getEntry(i);
                    if (associatedStudent.getID().equals(student.getID())) {
                        studentExists = true;
                        break;
                    }
                }

                if (studentExists) {
                    associatedStudents.remove(chosenStudentIndex);
                    System.out.println("\nStudent successfully removed from this tutorial group\n");
                    studentToTutorialGroupsMap.put(tutorialGroup.getGroups(), associatedStudents);
                } else {
                    System.out.println("\nStudent not found from this tutorial group\n");
                }
            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } else if (chosenStudentIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void changeStudentTutorialGroup() {
        // List all tutorial groups and prompt the user to select one
        System.out.println("Available tutorial groups:");
        systemUI.listAllTutorialGroups(getAllTutorialGroups());
        int oldGroupIndex = systemUI.tutorialGroupSelection();

        if (oldGroupIndex > 0 && oldGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups oldGroup = tutorialGroupsList.getEntry(oldGroupIndex);

            // Display all students in the selected tutorial group
            UniversalInterface<Students> studentsInOldGroup = studentToTutorialGroupsMap.getOrDefault(oldGroup.getGroups(), new UniversalList<>());
            if (studentsInOldGroup.isEmpty()) {
                System.out.println("\nNo students are enrolled in this tutorial group\n");
                return;
            }

            System.out.println("Students in " + oldGroup.getGroups() + ":");
            for (int i = 1; i <= studentsInOldGroup.getNumberOfEntries(); i++) {
                Students student = studentsInOldGroup.getEntry(i);
                System.out.println(i + ". " + student.getStudentsName() + " (ID: " + student.getID() + ")");
            }

            System.out.println("");

            // Prompt the user to select a student to move
            int studentIndex = systemUI.studentSelection();
            if (studentIndex > 0 && studentIndex <= studentsInOldGroup.getNumberOfEntries()) {
                Students studentToMove = studentsInOldGroup.getEntry(studentIndex);

                // List all tutorial groups again for selection of a new group
                systemUI.listAllTutorialGroups(getAllTutorialGroups());
                int newGroupIndex = systemUI.tutorialGroupSelection();
                if (newGroupIndex > 0 && newGroupIndex <= tutorialGroupsList.getNumberOfEntries() && newGroupIndex != oldGroupIndex) {
                    TutorialGroups newGroup = tutorialGroupsList.getEntry(newGroupIndex);

                    // Move the student from the old group to the new group
                    studentsInOldGroup.remove(studentIndex);
                    UniversalInterface<Students> studentsInNewGroup = studentToTutorialGroupsMap.getOrDefault(newGroup.getGroups(), new UniversalList<>());
                    studentsInNewGroup.add(studentToMove);
                    studentToTutorialGroupsMap.put(oldGroup.getGroups(), studentsInOldGroup);
                    studentToTutorialGroupsMap.put(newGroup.getGroups(), studentsInNewGroup);

                    System.out.println("\nStudent " + studentToMove.getStudentsName() + " successfully moved from " + oldGroup.getGroups() + " to " + newGroup.getGroups() + "\n");
                } else if (newGroupIndex == 0) {
                    System.out.println("\nOperation cancelled\n");
                } else {
                    System.out.println("\nInvalid tutorial group selection\n");
                }
            } else if (studentIndex == 0) {
                System.out.println("\nOperation cancelled\n");
            } else {
                System.out.println("\nInvalid student selection\n");
            }
        } else if (oldGroupIndex == 0) {
            System.out.println("\nOperation cancelled\n");
        } else {
            System.out.println("\nInvalid tutorial group selection\n");
        }
    }

    public void listProgrammesAndAssociatedTutorialGroups() {
        // Iterate over each program
        for (int i = 1; i <= programmesList.getNumberOfEntries(); i++) {
            Programmes programme = programmesList.getEntry(i);
            System.out.println("Programme: " + programme.getProgrammesName() + " (Code: " + programme.getProgrammesCode() + ")");

            // Find all tutorial groups associated with this program
            UniversalInterface<TutorialGroups> associatedTutorialGroups = tutorialGroupsToProgrammeMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

            if (!associatedTutorialGroups.isEmpty()) {
                System.out.println("Associated Tutorial Groups:");
                // Iterate over each associated tutorial group
                for (int j = 1; j <= associatedTutorialGroups.getNumberOfEntries(); j++) {
                    TutorialGroups tutorialGroup = associatedTutorialGroups.getEntry(j);
                    System.out.println("- Tutorial Group: " + tutorialGroup.getGroups());

                    // Find all students associated with this tutorial group
                    UniversalInterface<Students> associatedStudents = studentToTutorialGroupsMap.getOrDefault(tutorialGroup.getGroups(), new UniversalList<>());

                    if (!associatedStudents.isEmpty()) {
                        System.out.println("   Associated Students:");
                        // Iterate over each associated student
                        for (int k = 1; k <= associatedStudents.getNumberOfEntries(); k++) {
                            Students student = associatedStudents.getEntry(k);
                            System.out.println("   - " + student.getStudentsName() + " (ID: " + student.getID() + ")");
                        }
                    } else {
                        System.out.println("   No students associated with this tutorial group.");
                    }
                }
            } else {
                System.out.println("No tutorial groups associated with this program.");
            }
            System.out.println(); // Add a line break between programs
        }
    }

    public void mergeTutorialGroups() {
        // List all available tutorial groups
        for (int i = 1; i <= tutorialGroupsList.getNumberOfEntries(); i++) {
            TutorialGroups tutorialGroup = tutorialGroupsList.getEntry(i);
            System.out.println(i + ". " + tutorialGroup.getGroups());
        }

        // Get the selected tutorial groups to merge
        System.out.print("Enter the numbers of the tutorial groups to merge (separated by spaces): ");
        String input = systemUI.getUserInput();
        String[] selectedTutorialGroupsIndices = input.trim().split("\\s+");

        if (selectedTutorialGroupsIndices.length < 2) {
            System.out.println("Please select at least two tutorial groups to merge\n");
            return;
        }

        // Validate selected tutorial groups
        TutorialGroups[] selectedTutorialGroups = new TutorialGroups[selectedTutorialGroupsIndices.length];
        for (int i = 0; i < selectedTutorialGroupsIndices.length; i++) {
            int index = Integer.parseInt(selectedTutorialGroupsIndices[i]);
            if (index < 1 || index > tutorialGroupsList.getNumberOfEntries()) {
                System.out.println("\nInvalid tutorial group index: " + index + "\n");
                return;
            }
            selectedTutorialGroups[i] = tutorialGroupsList.getEntry(index);
        }

        // Ask for the new tutorial group name
        System.out.print("\nEnter the name for the merged tutorial group: ");
        String mergedGroupName = systemUI.getUserInput().trim();

        // Ask for the criteria to choose the programme for merging
        System.out.println("");
        for (int i = 1; i <= programmesList.getNumberOfEntries(); i++) {
            Programmes programme = programmesList.getEntry(i);
            System.out.println(i + ". " + programme.getProgrammesCode());
        }
        System.out.print("Enter the programme code to merge into (choose one of the existing programmes): ");
        int chosenProgrammeIndex = Integer.parseInt(systemUI.getUserInput().trim());
        if (chosenProgrammeIndex < 1 || chosenProgrammeIndex > programmesList.getNumberOfEntries()) {
            System.out.println("\nInvalid programme index\n");
            return;
        }
        Programmes chosenProgramme = programmesList.getEntry(chosenProgrammeIndex);
        String programmeCode = chosenProgramme.getProgrammesCode();

        // Check if the selected programme exists
        boolean programmeExists = false;
        for (int i = 1; i <= programmesList.getNumberOfEntries(); i++) {
            Programmes programme = programmesList.getEntry(i);
            if (programme.getProgrammesCode().equals(programmeCode)) {
                programmeExists = true;
                break;
            }
        }

        if (!programmeExists) {
            System.out.println("\nProgramme with code " + programmeCode + " does not exist\n");
            return;
        }

        // Merge tutorial groups and update associated students
        TutorialGroups mergedTutorialGroup = new TutorialGroups(mergedGroupName); // Set the name for merged group
        UniversalList<Students> mergedStudentsList = new UniversalList<>(); // Create a list to store merged students
        for (TutorialGroups group : selectedTutorialGroups) {

            // Get the associated students for the current tutorial group
            UniversalInterface<Students> associatedStudents = studentToTutorialGroupsMap.getOrDefault(group.getGroups(), new UniversalList<>());

            // Update the group for each associated student and add them to the merged students list
            for (int i = 1; i <= associatedStudents.getNumberOfEntries(); i++) {
                Students student = associatedStudents.getEntry(i);
                student.setGroups(mergedGroupName); // Update group for student
                mergedStudentsList.add(student); // Add student to merged list
            }

            // Remove the merged tutorial group from the map
            tutorialGroupsToProgrammeMap.remove(group.getGroups());
        }

        // Update the map with merged students list for the new merged group
        studentToTutorialGroupsMap.put(mergedGroupName, mergedStudentsList);

        // Add the merged tutorial group to the map for the selected programme
        UniversalInterface<TutorialGroups> associatedTutorialGroups = tutorialGroupsToProgrammeMap.getOrDefault(programmeCode, new UniversalList<>());
        associatedTutorialGroups.add(mergedTutorialGroup);
        tutorialGroupsToProgrammeMap.put(programmeCode, associatedTutorialGroups);

        // Remove the selected groups from the list of tutorial groups
        for (TutorialGroups group : selectedTutorialGroups) {
            // Find the index of the group in the list
            int indexToRemove = -1;
            for (int i = 1; i <= tutorialGroupsList.getNumberOfEntries(); i++) {
                TutorialGroups tutorialGroup = tutorialGroupsList.getEntry(i);
                if (tutorialGroup.equals(group)) {
                    indexToRemove = i;
                    break;
                }
            }
            // Remove the group if found
            if (indexToRemove != -1) {
                tutorialGroupsList.remove(indexToRemove);
            }
        }

        // Remove the previous groups from the programme
        for (TutorialGroups group : selectedTutorialGroups) {
            // Get all keys from the map as an array
            Object[] keys = tutorialGroupsToProgrammeMap.keySet();

            // Iterate through each key in the keys array
            for (Object objKey : keys) {
                String key = (String) objKey; // Assuming your keys are Strings
                UniversalInterface<TutorialGroups> value = tutorialGroupsToProgrammeMap.get(key);

                // Find and remove the group from the programme
                for (int i = 1; i <= value.getNumberOfEntries(); i++) {
                    if (value.getEntry(i).equals(group)) {
                        value.remove(i);
                        break; // Break the loop once the group is removed
                    }
                }
            }
        }

        System.out.println("\nTutorial groups successfully merged and students updated\n");
        tutorialGroupsList.add(mergedTutorialGroup);
    }

    public String getAllTutorialGroups() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= tutorialGroupsList.getNumberOfEntries(); i++) {
            outputStr.append(i)
                    .append(". ")
                    .append(tutorialGroupsList.getEntry(i))
                    .append("\n");
        }
        return outputStr.toString();
    }

    public void displayTutorialGroups() {
        systemUI.listAllTutorialGroups(getAllTutorialGroups());
    }

    public void removeTutorialGroups() {
        int removeTutorialGroups = systemUI.inputStudentsNumber();
        if (removeTutorialGroups > tutorialGroupsList.getNumberOfEntries()) {
            MessageUI.displayInvalidChoiceMessage();
        } else {
            tutorialGroupsList.remove(removeTutorialGroups);
            tutorialGroupsDAO.saveToFile(tutorialGroupsList);
            System.out.println("\nTutorial Group successfully removed");
        }
    }

    public void generateTutorialGroupManagementReport() {
        // Report header
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("                         TUTORIAL GROUP MANAGEMENT REPORT                              ");
        System.out.println("---------------------------------------------------------------------------------------\n");

        // Iterate over each programme
        for (int i = 1; i <= programmesList.getNumberOfEntries(); i++) {
            Programmes programme = programmesList.getEntry(i);
            System.out.println("Programme: " + programme.getProgrammesName() + " (Code: " + programme.getProgrammesCode() + ")");

            // Find all tutorial groups associated with this program
            UniversalInterface<TutorialGroups> associatedTutorialGroups = tutorialGroupsToProgrammeMap.getOrDefault(programme.getProgrammesCode(), new UniversalList<>());

            if (!associatedTutorialGroups.isEmpty()) {
                System.out.println("Associated Tutorial Groups:");

                // Iterate over each associated tutorial group
                for (int j = 1; j <= associatedTutorialGroups.getNumberOfEntries(); j++) {
                    TutorialGroups tutorialGroup = associatedTutorialGroups.getEntry(j);
                    System.out.println("- Tutorial Group: " + tutorialGroup.getGroups());

                    // Find all students associated with this tutorial group
                    UniversalInterface<Students> associatedStudents = studentToTutorialGroupsMap.getOrDefault(tutorialGroup.getGroups(), new UniversalList<>());

                    if (!associatedStudents.isEmpty()) {
                        System.out.println("   Associated Students:");

                        // Iterate over each associated student
                        for (int k = 1; k <= associatedStudents.getNumberOfEntries(); k++) {
                            Students student = associatedStudents.getEntry(k);
                            System.out.println("   - " + student.getStudentsName() + " (ID: " + student.getID() + ")");
                        }
                    } else {
                        System.out.println("   No students associated with this tutorial group.");
                    }
                }

            } else {
                System.out.println("No tutorial groups associated with this program.");
            }
            System.out.println(); // Add a line break between programs
        }

        System.out.println("---------------------------------------------------------------------------------------\n");
    }
    // Tutorial Group Management Subsystem (John Lee Xing) ---------------------

    // Assignment Team Management Subsystem (Ang Zi Qi) ------------------------
    public void createAssignmentTeamForTutorialGroup() {
        // Step 1: List out all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());

        // Step 2: Choose the tutorial group
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex > 0 && chosenGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

            // Step 3: Let the user find the assignment team
            System.out.print("Enter the name for the assignment team: ");
            String assignmentTeamName = systemUI.getUserInput().trim();

            // Check if the assignment team name already exists for the chosen tutorial group
            if (assignmentTutorialGroupMap.containsKey(chosenGroup.getGroups())) {
                UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
                for (int i = 1; i <= assignmentList.getNumberOfEntries(); i++) {
                    Assignment assignment = assignmentList.getEntry(i);
                    if (assignment.getTeamName().equals(assignmentTeamName)) {
                        System.out.println("\nAssignment team with the same name already exists for this tutorial group\n");
                        return; // Exit the method if duplicate found
                    }
                }
            }

            // Create the new assignment team
            Assignment newAssignmentTeam = new Assignment(assignmentTeamName);

            // Add the new assignment team to the assignment list for the chosen tutorial group
            UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.getOrDefault(chosenGroup.getGroups(), new UniversalList<>());
            assignmentList.add(newAssignmentTeam);
            assignmentTutorialGroupMap.put(chosenGroup.getGroups(), assignmentList);
            System.out.println("\nAssignment team created successfully\n");
        } else if (chosenGroupIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeAssignmentTeam() {
        // Step 1: List out all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());

        // Step 2: Choose the tutorial group
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex > 0 && chosenGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

            // Check if the assignment team name exists for the chosen tutorial group
            if (assignmentTutorialGroupMap.containsKey(chosenGroup.getGroups())) {
                UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
                for (int i = 1; i <= assignmentList.getNumberOfEntries(); i++) {
                    Assignment assignment = assignmentList.getEntry(i);
                    System.out.println(i + ". " + assignment);
                }

                int chosenAssignmentIndex = systemUI.assignmentSelection();
                if (chosenAssignmentIndex > 0 && chosenAssignmentIndex <= assignmentList.getNumberOfEntries()) {
                    assignmentList.remove(chosenAssignmentIndex);
                    System.out.println("\nAssignment team successfully removed\n");
                }

            } else {
                System.out.println("\nNo assignment team found for this tutorial group\n");
            }
        } else if (chosenGroupIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void amendAssingmentTeamDetails() {
        // Step 1: List out all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());

        // Step 2: Choose the tutorial group
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex > 0 && chosenGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

            // Check if the assignment team name exists for the chosen tutorial group
            if (assignmentTutorialGroupMap.containsKey(chosenGroup.getGroups())) {
                UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
                for (int i = 1; i <= assignmentList.getNumberOfEntries(); i++) {
                    Assignment assignment = assignmentList.getEntry(i);
                    System.out.println(i + ". " + assignment);
                }

                // Input assignment team details to update
                int chosenAssignmentIndex = systemUI.assignmentSelection();
                if (chosenAssignmentIndex > 0 && chosenAssignmentIndex <= assignmentList.getNumberOfEntries()) {
                    Assignment assignment = assignmentList.getEntry(chosenAssignmentIndex);
                    Assignment updatedAssignment = systemUI.inputAssignmentDetails();
                    assignment.setTeamName(updatedAssignment.getTeamName());
                    System.out.println("\nAssignment team details updated successfully\n");
                } else {
                    MessageUI.displayInvalidChoiceMessage();
                }

            } else {
                System.out.println("\nNo assignment team found for this tutorial group\n");
            }
        } else if (chosenGroupIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void addStudentsToAssignmentTeam() {
        // Step 1: List out all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());

        // Step 2: Choose the tutorial group
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex > 0 && chosenGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

            // Step 3: List all students in the chosen tutorial group
            if (studentToTutorialGroupsMap.containsKey(chosenGroup.getGroups())) {
                UniversalInterface<Students> studentList = studentToTutorialGroupsMap.get(chosenGroup.getGroups());
                for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                    Students student = studentList.getEntry(i);
                    System.out.println(i + ". " + student);
                }

                System.out.println("");

                int chosenStudentIndex = systemUI.studentSelection();
                if (chosenStudentIndex > 0 && chosenStudentIndex <= studentList.getNumberOfEntries()) {
                    Students student = studentList.getEntry(chosenStudentIndex);

                    // Step 4: Choose the assignment team for the student
                    if (assignmentTutorialGroupMap.containsKey(chosenGroup.getGroups())) {
                        UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
                        for (int i = 1; i <= assignmentList.getNumberOfEntries(); i++) {
                            Assignment assignment = assignmentList.getEntry(i);
                            System.out.println(i + ". " + assignment);
                        }

                        System.out.println("");

                        int chosenAssignmentIndex = systemUI.assignmentSelection();
                        if (chosenAssignmentIndex > 0 && chosenAssignmentIndex <= assignmentList.getNumberOfEntries()) {
                            Assignment assignment = assignmentList.getEntry(chosenAssignmentIndex);
                            UniversalInterface<Students> studentAssignments = studentsAssignmentMap.getOrDefault(assignment.getTeamName(), new UniversalList<>());

                            // Check if the student is already in the assignment team
                            boolean studentExists = false;
                            for (int i = 1; i <= studentAssignments.getNumberOfEntries(); i++) {
                                Students studentAssignment = studentAssignments.getEntry(i);
                                if (studentAssignment.getID().equals(student.getID())) {
                                    studentExists = true;
                                    break;
                                }
                            }

                            if (studentExists) {
                                System.out.println("\nStudent is already enrolled in this assignment team\n");
                            } else {
                                // Add the student to the assignment team
                                studentAssignments.add(student);
                                studentsAssignmentMap.put(assignment.getTeamName(), studentAssignments);
                                System.out.println("\nStudent successfully added into assignment team\n");
                            }
                        } else {
                            MessageUI.displayInvalidChoiceMessage();
                        }
                    } else {
                        System.out.println("\nNo assignment team found for this tutorial group\n");
                    }
                } else {
                    MessageUI.displayInvalidChoiceMessage();
                }
            } else {
                System.out.println("\nNo student found for this tutorial group\n");
            }
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeStudentFromAssignmentTeam() {
        // Step 1: List out all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());

        // Step 2: Choose the tutorial group
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex <= 0 || chosenGroupIndex > tutorialGroupsList.getNumberOfEntries()) {
            System.out.println("\nInvalid tutorial group selection\n");
            return;
        }

        TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

        // Check if there are students mapped to the tutorial group
        UniversalInterface<Students> studentList = studentToTutorialGroupsMap.get(chosenGroup.getGroups());
        if (studentList == null || studentList.getNumberOfEntries() == 0) {
            System.out.println("\nNo students found for this tutorial group\n");
            return;
        }

        // List students and let user choose one
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Students student = studentList.getEntry(i);
            System.out.println(i + ". " + student);
        }
        int chosenStudentIndex = systemUI.studentSelection();
        if (chosenStudentIndex <= 0 || chosenStudentIndex > studentList.getNumberOfEntries()) {
            System.out.println("\nInvalid student selection\n");
            return;
        }
        Students student = studentList.getEntry(chosenStudentIndex);

        // Check if there are assignment teams for the tutorial group
        UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
        if (assignmentList == null || assignmentList.getNumberOfEntries() == 0) {
            System.out.println("\nNo assignment teams found for this tutorial group\n");
            return;
        }

        // List assignment teams and let user choose one
        for (int i = 1; i <= assignmentList.getNumberOfEntries(); i++) {
            Assignment assignment = assignmentList.getEntry(i);
            System.out.println(i + ". " + assignment.getTeamName());
        }
        int chosenAssignmentIndex = systemUI.assignmentSelection();
        if (chosenAssignmentIndex <= 0 || chosenAssignmentIndex > assignmentList.getNumberOfEntries()) {
            System.out.println("\nInvalid assignment team selection\n");
            return;
        }
        Assignment chosenAssignment = assignmentList.getEntry(chosenAssignmentIndex);

        // Check if the student is part of the selected assignment team
        UniversalInterface<Students> studentAssignments = studentsAssignmentMap.get(chosenAssignment.getTeamName());
        if (studentAssignments == null || studentAssignments.getNumberOfEntries() == 0) {
            System.out.println("\nNo students found in the selected assignment team\n");
            return;
        }

        // Find and remove the student from the team
        boolean found = false;
        for (int i = 1; i <= studentAssignments.getNumberOfEntries(); i++) {
            Students assignedStudent = studentAssignments.getEntry(i);
            if (assignedStudent.getID().equals(student.getID())) {
                studentAssignments.remove(i);
                System.out.println("\nStudent successfully removed from assignment team\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nStudent not found in the selected assignment team\n");
        }
    }

    public void mergeAssignmentTeams() {
        // Step 1: List all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex <= 0 || chosenGroupIndex > tutorialGroupsList.getNumberOfEntries()) {
            System.out.println("\nInvalid tutorial group selection\n");
            return;
        }

        TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

        // Step 2: List all assignment teams within the chosen tutorial group
        if (!assignmentTutorialGroupMap.containsKey(chosenGroup.getGroups())) {
            System.out.println("\nNo assignment teams found for this tutorial group\n");
            return;
        }

        UniversalInterface<Assignment> assignmentTeams = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
        System.out.println("\nAssignment teams in " + chosenGroup.getGroups() + ":");
        for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
            System.out.println(i + ". " + assignmentTeams.getEntry(i).getTeamName());
        }

        System.out.println("");

        // Step 3: Get user input for teams to merge
        System.out.print("\nEnter the numbers of the assignment teams to merge (separated by spaces): ");
        String input = systemUI.getUserInput();
        String[] selectedTeamIndices = input.trim().split("\\s+");

        if (selectedTeamIndices.length < 2) {
            System.out.println("\nPlease select at least two assignment teams to merge\n");
            return;
        }

        // Step 4: Ask for the new assignment team name
        System.out.print("\nEnter the name for the merged assignment team: ");
        String mergedTeamName = systemUI.getUserInput().trim();

        // Initialize the new assignment list and the merged team
        Assignment mergedTeam = new Assignment(mergedTeamName);
        UniversalInterface<Assignment> newAssignmentList = new UniversalList<>();
        newAssignmentList.add(mergedTeam);

        // Merge selected teams
        UniversalList<Students> mergedStudents = new UniversalList<>();
        for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
            boolean isTeamSelected = false;
            for (String indexStr : selectedTeamIndices) {
                if (Integer.parseInt(indexStr) == i) {
                    isTeamSelected = true;
                    break;
                }
            }

            Assignment team = assignmentTeams.getEntry(i);
            if (isTeamSelected) {
                // If team is selected to merge, move its students to the merged team
                if (studentsAssignmentMap.containsKey(team.getTeamName())) {
                    UniversalInterface<Students> students = studentsAssignmentMap.get(team.getTeamName());
                    for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                        Students student = students.getEntry(j);
                        student.setAssignmentName(mergedTeamName); // Update student's team
                        mergedStudents.add(student);
                    }
                    studentsAssignmentMap.remove(team.getTeamName()); // Remove the old team from map
                }
            } else {
                newAssignmentList.add(team); // Keep unselected teams in the new list
            }
        }

        // Update the students map and the assignment team map
        studentsAssignmentMap.put(mergedTeamName, mergedStudents);
        assignmentTutorialGroupMap.put(chosenGroup.getGroups(), newAssignmentList);

        System.out.println("\nAssignment teams successfully merged into '" + mergedTeamName + "'\n");
    }

    public void listAssignmentTeamForTutorialGroup() {
        // Step 1: List out all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());
        // Step 2: Choose the tutorial group
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex > 0 && chosenGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

            // Step 3: Let the user create the assignment team
            // Check if the assignment team name already exists for the chosen tutorial group
            if (assignmentTutorialGroupMap.containsKey(chosenGroup.getGroups())) {
                UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
                for (int i = 1; i <= assignmentList.getNumberOfEntries(); i++) {
                    Assignment assignment = assignmentList.getEntry(i);
                    System.out.println(i + ". " + assignment);
                }

                System.out.println("");

            } else {
                System.out.println("\nNo assignment team found for this tutorial group\n");
            }
        } else if (chosenGroupIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public void listStudentsUnderAnAssignmentTeam() {
        // Step 1: List out all tutorial groups
        systemUI.listAllTutorialGroups(getAllTutorialGroups());

        // Step 2: Choose the tutorial group
        int chosenGroupIndex = systemUI.tutorialGroupSelection();
        if (chosenGroupIndex > 0 && chosenGroupIndex <= tutorialGroupsList.getNumberOfEntries()) {
            TutorialGroups chosenGroup = tutorialGroupsList.getEntry(chosenGroupIndex);

            // Step 3: Let the user create the assignment team
            // Check if the assignment team name already exists for the chosen tutorial group
            if (assignmentTutorialGroupMap.containsKey(chosenGroup.getGroups())) {
                UniversalInterface<Assignment> assignmentList = assignmentTutorialGroupMap.get(chosenGroup.getGroups());
                for (int i = 1; i <= assignmentList.getNumberOfEntries(); i++) {
                    Assignment assignment = assignmentList.getEntry(i);
                    System.out.println(i + ". " + assignment);
                }

                System.out.println("");

                int chosenAssignmentIndex = systemUI.assignmentSelection();
                if (chosenAssignmentIndex > 0 && chosenAssignmentIndex <= assignmentList.getNumberOfEntries()) {
                    Assignment chosenAssignment = assignmentList.getEntry(chosenAssignmentIndex);

                    if (studentsAssignmentMap.containsKey(chosenAssignment.getTeamName())) {
                        UniversalInterface<Students> studentList = studentsAssignmentMap.get(chosenAssignment.getTeamName());
                        for (int x = 1; x <= studentList.getNumberOfEntries(); x++) {
                            Students student = studentList.getEntry(x);
                            System.out.println(x + ". " + student);
                        }

                        System.out.println("");
                    } else {
                        System.out.println("\nNo student found from this assignment team\n");
                    }

                }

            } else {
                System.out.println("\nNo assignment team found for this tutorial group\n");
            }
        } else if (chosenGroupIndex == 0) {
            // Handle cancel action
        } else {
            MessageUI.displayInvalidChoiceMessage();
        }
    }

    public String getAllAssignment() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i <= assignmentsList.getNumberOfEntries(); i++) {
            outputStr.append(i)
                    .append(". ")
                    .append(assignmentsList.getEntry(i))
                    .append("\n");
        }
        return outputStr.toString();
    }

    public void generateAssignmentReport() {
        // Report title header
        System.out.println("Teaching Assignment Management System Report\n");

        // Summary of assignment teams
        System.out.println("- Summary of Assignment Teams:\n");
        listAssignmentTeamForTutorialGroup();
        System.out.println();

        // List of students under each assignment team
        System.out.println("- List of Students Under Each Assignment Team:\n");
        listStudentsUnderAnAssignmentTeam();
        System.out.println();
    }
    // Assignment Team Management Subsystem (Ang Zi Qi) ------------------------

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Main main = new Main();
        main.runSystem(scanner);
    }
}
