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
public class FateReportDTO implements Serializable {

    private String fateID;
    private String actorID;
    private String roleName;
    private String roleDescription;
    private Date startTime;
    private Date endTime;
    private String requirementFile;

    public FateReportDTO(String fateID, String actorID, String roleName, String roleDescription, Date startTime, Date endTime, String requirementFile) {
        this.fateID = fateID;
        this.actorID = actorID;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requirementFile = requirementFile;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRequirementFile() {
        return requirementFile;
    }

    public void setRequirementFile(String requirementFile) {
        this.requirementFile = requirementFile;
    }

    @Override
    public String toString() {
        return "FateReportDTO{" + "fateID=" + fateID + ", actorID=" + actorID + ", roleName=" + roleName + ", roleDescription=" + roleDescription + ", startTime=" + startTime + ", endTime=" + endTime + ", requirementFile=" + requirementFile + '}';
    }

}
