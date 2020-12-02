/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.dtos;

import java.io.Serializable;

/**
 *
 * @author ngota
 */
public class EquipmentDTO implements Serializable {

    private String equipmentID;
    private String equipmentName;
    private String equipmentDescription;
    private String equipmentImage;
    private int equipmentAmount;
    private boolean active;

    public EquipmentDTO(String equipmentID, String equipmentName, String equipmentDescription, String equipmentImage, int amount) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentDescription = equipmentDescription;
        this.equipmentImage = equipmentImage;
        this.equipmentAmount = amount;
        this.active = true;
    }

    public EquipmentDTO(String equipmentID, String equipmentName, String equipmentDescription, String equipmentImage, int amount, boolean active) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentDescription = equipmentDescription;
        this.equipmentImage = equipmentImage;
        this.equipmentAmount = amount;
        this.active = active;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public String getEquipmentImage() {
        return equipmentImage;
    }

    public void setEquipmentImage(String equipmentImage) {
        this.equipmentImage = equipmentImage;
    }

    public int getEquipmentAmount() {
        return equipmentAmount;
    }

    public void setEquipmentAmount(int equipmentAmount) {
        this.equipmentAmount = equipmentAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "EquipmentDTO{" + "equipmentID=" + equipmentID + ", equipmentName=" + equipmentName + ", equipmentDescription=" + equipmentDescription + ", equipmentImage=" + equipmentImage + ", equipmentAmount=" + equipmentAmount + ", active=" + active + '}';
    }

}
