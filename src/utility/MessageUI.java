package utility;

public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice\n");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system\n");
    }

    public static void displayStudentListEmptyMessage() {
        System.out.println("\nStudent list is empty\n");
    }

    public static void displayCourseListEmptyMessage() {
        System.out.println("\nCourse list is empty\n");
    }

    public static void displayProgrammeListEmptyMessage() {
        System.out.println("\nProgramme list is empty\n");
    }

    public static void displayTutorialGroupListEmptyMessage() {
        System.out.println("\nTutorial group list is empty\n");
    }
    
    public static void displayInvalidStudentNameInputMessage() {
        System.out.println("\nNames should not contain numbers. Please try again\n");
    }

    public static void displayInvalidCourseTypeMessage() {
        System.out.println("\nPlease enter 'main', 'elective', 'resit', or 'repeat'\n");
    }
    
    public static void displaySameDetailsMessage() {
        System.out.println("\nDetails entered are the same\n");
    }
    
}
