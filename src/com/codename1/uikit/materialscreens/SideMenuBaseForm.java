/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.mycompagny.Service.CvServices;
import com.mycompagny.Service.OffresServices;
import com.mycompany.Entities.User;
import com.mycompany.gui.Cv.AjouterCv;
import com.mycompany.gui.Cv.ModifierCv;
import com.mycompany.gui.Demandes.ListeAcceptés;
import com.mycompany.gui.Demandes.ListeDemandes;
import com.mycompany.gui.Demandes.QrCode;
import com.mycompany.gui.sarah.AffichageForm;
import com.mycompany.gui.sarah.RankingEntreprisesForm;
import com.mycompany.gui.stage.Affectation;
import com.mycompany.gui.stage.Affichage;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
        Image profilePic = usr.getImage();
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  " + usr.getNom() + " " + usr.getPrenom(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());
        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        if (usr.getRoles().equalsIgnoreCase("ROLE_ENTREPRISE")) {
            //getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
            getToolbar().addMaterialCommandToSideMenu("  Ajouter Offres", FontImage.MATERIAL_DASHBOARD, e -> showOtherForm(res));
            getToolbar().addMaterialCommandToSideMenu("  Liste Des offres", FontImage.MATERIAL_DASHBOARD, e -> showOtherForm(res));
            getToolbar().addMaterialCommandToSideMenu("  Liste Demandes", FontImage.MATERIAL_GROUP_WORK, e -> {

                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                ip.show();
                new ListeDemandes(res).show();

            });
            getToolbar().addMaterialCommandToSideMenu("  Liste Acceptés", FontImage.MATERIAL_TRENDING_UP, e -> {

                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                ip.show();
                new ListeAcceptés(res).show();

            });
            getToolbar().addMaterialCommandToSideMenu("  Lecteur QrCode", FontImage.MATERIAL_CODE, e -> new QrCode());
        }
        if (usr.getRoles().equalsIgnoreCase("ROLE_ETUDIANT")) {
            getToolbar().addMaterialCommandToSideMenu("  Afficher les offres", FontImage.MATERIAL_DASHBOARD, e -> {
                OffresServices.getOffersForUsers(res);
                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                ip.show();
            });
            getToolbar().addMaterialCommandToSideMenu("  partage d'experiences", FontImage.MATERIAL_DASHBOARD, e -> {
                AffichageForm p = new AffichageForm();
                p.getF().show();
            });
            getToolbar().addMaterialCommandToSideMenu("  Evaluation des entreprises", FontImage.MATERIAL_DASHBOARD, e -> {
                RankingEntreprisesForm p = new RankingEntreprisesForm();
                p.getF().show();
            });
            // getToolbar().addMaterialCommandToSideMenu("  Tasks", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
            getToolbar().addMaterialCommandToSideMenu("  Mon Cv", FontImage.MATERIAL_BOOK, (ActionEvent e) -> {

                CvServices cvSer = new CvServices();
                if (cvSer.FindOrCreateCv(usr.getId()) == 0) {
                    Toolbar.setGlobalToolbar(false);
                    Dialog ip = new InfiniteProgress().showInifiniteBlocking();

                    ip.show();
                    new AjouterCv(res, cvSer.CreerCv(usr.getId())).show();
                } else {
                    Dialog ip = new InfiniteProgress().showInifiniteBlocking();

                    ip.show();

                    new ModifierCv(res, usr.getId()).show();

                }

            });
        }
        if (usr.getRoles().equalsIgnoreCase("ROLE_ENSEIGNANT") && usr.getId()!=11) {
            getToolbar().addMaterialCommandToSideMenu("  Mes encadrements", FontImage.MATERIAL_DASHBOARD, e -> {
                MSUIKit z = new MSUIKit();
                z.init(new Object());
                Affichage a = new Affichage(UIManager.initNamedTheme("/theme1", "Theme"));
               a.getF().show();
            });
        }
        if(usr.getId()==11){
            getToolbar().addMaterialCommandToSideMenu("  Affectation", FontImage.MATERIAL_DASHBOARD, e -> {
                MSUIKit z = new MSUIKit();
                z.init(new Object());
                Affectation a = new Affectation(UIManager.initNamedTheme("/theme1", "Theme"));
               a.getF().show();
            });
        }

        // getToolbar().addMaterialCommandToSideMenu("  Parametres du compte", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Se deconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            usr = new User();
            new LoginForm(res).show();
        });
    }

    protected abstract void showOtherForm(Resources res);
}
