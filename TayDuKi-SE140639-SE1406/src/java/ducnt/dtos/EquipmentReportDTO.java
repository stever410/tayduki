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
public class EquipmentReportDTO implements Serializable {

    private String equipmentID;
    private String fateID;
    private Date addDate;
    private boolean active;
    private String directorID;

    public EquipmentReportDTO(String equipmentID, String fateID, Date addDate, boolean active, String directorID) {
        this.equipmentID = equipmentID;
        this.fateID = fateID;
        this.addDate = addDate;
        this.active = active;
        this.directorID = directorID;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getFateID() {
        return fateID;
    }

    public void setFateID(String fateID) {
        this.fateID = fateID;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    @Override
    public String toString() {
        return "EquipmentReportDTO{" + "equipmentID=" + equipmentID + ", fateID=" + fateID + ", addDate=" + addDate + ", status=" + active + ", directorID=" + directorID + '}';
    }

}
