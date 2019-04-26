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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import static com.codename1.uikit.materialscreens.MSUIKit.host;
import com.mycompany.Entities.CentresInterets;
import com.mycompany.Entities.CompetencesTechniques;
import com.mycompany.Entities.Formation;
import com.mycompany.Entities.Langues;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yahia
 */
public class CvServices {
   
 private int idCv=0; 
 private int createdCv=0;
   
    //formations
     public List AfficherCvEntreprise(int id){
    
    ArrayList<Formation> listFormation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/gestionpfe/web/app_dev.php/AfficherCvMobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                    
                try {
                               Map<String, Object> Cv = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                    //System.out.println(tasks);
                    Object o=new Object();
                    o=null;
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Cv.get("root");
                    for (Map<String, Object> obj : list) {
                        
            

                        Formation formation=new Formation();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                            formation.setId((int)id);
                            formation.setFormation(obj.get("formation").toString());
                           
                           SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd");
                           
                            formation.setAnnee(s.format(obj.get("annee")));
                            
                            listFormation.add(formation);
                        
                        
                           
            
                    }
                }
                catch (IOException ex) {
                } 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       return listFormation;
        
    }
     
     //competences techniques
      public List AfficherTechsEntreprise(int id){
    ArrayList<CompetencesTechniques> listCompetences = new ArrayList<>();
  
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/gestionpfe/web/app_dev.php/AfficherTechsMobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                               Map<String, Object> Cv = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    //System.out.println(tasks);
                    Object o=new Object();
                    o=null;
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Cv.get("root");
                    for (Map<String, Object> obj : list) {
                        
            

                        CompetencesTechniques competencesTechniques = new CompetencesTechniques();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                         competencesTechniques.setId((int) id);
                         competencesTechniques.setCompetence(obj.get("competence").toString());
                         competencesTechniques.setLevel(obj.get("level").toString());
                         
                            listCompetences.add(competencesTechniques);
                        
                        
                           
            
                    }
                }
                catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       
        return listCompetences;
    }

    //langues
      public List AfficherLanguesEntreprise(int id){
    ArrayList<Langues> listLangues = new ArrayList<>();
  
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/gestionpfe/web/app_dev.php/AfficherLangueMobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                               Map<String, Object> Cv = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    
                    //System.out.println(tasks);
                    Object o=new Object();
                    o=null;
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Cv.get("root");
                    for (Map<String, Object> obj : list) {
                        
            

                        Langues langues = new Langues();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                         langues.setId((int) id);
                         langues.setLangue(obj.get("langue").toString());
                         langues.setNiveau(obj.get("niveau").toString());
                         
                            listLangues.add(langues);
                        
                        
                           
            
                    }
                }
                catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       
        return listLangues;
    }
      //centres interets
      public List AfficherCentresEntreprise(int id){
    ArrayList<CentresInterets> listCentres = new ArrayList<>();
  
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/gestionpfe/web/app_dev.php/AfficherCentreMobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                               Map<String, Object> Cv = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                    //System.out.println(tasks);
                    Object o=new Object();
                    o=null;
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Cv.get("root");
                    for (Map<String, Object> obj : list) {
                        
            

                        CentresInterets centresInterets = new CentresInterets();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                         centresInterets.setId((int) id);
                         centresInterets.setCentreInteret(obj.get("centreInteret").toString());
                        
                         
                            listCentres.add(centresInterets);
                        
                        
                           
            
                    }
                }
                catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
       
        return listCentres;
    }      
      public int FindOrCreateCv(int id){
         
         
  
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/gestionpfe/web/app_dev.php/FindOrCreateCvMobile/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp = new JSONParser();
                
                try {
                               Map<String, Object> Cv = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                    //System.out.println(tasks);
                  
                    if(Cv.isEmpty())
                    {
                        idCv=0;
                    
                    }
                    else{
                    List<Map<String, Object>> list = (List<Map<String, Object>>) Cv.get("root");
                    for (Map<String, Object> obj : list) {
                        
            

                       
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                         idCv=(int) id;
                        // System.out.println(idCv);
                           
                        
                        
                           
            
                    }
                }
                }
                catch (IOException ex) {
                }

            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
          System.out.println("id cv user est : "+idCv);
            
          return idCv;
      }
      public int CreerCv(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/CreerCvMobile/"+id;
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            createdCv=Integer.parseInt(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return createdCv;
    }
      public void AjouterFormation(Formation f)
      {
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/AjouterFormationMobile/"+f.getIdCv()+"/"+f.getFormation()+"/"+f.getAnnee();
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      }
       public void AjouterTech(CompetencesTechniques f)
      {
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/AjouterTechMobile/"+f.getIdCv()+"/"+f.getCompetence()+"/"+f.getLevel();
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
         

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      }
        public void AjouterLangue(Langues  f)
      {
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/AjouterLangueMobile/"+f.getIdCv()+"/"+f.getLangue()+"/"+f.getNiveau();
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      }
         public void AjouterCentre(CentresInterets f)
      {
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/AjouterCentreMobile/"+f.getIdCv()+"/"+f.getCentreInteret();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
      }
         public void ModifierFormation(int id,String formation,String annee)
         {
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/ModifierFormationMobile/"+id+"/"+formation+"/"+annee;
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         }

    public void ModifierTech(int idDesiree, String text, String text0) {
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/ModifierTechMobile/"+idDesiree+"/"+text+"/"+text0;
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void ModifierLangue(int idDesiree, String text, String text0) {
       ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/ModifierLangueMobile/"+idDesiree+"/"+text+"/"+text0;
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void ModifierCentre(int idDesiree, String text) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/ModifierCentreMobile/"+idDesiree+"/"+text;
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void SupprimerCv(int idDesiree) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/gestionpfe/web/app_dev.php/SupprimerCvMobile/"+idDesiree;
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
           

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
