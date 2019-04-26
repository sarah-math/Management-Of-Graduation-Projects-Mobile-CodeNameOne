/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;

/**
 *
 * @author sana
 */
public class Experience {
   private int id;
   private  int id_user ;
   private String description;
   private String photo;
 
   private int nbrJaime;
  
    private Date dateModif;

    public Experience() {
    }

    public Experience(int id, int id_user, String description, String photo, int nbrJaime, Date dateModif) {
        this.id = id;
         this.id_user = id_user;
        this.description = description;
        this.photo = photo;
        this.nbrJaime = nbrJaime;
       
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

    public  void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNbrJaime() {
        return nbrJaime;
    }

    public void setNbrJaime(int nbrJaime) {
        this.nbrJaime = nbrJaime;
    }

   
    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    @Override
    public String toString() {
        return "Experience{" + "id=" + id + ", description=" + description + ", photo=" + photo + ", nbrJaime=" + nbrJaime +  ", dateModif=" + dateModif + '}';
    }
    
    
    
    
    
    

}
