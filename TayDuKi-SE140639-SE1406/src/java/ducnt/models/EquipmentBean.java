/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.EquipmentDAO;
import ducnt.dtos.EquipmentDTO;
import ducnt.dtos.FateEquipmentDetailDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ngota
 */
public class EquipmentBean implements Serializable {

    private String equipmentID;
    private EquipmentDAO dao;
    private List<EquipmentDTO> listEquipments;
    private String search;

    public EquipmentBean() {
        dao = new EquipmentDAO();
    }

    public boolean addEquipment(EquipmentDTO dto) throws Exception {
        return dao.addEquipment(dto);
    }

    public int getAmountOfFindByLikeName() throws Exception {
        return dao.getAmountOfFindByLikeName(search);
    }

    public boolean updateEquipment(EquipmentDTO dto) throws Exception {
        return dao.updateEquipment(dto);
    }

    public List<EquipmentDTO> findByLikeName(int start, int total) throws Exception {
        return listEquipments = dao.findByLikeName(search, start, total);
    }

    public EquipmentDTO findByPrimaryKey() throws Exception {
        return dao.findByPrimaryKey(equipmentID);
    }

    public boolean deleteEquipment() throws Exception {
        return dao.deleteEquipment(equipmentID);
    }

    public void updateEquipmentAmount(HashMap<FateEquipmentDetailDTO, Integer> cart) throws Exception {
        dao.updateEquipmentAmount(cart);
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public List<EquipmentDTO> getListEquipments() {
        return listEquipments;
    }

    public void setListEquipments(List<EquipmentDTO> listEquipments) {
        this.listEquipments = listEquipments;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
