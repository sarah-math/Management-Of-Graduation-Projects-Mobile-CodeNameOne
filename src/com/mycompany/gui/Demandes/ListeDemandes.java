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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.mycompagny.Service.CvServices;
import com.mycompagny.Service.DemandesServices;

import com.mycompany.Entities.DemandesPDF;
import com.mycompany.gui.Cv.VoirCvCandidatEntreprise;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yahia
 */
public class ListeDemandes extends SideMenuBaseForm {

public static int idCandidat;
private Resources rs;
    public ListeDemandes(Resources res) {
    
    
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
        menuButton.addActionListener(e -> ((SideMenuBar)getToolbar().getMenuBar()).openMenu(null));
        

            DemandesServices demand=new DemandesServices();
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Gestion De Demandes ", "Title"),
                                    new Label("La liste des Demandes", "SubTitle")
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
       
        for(DemandesPDF dem :d.AfficherToutesLesDemandes(usr.getId()))
        {
            SpanLabel lab=new SpanLabel(dem.getNomEtudiant().toUpperCase()+ " :\n " +dem.getOffreDemande().toUpperCase());
             //VoirCvButton.setText(String.valueOf(dem.getIdEtudiant()));
        addButtonBottom(arrowDown,lab.getText(), 0xd997f1, true,VoirCvButton,dem.getIdEtudiant(),dem);
            
        }

       
        rs=res;
        setupSideMenu(res);
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first, Button CV,int i,DemandesPDF de) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        finishLandingPage.addActionListener((evt) -> {
                

           
           Dialog ip = new InfiniteProgress().showInifiniteBlocking();
            ip.show();
// do some long operation here using invokeAndBlock or do something in a separate thread and callback later
// when you are done just call
                new VoirCvCandidatEntreprise(i,rs,de).show();
            

           
            
           // new VoirCvCandidatEntreprise(15).show();
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
