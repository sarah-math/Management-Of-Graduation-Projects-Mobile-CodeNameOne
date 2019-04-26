/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.offres;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import static com.codename1.uikit.materialscreens.MSUIKit.host;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.mycompagny.Service.DemandesServices;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class UnSeulOffre extends SideMenuBaseForm {

    public UnSeulOffre(List<String> get, HashMap<String, String> aa, Resources res) {

        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(15, 15, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, "user-picture.jpg",
                "http://" + host + "/gestionpfe/web/" + aa.get("imageEntreprise"));
        background.fetch();
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        Image profilePic = background;

        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> ((SideMenuBar) getToolbar().getMenuBar()).openMenu(null));
        Button postuler = new Button("postuler maintenant");
        postuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int idOffre = Integer.parseInt(aa.get("id"));
                DemandesServices dem=new DemandesServices();
                if(Dialog.show("Confirmation","Voulez vous vraiment postuler ? ", "Continuer", "Annuler"))
                {     Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                    ip.show();
                    dem.PostulerMobile(idOffre,usr.getId());
                    new OffresEtudiants(res).show();
                    
                }
               
                
                    
            }
        });
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                profilePicLabel,
                                postuler,
                                new Label("Votre chance " + aa.get("pourcentage") + "%", "SubTitle"),
                                new Label("     " + aa.get("titre"), "Title")
                        )
                )
        );
        tb.setTitleComponent(titleCmp);
        Button VoirOffreButton = new Button("");
        VoirOffreButton.setUIID("voir plus");
        FontImage.setMaterialIcon(VoirOffreButton, FontImage.MATERIAL_ARCHIVE);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, "Label", 3);
        addButtonBottom("Titre : " + aa.get("titre"));
        addButtonBottom("Description : " + aa.get("description"));
        addButtonBottom("Nombre d'etudiants\n demandes : " + aa.get("nbrDemandes"));
        addButtonBottom("Durée du stage : " + aa.get("duree"));
        addButtonBottom("Technologies   : ");
        for (String t : get) {
            addButtonBottom("         " + t);
        }
        setupSideMenu(res);
    }

    private void addButtonBottom(String text) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
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
        new StatsForm(res).show(); //To change body of generated methods, choose Tools | Templates.
    }

}

