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
import com.codename1.ui.events.ActionListener;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import static com.codename1.uikit.materialscreens.MSUIKit.host;
import com.codename1.util.StringUtil;
import com.mycompany.Entities.Stage;
import com.mycompany.Entities.Tache;
import com.mycompany.Entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *<
 * @author sana
 */
public class ServiceStage {

    
     public void ajoutEncadrant(int ids,int ide) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/pi/gestionpfe/web/app_dev.php/AfficheEncadrantAM/"+ids+"/"+ide;
        con.setUrl(Url);

        System.out.println("Affectation done");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     public ArrayList<User> getListEncad() {
        ArrayList<User> listEncad = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/pi/gestionpfe/web/app_dev.php/AfficheEncadrantsM");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();               
                try {
                    Map<String, Object> encadrants = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(encadrants);
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) encadrants.get("root");
                    for (Map<String, Object> obj : list) {
                        User user = new User();
                        float id = Float.parseFloat(obj.get("id").toString());
                                              
                        user.setId((int) id);
                        user.setNom(obj.get("nom").toString());
                        user.setPrenom(obj.get("prenom").toString());
                        user.setImage1(obj.get("image").toString());
                   
                        
                        
                        
                       
                        listEncad.add(user);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEncad;
    }
     
    
    
    public void ajoutReunion(int ids,String dat) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/pi/gestionpfe/web/app_dev.php/AjoutdateM/"+ids+"/"+dat;
        con.setUrl(Url);

        System.out.println("ajout date reunion done");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public void ajoutTache(int ids,String tach) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/pi/gestionpfe/web/app_dev.php/AjoutTacheM/"+ids+"/"+tach;
        con.setUrl(Url);

        System.out.println("Ajout Tache Done");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    public void ValiderTache(int idt) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/pi/gestionpfe/web/app_dev.php/ValiderTacheM/"+idt;
        con.setUrl(Url);

        System.out.println("Tache validé");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     public void DevaliderTache(int idt) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/pi/gestionpfe/web/app_dev.php/InvaliderTacheM/"+idt;
        con.setUrl(Url);

        System.out.println("Tache dévalidé");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     String pourcentage="";
     public String getPourc(int ids) {
       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/pi/gestionpfe/web/app_dev.php/DetailsEncadrementCOMPM/"+ids);
       
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                try {
                     pourcentage=new String(con.getResponseData(),"utf-8");
                   
                  
                    
                   
                } catch (IOException ex) {
                    System.out.println("aaa");
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return pourcentage;
       
    }
    
    
    
    public ArrayList<Tache> getListTaches(int ids) {
        ArrayList<Tache> listTach = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/pi/gestionpfe/web/app_dev.php/DetailsEncadrementM/"+ids);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> taches = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(taches);
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) taches.get("root");
                    for (Map<String, Object> obj : list) {
                        Tache tache = new Tache();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        
                                              
                        tache.setId((int) id);
                        tache.setTache(obj.get("tache").toString());
                        System.out.println(obj.get("etat").toString());
                        if((obj.get("etat").toString())=="true"){
                            tache.setEtat(true);
                        }
                        else if ((obj.get("etat").toString())=="false"){
                        tache.setEtat(false);
                        }
                        
                        
                        
                        
                       
                   
                        
                        
                        
                       
                        listTach.add(tache);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTach;
    }
    
     public ArrayList<Stage> getListMesStages() {
        ArrayList<Stage> listStages = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/pi/gestionpfe/web/app_dev.php/AfficheStageM");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> stages = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(stages);
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) stages.get("root");
                    for (Map<String, Object> obj : list) {
                        Stage stage = new Stage();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //float ide = Float.parseFloat(obj.get("idEtudiant").toString());
                        String mail;
                        
                         String nom;
                        String idEtudiant;
                        String string = obj.get("idEtudiant").toString();
                         List<String> parts= StringUtil.tokenize(string, ",");
                         nom=parts.get(3).substring(8)+" "+parts.get(2).substring(5);
                         mail=parts.get(13).substring(7);
                         System.out.println(mail);
                        //System.out.println(nom);
                        stage.setId((int) id);
                        stage.setTitreStage(obj.get("titreStage").toString());
                        stage.setDescreptif(obj.get("descreptif").toString());
                        
                        stage.setMailetudiant(mail);
                        stage.setNometudiant(nom);
                       // stage.setDateDebut((Date) obj.get("dateDebut"));
                        
                       //stage.setIdetudiant((int) ide);
                        listStages.add(stage);
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStages;
    }
    
    

    public ArrayList<Stage> getList2() {
        ArrayList<Stage> listStages = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/pi/gestionpfe/web/app_dev.php/AfficheMesEtudiantsM");
        con.addArgument("idUserConnecte", String.valueOf(usr.getId()));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> stages = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(stages);
                    
                    List<Map<String, Object>> list = (List<Map<String, Object>>) stages.get("root");
                    for (Map<String, Object> obj : list) {
                        Stage stage = new Stage();
                        float id = Float.parseFloat(obj.get("id").toString());
                        //float ide = Float.parseFloat(obj.get("idEtudiant").toString());
                        
                         String nom;
                        String idEtudiant;
                        String string = obj.get("idEtudiant").toString();
                         List<String> parts= StringUtil.tokenize(string, ",");
                         nom=parts.get(3).substring(8)+" "+parts.get(2).substring(5);
                        //System.out.println(nom);
                        stage.setId((int) id);
                        stage.setTitreStage(obj.get("titreStage").toString());
                        stage.setDescreptif(obj.get("descreptif").toString());
                        stage.setNometudiant(nom);
                        
                         String mail;
                         mail=parts.get(13).substring(7);
                         System.out.println(mail);
                      
                       
                        stage.setMailetudiant(mail);
                  
                        
                      
                        listStages.add(stage);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listStages;
    }

}
