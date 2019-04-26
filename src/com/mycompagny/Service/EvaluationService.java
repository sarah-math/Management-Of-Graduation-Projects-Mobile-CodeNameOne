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
import com.mycompany.Entities.Evaluation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sarah
 */
public class EvaluationService {
    
   public  class TitreEntreprise { 
       private String entreprise ; 
       private String titreStage ; 

        public TitreEntreprise(String entreprise, String titreStage) {
            this.entreprise = entreprise;
            this.titreStage = titreStage;
        }

        public TitreEntreprise() {
        }

        public String getEntreprise() {
            return entreprise;
        }

        public void setEntreprise(String entreprise) {
            this.entreprise = entreprise;
        }

        public String getTitreStage() {
            return titreStage;
        }

        public void setTitreStage(String titreStage) {
            this.titreStage = titreStage;
        }
       
   }
   
   public class NotesEntreprise { 
       private int idEntrep; 
       private String NomEntrep ; 
        private  float MoyC1 ; 
        private  float MoyC2 ; 
        private  float MoyC3 ; 
        private  float MoyC4 ;
        private  float MoyGlobale ;

        public NotesEntreprise(int idEntrep, String NomEntrep, float MoyC1, float MoyC2, float MoyC3, float MoyC4, float MoyGlobale) {
            this.idEntrep = idEntrep;
            this.NomEntrep = NomEntrep;
            this.MoyC1 = MoyC1;
            this.MoyC2 = MoyC2;
            this.MoyC3 = MoyC3;
            this.MoyC4 = MoyC4;
            this.MoyGlobale = MoyGlobale;
        }

        public int getIdEntrep() {
            return idEntrep;
        }

        public void setIdEntrep(int idEntrep) {
            this.idEntrep = idEntrep;
        }

      

      
        public NotesEntreprise() {
        }

        public String getNomEntrep() {
            return NomEntrep;
        }

        public void setNomEntrep(String NomEntrep) {
            this.NomEntrep = NomEntrep;
        }

        
        
        public float getMoyC1() {
            return MoyC1;
        }

        public void setMoyC1(float MoyC1) {
            this.MoyC1 = MoyC1;
        }

        public float getMoyC2() {
            return MoyC2;
        }

        public void setMoyC2(float MoyC2) {
            this.MoyC2 = MoyC2;
        }

        public float getMoyC3() {
            return MoyC3;
        }

        public void setMoyC3(float MoyC3) {
            this.MoyC3 = MoyC3;
        }

        public float getMoyC4() {
            return MoyC4;
        }

        public void setMoyC4(float MoyC4) {
            this.MoyC4 = MoyC4;
        }

        public float getMoyGlobale() {
            return MoyGlobale;
        }

        public void setMoyGlobale(float MoyGlobale) {
            this.MoyGlobale = MoyGlobale;
        }

        @Override
        public String toString() {
            return "NotesEntreprise{" + "idEntrep=" + idEntrep + ", NomEntrep=" + NomEntrep + ", MoyGlobale=" + MoyGlobale + '}';
        }
        
        
        
        
   
   } //moyenne Globales (AVG) de chaque Entreprise 
    
    public class SoutenanceTest { 
     private int id ; 
     private Date StDateFin ; 

        public SoutenanceTest(int id, Date StDateFin) {
            this.id = id;
            this.StDateFin = StDateFin;
        }

        public SoutenanceTest() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getStDateFin() {
            return StDateFin;
        }

        public void setStDateFin(Date StDateFin) {
            this.StDateFin = StDateFin;
        }
     
     
     
     
    } //fiha IdSout et DateFin Stage 
    
    public class EvalPerUser {
      private int id_user ;
      private String NomEntreprise; 
      private String Opinion ;
      private float NoteGlobale ; 

        public EvalPerUser(int id_user, String NomEntreprise, String Opinion, float NoteGlobale) {
            this.id_user = id_user;
            this.NomEntreprise = NomEntreprise;
            this.Opinion = Opinion;
            this.NoteGlobale = NoteGlobale;
        }

        public EvalPerUser() {
        }

        public int getId_user() {
            return id_user;
        }

        public void setId_user(int id_user) {
            this.id_user = id_user;
        }

        public String getNomEntreprise() {
            return NomEntreprise;
        }

        public void setNomEntreprise(String NomEntreprise) {
            this.NomEntreprise = NomEntreprise;
        }

        public String getOpinion() {
            return Opinion;
        }

        public void setOpinion(String Opinion) {
            this.Opinion = Opinion;
        }

        public float getNoteGlobale() {
            return NoteGlobale;
        }

