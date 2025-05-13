package entity;

import java.io.Serializable;
import java.util.Objects;

public class Courses implements Serializable {

    private String coursesCode;
    private String coursesName;
    private double coursesFee;
    private String coursesType;
    private String programmesCode;
    private String programmesName;
    private String faculties;

    public Courses() {

    }

    public Courses(String coursesCode, String coursesName, double coursesFee, String faculties) {
        this.coursesCode = coursesCode;
        this.coursesName = coursesName;
        this.coursesFee = coursesFee;
        this.faculties = faculties;
    }
    
    public String getFaculties() {
        return faculties;
    }

    public void setFaculties(String faculties) {
        this.faculties = faculties;
    }

    public Courses(String coursesCode, String coursesName) {
        this.coursesCode = coursesCode;
        this.coursesName = coursesName;
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

    public String getCoursesType() {
        return coursesType;
    }

    public void setCoursesType(String coursesType) {
        this.coursesType = coursesType;
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
        final Courses other = (Courses) obj;
        if (!Objects.equals(this.coursesCode, other.coursesCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-40s %-10s %-20s", coursesCode, coursesName, coursesFee, faculties);
    }

}
