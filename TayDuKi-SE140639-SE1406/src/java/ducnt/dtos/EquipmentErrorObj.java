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
public class EquipmentErrorObj implements Serializable {

    private String equipmentIDError;
    private String equipmentNameError;
    private String equipmentDescriptionError;
    private String equipmentImageError;
    private String equipmentAmountError;

    public EquipmentErrorObj() {
    }

    public String getEquipmentIDError() {
        return equipmentIDError;
    }

    public void setEquipmentIDError(String equipmentIDError) {
        this.equipmentIDError = equipmentIDError;
    }

    public String getEquipmentNameError() {
        return equipmentNameError;
    }

    public void setEquipmentNameError(String equipmentNameError) {
        this.equipmentNameError = equipmentNameError;
    }

    public String getEquipmentDescriptionError() {
        return equipmentDescriptionError;
    }

    public void setEquipmentDescriptionError(String equipmentDescriptionError) {
        this.equipmentDescriptionError = equipmentDescriptionError;
    }

    public String getEquipmentImageError() {
        return equipmentImageError;
    }

    public void setEquipmentImageError(String equipmentImageError) {
        this.equipmentImageError = equipmentImageError;
    }

    public String getEquipmentAmountError() {
        return equipmentAmountError;
    }

    public void setEquipmentAmountError(String equipmentAmountError) {
        this.equipmentAmountError = equipmentAmountError;
    }

}
