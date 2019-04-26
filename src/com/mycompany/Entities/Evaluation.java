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
public class Evaluation {
     private int id ;
     private int id_user ;
     private float C1 ;
     private float C2 ;
     private float C3 ;
     private float C4 ;
     private String opinion ;
     private Date dateModif ;

     
       public Evaluation() {
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
    
    
       public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

       
   /* public Evaluation(int id, int id_user, int C1, int C2, int C3, int C4, String opinion, Date dateModif) {
        this.id = id;
        this.id_user = id_user;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.opinion = opinion;
        this.dateModif = dateModif;
    }


    public int getC1() {
        return C1;
    }

    public void setC1(int C1) {
        this.C1 = C1;
    }

    public int getC2() {
        return C2;
    }

    public void setC2(int C2) {
        this.C2 = C2;
    }

    public int getC3() {
        return C3;
    }

    public void setC3(int C3) {
        this.C3 = C3;
    }

    public int getC4() {
        return C4;
    }

    public void setC4(int C4) {
        this.C4 = C4;
    }

 

*/

    public Evaluation(int id, int id_user, float C1, float C2, float C3, float C4, String opinion, Date dateModif) {
        this.id = id;
        this.id_user = id_user;
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        this.C4 = C4;
        this.opinion = opinion;
        this.dateModif = dateModif;
    }

    public float getC1() {
        return C1;
    }

    public void setC1(float C1) {
        this.C1 = C1;
    }

    public float getC2() {
        return C2;
    }

    public void setC2(float C2) {
        this.C2 = C2;
    }

    public float getC3() {
        return C3;
    }

    public void setC3(float C3) {
        this.C3 = C3;
    }

    public float getC4() {
        return C4;
    }

    public void setC4(float C4) {
        this.C4 = C4;
    }
     
     
    
    
     
    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", id_user=" + id_user + ", C1=" + C1 + ", C2=" + C2 + ", C3=" + C3 + ", C4=" + C4 + ", opinion=" + opinion + ", dateModif=" + dateModif + '}';
    }
     
     
     
     
     
     
     
     
     
     
     
     
}
