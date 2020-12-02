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
public class FateActorDetailDTO implements Serializable {

    private String fateID;
    private String actorID;
    private String roleName;
    private String roleDescription;
    private String directorID;

    public FateActorDetailDTO(String fateID, String actorID, String roleName, String roleDescription, String directorID) {
        this.fateID = fateID;
        this.actorID = actorID;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.directorID = directorID;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public String getFateID() {
        return fateID;
    }

    public void setFateID(String fateID) {
        this.fateID = fateID;
    }

    public String getActorID() {
        return actorID;
    }

    public void setActorID(String actorID) {
        this.actorID = actorID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public String toString() {
        return "FateActorDetailDTO{" + "fateID=" + fateID + ", actorID=" + actorID + ", roleName=" + roleName + ", roleDescription=" + roleDescription + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.fateID);
        hash = 37 * hash + Objects.hashCode(this.actorID);
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
        final FateActorDetailDTO other = (FateActorDetailDTO) obj;
        if (!Objects.equals(this.fateID, other.fateID)) {
            return false;
        }
        if (!Objects.equals(this.actorID, other.actorID)) {
            return false;
        }
        return true;
    }

}
