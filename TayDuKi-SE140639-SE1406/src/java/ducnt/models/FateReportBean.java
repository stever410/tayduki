/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.FateReportDAO;
import ducnt.dtos.FateReportDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ngota
 */
public class FateReportBean implements Serializable {

    FateReportDAO dao;
    List<FateReportDTO> listReports;
    String actorID;

    public List<FateReportDTO> getListReports() {
        return listReports;
    }

    public void setListReports(List<FateReportDTO> listReports) {
        this.listReports = listReports;
    }

    public String getActorID() {
        return actorID;
    }

    public void setActorID(String actorID) {
        this.actorID = actorID;
    }

    public FateReportBean() {
        dao = new FateReportDAO();
    }

    public List<FateReportDTO> getHistoryFatesOfActor(int start, int total) throws Exception {
        return listReports = dao.getHistoryFatesOfActor(actorID, start, total);
    }

    public int getAmountHistoryFatesOfActor() throws Exception {
        return dao.getAmountHistoryFatesOfActor(actorID);
    }

    public List<FateReportDTO> getFollowingFatesOfActor() throws Exception {
        return listReports = dao.getFollowingFatesOfActor(actorID);
    }

    public List<FateReportDTO> getFatesFileByActorID() throws Exception {
        return listReports = dao.getFatesFileByActorID(actorID);
    }
}
