/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.sarah;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.codename1.uikit.materialscreens.MSUIKit;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.mycompagny.Service.ServiceExperience;
import com.mycompagny.Service.ServiceExperience.Com;
import com.mycompagny.Service.ServiceExperience.Expe;
import com.mycompagny.Service.ServiceExperience.Jaim;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class AffichageForm {

    Form f;
    SpanLabel lb;
     private Resources theme;
  
    public AffichageForm() {
        
         theme = UIManager.initNamedTheme("/theme2", "Theme2");
        
        
        f = new Form("Experience Sharing" , new BoxLayout(BoxLayout.Y_AXIS));
        
        f.getToolbar().addCommandToLeftBar("Arrière", null, (ev) -> {
                       MSUIKit ert = new MSUIKit();
            ert.init(new Object());
            new ProfileForm(ert.getTheme()).show();
                    });
        
        
     //   lb = new SpanLabel("");
      //  f.add(lb);
        ServiceExperience serviceExperience=new ServiceExperience();
        ArrayList<Expe> lis =serviceExperience.AfficherAll();
      
        
        Container C0 = new Container (BoxLayout.x()) ; 
        Button AddB  = new Button ("+") ; 
        Button SearchB  = new Button ("Search") ; 
        Button ProfB = new Button ("Profil") ; 
        Button WishB = new Button ("Wish") ; 
        
        C0.add(SearchB);
          C0.add(AddB);
        C0.add(ProfB);
      
     //   C0.add(WishB);
        
        f.add(C0) ; 
        ///////////////////////////////////////////////////////ADDDDDD
     AddB.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
               AjoutForm ajF = new AjoutForm() ; 
               ajF.getF().show(); 
               
             }
         }) ; //te5dem jawwha béhi 
      ///////////////////////////////////////////////////////////////////////////////////////// SEARCH 
          SearchB.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
          
                 Form se = new Form ( "Rechercher une Experience" , BoxLayout.y())  ; 
                   
              TextField EntrepTf = new TextField("", "Entreprise") ; 
              TextField StageTf = new TextField("", "Titre Stage" ) ; 
          
                Button Val = new Button("Valider") ; 
              Container SearchC = new Container(BoxLayout.y()) ; 
              SearchC.add(EntrepTf) ; 
              SearchC.add(StageTf) ; 
              SearchC.add(Val) ; 
            
                se.add(SearchC) ; 
              
                Container CBlock = new Container(BoxLayout.y()) ; 
     Val.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                       ArrayList<Expe> lisR =serviceExperience.Recherche(EntrepTf.getText(),StageTf.getText()) ; 
                       
                        for(Expe e : lis) {
           
                 Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS) )   ;
               //  C1.setPreferredW(300);
                 Container UserC = new Container(BoxLayout.x()) ; 
                Container  Info =new Container(BoxLayout.y()) ; 
                 
                 // ImageViewer img= new ImageViewer(theme.getImage(e.getImage())) ;
                 
                 ImageViewer img= new ImageViewer(theme.getImage("aa.jpg")) ; //fill(300, 300)
                 ImageViewer imgProf= new ImageViewer(theme.getImage("meliodas.png").fill(80, 80)) ;
                    UserC.add(imgProf) ; 
                 // Image img=Image.createImage(p.getFlag()) ;
                 // e.setImage(img);
                 //Label labUser = new Label( e.getId_user());
                 Label labDescription = new Label(  e.getE().getDescription()) ;
                 
                 
                // SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
              //   Date  dateM = format.parse(e.getDateModif().toString());
            
   ///DAAAATE IS HEEEEERE                Label labDate = new Label( e.getE().getDateModif().toString()) ;
                 Label labJaime = new Label(e.getE().getNbrJaime()+ " j'aimes") ;
                 Label labNomPrenom = new Label(e.getNom()+ "  "+ e.getPrenom() ) ;
                 Label labStage = new Label(e.getTitreStage() + " Chez " + e.getUsername() ) ;
             //    Label labEntreprise = new Label(e.getUsername() ) ;
                 
                     Button bJaime = new Button ("like") ; 
                     Button bCom = new Button("Comment") ; 
                     Button bPartage = new Button("Share");
                     Button bWishl = new Button("Wish") ; 
                     
                     
                    Container Buts  = new Container(BoxLayout.x()) ; 
                 Buts.add(bJaime) ;
                 Buts.add(bCom) ;
                 Buts.add(bPartage) ;
                 Buts.add(bWishl) ;
                 
                 
                 Info.add(labNomPrenom) ; 
                 Info.add(labStage) ; 
                UserC.add(Info) ;    
              
                C1.add(UserC) ; 
                 C1.add(img) ;
                   C1.add(Buts) ; 
                 C1.add(labDescription) ;
                // C1.add(labDate) ;
                 C1.add(labJaime) ;
               
                 
                 
            //  ArrayList<Com> lis2 = new  ArrayList() ; 
                   //     lis2 =serviceExperience.AfficherAllComm(e.getE().getId());            
                            
             //   int x = lis2.size() ;
                 
               Label labComm = new Label ("Afficher les n commentaires") ; 
                      C1.add(labComm) ; 
                     
                 
                
                 se.add(C1) ;
                 
             se.refreshTheme();
             
                 // lb.setText(lis.toString());
                 //  f.refreshTheme();
            
        }  
                       
                   
                       
                     }   
                 });
                
                    se.show(); 
                /////////////////////////BLOCK RECHERCHE/////////////////////////////////////////////
               
                
            /////////////////////////////////////////////////Sinon Show la Liste Normale////////////////////////////
                 
                
             // a.getF().add(SearchC) ; 
                     
             // a.getF().show() ; 
              
               
             }
         }) ;  // ma te5demch  
         ///////////////////////////////////END SEARCH ///////////////////////////////////////////////////////
        
        int id_user=usr.getId();
         //////////////////////////////////DEBUT PROFIL ////////////////////////////////////////////////
         
          ProfB.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
                  Form MineF = new Form("Profile" , BoxLayout.y()) ;
         
                  MineF.getToolbar().addCommandToLeftBar("back", null, (e)->{AffichageForm aff= new AffichageForm();
                  aff.getF().show();}) ;
                  
                   ArrayList<Expe> lis =serviceExperience.AfficherUserList(id_user); /////STATIC
                   
                  
                   Container InfoTOP = new Container(BoxLayout.x()) ;
                    ImageViewer imgProf= new ImageViewer(theme.getImage("meliodas.png").fill(100, 100)) ;
                    
                    Expe aa = lis.get(1) ; 
                    
                    Container infUser = new Container(BoxLayout.y()) ; 
                        Label labNomPrenom = new Label(aa.getNom()+ "  "+ aa.getPrenom() ) ;
                 Label labStage = new Label(aa.getTitreStage() + " Chez " + aa.getUsername() ) ;
                 infUser.add(labNomPrenom) ; 
                 infUser.add(labStage) ; 
                 
                 InfoTOP.add(imgProf) ; 
                 InfoTOP.add(infUser) ; 
                         
             MineF.add(InfoTOP) ;
                    for(Expe e : lis) {    
   
                    
         /// --    Publication USER****************************************      
                    
                   Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS) )   ;
                
               //  C1.setPreferredW(300);
                
                 
                
           
                 EncodedImage inc = EncodedImage.createFromImage(theme.getImage("aa.jpg").fill(300,300), false);
              
