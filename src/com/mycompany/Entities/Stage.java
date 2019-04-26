/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

import java.util.Date;


/**
 *
 * @author user
 */
public class Stage {
    private int id;
    private int identreprise;
    private int idencadrant;
    private int idetudiant;
    private String titreStage;
    private String Descreptif ;
    private Date dateDebut;
    private Date dateFin;
    private String nometudiant;
    private String mailetudiant;

    public Stage(int id, int identreprise, int idencadrant, int idetudiant, String titreStage, String Descreptif, Date dateDebut, Date dateFin) {
        this.id = id;
        this.identreprise = identreprise;
        this.idencadrant = idencadrant;
        this.idetudiant = idetudiant;
        this.titreStage = titreStage;
        this.Descreptif = Descreptif;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Stage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentreprise() {
        return identreprise;
    }

    public void setIdentreprise(int identreprise) {
        this.identreprise = identreprise;
    }

    public int getIdencadrant() {
        return idencadrant;
    }

    public void setIdencadrant(int idencadrant) {
        this.idencadrant = idencadrant;
    }

    public int getIdetudiant() {
        return idetudiant;
    }
    
    public void setIdetudiant(int idetudiant) {
        this.idetudiant = idetudiant;
    }

    public String getNometudiant() {
        return nometudiant;
    }

    public void setNometudiant(String nometudiant) {
        this.nometudiant = nometudiant;
    }
    
    

    public String getTitreStage() {
        return titreStage;
    }

    public void setTitreStage(String titreStage) {
        this.titreStage = titreStage;
    }

    public String getDescreptif() {
        return Descreptif;
    }

    public void setDescreptif(String Descreptif) {
        this.Descreptif = Descreptif;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMailetudiant() {
        return mailetudiant;
    }

    public void setMailetudiant(String mailetudiant) {
        this.mailetudiant = mailetudiant;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    

    @Override
    public String toString() {
        return "Stage{" + "id=" + id + ", identreprise=" + identreprise + ", idencadrant=" + idencadrant + ", idetudiant=" + idetudiant + ", titreStage=" + titreStage + ", Descreptif=" + Descreptif + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    
    
    
}
