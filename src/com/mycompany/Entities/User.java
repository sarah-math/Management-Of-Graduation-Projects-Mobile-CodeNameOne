/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

import com.codename1.ui.Image;
import java.util.Date;

/**
 *
 * @author user
 */


/**
 *
 * @author ahmed
 */
public class User {

    private String username;
    private int id;
    private String email;
    private boolean enabled;
    private String password;
    private Date lastLogin;
    private String roles;
    private String nom;
    private String prenom;
    private String age;
    private String ville;
    private Image image;
    private String rue;
    private String codePostal;
    private int numTel;
    private boolean confirmer;
    private String image1;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    
    
    public boolean isConfirmer() {
        return confirmer;
    }

    public void setConfirmer(boolean confirmer) {
        this.confirmer = confirmer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) 
    {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", id=" + id + ", email=" + email + ", enabled=" + enabled + ", password=" + password + ", lastLogin=" + lastLogin + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", ville=" + ville + ", image=" + image + ", rue=" + rue + ", codePostal=" + codePostal + ", numTel=" + numTel + '}';
    }

}
