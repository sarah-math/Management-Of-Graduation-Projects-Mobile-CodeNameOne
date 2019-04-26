/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.sarah;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.materialscreens.LoginForm;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.mycompagny.Service.EvaluationService;
import com.mycompagny.Service.ServiceExperience;
import com.mycompany.Entities.Experience;
import java.util.Date;

/**
 *
 * @author sana
 */
public class AjoutForm {

    Form f;
  //  TextField tTitre;
    TextField tDescription;
   // TextField tCategorie;
   // TextField tDescription;
    
   
   
    
    
    Button btnajout ;

    public AjoutForm() {
         int id_user=usr.getId() ;
        
        f = new Form("Ajouter Experience" , BoxLayout.y());
        Button butPhoto = new Button("Photo") ;
 //   Button butCamera = new Button("Camera") ;
        tDescription = new TextField();
      
        btnajout = new Button("ajouter");
       // btnaff=new Button("Affichage");
       
       f.add(butPhoto) ; 
     //  f.add(butCamera) ; 
       
   butPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
     if (Dialog.show("Ajouter Photo", null, "Galerie" , "Camera"))  { /* Upload Galerie*/ System.out.println("HELLO"); }
     else  {
   /* String i=  Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1 ); 
    if (i!=null) {
        try {
            Image img= Image.createImage(i);
            //findP
              //findPhoto().setIcon(img) ;
        } catch (IOException ex) {
            Logger.getLogger(AjoutForm.class.getName()).log(Level.SEVERE, null, ex);
        }*/
   
         System.out.println("COCO");
  
    
    } 
     
     
     
     
     /*Camera  */ 
      

      
            }
        });
       
       
        f.add(tDescription);
        
        f.add(btnajout);
     //   f.add(btnaff);
        
        btnajout.addActionListener((e) -> {
            ServiceExperience ser = new ServiceExperience();
            EvaluationService ser2 = new EvaluationService();
         if(   ser2.ControlHasStage(id_user) ) 
         {  Date d = new Date(); //(int id, String description, String photo, int nbrJaime, Date dateModif)
            Experience t = new Experience(0, id_user ,  tDescription.getText(), "" , 0 , d );
            ser.ajoutExperience(t);
            Dialog.show("Ajout","Ajouté avec Succes", "Ok" , null ) ; 
         } 
         
         else {Dialog.show("Impossible d'ajouter une experience" , "Vous ne pouvez pas partager une experience tant que vous n avez pas de stage", "ok" , null) ;  }  
       
            

        });
     /*   btnaff.addActionListener((e)->{
        AffichageForm a=new AffichageForm();
        a.getF().show();
        }); */
     
     
     
      f.getToolbar().addCommandToLeftBar("back", null, (e)->{AffichageForm aff = new AffichageForm();   aff.getF().show(); }) ;
     
     
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

   

    public TextField gettDescription() {
        return tDescription;
    }

    public void settDescription(TextField tDescription) {
        this.tDescription = tDescription;
    }

   

  
}
