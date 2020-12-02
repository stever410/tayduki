/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.EquipmentReportDAO;
import ducnt.dtos.EquipmentReportDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ngota
 */
public class EquipmentReportBean implements Serializable {

    private Date startTime, endTime;
    private boolean active;
    private EquipmentReportDAO dao;
    private List<EquipmentReportDTO> equipmentsReportList;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<EquipmentReportDTO> getEquipmentsReportList() {
        return equipmentsReportList;
    }

    public void setEquipmentsReportList(List<EquipmentReportDTO> equipmentsReportList) {
        this.equipmentsReportList = equipmentsReportList;
    }

    public EquipmentReportBean() {
        dao = new EquipmentReportDAO();
    }

    public List<EquipmentReportDTO> findEquipmentsByDateAndStatus(int start, int total) throws Exception {
        return equipmentsReportList = dao.findEquipmentsByDateAndStatus(startTime, endTime, active, start, total);
    }

    public int getAmountOfFindEquipmentsByDateAndStatus() throws Exception {
        return dao.getAmountOfFindEquipmentsByDateAndStatus(startTime, endTime, active);
    }
}
