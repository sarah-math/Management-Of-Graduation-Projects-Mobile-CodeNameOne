/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Cv;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.codename1.util.StringUtil;
import com.mycompagny.Service.CvServices;
import com.mycompany.Entities.CentresInterets;
import com.mycompany.Entities.CompetencesTechniques;
import com.mycompany.Entities.Formation;
import com.mycompany.Entities.Langues;
import static com.mycompany.gui.Cv.AjouterCv.CentresInterets;
import static com.mycompany.gui.Cv.AjouterCv.TechLevel;
import static com.mycompany.gui.Cv.AjouterCv.langues;
import static com.mycompany.gui.Cv.AjouterCv.niveaux;
import static com.mycompany.gui.Cv.AjouterCv.possibleSuggestions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yahia
 */
public class ModifierCv extends SideMenuBaseForm {

    private int idus;
    private Resources resX;

    public ModifierCv(Resources res, int i) {
        super(BoxLayout.y());
        idus = i;
        resX = res;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = usr.getImage();
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar) getToolbar().getMenuBar()).openMenu(null));

        Container remainingTasks = BoxLayout.encloseY(
                new SpanLabel("Tunisie "+usr.getVille()+"\n"+(int)Float.parseFloat(usr.getCodePostal()), "CenterSubTitle"),
                new Label("Adresse Candidat", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("+216" +usr.getNumTel(), "CenterSubTitle"),
                new Label("Numero Telephone", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new SpanLabel(usr.getNom()+" "+usr.getPrenom(), "SubTitle"),
                                new SpanLabel("Université : ESPRIT ", "SubTitle"),
                                new SpanLabel("Email : \n"+usr.getEmail(), "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE);
        fab.addActionListener((evt) -> {
            if (Dialog.show("Suppression", "Voulez vous vraiment supprimer votre CV?", "oui", "non")) {
                 CvServices cvse = new CvServices();
                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                ip.show();
               
                System.out.println("id a supprimer est :" + cvse.FindOrCreateCv(i));
                    cvse.SupprimerCv(cvse.FindOrCreateCv(i));
                    if(cvse.FindOrCreateCv(i)!=0)
                    {
                    Toolbar.setGlobalToolbar(false);
                    //Dialog ip = new InfiniteProgress().showInifiniteBlocking();
               
                   // ip.show();
                   new AjouterCv(res, cvse.CreerCv(i)).show();
                    }
                   
                    
                
                    
                   
               // ip.dispose();
            }

        });

        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        add(new Label("Formations", "TodayTitle"));
        CvServices cvSer = new CvServices();
        List<Formation> form = cvSer.AfficherCvEntreprise(i);
         
        List<CompetencesTechniques> tech = cvSer.AfficherTechsEntreprise(i);
        List<Langues> lang = cvSer.AfficherLanguesEntreprise(i);
        List<CentresInterets> cent = cvSer.AfficherCentresEntreprise(i);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        List<String> Allform = new ArrayList<>();
        List<String> AllTech = new ArrayList<>();
        List<String> AllLangues = new ArrayList<>();
        List<String> AllCentre = new ArrayList<>();
        for (Formation f : form) {

            //addButtonBottom(arrowDown, f.getFormation() + "  " + f.getAnnee(), 0xd997f1, true, f.getId(), Allform);
            Allform.add(f.getFormation());
        }
        for (CompetencesTechniques f : tech) {

            //addButtonBottom(arrowDown, f.getCompetence() + "  " + f.getLevel(), 0x5ae29d, true, f.getId(), AllTech);
            AllTech.add(f.getCompetence());
        }
             for (Langues f : lang) {

            //addButtonBottom(arrowDown, f.getCompetence() + "  " + f.getLevel(), 0x5ae29d, true, f.getId(), AllTech);
            AllLangues.add(f.getLangue());
        }
              for (CentresInterets f : cent) {

            //addButtonBottom(arrowDown, f.getCompetence() + "  " + f.getLevel(), 0x5ae29d, true, f.getId(), AllTech);
            AllCentre.add(f.getCentreInteret());
        }
        for (Formation f : form) {

            addButtonBottom(arrowDown, f.getFormation() + "  " + f.getAnnee(), 0xd997f1, true, f.getId(), Allform);
           // Allform.add(f.getFormation());
        }
        add(new Label("Competences Techniques", "TodayTitle"));
        for (CompetencesTechniques f : tech) {

            addButtonBottom(arrowDown, f.getCompetence() + "  " + f.getLevel(), 0x5ae29d, true, f.getId(), AllTech);
           // AllTech.add(f.getCompetence());
        }
        add(new Label("Langues maitrisés", "TodayTitle"));
        for (Langues f : lang) {

            addButtonBottom(arrowDown, f.getLangue() + "  " + f.getNiveau(), 0xffc06f, true, f.getId(), AllLangues);
           // AllLangues.add(f.getLangue());
        }
        add(new Label("Centres d'interets", "TodayTitle"));
        for (CentresInterets f : cent) {

            addButtonBottom(arrowDown, f.getCentreInteret(), 0x4dc2ff, true, f.getId(), AllCentre);
           // AllCentre.add(f.getCentreInteret());
        }

        setupSideMenu(res);
    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first, int idDesiree, List<String> ls) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("CentreSubtitle");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        finishLandingPage.addActionListener((evt) -> {
            CvServices cvs = new CvServices();
            List<String> utils = StringUtil.tokenize(text, "  ");
            if (color == 0x4dc2ff) {

                System.out.println("centre interet avec id : " + idDesiree + " centre : " + text);
                Picker CentreInput = new Picker();
                CentreInput.setType(Display.PICKER_TYPE_STRINGS);
                
                CentreInput.setStrings(CentresInterets);
                CentreInput.setText(utils.get(0));
                 Button confirmer = new Button("Modifier");
                Button retour = new Button("Retour");

                Dialog d = new Dialog("Modification");
                confirmer.addActionListener((e) -> {
                    if (!verif(CentreInput.getText(), ls)) {

                        d.dispose();
                        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                        ip.show();
                        cvs.ModifierCentre(idDesiree, CentreInput.getText());
                        new ModifierCv(resX, idus).show();

                    } else {
                        Dialog.show("Erreur Centre d'interet", "Vous avez deja ajouté ce Centre d'interet!", "OK", "");
                     CentreInput.setText(utils.get(0));

                       // niveauLangue.setText(utils.get(1));
                    }
                   

                });
                retour.addActionListener((e) -> {
                    d.dispose();
                });
                d.add(BoxLayout.encloseY(CentreInput, BoxLayout.encloseX(confirmer, retour)));

                d.show();
                
            }
            //technologie Modiff
            if (color == 0x5ae29d) {
                System.out.println("tech avec id : " + idDesiree + " centre : " + utils.get(0) + " " + utils.get(1));
                AutoCompleteTextField competenceInput = new AutoCompleteTextField(possibleSuggestions);
                competenceInput.setText(utils.get(0));
                
                competenceInput.setMinimumElementsShownInPopup(5);
                Picker niveauTech = new Picker();
                niveauTech.setType(Display.PICKER_TYPE_STRINGS);
                niveauTech.setStrings(TechLevel);
                niveauTech.setText(utils.get(1));
                Button confirmer = new Button("Modifier");
                Button retour = new Button("Retour");

                Dialog d = new Dialog("Modification");
                confirmer.addActionListener((e) -> {
                    if (!verif(competenceInput.getText(), ls)) {

                        d.dispose();
                        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                        ip.show();
                        cvs.ModifierTech(idDesiree, competenceInput.getText(), niveauTech.getText());
                        new ModifierCv(resX, idus).show();

                    } else {
                        Dialog.show("Erreur Competence", "Vous avez deja ajouté cette Competence !!", "OK", "");
                     competenceInput.setText(utils.get(0));

                    niveauTech.setText(utils.get(1));
                    }
                   

                });
                retour.addActionListener((e) -> {
                    d.dispose();
                });
                d.add(BoxLayout.encloseY(competenceInput, niveauTech, BoxLayout.encloseX(confirmer, retour)));

                d.show();
                

            }
            // fin tech
            if (color == 0xffc06f) {
                System.out.println("langue avec id : " + idDesiree + " centre : " + utils.get(0) + " " + utils.get(1));
            Picker langueInput = new Picker();
        langueInput.setType(Display.PICKER_TYPE_STRINGS);
        langueInput.setStrings(langues);
        langueInput.setText(utils.get(0));
        Picker niveauLangue = new Picker();
        niveauLangue.setType(Display.PICKER_TYPE_STRINGS);
        niveauLangue.setStrings(niveaux);
        niveauLangue.setText(utils.get(1));
        
                Button confirmer = new Button("Modifier");
                Button retour = new Button("Retour");

                Dialog d = new Dialog("Modification");
                confirmer.addActionListener((e) -> {
                    if (!verif(langueInput.getText(), ls)) {

                        d.dispose();
                        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                        ip.show();
                        cvs.ModifierLangue(idDesiree, langueInput.getText(), niveauLangue.getText());
                        new ModifierCv(resX, idus).show();

                    } else {
                        Dialog.show("Erreur Langue", "Vous avez deja ajouté cette Langue!!", "OK", "");
                     langueInput.setText(utils.get(0));

                        niveauLangue.setText(utils.get(1));
                    }
                   

                });
                retour.addActionListener((e) -> {
                    d.dispose();
                });
                d.add(BoxLayout.encloseY(langueInput, niveauLangue, BoxLayout.encloseX(confirmer, retour)));

                d.show();
                
            }
            //fin langue
            
            if (color == 0xd997f1) {

                System.out.println("formation avec id : " + idDesiree + " centre : " + utils.get(0) + " " + utils.get(1));
                TextField form = new TextField(utils.get(0));
                Picker dateform = new Picker();
                //dateform.setText(utils.get(1));
                dateform.setType(Display.PICKER_TYPE_DATE);
                Button confirmer = new Button("Modifier");
                Button retour = new Button("Retour");

                Dialog d = new Dialog("Modification");
                confirmer.addActionListener((e) -> {
                    try {
                        if (!verif(form.getText(), ls)) {
                            DateFormat formatter = new SimpleDateFormat("dd-MM-y ");
                            DateFormat formatternew = new SimpleDateFormat("y-MM-dd ");
                            Date date = (Date) formatter.parse(dateform.getText());
                            String finString = formatternew.format(date);
                            d.dispose();
                            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                            ip.show();
                            cvs.ModifierFormation(idDesiree, form.getText(), finString);
                            new ModifierCv(resX, idus).show();

                        } else {
                            Dialog.show("Erreur Formation", "Vous avez deja ajouté cette formation !!", "OK", "");
                         form.setText(utils.get(0));
                        }
                       
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }

                });
                retour.addActionListener((e) -> {
                    d.dispose();
                });
                d.add(BoxLayout.encloseY(form, dateform, BoxLayout.encloseX(confirmer, retour)));

                d.show();

            }

        });
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    private boolean verif(String txt, List<String> ls) {
        boolean verif = false;
        for (String s : ls) {
            if (s.equalsIgnoreCase(txt)) {
                verif = true;
                break;
            }
            
        }
        return verif;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