String URLimage = "http://localhost/gestionpfe/web/uploads/user/exp/" + e.getE().getPhoto();
//String URLimage = "http://localhost/gestionpfe/web/uploads/user/exp/hello.jpg";
             

URLImage sarah = URLImage.createToStorage(inc, e.getE().getId()+"", URLimage);
                                sarah.fetch();
                       
                                ImageViewer expPhoto = new ImageViewer(sarah);

                        
                 Label labDescription = new Label(  e.getE().getDescription()) ;
                 
                 
                // SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
              //   Date  dateM = format.parse(e.getDateModif().toString());
            ///DAAAATE IS HEEEEERE                Label labDate = new Label( e.getE().getDateModif().toString()) ;
     Label labJaime = new Label( serviceExperience.AfficherAllJaime(e.getE().getId()).size() + " j'aimes") ;
              
             //    Label labEntreprise = new Label(e.getUsername() ) ;
                 
                     Button bSupprimer = new Button ("Supp") ; 
                     Button bCom = new Button("Comment") ; 
                     Button bPartage = new Button("Share");
                     Button bModif = new Button("Modif") ; 
                     
                     
                    Container Buts  = new Container(BoxLayout.x()) ; 
                 Buts.add(bSupprimer) ;
                 Buts.add(bCom) ;
                 Buts.add(bPartage) ;
                 Buts.add(bModif) ;
                 
                 
               
    //  f.add(ctn2);
                
                // C1.add(expPhoto) ;
                C1.add(expPhoto) ;
               C1.add(Buts) ; 
                 C1.add(labDescription) ;
                // C1.add(labDate) ;
                 C1.add(labJaime) ;
                 
                 
               
                 
               
                 
               
                 
            //  ArrayList<Com> lis2 = new  ArrayList() ; 
                   //     lis2 =serviceExperience.AfficherAllComm(e.getE().getId());            
                            
             //   int x = lis2.size() ;
                 
               Label labComm = new Label ( serviceExperience.AfficherAllComm(e.getE().getId()).size() + " commentaires") ; 
                      C1.add(labComm) ; 
                     
