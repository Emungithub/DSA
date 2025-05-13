package entity;

import java.io.Serializable;
import java.util.Objects;

public class Assignment implements Serializable {

    private String teamName;
    private String ID;

    public Assignment() {

    }

    public Assignment(String teamName) {
        this.teamName = teamName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
        final Assignment other = (Assignment) obj;
        return Objects.equals(this.teamName, other.teamName);
    }

    @Override
    public String toString() {
        return String.format("%-40s", teamName);
    }

}
