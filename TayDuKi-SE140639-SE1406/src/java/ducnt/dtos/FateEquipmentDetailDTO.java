/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.dtos;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ngota
 */
public class FateEquipmentDetailDTO implements Serializable {

    private String fateID;
    private String equipmentID;
    private int equipmentAmount;
    private String directorID;

    public FateEquipmentDetailDTO(String fateID, String equipmentID, int equipmentAmount, String directorID) {
        this.fateID = fateID;
        this.equipmentID = equipmentID;
        this.equipmentAmount = equipmentAmount;
        this.directorID = directorID;
    }

    public String getFateID() {
        return fateID;
    }

    public void setFateID(String fateID) {
        this.fateID = fateID;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public int getEquipmentAmount() {
        return equipmentAmount;
    }

    public void setEquipmentAmount(int equipmentAmount) {
        this.equipmentAmount = equipmentAmount;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    @Override
    public String toString() {
        return "FateEquipmentDetailDTO{" + "fateID=" + fateID + ", equipmentID=" + equipmentID + ", equipmentAmount=" + equipmentAmount + ", directorID=" + directorID + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.fateID);
        hash = 47 * hash + Objects.hashCode(this.equipmentID);
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
        final FateEquipmentDetailDTO other = (FateEquipmentDetailDTO) obj;
        if (!Objects.equals(this.fateID, other.fateID)) {
            return false;
        }
        if (!Objects.equals(this.equipmentID, other.equipmentID)) {
            return false;
        }
        return true;
    }

}
