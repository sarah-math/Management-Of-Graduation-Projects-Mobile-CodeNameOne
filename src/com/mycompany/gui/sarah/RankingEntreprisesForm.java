/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.sarah;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.codename1.uikit.materialscreens.MSUIKit;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.mycompagny.Service.EvaluationService;
import com.mycompagny.Service.EvaluationService.EvalPerUser;
import com.mycompagny.Service.EvaluationService.NotesEntreprise;
import java.util.ArrayList;


/**
 *
 * @author sarah
 */
public class RankingEntreprisesForm {
     Form f;
    
     private Resources theme;

    public RankingEntreprisesForm() {
        theme = UIManager.initNamedTheme("/theme2", "Theme2");
        
        
        f = new Form("Ranking Entreprises" ,  BoxLayout.y());
        
        f.getToolbar().addCommandToLeftBar("Arrière", null, (ev) -> {
                       MSUIKit ert = new MSUIKit();
            ert.init(new Object());
            new ProfileForm(ert.getTheme()).show();
                    });
   //   SpanLabel  lb = new SpanLabel("");
      //  f.add(lb);
       EvaluationService ser = new EvaluationService() ; 
           ArrayList<NotesEntreprise> lis = ser.SortByMOYntreprise() ; 
      
      //-----------------------test
      final Button add = new Button("Ajouter");

f.add(add);

 add.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        
         int user_id = usr.getId() ;  
             Boolean check = ser.ControlHasEvaluated(user_id) ;
             Boolean check2 = ser.ControlHasFinishedStage(user_id) ;
             Boolean check3 = ser.ControlHasStage(user_id) ;
             if (check ) {  
                // Form NewDenial= new Form("DENIAL" , BoxLayout.y()) ; 
                  ArrayList<EvalPerUser> L= ser.AfficherEvalPerUser(user_id)  ; 
               
                  for ( EvalPerUser ev : L ) {
               
          Dialog.show("Evaluation Impossible", "Vous avez déja Attribué la note de " + ev.getNoteGlobale() +"/10  à l'Entreprise " + ev.getNomEntreprise() + "\n Vous avez Témoigné: \n" +  ev.getOpinion() , "OK", null);             
                      
             //    Container NOPE = new Container(BoxLayout.y()) ; 
                 
           //  SpanLabel Note = new SpanLabel("Vous avez Attribué la note de " + ev.getNoteGlobale() +"/10  à l'Entreprise " + ev.getNomEntreprise() ) ; 
             //  SpanLabel opinion = new SpanLabel (ev.getOpinion())  ; 
           
        
               
               
            //   NOPE.add(Note) ; 
             //  NOPE.add(opinion) ; 
              //  NewDenial.add(NOPE) ; 
                  
                  } // NewDenial.show() ;  
                 
                 }
             
             else {
                  if (!check3) {  
               //  Form NewDenial= new Form("DENIAL" , BoxLayout.y()) ; 
             // Container NOPE = new Container(BoxLayout.y()) ; 
         //    SpanLabel Alert = new SpanLabel("Vous n'avez meme pas de stage vous ne pouvez effectuer aucune evaluation") ;  
           Dialog.show("EVALUATION IMPOSSIBLE", "Vous n'avez meme pas de stage vous ne pouvez effectuer aucune evaluation", "Ok" , null) ; 


//   NOPE.add(Alert) ; 
             //  NewDenial.add(NOPE) ; 
             //   NewDenial.show() ; 
             
             } else {
                 
                 
                 
                 
                 if( !check2) {
                     // Form NewDenial= new Form("DENIAL" , BoxLayout.y()) ; 
                  
                  
               //  Container NOPE = new Container(BoxLayout.y()) ; 
           
               String ALERT = "Vous ne pouvez pas evaluer l'entreprise tant que vous n'avez pas fini votre Stage"  ; 
          Dialog.show("Evaluation Impossible", ALERT , "Ok" , null ) ; 
             //  NOPE.add(ALERT) ; 
              
              //  NewDenial.add(NOPE) ; 
                  
                //    NewDenial.show() ; 
                 }  
                  else 
                 
                  { AjoutEvaluationForm Ajout = new AjoutEvaluationForm() ; 
             Ajout.getF().show() ;
                  
                  Ajout.getF().getToolbar().addCommandToLeftBar("back", null, (e)-> {
                      RankingEntreprisesForm rk = new RankingEntreprisesForm(); 
                      rk.getF().show();}) ; }
             } }
            
    
    }
        
   
                
});
      //---------------------------------
           
       //Button AddB= new Button("Ajouter"); 
         
       //    lb.setText(lis + "");

           
        for  ( NotesEntreprise e : lis)  {
               
               Container CHoriz = new Container(new BorderLayout()) ;
           Label NomEntrep = new Label(e.getNomEntrep() ) ; 
           Label NoteGlob = new Label(e.getMoyGlobale()+ "") ; 
           Label Rank = new Label(lis.indexOf(e)+1 + "") ; 
        
           CHoriz.add(BorderLayout.WEST,Rank) ; 
           CHoriz.add(BorderLayout.CENTER , NomEntrep) ; 
           CHoriz.add(BorderLayout.EAST, NoteGlob) ; 
           
           
      NomEntrep.addPointerPressedListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent evt) {/*
          Form StatFF = new Form("Testimonials" , BoxLayout.y()) ; 
       ArrayList<Evaluation> OpinionList= ser.AfficherOpinionEntreprise(e.getIdEntrep()) ;
       
        for  ( Evaluation op :  OpinionList)  {
               
               Container COp = new Container( ) ;
           Label Op = new Label(op.getOpinion() ) ;    
           COp.add(Op) ; 
                  StatFF.add(COp) ; 
                   } 
                StatFF.show();
            
             */
      StatisticsEntrepriseForm Stat= new StatisticsEntrepriseForm(e.getIdEntrep(), e.getNomEntrep()) ; 
      
       Stat.getF() ; 
              
                   
                   } 
                   
               
               });        
           
           
           f.add(CHoriz) ; 
           
           } 
         
    // f.add(lb) ; 
  //  ----------------------------SORT 1 -----------------------------------------------------
    f.getToolbar().addCommandToOverflowMenu("Consistance du projet" , null , e->{ 
            
        Form F1= new Form("Consistance du projet" , BoxLayout.y()) ; 
            ArrayList<NotesEntreprise> lis1 = ser.SortByC1ntreprise(); 
               for  ( NotesEntreprise e1 : lis1)  {
               
               Container CHoriz = new Container() ;
           Label NomEntrep = new Label(e1.getNomEntrep()) ; 
           Label NoteGlob = new Label(e1.getMoyC1()+ "") ; 
           Label Rank = new Label(lis1.indexOf(e1)+1 + "") ; 
        
           CHoriz.add(BorderLayout.WEST,Rank) ; 
           CHoriz.add(BorderLayout.CENTER , NomEntrep) ; 
           CHoriz.add(BorderLayout.EAST, NoteGlob) ; 
           F1.add(CHoriz) ; 
           
      
    
           
               } 
               
           F1.getToolbar().addCommandToRightBar( "back" , null ,
                     d->{ f.show() ; 
             }) ;
        F1.show() ;
         });
    
  //--------------------------------------SORT 2 ---//CHECK FAZET EL LIST1 -------------------------------------------------------  
    
     
     f.getToolbar().addCommandToOverflowMenu("Qualité Encadrement" , null , e->{ 
           
         Form F2= new Form("Qualité Encadrement" , BoxLayout.y()) ; 
              ArrayList<NotesEntreprise> lis1 = ser.SortByC2ntreprise(); 
               for  ( NotesEntreprise e1 : lis1)  {
               
               Container CHoriz = new Container() ;
           Label NomEntrep = new Label(e1.getNomEntrep()) ; 
           Label NoteGlob = new Label(e1.getMoyC2()+ "") ; 
           Label Rank = new Label(lis1.indexOf(e1)+1 + "") ; 
        
          CHoriz.add(BorderLayout.WEST,Rank) ; 
           CHoriz.add(BorderLayout.CENTER , NomEntrep) ; 
           CHoriz.add(BorderLayout.EAST, NoteGlob) ; 
            
           F2.add(CHoriz) ; 
           
           } 
           
             F2.getToolbar().addCommandToRightBar( "back" , null ,
                     d->{ f.show() ; 
             }) ;
         F2.show() ;
         });
  
     
