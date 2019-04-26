/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Demandes;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.mycompagny.Service.DemandesServices;
import com.mycompany.Entities.DemandesPDF;
import com.mycompany.gui.Cv.VoirCvCandidatEntreprise;

/**
 *
 * @author yahia
 */
public class ListeAcceptés  extends SideMenuBaseForm{
    private Resources rs;
       public ListeAcceptés(Resources res) {
    
    
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        

            DemandesServices demand=new DemandesServices();
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Gestion De Demandes ", "Title"),
                                    new Label("La liste des Candidat Acceptés", "SubTitle")
                                )
                            )
                     
                );
        
      
        tb.setTitleComponent(titleCmp);
                        
        add(new Label("Les Demandes Par Offre", "TodayTitle"));
      Button VoirCvButton = new Button("");
       DemandesServices d=new DemandesServices();
        VoirCvButton.setUIID("Title");
        FontImage.setMaterialIcon(VoirCvButton, FontImage.MATERIAL_ARCHIVE);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, "Label", 3);
       
        for(DemandesPDF dem :d.AfficherToutesLesDemandesAccepted(10))
        {
            SpanLabel lab=new SpanLabel(dem.getNomEtudiant().toUpperCase()+ " :\n " +dem.getOffreDemande().toUpperCase());
             //VoirCvButton.setText(String.valueOf(dem.getIdEtudiant()));
        addButtonBottom(arrowDown,lab.getText(), 0xd997f1, true,VoirCvButton,dem.getIdEtudiant(),dem);
            
        }

       
       rs=res;
        setupSideMenu(res);
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first, Button CV,int i,DemandesPDF de) {
        DemandesServices demser=new DemandesServices();
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        finishLandingPage.addActionListener((evt) -> {
               Dialog dlg = new Dialog("Choix d'action");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
   // title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    title.getUnselectedStyle().setFgColor(0xff);
   // title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("Veuillez Choisir une action..");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);
    
    Button ok = new Button(new Command("Accepter"));
    Button non = new Button(new Command("Refuser"));
    Button retour = new Button(new Command("retour"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(BoxLayout.encloseX(ok,non,retour));
    retour.addActionListener((ret) -> {
        dlg.dispose();
    });
    ok.addActionListener((k) -> {
        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
            ip.show();
            demser.AccepterCandidatAccepte(de.getMatriculeDemande());
           new ListeAcceptés(rs).show();
    });
        
    non.addActionListener((n) -> {
        Dialog ip = new InfiniteProgress().showInifiniteBlocking();
          ip.show();
            demser.RefuserCandidatAccepte(de.getMatriculeDemande());
            new ListeAcceptés(rs).show();
           
    });
    dlg.showDialog();
            
           
           

        });
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
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