        public void setNoteGlobale(float NoteGlobale) {
            this.NoteGlobale = NoteGlobale;
        }
      
      
      
      
      
    }

    public EvaluationService() {
    }
    public ArrayList<TitreEntreprise> getTitreStageEntreprise (int id_user) {
      
         ArrayList<TitreEntreprise> listTE = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/AfficheTitreStageEntreprise.php?id_user=" + id_user);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                    
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> TitreEntreprises; 
             
                   
               
                        TitreEntreprises = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(TitreEntreprises);
                 
                   List<Map<String, Object>> list = (List<Map<String, Object>>) TitreEntreprises.get("root");
                    for (Map<String, Object> obj : list) {
                      TitreEntreprise TE = new TitreEntreprise() ; 
        
                     TE.setEntreprise(obj.get("username").toString());
                     TE.setTitreStage(obj.get("titreStage").toString());
               
                    
                      listTE.add(TE);

                    } 
        
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listTE;
  
    } //Null Pointer Exception
    
    
     public void ajoutEvaluation(Evaluation ev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://"+host+"/EvaluationMobile/Insert.php?"  + "id_user=" + ev.getId_user() +  "&opinion=" + ev.getOpinion()  + "&C1=" + ev.getC1()  + "&C2=" + ev.getC2() + "&C3=" + ev.getC3() + "&C4=" + ev.getC4() ;
     
        con.setUrl(Url);
        System.out.println("ajouteEVAL");
 
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


        NetworkManager.getInstance().addToQueueAndWait(con);
    } //temchi jawwha béhi
     
     
 public ArrayList<Evaluation> AfficherOpinionEntreprise (int id ) {
  ArrayList<Evaluation> listOpinion = new ArrayList<>();
  /* t3agriba 5ater ken la liste yebda fiha Element We7ed bark tjini Cast Class Exception,, so lezem n7ott fiha element zero" 
  Date d= new Date() ;
        Evaluation eval0  = new Evaluation(0, 0, 0, 0, 0, 0,"",   d  );   
        listOpinion.add(eval0) ;  */ 
  
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/AfficheListOpinions.php?id_entreprise=" + id );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Opinions; 
             
                        Opinions = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Opinions);
                   