//--------------------------------Debut Commentaires Here------------------------------------------
              labComm.addPointerPressedListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                       Form CommentListF = new Form(BoxLayout.y()) ; 
                        ArrayList<Com> lis =serviceExperience.AfficherAllComm(e.getE().getId());
                      //  ArrayList<Com> lis =serviceExperience.AfficherAllComm(1);
                        for (Com c : lis ) {
                        Container ComC = new Container(BoxLayout.y()) ;
                        Label NomPrenomLab  = new Label(c.getNom() + "  " + c.getPrenom()) ; 
                        Label DateL  = new Label(c.getC().getDateModif().toString()) ; 
                        Label ContenuLab = new Label (c.getC().getContenu()) ; 
                       Container ComHoriz= new Container(BoxLayout.x()) ; 
                        ComHoriz.add(NomPrenomLab) ; 
                        ComHoriz.add(DateL) ; 
                        ComC.add(ComHoriz) ; 
                        ComC.add(ContenuLab) ; 
                        
                        CommentListF.add(ComC) ; 
                        
                        }
                        
                        CommentListF.getToolbar().addCommandToLeftBar("back", null, e->{AffichageForm aff= new AffichageForm(); aff.getF().show() ; }) ;
                       CommentListF.show();
                     }
                 }); //NullPointerException (7atta lel ka3ba elli fiha Commentaire 
               //--------------------------------End Commentaires HERE -------------------------------------------
               
               
               //------------------------------Debut Jaimes --------------------------------//
               
              labJaime.addPointerPressedListener(new ActionListener() {
                @Override
                     public void actionPerformed(ActionEvent evt) {
                       Form JaimeListF = new Form(BoxLayout.y()) ; 
                        ArrayList<Jaim> lis =serviceExperience.AfficherAllJaime(e.getE().getId());
                        
                        for (Jaim j : lis ) {
                        Container ComC = new Container(BoxLayout.y()) ;
                        Label NomPrenomLab  = new Label(j.getNom() + "  " + j.getPrenom()) ; 
                      
                       Container JaimCont= new Container(BoxLayout.x()) ; 
                     Image   Heart = (theme.getImage("round.png")).fill(50, 50);
                        JaimCont.add(NomPrenomLab) ; 
                        JaimCont.add(Heart) ; 
                        
                        JaimeListF.add(JaimCont) ; 
                        
                        }
                        
                        JaimeListF.getToolbar().addCommandToLeftBar("back", null, e->{AffichageForm aff= new AffichageForm(); aff.getF().show() ; }) ;
                          
                        
                        
                      JaimeListF.show();
                     }} ) ;    //still didnt test
      //--------------------------------------------------- Start Partage --------------------------------------------           
             //--------------------------------------END PARTAGE--------------------------
             
             //---------------------------------Start Modif-----------------------------------
            /*
             bModif.addActionListener(new ActionListener() {
                       @Override
                       
                       
                       public void actionPerformed(ActionEvent evt) {
                           ServiceExperience ser = new ServiceExperience(); 
                           AjoutForm aj = new AjoutForm() ; 
                           aj.getF().show();
                           TextField newDes= new TextField(); 
                           newDes.setHint(e.getE().getDescription());
                          aj.settDescription(newDes);
                         
                           ser.Modifier(id_user, newDes.getText());
                           
                       }
                   });
             
             */
             //------------------------------------End Modif -------------------------------
             
             
             //----------------------------------Start Supprim --------------------------------------
             
           /*  bSupprimer.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           
                           serviceExperience ser = new ServiceExperience();
                           
                        
                       }
                   });*/
               //------------------------------------End Supprim --------------------------
               bCom.addActionListener(new ActionListener() {
                       @Override
                     public void actionPerformed(ActionEvent evt) {
                          
                         TextField ComTf = new TextField() ; 
         Form CommentListF = new Form(BoxLayout.y()) ; 
         Button Valider = new Button("Post") ;
          CommentListF.add(ComTf) ; 
          CommentListF.add(Valider) ; 
                        ArrayList<Com> lis =serviceExperience.AfficherAllComm(e.getE().getId());
                      //  ArrayList<Com> lis =serviceExperience.AfficherAllComm(1);
                        for (Com c : lis ) {
                        Container ComC = new Container(BoxLayout.y()) ;
                        Label NomPrenomLab  = new Label(c.getNom() + "  " + c.getPrenom()) ; 
                        Label DateL  = new Label(c.getC().getDateModif().toString()) ; 
                        Label ContenuLab = new Label (c.getC().getContenu()) ; 
                       Container ComHoriz= new Container(BoxLayout.x()) ; 
                        ComHoriz.add(NomPrenomLab) ; 
                        ComHoriz.add(DateL) ; 
                        ComC.add(ComHoriz) ; 
                        ComC.add(ContenuLab) ; 
                       
                        CommentListF.add(ComC) ; 
                        
                        }
                        
                        CommentListF.getToolbar().addCommandToLeftBar("back", null, e->{AffichageForm aff= new AffichageForm(); aff.getF().show() ; }) ;
                          
                Valider.addActionListener((new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent evt) { 
                                   serviceExperience.AjoutCommentaire(e.getE().getId() , usr.getId() , ComTf.getText()); //normalement tGueeti l'Id Userr zeda bch trassili nbaddel later
                              CommentListF.refreshTheme();
                             }   
                        
                }));
                        
                       CommentListF.show();                  
                         
                         
                         
                         
                       //  Date d= new Date() ; 
                       // Commentaire com = new Commentaire (0 , 8 , e.getE().getId() , ComTf.getText(), d ) ;    
                         
                       
                       
                     }
               }) ;  //normalement çaa marche aussi
                 
                 MineF.add(C1) ;
                        
                    
                    
                    
                    
                    
                    
                    
           // ***********************************************************************       
                    
                    }               
        
        MineF.show();
             }
         }) ;  //ma te5demch  
          
          //------------------------------------END PROFIL USER ------------------------------------------------------
        
          
          
  //-------------------------------------------DEBUT FILL DES EXPERIENCES ---------------------------------------       
          
          
        for(Expe e : lis) {
             
                 Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS) )   ;
                
               //  C1.setPreferredW(300);
                 Container UserC = new Container(BoxLayout.x()) ; 
                Container  Info =new Container(BoxLayout.y()) ; 
                 
                
                //-------------------------------------PHOTO ISSUUE --------------------------------------------
                 // ImageViewer img= new ImageViewer(theme.getImage(e.getImage())) ;
                 
                // ImageViewer img= new ImageViewer(theme.getImage("aa.jpg").fill(300, 300)) ;
       ///GET IMAGE         
                 EncodedImage inc = EncodedImage.createFromImage(theme.getImage("aa.jpg").fill(300,300), false);
              
