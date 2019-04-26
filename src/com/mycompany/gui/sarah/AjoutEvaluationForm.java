/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.sarah;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.mycompagny.Service.EvaluationService;
import com.mycompagny.Service.EvaluationService.TitreEntreprise;
import com.mycompany.Entities.Evaluation;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sarah
 */
public class AjoutEvaluationForm {
     Form f;
    TextField tOpinion;
    Slider SC1 ; 
    Slider SC2 ; 
    Slider SC3 ; 
    Slider SC4 ; 
   
    SpanLabel LC1 , LC2 , LC3 , LC4 ; 
    
    Button btnajout ; 

    public AjoutEvaluationForm() {
        f = new Form("Ajouter Experience" , BoxLayout.y());
       
       tOpinion = new TextField("", "Opinion général sur votre 'journey' dans l'entreprise");
      SC1 = new Slider() ; 
       SC2 = new Slider() ; 
       SC3= new Slider() ; 
       SC4 = new Slider() ; 
       
       LC1= new SpanLabel("Qualité et Consistance du Sujet Stage") ; 
       LC2= new SpanLabel("Encadrement et Soutient dans la réalisation du Projet ") ; 
       LC3= new SpanLabel("Ambiance de Travail et integration au Sein de l'Entreprise") ; 
       LC4= new SpanLabel("Disponibilité des Ressources et Documentation necessaire") ; 
       
       
     //  ----------------------------------------PARAMETRES SLIDERS------------------------------------
      SC1.setMaxValue(10);
    SC1.setMinValue(0); 
    SC1.setProgress(1); // Set  the starting value
   SC1.setEditable(true); // To it works as a slider instead of a progress bar 
       
    SC2.setMaxValue(10);
    SC2.setMinValue(0); 
    SC2.setProgress(1); // Set  the starting value
  SC2.setEditable(true); 
  
   
   SC3.setMaxValue(10);
    SC3.setMinValue(0); 
    SC3.setProgress(1); // Set  the starting value
   SC3.setEditable(true); 
   
    SC4.setMaxValue(10);
    SC4.setMinValue(0); 
   SC4.setProgress(1); // Set  the starting value
   SC4.setEditable(true); 
   
 //---------------------------------------------END PARAMETRES SLIDERS -----------------------------------------   
       
        btnajout = new Button("ajouter");
     
        
        //------------------------------------------------------
           EvaluationService ser = new EvaluationService() ; 
      
             ArrayList<TitreEntreprise> lis = ser.getTitreStageEntreprise(usr.getId());
             
             for (TitreEntreprise e : lis ) {
             SpanLabel LabStage = new SpanLabel("Evaluation du Stage " + e.getTitreStage() + " chez " + e.getEntreprise() ) ; 
             f.add(LabStage) ;
             }
       
       
        f.add(LC1) ; 
        f.add(SC1) ; 
        f.add(LC2) ; 
        f.add(SC2) ; 
        f.add(LC3) ; 
        f.add(SC3) ; 
        f.add(LC4) ; 
        f.add(SC4) ; 
        
         f.add(tOpinion);
        f.add(btnajout);
       
        
        btnajout.addActionListener((e) -> {
           
            Date d = new Date(); //(int id, String description, String photo, int nbrJaime, Date dateModif)
            Evaluation ev = new Evaluation(0, usr.getId(), SC1.getProgress(),SC2.getProgress(),SC3.getProgress(), SC4.getProgress(), tOpinion.getText(), d) ; 
            ser.ajoutEvaluation(ev);
            Dialog.show("Ajout","Ajouté avec Succes", "Ok" , null ) ; 
            
        });
       
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField gettOpinion() {
        return tOpinion;
    }

    public void settOpinion(TextField tOpinion) {
        this.tOpinion = tOpinion;
    }

    public Slider getSC1() {
        return SC1;
    }

    public void setSC1(Slider SC1) {
        this.SC1 = SC1;
    }

    public Slider getSC2() {
        return SC2;
    }

    public void setSC2(Slider SC2) {
        this.SC2 = SC2;
    }

    public Slider getSC3() {
        return SC3;
    }

    public void setSC3(Slider SC3) {
        this.SC3 = SC3;
    }

    public Slider getSC4() {
        return SC4;
    }

    public void setSC4(Slider SC4) {
        this.SC4 = SC4;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

   

  

   

}
