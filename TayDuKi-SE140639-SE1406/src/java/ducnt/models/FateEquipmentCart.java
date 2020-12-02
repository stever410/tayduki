/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.FateEquipmentDetailDAO;
import ducnt.dtos.FateEquipmentDetailDTO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author ngota
 */
public class FateEquipmentCart implements Serializable {

    private String fateID;
    private int quantity;
    private String directorID;
    private HashMap<FateEquipmentDetailDTO, Integer> cart;

    public FateEquipmentCart(String directorID) {
        this.directorID = directorID;
        this.cart = new HashMap<>();
    }

    public String getFateID() {
        return fateID;
    }

    public void setFateID(String fateID) {
        this.fateID = fateID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public HashMap<FateEquipmentDetailDTO, Integer> getCart() {
        return cart;
    }

    public void addEquipmentToCart(FateEquipmentDetailDTO dto) throws Exception {
        if (cart == null) {
            cart = new HashMap<>();
        }
        int equipmentQuantity = 1;
        if (cart.containsKey(dto)) {
            equipmentQuantity = cart.get(dto) + 1;
        }
        this.cart.put(dto, equipmentQuantity);
    }

    public void removeEquipmentFromCart(String equipmentID) throws Exception {
        if (this.cart == null) {
            return;
        }
        FateEquipmentDetailDTO dto = new FateEquipmentDetailDTO(null, equipmentID, 1, directorID);
        if (this.cart.containsKey(dto)) {
            this.cart.remove(dto);
        }
    }

    public void updateEquipmentInCart(String equipmentID, int quantity) throws Exception {
        if (this.cart == null) {
            return;
        }
        FateEquipmentDetailDTO dto = new FateEquipmentDetailDTO(null, equipmentID, quantity, directorID);
        if (this.cart.containsKey(dto)) {
            this.cart.put(dto, quantity);
        }
    }

    public void insertFateEquipmentCart() throws Exception {
        if (this.cart == null) {
            return;
        }
        FateEquipmentDetailDAO dao = new FateEquipmentDetailDAO();
        dao.insertFateEquipmentDetailCart(fateID, directorID, cart);
    }
}
