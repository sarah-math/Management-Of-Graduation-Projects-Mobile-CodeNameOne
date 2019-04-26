/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.stage;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.MSUIKit;
import static com.codename1.uikit.materialscreens.MSUIKit.host;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.util.StringUtil;
import com.mycompagny.Service.ServiceStage;
import com.mycompany.Entities.Stage;
import com.mycompany.Entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sana
 */
public class Affectation {

    Form f;
    SpanLabel lb;
    SpanLabel lb2;
    Form f2;

    public Affectation(Resources theme) {
        Stage a = new Stage();
        Image img1 = theme.getImage("back.jpg");;

        f = new Form("Liste Stages", new BoxLayout(BoxLayout.Y_AXIS));
        lb = new SpanLabel("");

        Container ctn = new Container(BoxLayout.y());
        ctn.setScrollableY(true);
        f.add(lb);
        ServiceStage prc = new ServiceStage();
        ArrayList<Stage> lis = prc.getListMesStages();

        for (Stage pr : lis) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label mailet = new Label("mail etudiant:" + pr.getMailetudiant());
            Label idsta = new Label("id stage:" + pr.getId());
            String str = idsta.getText();
            List<String> parts = StringUtil.tokenize(str, ":");
            final String idstage;
            idstage = parts.get(1).substring(0);
            int ids = Integer.parseInt(idstage);

            Label titre = new Label("Titre Stage :" + pr.getTitreStage());
            Label description = new Label("Description Stage :" + pr.getDescreptif());
            Label nometudiant = new Label("Etudiant :" + pr.getNometudiant());
            Label mailetudiant = new Label(pr.getMailetudiant());
            String maile;
            maile = mailetudiant.getText();

            FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);

            fab.createSubFAB(FontImage.MATERIAL_PEOPLE, "").addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    f2 = new Form("", BoxLayout.y());
                    f2.setTitle("choix Encadrant");
                    f2.getToolbar().addCommandToLeftBar("Arrière", null, (ev) -> {
                        f.showBack();
                    });
                    f2.getAllStyles().setBgImage(img1);
        f2.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

                    lb2 = new SpanLabel("");

                    Container ctn = new Container(BoxLayout.y());
                    ctn.setScrollableY(true);
                    f2.add(lb2);
                    ServiceStage prc = new ServiceStage();
                    ArrayList<User> lis = prc.getListEncad();

                    Style s = UIManager.getInstance().getComponentStyle("MultiLine1");
                    FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s);
                    EncodedImage pp = EncodedImage.createFromImage(p.scaled(p.getWidth() * 4, p.getHeight() * 4), false);

                    for (User pr : lis) {
                        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                        Label idenca = new Label("id encadrant:" + pr.getId());
                        String str = idenca.getText();
                        List<String> parts = StringUtil.tokenize(str, ":");
                        final String idencadrant;
                        idencadrant = parts.get(1).substring(0);
                        int ide = Integer.parseInt(idencadrant);

                        Label nom = new Label("Nom Enseignant :" + pr.getNom());
                        Label prenom = new Label("Prenom Enseignant :" + pr.getPrenom());
                        Image img = URLImage.createToStorage(pp, pr.getImage1(), "http://"+host+"/pi/gestionpfe/web/uploads/user/" + pr.getImage1());

                        //System.out.println(maile);
                        Button btn2 = new Button("Affecter");
                        btn2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                System.out.println(idstage);

                                System.out.println("aaa:" + ids);
                                System.out.println("bbb:" + ide);
                                ServiceStage pre = new ServiceStage();

                                if (Dialog.show("Confirmer", "Voulez vous Affecter cet Encadrant ?", "Oui", "Non")) {
                                    pre.ajoutEncadrant(ids, ide);

                                    Message m = new Message("Vous avez un nouveau encadrant, c'est MR." + pr.getNom());

                                    Display.getInstance().sendMessage(new String[]{maile}, "Encadrement", m);
                                    Dialog d = new Dialog("--Affectation--");

                                    TextArea popupBody = new TextArea("Encadrant affecté et Etudiant Notifié", 4, 12);

                                    popupBody.setUIID("PopupBody");
                                    popupBody.setEditable(false);
                                    d.setLayout(new BorderLayout());
                                    d.add(BorderLayout.CENTER, popupBody);

                                    d.showPopupDialog(btn2);
                                    LocalNotification n = new LocalNotification();
                                    n.setId("AFK-notifications");
                                    n.setAlertBody("Vous avez ajouter un commentaire!");
                                    n.setAlertTitle("AFK Comment System!");
                                    Display.getInstance().scheduleLocalNotification(
                                            n,
                                            System.currentTimeMillis() + 10, // fire date/time
                                            LocalNotification.REPEAT_NONE // Whether to repeat and what frequency
                                    );

                                }

                            }
                        });

                        c2.add(nom);
                        c2.add(prenom);
                        c2.add(img);

                        c2.add(btn2);
                        f2.add(c2);
                    }

                    f2.refreshTheme();
                    f2.show();
                }

            });;

            c1.add(titre);
            c1.add(description);
            c1.add(nometudiant);
            // c1.add(datedebut);
            //c1.add(idetu);
            //c1.add(btn);
            c1.add(fab);

            f.add(c1);
        }

      
        f.getAllStyles().setBgImage(img1);
        f.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
        f.getToolbar().addCommandToLeftBar("Arrière", null, (ev) -> {
            MSUIKit ert = new MSUIKit();
            ert.init(new Object());
            new ProfileForm(ert.getTheme()).show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
