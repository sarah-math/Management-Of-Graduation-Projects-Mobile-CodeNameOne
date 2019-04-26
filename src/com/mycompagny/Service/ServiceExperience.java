/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import static com.codename1.uikit.materialscreens.MSUIKit.host;
import com.mycompany.Entities.Commentaire;
import com.mycompany.Entities.Experience;
import com.mycompany.Entities.Jaime;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */

public class ServiceExperience {

    public class Expe { 
       private Experience e ; 
       private  String nom ; 
       private  String prenom ; 
      private   String titreStage ; 
       private String username;
        private  String photo ; 

        public Expe() {
        }

        public Expe(Experience e, String nom, String prenom, String titreStage, String username , String photo) {
            this.e = e;
            this.nom = nom;
            this.prenom = prenom;
            this.titreStage = titreStage;
            this.username = username;
            this.photo = photo ; 
        }

        public Experience getE() {
            return e;
        }

        public void setE(Experience e) {
            this.e = e;
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

        public String getTitreStage() {
            return titreStage;
        }

        public void setTitreStage(String titreStage) {
            this.titreStage = titreStage;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
        
        
        
} 
    
   public class Com {
       
       private Commentaire c ;
       private String nom ; 
       private String prenom ;

        public Com(Commentaire c, String nom, String prenom) {
            this.c = c;
            this.nom = nom;
            this.prenom = prenom;
        }

        public Com() {
        }

        public Commentaire getC() {
            return c;
        }

        public void setC(Commentaire c) {
            this.c = c;
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
       
   } 
   
   public class Jaim {
   private Jaime jm ;
   private String nom ; 
   private String prenom ; 

        public Jaim() {
        }

        public Jaim(Jaime jm, String nom, String prenom) {
            this.jm = jm;
            this.nom = nom;
            this.prenom = prenom;
        }

        public Jaime getJm() {
            return jm;
        }

        public void setJm(Jaime jm) {
            this.jm = jm;
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
   
   
   } 
   
    
    public void ajoutExperience(Experience ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/ExperienceMobile/Insert.php?"  + "&description=" + ta.getDescription()  + "&photo=" + ta.getPhoto()+ "&id_user=" + ta.getId_user();
     
        con.setUrl(Url);
       // con.setUrl(Url);

        System.out.println("tt");

        
         con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                        } //else {
                           // Dialog.show("Erreur", "erreur", "Ok", null);
                      //  }
                    }
                });

        
     /*   con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });*/
     
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  public void   AjoutCommentaire(int id , int id_user, String cont ) {
    ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/ExperienceMobile/AjouterCommentaire.php?id_exp=" + id + "&id_user=" + id_user +  "&contenu=" + cont  ;
     
        con.setUrl(Url);
       // con.setUrl(Url);

        System.out.println("Commentaire");

        
         con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                        } //else {
                          //  Dialog.show("Erreur", "erreur", "Ok", null);
                     //   }
                    }
                });

        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  
    
    
       public void CalculJaime(int id ) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/ExperienceMobile/jaimeSTILLenCours.php?id=" + id ; 
     
        con.setUrl(Url);
       System.out.println("jaime" );

         con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                        }// else {
                           // Dialog.show("Erreur", "erreur", "Ok", null);
                      //  }
                    }
                });

        NetworkManager.getInstance().addToQueueAndWait(con);
    } //AVOIR ENCORE!! Surtout le User_Co en Parametre
   
    
    public ArrayList<Expe> AfficherAll() {
        ArrayList<Expe> listExperiences = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/ExperienceMobile/Afficher.php/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                  
                     
                   Map<String, Object> Experiences;
                   
                    
                        Experiences = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Experiences);
                    
                  
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Experiences.get("root");
                    for (Map<String, Object> obj : list) {
                      Experience experience = new Experience();
                      
                       experience.setDescription(obj.get("description").toString());
                       experience.setPhoto(obj.get("photo").toString());                     
                       experience.setNbrJaime((int) Float.parseFloat(obj.get("nbrJaime").toString()));
                        experience.setId((int) Float.parseFloat(obj.get("id").toString()));
                       
                       
                  //  SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
              //  Date  dateM = format.parse(obj.get("dateModif").toString());
              
                     //  experience.setDateModif(dateM);
                     
                      SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");  
                    
                  try{  experience.setDateModif((Date) SimpleDateFormat.parse(obj.get("dateModif").toString()));
                      
                     // Date  dateM = format.parse(obj.get("dateModif").toString()); } catch (ParseException ex) {
                  } catch(ParseException ex){  System.out.println(ex.getMessage()) ;
                }
                    
                     
                     String Nom= obj.get("nom").toString() ; 
                      String Prenom= obj.get("prenom").toString() ; 
                      String TitreStage= obj.get("titreStage").toString() ; 
                      String Username= obj.get("username").toString() ;
                      String Photo = obj.get("photo").toString() ;
                      
                        System.out.println("Nom is =  " + Nom + "PreNom is =  " +Prenom + "Stage is =  " +TitreStage +"Entreprise is =  " +Username ) ;
                     
                        Expe ex = new Expe(experience , Nom , Prenom , TitreStage , Username, Photo) ; 
                         
                      listExperiences.add(ex);

                    } 
                    
                    
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
                    
                    
                    
                    
              

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listExperiences;
    }
    
    
    public ArrayList<Expe> Recherche( String username , String titreStage) {
    
      ArrayList<Expe> listExperiences = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/ExperienceMobile/Recherche.php?username=" + username  + "&titreStage=" + titreStage);
         con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                  
                     
                   Map<String, Object> Experiences;
                   
                    try {
                        Experiences = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Experiences);
                    
                  
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Experiences.get("root");
                    for (Map<String, Object> obj : list) {
                      Experience experience = new Experience();
                      
                       experience.setDescription(obj.get("description").toString());
                       experience.setPhoto(obj.get("photo").toString());                     
                       experience.setNbrJaime((int) Float.parseFloat(obj.get("nbrJaime").toString()));
                       
                       
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                Date  dateM = format.parse(obj.get("dateModif").toString());
              
                       experience.setDateModif(dateM);
                     // experience.setDateModif(obj.get("dateModif").toString());
                     
                     String Nom= obj.get("nom").toString() ; 
                      String Prenom= obj.get("prenom").toString() ; 
                      String TitreStage= obj.get("titreStage").toString() ; 
                      String Username= obj.get("username").toString() ; 
                        String Photo= obj.get("photo").toString()  ; 
                        System.out.println("Nom is =  " + Nom + "PreNom is =  " +Prenom + "Stage is =  " +TitreStage +"Entreprise is =  " +Username ) ;
                     
                        Expe ex = new Expe(experience , Nom , Prenom , TitreStage , Username, Photo) ; 
                         
                      listExperiences.add(ex);

                    } 
                    
                    
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
                    
                    
                    
                    
                } catch (ParseException ex) {
                   System.out.println(ex.getMessage()) ;
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listExperiences;
    }
    
     public ArrayList<Expe> AfficherUserList( int id ) {
     ArrayList<Expe> listExperiences = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/ExperienceMobile/AfficherListPerUser.php?id_user=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                  
                     
                   Map<String, Object> Experiences;
                   
                    try {
                        Experiences = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Experiences);
                    
                  
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Experiences.get("root");
                    for (Map<String, Object> obj : list) {
                      Experience experience = new Experience();
                      
                       experience.setDescription(obj.get("description").toString());
                       experience.setPhoto(obj.get("photo").toString());                     
                       experience.setNbrJaime((int) Float.parseFloat(obj.get("nbrJaime").toString()));
                       
                       
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                Date  dateM = format.parse(obj.get("dateModif").toString());
              
                       experience.setDateModif(dateM);
                     // experience.setDateModif(obj.get("dateModif").toString());
                     
                     String Nom= obj.get("nom").toString() ; 
                      String Prenom= obj.get("prenom").toString() ; 
                      String TitreStage= obj.get("titreStage").toString() ; 
                      String Username= obj.get("username").toString() ; 
                       String Photo= obj.get("photo").toString() ; 
                        System.out.println("Nom is =  " + Nom + "PreNom is =  " +Prenom + "Stage is =  " +TitreStage +"Entreprise is =  " +Username ) ;
                     
                        Expe ex = new Expe(experience , Nom , Prenom , TitreStage , Username, Photo) ; 
                         
                      listExperiences.add(ex);

                    } 
                    
                    
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
                    
                    
                    
                    
                } catch (ParseException ex) {
                   System.out.println(ex.getMessage()) ;
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listExperiences;
    
     }  
    
    
    
     public void Supprimer (int id)  {
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/ExperienceMobile/Supprimer.php?id="  + id   ;
     
        con.setUrl(Url);
       // con.setUrl(Url);

        System.out.println("Suppression en cours");

         con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

        
     /*   con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });*/
     
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     
     public void Modifier (int id, String description)  {
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/ExperienceMobile/Update.php?id="  + id + "&description=" + description  ;
     
        con.setUrl(Url);
       // con.setUrl(Url);

        System.out.println("Modif en cours");

         con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "Modifié ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

        
     /*   con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });*/
     
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
     
 public ArrayList<Com> AfficherAllComm (int id ) {
  ArrayList<Com> listCommentaires = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/ExperienceMobile/AfficherCommentaires.php?id=" + id );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
              
                
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Commentaires; 
             
                   
               
                        Commentaires = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Commentaires);
                    
                  
                    //jusque la yedem
              
                    if (Commentaires.isEmpty()) {System.out.println("No Value");}
                    else {     List<Map<String, Object>> list = (List<Map<String, Object>>) Commentaires.get("root");
                    for (Map<String, Object> obj : list) {
                     Commentaire commentaire = new Commentaire();
                      
                      commentaire.setContenu(obj.get("contenu").toString());
                  SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");  
                    
                  try{  commentaire.setDateModif((Date) SimpleDateFormat.parse(obj.get("dateModif").toString()));
                      
                     // Date  dateM = format.parse(obj.get("dateModif").toString()); } catch (ParseException ex) {
                  } catch(ParseException ex){  System.out.println(ex.getMessage()) ;
                }
              
                   //    commentaire.setDateModif(dateM);
                     // experience.setDateModif(obj.get("dateModif").toString());
                     
                     String Nom= obj.get("nom").toString() ; 
                      String Prenom= obj.get("prenom").toString() ; 
                     
                      
                      
                     
                        Com cm = new Com (commentaire , Nom , Prenom ) ; 
                         
                      listCommentaires.add(cm);
                        System.out.println("Nom is =  " + cm.getNom() + "PreNom is =  " +cm.getPrenom() + "comm is =  " + cm.getC().getContenu() ) ;

                    } 
                    
                    } 
                    
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
                    
                    
                    
                    
               

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listCommentaires;
 }
 
 public ArrayList<Jaim> AfficherAllJaime (int id ) {
  ArrayList<Jaim> listJaimes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/ExperienceMobile/ListeJaime.php?id=" + id );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
              
                
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Jaimes; 
             
                   
               
                        Jaimes = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Jaimes);
                    
                  
                    //jusque la yedem
              
                    if (Jaimes.isEmpty()) {System.out.println("No Values");  }
                    else {     List<Map<String, Object>> list = (List<Map<String, Object>>) Jaimes.get("root");
                    for (Map<String, Object> obj : list) {
                    Jaime jaime = new Jaime();
                      
                    
                     
                     String Nom= obj.get("nom").toString() ; 
                      String Prenom= obj.get("prenom").toString() ; 
                     
                      
                       // System.out.println("Nom is =  " + Nom + "PreNom is =  " +Prenom + "Stage is =  " +TitreStage +"Entreprise is =  " +Username ) ;
                     
                       Jaim jm = new Jaim (jaime , Nom , Prenom ) ; 
                         
                      listJaimes.add(jm);

                    } }
                    
                    
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
                    
                    
                    
                    
               

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listJaimes;
 }

 
 
 
    public ServiceExperience() {
        
    }
}