String URLimage = "http://localhost/gestionpfe/web/uploads/user/exp/" + e.getE().getPhoto();
//String URLimage = "http://localhost/gestionpfe/web/uploads/user/exp/hello.jpg";
             

URLImage sarah = URLImage.createToStorage(inc, e.getE().getId()+"", URLimage);
                                sarah.fetch();
                       
                                ImageViewer expPhoto = new ImageViewer(sarah);

                                // F4.add(img)
                                //Container ctn2= new Container();
                           //    ctn2.add(BorderLayout.WEST, expPhoto);
                            //    F4.add(ctn2);
                                // F4.add(exp);


                 
               // Image storagePhoto = URLImage.createToStorage(inc, "hello.jpg"  , URLimage);
                            //  storagePhoto.fetch();
                             //  storagePhoto. ;
                              // storagePhoto.scaledWidth(300) ;
                               // storagePhoto.fill(300, 300) ; 

                           //     ImageViewer expPhoto = new ImageViewer(storagePhoto);

                       //   Container ctn2 = BorderLayout.center(C1);
                          //      ctn2.add(BorderLayout.WEST,  expPhoto);
                             
  //-------------------------------------------PPHOTO ISSUE-----------------------------------------------------              
            
  ImageViewer imgProf= new ImageViewer(theme.getImage("meliodas.png").fill(80, 80)) ;
  
         //       EncodedImage inc2 = EncodedImage.createFromImage(theme.getImage("meliodas.png").fill(80, 80), false);
              
