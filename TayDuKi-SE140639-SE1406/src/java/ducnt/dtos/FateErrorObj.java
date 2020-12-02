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
public class FateErrorObj implements Serializable {

    private String fateIDError;
    private String fateNameError;
    private String fateDescriptionError;
    private String fateShootPlaceError;
    private String fateStartTimeError;
    private String fateEndTimeError;
    private String fateShootCountError;
    private String fateRequirementFileError;
    private String fateDirectorIDError;

    public FateErrorObj() {
    }

    public String getFateIDError() {
        return fateIDError;
    }

    public void setFateIDError(String fateIDError) {
        this.fateIDError = fateIDError;
    }

    public String getFateNameError() {
        return fateNameError;
    }

    public void setFateNameError(String fateNameError) {
        this.fateNameError = fateNameError;
    }

    public String getFateDescriptionError() {
        return fateDescriptionError;
    }

    public void setFateDescriptionError(String fateDescriptionError) {
        this.fateDescriptionError = fateDescriptionError;
    }

    public String getFateShootPlaceError() {
        return fateShootPlaceError;
    }

    public void setFateShootPlaceError(String fateShootPlaceError) {
        this.fateShootPlaceError = fateShootPlaceError;
    }

    public String getFateStartTimeError() {
        return fateStartTimeError;
    }

    public void setFateStartTimeError(String fateStartTimeError) {
        this.fateStartTimeError = fateStartTimeError;
    }

    public String getFateEndTimeError() {
        return fateEndTimeError;
    }

    public void setFateEndTimeError(String fateEndTimeError) {
        this.fateEndTimeError = fateEndTimeError;
    }

    public String getFateShootCountError() {
        return fateShootCountError;
    }

    public void setFateShootCountError(String fateShootCountError) {
        this.fateShootCountError = fateShootCountError;
    }

    public String getFateRequirementFileError() {
        return fateRequirementFileError;
    }

    public void setFateRequirementFileError(String fateRequirementFileError) {
        this.fateRequirementFileError = fateRequirementFileError;
    }

    public String getFateDirectorIDError() {
        return fateDirectorIDError;
    }

    public void setFateDirectorIDError(String fateDirectorIDError) {
        this.fateDirectorIDError = fateDirectorIDError;
    }

}
