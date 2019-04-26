/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;



/**
 *
 * @author user
 */
public class Tache {

    private int id;
    private int idstage;
    private String tache;
    private Boolean Etat;

    public Tache(int id, int idstage, String tache, Boolean Etat) {
        this.id = id;
        this.idstage = idstage;
        this.tache = tache;
        this.Etat = Etat;
    }

    public Tache() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdstage() {
        return idstage;
    }

    public void setIdstage(int idstage) {
        this.idstage = idstage;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public Boolean getEtat() {
        return Etat;
    }

    public void setEtat(Boolean Etat) {
        this.Etat = Etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    

    @Override
    public String toString() {
        return "Tache{" + "id=" + id + ", idstage=" + idstage + ", tache=" + tache + ", Etat=" + Etat + '}';
    }
    

}
