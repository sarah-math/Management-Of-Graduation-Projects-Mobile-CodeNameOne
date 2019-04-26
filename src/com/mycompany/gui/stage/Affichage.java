/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.stage;

import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.MSUIKit;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.util.StringUtil;
import com.codename1.util.regex.RE;
import com.mycompagny.Service.ServiceStage;
import com.mycompany.Entities.Stage;
import com.mycompany.Entities.Tache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Affichage extends SideMenuBaseForm {

    Form f;
    Form f3;
    Form f4;
    SpanLabel lb;
    SpanLabel lb3;
    SpanLabel lb4;
    Button tach;
    Boolean flag;

    public Affichage(Resources theme) {
        super(BoxLayout.y());
        Stage a = new Stage();
        Image img1 = theme.getImage("back.jpg");

        f = new Form("Encadrement", new BoxLayout(BoxLayout.Y_AXIS));
        lb = new SpanLabel("");

        Container ctn = new Container(BoxLayout.y());
        ctn.setScrollableY(true);
        f.add(lb);
        ServiceStage prc = new ServiceStage();
        ArrayList<Stage> lis = prc.getList2();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar) getToolbar().getMenuBar()).openMenu(null));
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Offres ", "Title"),
                                new Label("La liste des Offres", "SubTitle")
                        )
                )
        );

        for (Stage pr : lis) {
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Label titre = new Label("Titre Stage :" + pr.getTitreStage());
            Label description = new Label("Description Stage :" + pr.getDescreptif());
            Label nometudiant = new Label("Etudiant :" + pr.getNometudiant());
            Label idsta = new Label("id stage:" + pr.getId());
            String str = idsta.getText();
            List<String> parts = StringUtil.tokenize(str, ":");
            final String idstage;
            idstage = parts.get(1).substring(0);
            int ids = Integer.parseInt(idstage);
            Label mailetudiant = new Label(pr.getMailetudiant());
            String maile;
            maile = mailetudiant.getText();

            FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);

            fab.createSubFAB(FontImage.MATERIAL_ACCOUNT_BOX, "").addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    f3 = new Form("", BoxLayout.y());
                    f3.setTitle("Encadrement");
                    f3.getAllStyles().setBgImage(img1);
                    f3.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);
                    lb3 = new SpanLabel("");

                    Container ctn = new Container(BoxLayout.y());
                    ctn.setScrollableY(true);
                    f3.add(lb3);

                    Button tach = new Button("La liste des Taches");
                    Button Soutenance = new Button("soutenance");
                    if ((prc.getPourc(ids).substring(1, 4)).equals("100")) {
                        Soutenance.setVisible(true);

                    } else {
                        Soutenance.setVisible(false);
                    }

                    Button avancement = new Button("Stage Terminé à: " + prc.getPourc(ids).substring(1, 6) + "%");

                    Picker dateTimePicker = new Picker();
                    TextField tache = new TextField();

                    Button ValidTach = new Button("Ajouter Tache");
                    ValidTach.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ServiceStage serv = new ServiceStage();
                            String newtache;
                            newtache = tache.getText();

                            RE r = new RE("[a-zA-Z]+");
                            boolean matched = r.match(newtache);

                            if (Dialog.show("Confirmer", "Voulez vous Confirmer la tache ?", "Oui", "Non")) {
                                ;

                                if (matched == false) {
                                    Dialog.show("Problème", "Votre tache n'est pas valide", "OK", null);
                                } else {
                                    serv.ajoutTache(ids, newtache);
                                    TextArea popupBody = new TextArea("nouvelle tache ajoutée!", 4, 12);
                                    Dialog e = new Dialog("--Tache--");
                                    popupBody.setUIID("PopupBody");
                                    popupBody.setEditable(false);
                                    e.setLayout(new BorderLayout());
                                    e.add(BorderLayout.CENTER, popupBody);

                                    e.showPopupDialog(ValidTach);
                                    Message m = new Message("vouz avez une nouvelle tache: " + newtache);

                                    Display.getInstance().sendMessage(new String[]{maile}, "Tache", m);

                                }

                            }

                        }
                    });

                    dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
                    Label loginIcon = new Label("", "TextField");
                    Label passwordIcon = new Label("", "TextField");
                    FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_DATE_RANGE, 3);
                    FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_CALL, 3);
                    Picker stringPicker = new Picker();

                    stringPicker.setType(Display.PICKER_TYPE_STRINGS);
                    //dateTimePicker.setDate(new Date());
                    Button valid = new Button("valider reunion");

                    valid.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            ServiceStage serv = new ServiceStage();
                            List<String> ls = StringUtil.tokenize(dateTimePicker.getText(), " ");

                            String waa = null;
                            waa = new SimpleDateFormat("MM/dd/yy").format(new Date());

                            DateFormat formatter = new SimpleDateFormat("dd-MM-y HH:mm");

                            DateFormat formatternew = new SimpleDateFormat("y-MM-dd HH:mm");
                            System.out.println("yalla " + dateTimePicker.getText());
                            System.out.println("bla: " + waa);
                            System.out.println(waa.substring(3, 5));
                            System.out.println(dateTimePicker.getText().substring(0, 2));
