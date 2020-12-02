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
public class FateActorDetailErrorObj implements Serializable {

    private String fateIDError;
    private String actorIDError;
    private String roleNameError;
    private String roleDescriptionError;

    public FateActorDetailErrorObj() {
    }

    public String getFateIDError() {
        return fateIDError;
    }

    public void setFateIDError(String fateIDError) {
        this.fateIDError = fateIDError;
    }

    public String getActorIDError() {
        return actorIDError;
    }

    public void setActorIDError(String actorIDError) {
        this.actorIDError = actorIDError;
    }

    public String getRoleNameError() {
        return roleNameError;
    }

    public void setRoleNameError(String roleNameError) {
        this.roleNameError = roleNameError;
    }

    public String getRoleDescriptionError() {
        return roleDescriptionError;
    }

    public void setRoleDescriptionError(String roleDescriptionError) {
        this.roleDescriptionError = roleDescriptionError;
    }

}
