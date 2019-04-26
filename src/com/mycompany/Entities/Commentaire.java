/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

import java.util.Date;

/**
 *
 * @author sarah
 */
public class Commentaire {
     private int id ;
     private int id_user;
     private int id_exp ; 
     private String contenu ;
     private Date dateModif ; 

    public Commentaire() {
    }

    public Commentaire(int id, int id_user, int id_exp, String contenu, Date dateModif) {
        this.id = id;
        this.id_user = id_user;
        this.id_exp = id_exp;
        this.contenu = contenu;
        this.dateModif = dateModif;
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", id_user=" + id_user + ", id_exp=" + id_exp + ", contenu=" + contenu + ", dateModif=" + dateModif + '}';
    }
     
     
     
}
