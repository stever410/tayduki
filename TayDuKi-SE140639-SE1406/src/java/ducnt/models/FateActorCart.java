/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducnt.models;

import ducnt.daos.FateActorDetailDAO;
import ducnt.dtos.FateActorDetailDTO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author ngota
 */
public class FateActorCart implements Serializable {

    private String directorID;
    private String fateID;
    private HashMap<String, FateActorDetailDTO> cart;

    public FateActorCart(String directorID) {
        this.directorID = directorID;
        this.cart = new HashMap<>();
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

    public HashMap<String, FateActorDetailDTO> getCart() {
        return cart;
    }

    public void addActorToCart(FateActorDetailDTO dto) throws Exception {
        if(this.cart == null) {
            cart = new HashMap<>();
        }
        this.cart.put(dto.getRoleName(), dto);
    }

    public void removeRoleFromCart(String roleName) throws Exception {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(roleName)) {
            this.cart.remove(roleName);
        }
    }

    public void updateActorInCart(FateActorDetailDTO dto) throws Exception {
        if(this.cart == null) {
            return;
        }
        if(this.cart.containsKey(dto.getRoleName())) {
            this.cart.put(dto.getRoleName(), dto);
        }
    }
    
    public void insertFateActorCart() throws Exception {
        if (this.cart == null) {
            return;
        }
        FateActorDetailDAO dao = new FateActorDetailDAO();
        dao.insertFateActorDetailCart(fateID, directorID, cart);
    }
    
    public FateActorDetailDTO getRoleNameFromCart(String roleName) {
        if(this.cart == null) {
            return null;
        }
        return this.cart.get(roleName);
    }
}
