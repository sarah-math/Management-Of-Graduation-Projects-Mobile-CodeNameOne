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
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.codename1.util.StringUtil;
import com.mycompagny.Service.CvServices;
import com.mycompagny.Service.DemandesServices;
import com.mycompany.Entities.CentresInterets;
import com.mycompany.Entities.CompetencesTechniques;
import com.mycompany.Entities.DemandesPDF;
import com.mycompany.Entities.Formation;
import com.mycompany.Entities.Langues;
import com.mycompany.gui.Demandes.ListeDemandes;
import java.util.Date;
import java.util.List;


/**
 *
 * @author yahia
 */
public class VoirCvCandidatEntreprise extends SideMenuBaseForm {

    public VoirCvCandidatEntreprise(int i, Resources res, DemandesPDF de) {

        super(BoxLayout.y());
            
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
                new Label(de.getAdresseEtudiant(), "CenterSubTitle"),
                new Label("Adresse Candidat", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label("+216" + String.valueOf(de.getNumTelEtudiant()), "CenterSubTitle"),
                new Label("Numero Telephone", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new SpanLabel(" " + de.getNomEtudiant().toUpperCase(), "SubTitle"),
                                new SpanLabel("Université : ESPRIT " + "\n " + de.getEmailEtu(), "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.createSubFAB(FontImage.MATERIAL_CHECK, "").addActionListener((evt) -> {
             
            
            Dialog dlg = new Dialog("Fixation d'Entretien");

            Container hi = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Picker dateTimePicker = new Picker();
            dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
            dateTimePicker.setUIID("CenterSubTitle");
            Label loginIcon = new Label("", "TextField");
            Label passwordIcon = new Label("", "TextField");
            FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_DATE_RANGE, 3);
            FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_CALL, 3);
            Picker stringPicker = new Picker();
            stringPicker.setType(Display.PICKER_TYPE_STRINGS);
            
            
            stringPicker.setUIID("CenterSubTitle");

            dateTimePicker.setDate(new Date());

            stringPicker.setStrings("Skype", "Viber", "Reunion Direct");
            stringPicker.setSelectedString("Skype");
            Button loginButton = new Button("valider");

            Button closeButton = new Button("retour");

            closeButton.addActionListener(e -> {
                dlg.dispose();
            });
            //L'action d'ajoutt
                           
            
            loginButton.addActionListener((valider) -> {
                try {
                    DemandesServices demand=new DemandesServices();
                    List<String> ls=StringUtil.tokenize(dateTimePicker.getText(), " ");
                        
                    DateFormat formatter = new SimpleDateFormat("dd-MM-y HH:mm");
                    DateFormat formatternew = new SimpleDateFormat("y-MM-dd HH:mm");
                            
                        
                    Date date = (Date)formatter.parse(dateTimePicker.getText());
                   
                    
                    String finString =formatternew.format(date);
                    
                    //Date newDate=new Date(finString);
                      Dialog dlgs = new Dialog("Confirmation");
    Style dlgStyle = dlgs.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
    

    dlgs.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("Voulez Vous Vraiment ajouter Ces Details?");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlgs.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.addActionListener((o) -> {
                  
                    
                demand.FixerDateEntretien(finString,stringPicker.getText(),de.getMatriculeDemande());
                  dlgs.dispose();
                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                ip.show();
                new ListeDemandes(res).show();
                
        
    });
    
                        
    Button Non = new Button(new Command("Non"));
    Non.addActionListener((e) -> {
       dlgs.dispose();
    });
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlgs.add(BoxLayout.encloseX(ok,Non));
   
    dlgs.showDialog();
                   
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
                
            });
            hi.add(BoxLayout.encloseX(loginIcon, dateTimePicker)).add(BoxLayout.encloseX(passwordIcon, stringPicker)).add(BoxLayout.encloseX(loginButton, closeButton));

            dlg.add(hi);
            dlg.show();
        });
        //Annulation de la Demande
        fab.createSubFAB(FontImage.MATERIAL_DELETE, "").addActionListener((evt) -> {

           DemandesServices ds=new DemandesServices();
           
           
           
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
          ip.show();
          ds.RefuserCandidat(de.getMatriculeDemande());
            new ListeDemandes(res).show();
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
        for (Formation f : form) {

            addButtonBottom(arrowDown, f.getFormation() + "  " + f.getAnnee(), 0xd997f1, true);
        }
        add(new Label("Competences Techniques", "TodayTitle"));
        for (CompetencesTechniques f : tech) {

            addButtonBottom(arrowDown, f.getCompetence() + "  " + f.getLevel(), 0x5ae29d, true);
        }
        add(new Label("Langues maitrisés", "TodayTitle"));
        for (Langues f : lang) {

            addButtonBottom(arrowDown, f.getLangue() + "  " + f.getNiveau(), 0xffc06f, true);
        }
        add(new Label("Centres d'interets", "TodayTitle"));
        for (CentresInterets f : cent) {

            addButtonBottom(arrowDown, f.getCentreInteret(), 0x4dc2ff, true);
        }
        // addButtonBottom(arrowDown, "Design app illustrations", 0x5ae29d, false);
//        addButtonBottom(arrowDown, "Javascript training ", 0x4dc2ff, false);
//        addButtonBottom(arrowDown, "Surprise Party for Matt", 0xffc06f, false);
        setupSideMenu(res);
    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("CentreSubtitle");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
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

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
