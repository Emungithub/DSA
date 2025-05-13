package entity;

import java.io.Serializable;
import java.util.Objects;

public class TutorialGroups implements Serializable {

    private String groups;
    private String programmesCode;
    private String programmesName;
    private String tutorsID;
    private String tutorsName;
    
    private String coursesCode;
    private String coursesName;
    private double coursesFee;
    private String coursesType;
    
    private String tutorialType; // New attribute for tutorial type

    public TutorialGroups() {

    }

    public TutorialGroups(String groups) {
        this.groups = groups;
    }
    
    public TutorialGroups(String groups, String coursesCode, String tutorialType) {
        this.groups = groups;
        this.coursesCode = coursesCode;
        this.tutorialType = tutorialType;
    }

    public String getTutorialType() {
        return tutorialType;
    }

    public void setTutorialType(String tutorialType) {
        this.tutorialType = tutorialType;
    }

    public String getCoursesCode() {
        return coursesCode;
    }

    public void setCoursesCode(String coursesCode) {
        this.coursesCode = coursesCode;
    }

    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
    }

    public double getCoursesFee() {
        return coursesFee;
    }

    public void setCoursesFee(double coursesFee) {
        this.coursesFee = coursesFee;
    }

    public String getCoursesType() {
        return coursesType;
    }

    public void setCoursesType(String coursesType) {
        this.coursesType = coursesType;
    }

    public String getTutorsID() {
        return tutorsID;
    }

    public void setTutorsID(String tutorsID) {
        this.tutorsID = tutorsID;
    }

    public String getTutorsName() {
        return tutorsName;
    }

    public void setTutorsName(String tutorsName) {
        this.tutorsName = tutorsName;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getProgrammesCode() {
        return programmesCode;
    }

    public void setProgrammesCode(String programmesCode) {
        this.programmesCode = programmesCode;
    }

    public String getProgrammesName() {
        return programmesName;
    }

    public void setProgrammesName(String programmesName) {
        this.programmesName = programmesName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TutorialGroups other = (TutorialGroups) obj;
        if (!Objects.equals(this.groups, other.groups)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-10s",groups);
    }

}
