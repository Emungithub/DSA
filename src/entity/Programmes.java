package entity;

import java.io.Serializable;
import java.util.Objects;

public class Programmes implements Serializable {

    private String programmesCode;
    private String programmesName;
    private String courseCode;
    private String courseType;
    private String courseName; // Added field to store course name
    private double courseFee;

    public Programmes() {

    }

    public Programmes(String programmesCode, String programmesName) {
        this.programmesCode = programmesCode;
        this.programmesName = programmesName;
    }
    
    public Programmes(String programmesCode) {
        this.programmesCode = programmesCode;
    }

    // Getters and setters for course fields
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    // Getter and setter for course name
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public String getProgrammesCode() {
        return programmesCode;
    }

    public void setProgrammesCode(String programemsCode) {
        this.programmesCode = programemsCode;
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
        final Programmes other = (Programmes) obj;
        if (!Objects.equals(this.programmesName, other.programmesName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-40s", programmesCode, programmesName);
    }

}