//                           if ((Float.parseFloat(dateTimePicker.getText().substring(0,2)))<(Float.parseFloat(waa.substring(3,5))))
//                           { System.out.println("NONONONO");
//                           }
//                           if ((Float.parseFloat(dateTimePicker.getText().substring(6,8)))<(Float.parseFloat(waa.substring(6,8))))
//                           {System.out.println("year no");}
//                            if ((Float.parseFloat(dateTimePicker.getText().substring(3,5)))<(Float.parseFloat(waa.substring(0,2))))
//                            {System.out.println("month no");}
                            if (((Float.parseFloat(dateTimePicker.getText().substring(6, 8))) < (Float.parseFloat(waa.substring(6, 8)))) || (((Float.parseFloat(dateTimePicker.getText().substring(3, 5))) < (Float.parseFloat(waa.substring(0, 2)))) && ((Float.parseFloat(dateTimePicker.getText().substring(6, 8))) == (Float.parseFloat(waa.substring(6, 8))))) || (((Float.parseFloat(dateTimePicker.getText().substring(6, 8))) == (Float.parseFloat(waa.substring(6, 8)))) && ((Float.parseFloat(dateTimePicker.getText().substring(3, 5))) == (Float.parseFloat(waa.substring(0, 2)))) && ((Float.parseFloat(dateTimePicker.getText().substring(0, 2))) <= (Float.parseFloat(waa.substring(3, 5)))))) {
                                TextArea popupBody = new TextArea("Date Impossible!", 4, 12);
                                Dialog e = new Dialog("--Problème--");
                                popupBody.setUIID("PopupBody");
                                popupBody.setEditable(false);
                                e.setLayout(new BorderLayout());
                                e.add(BorderLayout.CENTER, popupBody);

                                e.showPopupDialog(valid);

                            } else {
                                Date date = null;
                                try {
                                    date = (Date) formatter.parse(dateTimePicker.getText());
                                } catch (ParseException ex) {
                                    System.out.println("erreur");

                                }

                                String finString = formatternew.format(date);

                                if (Dialog.show("Confirmer", "Voulez vous Confirmer la reunion ?", "Oui", "Non")) {
                                    serv.ajoutReunion(ids, finString);
                                    Message m = new Message("Vous avez une nouvelle reunion: " + date + " \n L'avancement de votre PFE est à: " + prc.getPourc(ids).substring(1, 6) + "%");

                                    Display.getInstance().sendMessage(new String[]{maile}, "Reunion", m);
                                    Dialog d = new Dialog("--Affectation--");

                                    TextArea popupBody = new TextArea("Nouvelle réunion, étudiant notifié!", 4, 12);
                                    popupBody.setUIID("PopupBody");
                                    popupBody.setEditable(false);
                                    d.setLayout(new BorderLayout());
                                    d.add(BorderLayout.CENTER, popupBody);

                                    d.showPopupDialog(valid);
                                }

                            }

                        }
                    });

                    tach.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            f4 = new Form("", BoxLayout.y());
                            f4.setTitle("Liste des taches");
                            f4.getAllStyles().setBgImage(img1);
                            f4.getAllStyles().setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

                            lb4 = new SpanLabel("");
                            f4.getToolbar().addCommandToRightBar("back", null, (ev) -> {
                                f3.showBack();
                            });

                            Container ctn = new Container(BoxLayout.y());
                            ctn.setScrollableY(true);
                            f4.add(lb4);
                            ServiceStage prc = new ServiceStage();

                            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                            ip.show();

                            System.out.println(prc.getPourc(ids));

                            ArrayList<Tache> lis = prc.getListTaches(ids);

                            for (Tache pr : lis) {
                                Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                                //int idt = Integer.parseInt(idstage);
                                Label idenca = new Label("id tache:" + pr.getId());

                                String str = idenca.getText();

                                List<String> parts = StringUtil.tokenize(str, ":");
                                final String idstaache;
                                idstaache = parts.get(1).substring(0);
                                int idt = Integer.parseInt(idstaache);

                                Label nom = new Label("Nom Tache :" + pr.getTache());
                                String nomtache = nom.getText();
                                if (pr.getEtat().toString() == "true") {
                                    Label etat = new Label("Etat de la tache: Terminée! ");
                                    c3.add(etat);
                                } else if (pr.getEtat().toString() == "false") {
                                    Label etat = new Label("Etat de la tache: Non Terminée! ");
                                    c3.add(etat);
                                }
                                Container C1 = new Container(BoxLayout.x());
                                Button Validate = new Button("Valider Tache ");
                                Validate.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        ServiceStage prc = new ServiceStage();
                                        if (Dialog.show("Confirmer", "Voulez vous Valider cette tache ?", "Oui", "Non")) {
                                            if (pr.getEtat().toString() == "true") {
                                                Dialog.show("Problème", "Cette tache est déjà validé", "OK", null);
                                            } else if (pr.getEtat().toString() == "false") {
                                                prc.ValiderTache(idt);
                                                Dialog d = new Dialog("--Gestion Tache--");

                                                TextArea popupBody = new TextArea("La tache a été validé!", 4, 12);
                                                popupBody.setUIID("PopupBody");
                                                popupBody.setEditable(false);
                                                d.setLayout(new BorderLayout());
                                                d.add(BorderLayout.CENTER, popupBody);

                                                d.showPopupDialog(Validate);

                                                Message m = new Message("Une tache à été validé, C'est :   " + nomtache);

                                                Display.getInstance().sendMessage(new String[]{maile}, "Tache", m);

                                            }

                                        }

                                    }
                                });

                                Container C2 = new Container(BoxLayout.y());
                                Button Invalidat = new Button("Annuler Tache ");

                                Invalidat.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        ServiceStage prc = new ServiceStage();
                                        if (Dialog.show("Confirmer", "Voulez vous Dévalider cette tache ?", "Oui", "Non")) {
                                            if (pr.getEtat().toString() == "false") {
                                                Dialog.show("Problème", "Cette tache est déjà non validée", "OK", null);
                                            } else if (pr.getEtat().toString() == "true") {
                                                prc.DevaliderTache(idt);
                                                Dialog d = new Dialog("--Gestion Tache--");

                                                TextArea popupBody = new TextArea("La tache a été dévalidé!", 4, 12);
                                                popupBody.setUIID("PopupBody");
                                                popupBody.setEditable(false);
                                                d.setLayout(new BorderLayout());
                                                d.add(BorderLayout.CENTER, popupBody);

                                                d.showPopupDialog(Invalidat);

                                                Message m = new Message("Désolé mais un tache a été dévalidé, " + nomtache + " veillez vérifier votre travail");

                                                Display.getInstance().sendMessage(new String[]{maile}, "Tache", m);

                                            }

                                        }
                                    }
                                });

                                C1.add(Validate);
                                C2.add(Invalidat);

                                C1.add(C2);

                                c3.add(nom);

                                f4.add(c3);
                                f4.add(C1);
                            }

                            f4.refreshTheme();
                            f4.show();
                        }
                    });
                    f3.getToolbar().addCommandToRightBar("back", null, (ev) -> {
                        f.showBack();
                    });
                    f3.add(tach);
                    f3.add(avancement);
                    f3.add(Soutenance);

                    f3.add(dateTimePicker);
                    f3.add(valid);
                    f3.add(tache);

                    f3.add(ValidTach);

                    f3.refreshTheme();
                    f3.show();

                }
            });

            c1.add(titre);
            c1.add(description);
            c1.add(nometudiant);
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

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

}