                    //jusque la yedem
              if (Opinions.isEmpty()) {System.out.println("No Values"); }  
             else {   List<Map<String, Object>> list = (List<Map<String, Object>>) Opinions.get("root"); 
                  for (Map<String, Object> obj : list) {
                     Evaluation eval  = new Evaluation();
                    
                      eval.setOpinion(obj.get("opinion").toString());
            
                   
        
                      listOpinion.add(eval);

                    }  }
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listOpinion;
 } // WORKS!! :D temchich if ONLY ONE ELEMENT fel List => Class Cast Exception Java Util Linked Hash Map cnt be casted to Java Util List
 
 
 public ArrayList<NotesEntreprise> AfficherNotesEntreprise (int id) {
 
  ArrayList<NotesEntreprise> listNotes = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/AfficherMoyennePerCritere.php?id_entreprise=" + id );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Notes; 
             
                        Notes = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Notes);
                   
                    //jusque la yedem
              
                 if (Notes.isEmpty()) {System.out.println("ZERO VALUES FEL STAT");}  
                 else {List<Map<String, Object>> list = (List<Map<String, Object>>) Notes.get("root");
                    for (Map<String, Object> obj : list) {
                     NotesEntreprise n  = new NotesEntreprise();
                      
                   //   n.setIdEntrep((int)Float.parseFloat(obj.get("idEntreprise").toString()));
                      n.setNomEntrep(obj.get("nomEntreprise").toString());
                        n.setIdEntrep((int)Float.parseFloat(obj.get("id").toString()));
                      n.setMoyC1(Float.parseFloat(obj.get("avg(C1)").toString()));
                      n.setMoyC2(Float.parseFloat(obj.get("avg(C2)").toString()));
                      n.setMoyC3(Float.parseFloat(obj.get("avg(C3)").toString()));
                      n.setMoyC4(Float.parseFloat(obj.get("avg(C4)").toString()));
                      n.setMoyGlobale(Float.parseFloat(obj.get("MOYENNE GENERALE").toString()));
                     
                      listNotes.add(n);System.out.println("nom Entrep=" + n.getNomEntrep() + "moy gen= " + n.getMoyGlobale());
                      
                    } 
                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listNotes;
 }
 
 
 
 public ArrayList<NotesEntreprise> SortByMOYntreprise() {
   ArrayList<NotesEntreprise> listEntreprises = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/SortByMoyenneGlob.php"  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Entreprises; 
             
                        Entreprises = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Entreprises);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Entreprises.get("root");
                    for (Map<String, Object> obj : list) {
                        
                      
                     NotesEntreprise n  = new NotesEntreprise();
                      
                        n.setIdEntrep((int)Float.parseFloat(obj.get("idEntreprise").toString()));
                      n.setNomEntrep(obj.get("nomEntreprise").toString());
                     // n.setMoyC1(Float.parseFloat(obj.get("avg(C1)").toString()));
                     // n.setMoyC2(Float.parseFloat(obj.get("avg(C2)").toString()));
                     // n.setMoyC3(Float.parseFloat(obj.get("avg(C3)").toString()));
                     // n.setMoyC4(Float.parseFloat(obj.get("avg(C4)").toString()));
                    n.setMoyGlobale(Float.parseFloat(obj.get("MOYENNE GENERALE").toString()));
                     
                    listEntreprises.add(n) ; 
                        System.out.println(n.toString());

                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listEntreprises;
 }
 
 
  public ArrayList<NotesEntreprise> SortByC1ntreprise() {
   ArrayList<NotesEntreprise> listEntreprises = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/SortByC1.php"  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Entreprises; 
             
                        Entreprises = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Entreprises);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Entreprises.get("root");
                    for (Map<String, Object> obj : list) {
                     NotesEntreprise n  = new NotesEntreprise();
                      
                     
                      n.setNomEntrep(obj.get("nomEntreprise").toString());
                      n.setMoyC1(Float.parseFloat(obj.get("avg(C1)").toString()));
                     // n.setMoyC2(Float.parseFloat(obj.get("avg(C2)").toString()));
                     // n.setMoyC3(Float.parseFloat(obj.get("avg(C3)").toString()));
                     // n.setMoyC4(Float.parseFloat(obj.get("avg(C4)").toString()));
                     // n.setMoyGlobale(Float.parseFloat(obj.get("MOYENNE GENERALE").toString()));
                     
                      listEntreprises.add(n);

                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listEntreprises;
 }
  public ArrayList<NotesEntreprise> SortByC2ntreprise() {
   ArrayList<NotesEntreprise> listEntreprises = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/SortByC2.php"  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Entreprises; 
             
                        Entreprises = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Entreprises);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Entreprises.get("root");
                    for (Map<String, Object> obj : list) {
                     NotesEntreprise n  = new NotesEntreprise();
                      
                     
                      n.setNomEntrep(obj.get("nomEntreprise").toString());
                     // n.setMoyC1(Float.parseFloat(obj.get("avg(C1)").toString()));
                      n.setMoyC2(Float.parseFloat(obj.get("avg(C2)").toString()));
                     // n.setMoyC3(Float.parseFloat(obj.get("avg(C3)").toString()));
                     // n.setMoyC4(Float.parseFloat(obj.get("avg(C4)").toString()));
                     // n.setMoyGlobale(Float.parseFloat(obj.get("MOYENNE GENERALE").toString()));
                     
                      listEntreprises.add(n);

                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listEntreprises;
 }
 public ArrayList<NotesEntreprise> SortByC3ntreprise() {
   ArrayList<NotesEntreprise> listEntreprises = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/SortByC3.php"  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Entreprises; 
             
                        Entreprises = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Entreprises);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Entreprises.get("root");
                    for (Map<String, Object> obj : list) {
                     NotesEntreprise n  = new NotesEntreprise();
                      
                     
                      n.setNomEntrep(obj.get("nomEntreprise").toString());
                    //  n.setMoyC1(Float.parseFloat(obj.get("avg(C1)").toString()));
                     // n.setMoyC2(Float.parseFloat(obj.get("avg(C2)").toString()));
                      n.setMoyC3(Float.parseFloat(obj.get("avg(C3)").toString()));
                     // n.setMoyC4(Float.parseFloat(obj.get("avg(C4)").toString()));
                     // n.setMoyGlobale(Float.parseFloat(obj.get("MOYENNE GENERALE").toString()));
                     
                      listEntreprises.add(n);

                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listEntreprises;
 }
  public ArrayList<NotesEntreprise> SortByC4ntreprise() {
   ArrayList<NotesEntreprise> listEntreprises = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/SortByC4.php"  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Entreprises; 
             
                        Entreprises = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Entreprises);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Entreprises.get("root");
                    for (Map<String, Object> obj : list) {
                     NotesEntreprise n  = new NotesEntreprise();
                      
                     
                      n.setNomEntrep(obj.get("nomEntreprise").toString());
                    //  n.setMoyC1(Float.parseFloat(obj.get("avg(C1)").toString()));
                     // n.setMoyC2(Float.parseFloat(obj.get("avg(C2)").toString()));
                     // n.setMoyC3(Float.parseFloat(obj.get("avg(C3)").toString()));
                      n.setMoyC4(Float.parseFloat(obj.get("avg(C4)").toString()));
                     // n.setMoyGlobale(Float.parseFloat(obj.get("MOYENNE GENERALE").toString()));
                     
                      listEntreprises.add(n);

                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        return listEntreprises;
 }
  
  
  
  public boolean ControlHasEvaluated (int id ) {
  
      
       ArrayList< Evaluation > listEval = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/ControlIfHasEvaluated.php?id_user=" + id  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Evaluations; 
             
                        Evaluations = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Evaluations);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Evaluations.get("root");
                    for (Map<String, Object> obj : list) {
                     
                        Evaluation e = new Evaluation() ; 
                      
                        e.setId((int)Float.parseFloat(obj.get("id").toString()));
                     
                      listEval.add(e);
                            System.out.println(e.getId());
                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        
        if (listEval.isEmpty()) return false  ;
      
      return true ; 
      
  } //still didnt Test
  
  public boolean ControlHasFinishedStage (int id ) {
  
       ArrayList< SoutenanceTest > listSout = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/ControlFinStage.php?id_user=" + id  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Soutenances; 
             
                        Soutenances = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Soutenances);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Soutenances.get("root");
                    for (Map<String, Object> obj : list) {
                     
                        SoutenanceTest e = new SoutenanceTest(); 
                      
                        e.setId((int)Float.parseFloat(obj.get("id").toString()));
                        
                        
                   //     e.setStDateFin((obj.get("dateFin").toString()));
                    SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");  
                    
                  try{ e.setStDateFin((Date) SimpleDateFormat.parse(obj.get("dateFin").toString()));
                      
                    
                  } catch(ParseException ex){  System.out.println(ex.getMessage()) ;
                }
                        System.out.println("id= " + e.getId() + " , dateFin=  " + e.getStDateFin()  );
                      listSout.add(e);

                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        
        if (listSout.isEmpty()) return false ;
      
      return true ; 
      
  } //still didnt Test
      
  public boolean ControlHasStage (int id ) {
  
      
       ArrayList< Evaluation > listEval = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/ExperienceMobile/ControlHASstage.php?id_user=" + id  );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Evaluations; 
             
                        Evaluations = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Evaluations);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Evaluations.get("root");
                    for (Map<String, Object> obj : list) {
                     
                        Evaluation e = new Evaluation() ; //normalenet Stage=new Stage but La Flemme :p 
                      
                        e.setId((int)Float.parseFloat(obj.get("id").toString())); //tchecki if there's a stage ou pas
                 
                      listEval.add(e);
                            System.out.println(e.getId());
                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //yab3ath el requete w yestanna el réponse
        
        if (listEval.isEmpty()) return false  ;
      
      return true ; 
      
  }     
  
  
  
  
  public ArrayList<EvalPerUser> AfficherEvalPerUser  (int id)    {
  
      ArrayList<EvalPerUser> EvalUserList = new ArrayList<>() ; 
      
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://"+host+"/EvaluationMobile/NoteGlobalPerUser.php?id_user=" + id );
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            //    listExperiences = getListExperience(new String(con.getResponseData()));
                 try {
                    JSONParser jsonp = new JSONParser();
                         Map<String, Object> Notes; 
             
                        Notes = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   
                      System.out.println(Notes);
                   
                    //jusque la yedem
              
                   List<Map<String, Object>> list = (List<Map<String, Object>>) Notes.get("root");
                    for (Map<String, Object> obj : list) {
                     EvalPerUser n  = new EvalPerUser() ; 
                      
                     // n.setId_user((int)Float.parseFloat(obj.get("idEntreprise").toString()));
                      n.setNomEntreprise(obj.get("nomEntreprise").toString());
                      n.setOpinion(obj.get("opinion").toString());
                      n.setNoteGlobale(Float.parseFloat(obj.get("NOTEGLOBALE").toString()));
                  
                     
                      EvalUserList.add(n);
                        System.out.println("nomEntr=" + n.getNomEntreprise() + "opinion== " + n.getOpinion() + "NoteGlib=  " + n.getNoteGlobale() );
                    } 
                                       
                  } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con); 
  
  return  EvalUserList ; } 
      
  
  
  
  
 
 
 
 
 
 
 
 
 
 
 
 
 
 }
 
 
 
    