//String URLimageP = "http://localhost/gestionpfe/web/uploads/user/" + e.getPhoto();
//String URLimage = "http://localhost/gestionpfe/web/uploads/user/exp/hello.jpg";
             

//URLImage sarah2 = URLImage.createToStorage(inc2, e.getPhoto(), URLimageP);
                             //   sarah2.fetch();
                       
                              //  ImageViewer expPhoto2 = new ImageViewer(sarah2);
  UserC.add(imgProf) ;
     // UserC.add(expPhoto2) ; 
                 // Image img=Image.createImage(p.getFlag()) ;
                 // e.setImage(img);
                 //Label labUser = new Label( e.getId_user());
                 Label labDescription = new Label(  e.getE().getDescription()) ;
                 
                 
                // SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
              //   Date  dateM = format.parse(e.getDateModif().toString());
            ///DAAAATE IS HEEEEERE                Label labDate = new Label( e.getE().getDateModif().toString()) ;
                 Label labJaime = new Label("Afficher les "  + serviceExperience.AfficherAllJaime(e.getE().getId()).size() + " j'aimes") ;
                 Label labNomPrenom = new Label(e.getNom()+ "  "+ e.getPrenom() ) ;
                 Label labStage = new Label(e.getTitreStage() + " Chez " + e.getUsername() ) ;
             //    Label labEntreprise = new Label(e.getUsername() ) ;
                 
                     Button bJaime = new Button ("like") ; 
                     Button bCom = new Button("Comment") ; 
                     Button bPartage = new Button("Share");
                     Button bWishl = new Button("Wish") ; 
                     
                     
                    Container Buts  = new Container(BoxLayout.x()) ; 
                 Buts.add(bJaime) ;
                 Buts.add(bCom) ;
                 Buts.add(bPartage) ;
                 Buts.add(bWishl) ;
                 
                 
                 Info.add(labNomPrenom) ; 
                 Info.add(labStage) ; 
                UserC.add(Info) ;    
              
                C1.add(UserC) ; 
    //  f.add(ctn2);
                
                // C1.add(expPhoto) ;
                C1.add(expPhoto) ;
               C1.add(Buts) ; 
                 C1.add(labDescription) ;
                // C1.add(labDate) ;
                 C1.add(labJaime) ;
               
                 
               
                 
               
                 
            //  ArrayList<Com> lis2 = new  ArrayList() ; 
                   //     lis2 =serviceExperience.AfficherAllComm(e.getE().getId());            
                            
             //   int x = lis2.size() ;
                 
               Label labComm = new Label ("Afficher les "  + serviceExperience.AfficherAllComm(e.getE().getId()).size() + " commentaires") ; 
                      C1.add(labComm) ; 
                     