//--------------------------------------SORT 3 ----------------------------------------------------------  
      f.getToolbar().addCommandToOverflowMenu("Ambiance de Travail" , null , e->{ 
             Form F3= new Form("Ambiance de Travail" , BoxLayout.y()) ; 
            ArrayList<NotesEntreprise> lis1 = ser.SortByC3ntreprise() ;
               for  ( NotesEntreprise e1 : lis1)  {
               
               Container CHoriz = new Container() ;
           Label NomEntrep = new Label(e1.getNomEntrep()) ; 
           Label NoteGlob = new Label(e1.getMoyC3()+ "") ; 
           Label Rank = new Label(lis1.indexOf(e1)+1 + "") ; 
        
           CHoriz.add(BorderLayout.WEST,Rank) ; 
           CHoriz.add(BorderLayout.CENTER , NomEntrep) ; 
           CHoriz.add(BorderLayout.EAST, NoteGlob) ; 
            
           F3.add(CHoriz) ; 
           
           } 
             
             F3.getToolbar().addCommandToRightBar( "back" , null ,
                     d->{ f.show() ; 
             }) ;
        F3.show() ;
         });
   
      
 //--------------------------------------SORT 4----------------------------------------------------------  
       f.getToolbar().addCommandToOverflowMenu("Disponibilité Ressources" , null , e->{ 
             Form F4= new Form("Disponibilité Ressources" , BoxLayout.y()) ; 
              ArrayList<NotesEntreprise> lis1 = ser.SortByC4ntreprise(); 
               for  ( NotesEntreprise e1 : lis1)  {
               
               Container CHoriz = new Container() ;
           Label NomEntrep = new Label(e1.getNomEntrep()) ; 
           Label NoteGlob = new Label(e1.getMoyC4()+ "") ; 
           Label Rank = new Label(lis1.indexOf(e1)+1 + "") ; 
        
           CHoriz.add(BorderLayout.WEST,Rank) ; 
           CHoriz.add(BorderLayout.CENTER , NomEntrep) ; 
           CHoriz.add(BorderLayout.EAST, NoteGlob) ; 
           F4.add(CHoriz) ; 
           
           } 
          
            F4.getToolbar().addCommandToRightBar( "back" , null ,
                     d->{ f.show() ; 
             }) ;
         F4.show() ;
         });
     
 //-------------------------------------------------------------------------------------------------------------        
         
         
       /*  f.getToolbar().addCommandToOverflowMenu("Add Evaluation" , null , e->{ 
            
             int user_id = 8 ;  
             Boolean check = ser.ControlHasEvaluated(user_id) ;
             Boolean check2 = ser.ControlHasFinishedStage(user_id) ;
             Boolean check3 = ser.ControlHasStage(user_id) ;
             if (check ) {  
                 Form NewDenial= new Form("DENIAL" , BoxLayout.y()) ; 
                  ArrayList<EvalPerUser> L= ser.AfficherEvalPerUser(8)  ; 
               
                  for ( EvalPerUser ev : L ) {
                  
                 Container NOPE = new Container(BoxLayout.y()) ; 
             SpanLabel Note = new SpanLabel("Vous avez Attribué la note de " + ev.getNoteGlobale() +"/10  à l'Entreprise " + ev.getNomEntreprise() ) ; 
               SpanLabel opinion = new SpanLabel (ev.getOpinion())  ; 
               
               NOPE.add(Note) ; 
               NOPE.add(opinion) ; 
                NewDenial.add(NOPE) ; 
                  
                  }  NewDenial.show() ; 
                 }
             
             else {
                  if (!check3) {  
                 Form NewDenial= new Form("DENIAL" , BoxLayout.y()) ; 
              Container NOPE = new Container(BoxLayout.y()) ; 
             SpanLabel Alert = new SpanLabel("Vous n'avez meme pas de stage vous ne pouvez effectuer aucune evaluation") ;  
              NOPE.add(Alert) ; 
               NewDenial.add(NOPE) ; 
                NewDenial.show() ; 
             
             } else {
                 
                 
                 
                 
                 if( !check2) {
                      Form NewDenial= new Form("DENIAL" , BoxLayout.y()) ; 
                  
                  
                 Container NOPE = new Container(BoxLayout.y()) ; 
           
               SpanLabel ALERT = new SpanLabel ("Vous ne pouvez pas evaluer l'entreprise tant que vous n'avez pas fini votre Stage")  ; 
               
               NOPE.add(ALERT) ; 
              
                NewDenial.add(NOPE) ; 
                  
                    NewDenial.show() ; }  
                  else 
                 
                  { AjoutEvaluationForm Ajout = new AjoutEvaluationForm() ; 
             Ajout.getF().show() ; }
             } }
            
             
         }); */
         
         
         
         
         
         
           
        f.show();
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }
    
    
    
     
     
     
     
        
     
     
     
     
     
}
