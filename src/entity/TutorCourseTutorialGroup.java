package entity;

import java.io.Serializable;

public class TutorCourseTutorialGroup implements Serializable {

    private String tutorID;
    private String courseCode;
    private String tutorialGroup;
    private String tutorialType;

    public TutorCourseTutorialGroup(String tutorID, String courseCode, String tutorialGroup, String tutorialType) {
        this.tutorID = tutorID;
        this.courseCode = courseCode;
        this.tutorialGroup = tutorialGroup;
        this.tutorialType = tutorialType;
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(String tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public String getTutorialType() {
        return tutorialType;
    }

    public void setTutorialType(String tutorialType) {
        this.tutorialType = tutorialType;
    }

    @Override
    public String toString() {
        return "Tutor ID: " + tutorID + ", Course Code: " + courseCode + ", Tutorial Group: " + tutorialGroup + ", Tutorial Type: " + tutorialType;
    }
}