//--------------------------------Debut Commentaires Here------------------------------------------
              labComm.addPointerPressedListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                       Form CommentListF = new Form(BoxLayout.y()) ; 
                        ArrayList<Com> lis =serviceExperience.AfficherAllComm(e.getE().getId());
                      //  ArrayList<Com> lis =serviceExperience.AfficherAllComm(1);
                        for (Com c : lis ) {
                        Container ComC = new Container(BoxLayout.y()) ;
                        Label NomPrenomLab  = new Label(c.getNom() + "  " + c.getPrenom()) ; 
                        Label DateL  = new Label(c.getC().getDateModif().toString()) ; 
                        Label ContenuLab = new Label (c.getC().getContenu()) ; 
                       Container ComHoriz= new Container(BoxLayout.x()) ; 
                        ComHoriz.add(NomPrenomLab) ; 
                        ComHoriz.add(DateL) ; 
                        ComC.add(ComHoriz) ; 
                        ComC.add(ContenuLab) ; 
                        
                        CommentListF.add(ComC) ; 
                        
                        }
                        
                        CommentListF.getToolbar().addCommandToLeftBar("back", null, e->{AffichageForm aff= new AffichageForm(); aff.getF().show() ; }) ;
                       CommentListF.show();
                     }
                 }); //NullPointerException (7atta lel ka3ba elli fiha Commentaire 
               //--------------------------------End Commentaires HERE -------------------------------------------
               
               
               //------------------------------Debut Jaimes --------------------------------//
               
              labJaime.addPointerPressedListener(new ActionListener() {
                @Override
                     public void actionPerformed(ActionEvent evt) {
                       Form JaimeListF = new Form(BoxLayout.y()) ; 
                        ArrayList<Jaim> lis =serviceExperience.AfficherAllJaime(e.getE().getId());
                        
                        for (Jaim j : lis ) {
                        Container ComC = new Container(BoxLayout.y()) ;
                        Label NomPrenomLab  = new Label(j.getNom() + "  " + j.getPrenom()) ; 
                      
                       Container JaimCont= new Container(BoxLayout.x()) ; 
                     Image   Heart = (theme.getImage("round.png")).fill(50, 50);
                        JaimCont.add(NomPrenomLab) ; 
                        JaimCont.add(Heart) ; 
                        
                        JaimeListF.add(JaimCont) ; 
                        
                        }
                        
                        JaimeListF.getToolbar().addCommandToLeftBar("back", null, e->{AffichageForm aff= new AffichageForm(); aff.getF().show() ; }) ;
                          
                        
                        
                      JaimeListF.show();
                     }} ) ;    //still didnt test
      //----------------------------------------------------End Jaimes --------------------------------------------           
               bJaime.addActionListener(new ActionListener() { 
               
                @Override
                     public void actionPerformed(ActionEvent evt) {
                          
                        // Jaime jm = new Jaime(0 , 8 , e.getE().getId() ) ; 
                         
                         serviceExperience.CalculJaime(e.getE().getId()); //normalement tGueeti l'Id Userr zeda bch trassili nbaddel later
                       
                     }
               }) ;  //normalement temchi still didnt test
               
               bCom.addActionListener(new ActionListener() {
                       @Override
                     public void actionPerformed(ActionEvent evt) {
                          
                         TextField ComTf = new TextField() ; 
         Form CommentListF = new Form(BoxLayout.y()) ; 
         Button Valider = new Button("Post") ;
          CommentListF.add(ComTf) ; 
          CommentListF.add(Valider) ; 
                        ArrayList<Com> lis =serviceExperience.AfficherAllComm(e.getE().getId());
                      //  ArrayList<Com> lis =serviceExperience.AfficherAllComm(1);
                        for (Com c : lis ) {
                        Container ComC = new Container(BoxLayout.y()) ;
                        Label NomPrenomLab  = new Label(c.getNom() + "  " + c.getPrenom()) ; 
                        Label DateL  = new Label(c.getC().getDateModif().toString()) ; 
                        Label ContenuLab = new Label (c.getC().getContenu()) ; 
                       Container ComHoriz= new Container(BoxLayout.x()) ; 
                        ComHoriz.add(NomPrenomLab) ; 
                        ComHoriz.add(DateL) ; 
                        ComC.add(ComHoriz) ; 
                        ComC.add(ContenuLab) ; 
                       
                        CommentListF.add(ComC) ; 
                        
                        }
                        
                        CommentListF.getToolbar().addCommandToLeftBar("back", null, e->{AffichageForm aff= new AffichageForm(); aff.getF().show() ; }) ;
                          
                Valider.addActionListener((new ActionListener() {
                             @Override
                             public void actionPerformed(ActionEvent evt) { 
                                   serviceExperience.AjoutCommentaire(e.getE().getId() , usr.getId() , ComTf.getText()); //normalement tGueeti l'Id Userr zeda bch trassili nbaddel later
                              CommentListF.refreshTheme();
                             }   
                        
                }));
                        
                       CommentListF.show();                  
                         
                         
                         
                         
                       //  Date d= new Date() ; 
                       // Commentaire com = new Commentaire (0 , 8 , e.getE().getId() , ComTf.getText(), d ) ;    
                         
                       
                       
                     }
               }) ;  //normalement çaa marche aussi
                 
                 f.add(C1) ;
                 
                 
                 // lb.setText(lis.toString());
                 //  f.refreshTheme();
                 
         
         
        }               
      
      /*  f.getToolbar().addCommandToRightBar("back", null, (ev)->{AjoutForm h=new AjoutForm();
          h.getF().show();
          });  */
      
      
      f.getToolbar().addCommandToOverflowMenu("Sort by Date" , null , e->{ f.show() ; });
         f.getToolbar().addCommandToOverflowMenu("Sort by Popularity" , null , e->{ 
             Form pop= new Form("Most Liked" , BoxLayout.y()) ; 
             pop.add("sort by pop" ) ;
             pop.getToolbar().addCommandToRightBar( "back" , null ,
                     d->{ f.show() ; 
             }) ;
         pop.show() ;
         });
     
      
      
      
      
      
         
         f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
