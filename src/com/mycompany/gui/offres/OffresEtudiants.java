package com.mycompany.gui.offres;

/**
 *
 * @author ahmed
 */
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import static com.codename1.uikit.materialscreens.MSUIKit.host;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.uikit.materialscreens.StatsForm;
import com.mycompagny.Service.DemandesServices;
import com.mycompagny.Service.OffresServices;
import static com.mycompagny.Service.OffresServices.retur;
import static com.mycompagny.Service.OffresServices.tcs;

import java.util.HashMap;

/**
 *
 * @author ahmed
 */
public class OffresEtudiants extends SideMenuBaseForm {

    public OffresEtudiants(Resources res) {

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
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Offres ", "Title"),
                                new Label("La liste des Offres", "SubTitle")
                        )
                )
        );
        tb.setTitleComponent(titleCmp);
        Button VoirOffreButton = new Button("");
        DemandesServices d = new DemandesServices();
        VoirOffreButton.setUIID("voir plus");
        FontImage.setMaterialIcon(VoirOffreButton, FontImage.MATERIAL_ARCHIVE);
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, "Label", 3);

        for (HashMap<String, String> aa : retur) {
            addButtonBottom(arrowDown, "Titre  :" + aa.get("titre"), res, aa);
        }
        

        setupSideMenu(res);
    }

    private void addButtonBottom(Image arrowDown, String text, Resources res, HashMap<String, String> aa) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(15, 15, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, "user-picture.jpg",
                "http://"+host+"/gestionpfe/web/" + aa.get("imageEntreprise"));
        background.fetch();
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        Image bc = background;
        bc =  bc.fill(mask.getWidth(), mask.getHeight());
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(bc);
        finishLandingPage.setIconUIID("Container");
        finishLandingPage.addActionListener((evt) -> {
            Dialog ip = new InfiniteProgress().showInifiniteBlocking();
            ip.show();
            new UnSeulOffre(tcs.get(aa.get("id")),aa,res).show();
           
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

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }

}
