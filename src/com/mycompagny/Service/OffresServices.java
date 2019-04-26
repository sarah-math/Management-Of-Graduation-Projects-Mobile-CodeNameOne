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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import static com.codename1.uikit.materialscreens.MSUIKit.host;
import com.mycompany.gui.offres.OffresEtudiants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahmed
 */
public class OffresServices {

    public static List<HashMap<String, String>> retur = new ArrayList<HashMap<String, String>>();
    public static HashMap<String, List<String>> tcs = new HashMap<String, List<String>>();
    public static void getOffersForUsers(Resources res) {
        String url = "http://"+host+"/gestionpfe/web/app_dev.php/GetAllOffersMobile";
        ConnectionRequest cr = new ConnectionRequest(url);
        cr.setPost(true);
        cr.addArgument("name", usr.getUsername());
        NetworkManager.getInstance().addToQueue(cr);

        int j = 0;
        cr.addResponseListener((NetworkEvent evt) -> {
            try {
                JSONParser jsonp = new JSONParser();
                Map<String,Object> offres = jsonp.parseJSON(new CharArrayReader(new String(cr.getResponseData()).toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) offres.get("offres");
                for (Map<String, Object> obj : list) {
                    HashMap<String, String> o = new HashMap<>();
                    o.put("titre", obj.get("titre").toString());
                    o.put("idEntreprise", obj.get("idEntreprise").toString());
                    o.put("description", obj.get("description").toString());
                    o.put("nbrDemandes", obj.get("nbrDemandes").toString());
                    o.put("imageEntreprise", obj.get("imageEntreprise").toString());
                    o.put("nomEntreprise", obj.get("nomEntreprise").toString());
                    o.put("duree", obj.get("duree").toString());
                    System.out.println(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2));
                    List<Map<String, Object>> list2 = (List<Map<String, Object>>) offres.get("pourcentage");
                    Map<String, List<String>> list3 = (Map<String, List<String>>) offres.get("technologies");
                    int id = Integer.parseInt(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2));
                    o.put("id", String.valueOf(id));
                    for (Map<String, Object> obj1 : list2) {
                        if (obj1.get(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2)) != null) {
                            System.out.println("pourcentage  : " + obj1.get(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2)));
                            o.put("pourcentage", obj1.get(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2)).toString());
                        }
                    }
                    if(list3.get(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2)) != null){
                        for(String z : list3.get(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2))){
                            System.out.println(z);
                        }    
                            tcs.put(String.valueOf(id), list3.get(obj.get("id").toString().substring(0, obj.get("id").toString().length() - 2)));                    
                    }
                    
                    System.out.println("______________________________________________");
                    
                    retur.add(o);
                }
                new OffresEtudiants(res).show();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public static void afficheUnSeulOffre(List<String> get, HashMap<String, String> aa,Resources res) {
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(15, 15, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, "user-picture.jpg",
                "http://"+host+"/gestionpfe/web/" + aa.get("imageEntreprise"));
        background.fetch();
        
        for(String a : get){
        
        }
       
    }
    public void ajouterOffreMobile(){
    
        
    
    }
}
