/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngota
 */
public class FateDTO implements Serializable {

    private String fateID;
    private String fateName;
    private String fateDescription;
    private String fateShootPlace;
    private Date fateStartTime;
    private Date fateEndTime;
    private int fateShootCount;
    private String fateRequirementFile;
    private boolean active;
    private String fateDirectorID;

    public FateDTO(String fateID, String fateName, String fateDescription, String fateShootPlace, Date fateStartTime, Date fateEndTime, int fateShootCount, String fateRequirementFile, String fateDirectorID) {
        this.fateID = fateID;
        this.fateName = fateName;
        this.fateDescription = fateDescription;
        this.fateShootPlace = fateShootPlace;
        this.fateStartTime = fateStartTime;
        this.fateEndTime = fateEndTime;
        this.fateShootCount = fateShootCount;
        this.fateRequirementFile = fateRequirementFile;
        this.fateDirectorID = fateDirectorID;
        this.active = true;
    }

    public FateDTO(String fateID, String fateName, String fateDescription, String fateShootPlace, Date fateStartTime, Date fateEndTime, int fateShootCount, String fateRequirementFile, boolean active, String fateDirectorID) {
        this.fateID = fateID;
        this.fateName = fateName;
        this.fateDescription = fateDescription;
        this.fateShootPlace = fateShootPlace;
        this.fateStartTime = fateStartTime;
        this.fateEndTime = fateEndTime;
        this.fateShootCount = fateShootCount;
        this.fateRequirementFile = fateRequirementFile;
        this.active = active;
        this.fateDirectorID = fateDirectorID;
    }

    public String getFateID() {
        return fateID;
    }

    public void setFateID(String fateID) {
        this.fateID = fateID;
    }

    public String getFateName() {
        return fateName;
    }

    public void setFateName(String fateName) {
        this.fateName = fateName;
    }

    public String getFateDescription() {
        return fateDescription;
    }

    public void setFateDescription(String fateDescription) {
        this.fateDescription = fateDescription;
    }

    public String getFateShootPlace() {
        return fateShootPlace;
    }

    public void setFateShootPlace(String fateShootPlace) {
        this.fateShootPlace = fateShootPlace;
    }

    public Date getFateStartTime() {
        return fateStartTime;
    }

    public void setFateStartTime(Date fateStartTime) {
        this.fateStartTime = fateStartTime;
    }

    public Date getFateEndTime() {
        return fateEndTime;
    }

    public void setFateEndTime(Date fateEndTime) {
        this.fateEndTime = fateEndTime;
    }

    public int getFateShootCount() {
        return fateShootCount;
    }

    public void setFateShootCount(int fateShootCount) {
        this.fateShootCount = fateShootCount;
    }

    public String getFateRequirementFile() {
        return fateRequirementFile;
    }

    public void setFateRequirementFile(String fateRequirementFile) {
        this.fateRequirementFile = fateRequirementFile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFateDirectorID() {
        return fateDirectorID;
    }

    public void setFateDirectorID(String fateDirectorID) {
        this.fateDirectorID = fateDirectorID;
    }

    @Override
    public String toString() {
        return "FateDTO{" + "fateID=" + fateID + ", fateName=" + fateName + ", fateDescription=" + fateDescription + ", fateShootPlace=" + fateShootPlace + ", fateStartTime=" + fateStartTime + ", fateEndTime=" + fateEndTime + ", fateShootCount=" + fateShootCount + ", fateRequirementFile=" + fateRequirementFile + ", active=" + active + ", fateDirectorID=" + fateDirectorID + '}';
    }

}
