/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.FateDAO;
import ducnt.dtos.FateDTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ngota
 */
public class FateBean implements Serializable {

    private String fateID;
    private FateDAO dao;
    private List<FateDTO> listFates;
    private String search;
    private String directorID;

    public FateBean() {
        dao = new FateDAO();
    }

    public boolean addFate(FateDTO dto) throws Exception {
        return dao.addFate(dto);
    }

    public List<FateDTO> findByLikeName(int start, int total) throws Exception {
        return listFates = dao.findByLikeName(search, start, total);
    }

    public int getAmountOfFindByLikeName() throws Exception {
        return dao.getAmountOfFindByLikeName(search);
    }

    public boolean updateFate(FateDTO dto) throws Exception {
        return dao.updateFate(dto);
    }

    public boolean deleteFate() throws Exception {
        return dao.deleteFate(fateID);
    }

    public FateDTO findByPrimaryKey() throws Exception {
        return dao.findByPrimaryKey(fateID);
    }

    public List<FateDTO> getListFates() {
        return listFates;
    }

    public List<FateDTO> findFateOfDirector() throws Exception {
        return listFates = dao.findFateOfDirector(directorID);
    }

    public void setListFates(List<FateDTO> listFates) {
        this.listFates = listFates;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getFateID() {
        return fateID;
    }

    public void setFateID(String fateID) {
        this.fateID = fateID;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

}
