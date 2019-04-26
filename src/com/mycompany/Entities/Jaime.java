/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

/**
 *
 * @author sarah
 */
public class Jaime {
   private int id ;
    private int id_user ;
    private int id_exp ; 

    public Jaime(int id, int id_user, int id_exp) {
        this.id = id;
        this.id_user = id_user;
        this.id_exp = id_exp;
    }

    public Jaime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_exp() {
        return id_exp;
    }

    public void setId_exp(int id_exp) {
        this.id_exp = id_exp;
    }

    @Override
    public String toString() {
        return "Jaime{" + "id=" + id + ", id_user=" + id_user + ", id_exp=" + id_exp + '}';
    }
    
    
      
}
