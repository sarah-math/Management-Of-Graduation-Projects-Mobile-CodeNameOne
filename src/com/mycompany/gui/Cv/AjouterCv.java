/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Cv;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import static com.codename1.uikit.materialscreens.LoginForm.usr;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.mycompagny.Service.CvServices;
import com.mycompany.Entities.CentresInterets;
import com.mycompany.Entities.CompetencesTechniques;
import com.mycompany.Entities.Formation;
import com.mycompany.Entities.Langues;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yahia
 */
public class AjouterCv extends SideMenuBaseForm {

    public static String[] possibleSuggestions = {
        "ActionScript",
        "AppleScript",
        "Asp",
        "BASIC",
        "C",
        "C++",
        "Clojure",
        "COBOL",
        "ColdFusion",
        "Erlang",
        "Fortran",
        "Groovy",
        "Haskell",
        "Java",
        "JavaScript",
        "Lisp",
        "Perl",
        "PHP",
        "Python",
        "Ruby",
        "Scala",
        "Scheme"
    };
    public static String[] langues = {
        "arabe",
        "francais",
        "Anglais",
        "Allmand",
        "chinoise",
        "japponaise"

    };
    public static String[] niveaux = {
        "A1",
        "A2",
        "B1",
        "B2",
        "C1",
        "C2"

    };
    public static String[] CentresInterets = {
        "Activite sportive collective",
        "Activite artistique",
        "Activite culturelle",
        "Activite musicale"

    };
    public static String[] TechLevel = {
        "Faible",
        "peu de connaissance",
        "Bien",
        "Excellent"

    };
    

    public AjouterCv(Resources res, int id) {

        super(new LayeredLayout());
        CvServices cvSer = new CvServices();
        System.out.println("l'id est celui la : " + id);
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");

        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));

        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();

        Image notes = res.getImage("notes.png");
        Image duke = res.getImage("duke.png");

        Label notesPlaceholder = new Label("", "ProfilePic");
        Label notesPlaceholder2 = new Label("", "ProfilePic");
        Label notesLabel = new Label(notes, "ProfilePic");
        Label notesLabel2 = new Label(notes, "ProfilePic");
        Component.setSameHeight(notesLabel, notesPlaceholder);
        Component.setSameHeight(notesLabel2, notesPlaceholder2);
        Component.setSameWidth(notesLabel, notesPlaceholder);
        Component.setSameWidth(notesLabel2, notesPlaceholder2);
        Label bottomSpace = new Label();
        Label bottomSpace3 = new Label();
        TextField formationInput = new TextField("", "Formation");
        Picker DateformationInput = new Picker();

        AutoCompleteTextField competenceInput = new AutoCompleteTextField(possibleSuggestions);
        competenceInput.setHint("Competence Technique");
        competenceInput.setMinimumElementsShownInPopup(5);
        Picker niveauTech = new Picker();
        niveauTech.setType(Display.PICKER_TYPE_STRINGS);
        niveauTech.setStrings(TechLevel);
        
        Picker langueInput = new Picker();
        langueInput.setType(Display.PICKER_TYPE_STRINGS);
        langueInput.setStrings(langues);
        Picker niveauLangue = new Picker();
        niveauLangue.setType(Display.PICKER_TYPE_STRINGS);
        niveauLangue.setStrings(niveaux);
        Picker CentreInput = new Picker();
        CentreInput.setType(Display.PICKER_TYPE_STRINGS);
        CentreInput.setStrings(CentresInterets);
         /* !!!!!!!!!!!   Controle de Saisie Start !!!!!!!!!!! */
        Validator v = new Validator();
        v.addConstraint(formationInput,new LengthConstraint(5))
        .addConstraint(competenceInput, new LengthConstraint(1, "Verifiez competence"))
        .addConstraint(niveauTech, new LengthConstraint(2)).addConstraint(niveauLangue, new LengthConstraint(2))
        .addConstraint(langueInput, new LengthConstraint(2)).addConstraint(CentreInput, new LengthConstraint(2));
       
        
       
                
        /* !!!!!!!!!!!   Controle de Saisie End !!!!!!!!!!! */

//         TextField formationInput=new TextField("", "Formation");
//         Picker DateformationInput=new Picker();
        Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                notesPlaceholder,
                new Label("Ajouter vos Formations", "WalkthruWhite"),
                formationInput,
                DateformationInput,
                bottomSpace
        ));

        tab1.setUIID("WalkthruTab1");

        walkthruTabs.addTab("", tab1);

        Label bottomSpaceTab2 = new Label();

        Label addForm = new Label(duke, "ProfilePic");

        Container tab2 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                addForm,
                new Label("Ajouter vos Competences", "WalkthruWhite"),
                competenceInput, niveauTech,
                bottomSpaceTab2
        ));

        tab2.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab2);

        Label bottomSpaceTab3 = new Label();

        Label addCompetence = new Label(duke, "ProfilePic");

        Container tab3 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                addCompetence,
                new Label("Ajouter des Langues", "WalkthruWhite"),
                langueInput,
                niveauLangue,
                bottomSpaceTab3
        ));

        tab3.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab3);
        Label bottomSpaceTab4 = new Label();

        Label addCentre = new Label(duke, "ProfilePic");

        Container tab4 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                addCentre,
                new Label("Ajouter vos Interets", "WalkthruWhite"),
                CentreInput,
                bottomSpaceTab4
        ));

        tab4.setUIID("WalkthruTab2");

        walkthruTabs.addTab("", tab4);

        add(walkthruTabs);

        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Button skip = new Button("Confirmer");
         
        skip.setUIID("SkipButton");
        v.addSubmitButtons(skip);
        skip.addActionListener((ActionEvent e) -> {

            Dialog dlgs = new Dialog("Confirmation");
            Style dlgStyle = dlgs.getDialogStyle();
            dlgStyle.setBorder(Border.createEmpty());
            dlgStyle.setBgTransparency(255);
            dlgStyle.setBgColor(0xffffff);

            Label title = dlgs.getTitleComponent();

            dlgs.setLayout(BoxLayout.y());
            Label blueLabel = new Label();
            blueLabel.setShowEvenIfBlank(true);
            blueLabel.getUnselectedStyle().setBgColor(0xff);
            blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
            blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
            dlgs.add(blueLabel);
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
            dlgs.add(grayLabel);

            Button ok = new Button(new Command("OK"));
            ok.addActionListener((ActionEvent o) -> {
                try {
                    
                    //confirmation ajout cv
                    {   Formation form = new Formation();
                    CompetencesTechniques Tech = new CompetencesTechniques();
                    Langues lang = new Langues();
                    CentresInterets centr = new CentresInterets();
                    dlgs.dispose();
                    Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                    ip.show();

                    DateFormat formatter = new SimpleDateFormat("dd-MM-y ");
                    DateFormat formatternew = new SimpleDateFormat("y-MM-dd ");
                    Date date = (Date) formatter.parse(DateformationInput.getText());
                    String finString = formatternew.format(date);
                    System.out.println("la date est : " + finString);

                    form.setIdCv(id);
                    form.setFormation(formationInput.getText());
                    form.setAnnee(finString);
                    Tech.setIdCv(id);
                    Tech.setCompetence(competenceInput.getText());
                    Tech.setLevel(niveauTech.getText());
                    lang.setIdCv(id);
                    lang.setLangue(langueInput.getText());
                    lang.setNiveau(niveauLangue.getText());
                    centr.setIdCv(id);
                    centr.setCentreInteret(CentreInput.getText());
                    cvSer.AjouterFormation(form);
                    cvSer.AjouterTech(Tech);
                    cvSer.AjouterLangue(lang);
                    cvSer.AjouterCentre(centr);
                    ip.dispose();
                  if( Dialog.show("Choix d'action", "Voulez vous ajouter Encore ?", "Oui", "Non")){
                     
List<String> list = new ArrayList<>(Arrays.asList(possibleSuggestions));
List<String> list1 = new ArrayList<>(Arrays.asList(CentresInterets));
List<String> list2 = new ArrayList<>(Arrays.asList(langues));
list.remove(competenceInput.getText());
possibleSuggestions = list.toArray(new String[0]);
list1.remove(CentreInput.getText());
CentresInterets = list1.toArray(new String[0]);
list2.remove(langueInput.getText());
langues = list2.toArray(new String[0]);
                      new AjouterCv(res, id).show();
                  }
                  else
                  {
                        Toolbar.setGlobalToolbar(true);
                       //Dialog ips = new InfiniteProgress().showInifiniteBlocking();
               
                        ip.show();
                      new ModifierCv(res,usr.getId()).show();
                  }
                    }
                    
                } catch (ParseException ex) {
                    System.out.println("erreur fi date el formation");
                }

            });

            Button Non = new Button(new Command("Non"));
            Non.addActionListener((dd) -> {
                dlgs.dispose();
            });
            ok.getAllStyles().setBorder(Border.createEmpty());
            ok.getAllStyles().setFgColor(0);
            dlgs.add(BoxLayout.encloseX(ok, Non));

            dlgs.showDialog();

        });

        Container southLayout = BoxLayout.encloseY(
                radioContainer,
                skip
        );
        add(BorderLayout.south(
                southLayout
        ));

        Component.setSameWidth(bottomSpace, bottomSpaceTab2, southLayout);
        Component.setSameHeight(bottomSpace, bottomSpaceTab2, southLayout);
        Component.setSameWidth(bottomSpace, bottomSpaceTab3, southLayout);
        Component.setSameHeight(bottomSpace, bottomSpaceTab3, southLayout);
        Component.setSameWidth(bottomSpace, bottomSpaceTab4, southLayout);
        Component.setSameHeight(bottomSpace, bottomSpaceTab4, southLayout);

        // visual effects in the first show
        addShowListener(e -> {
            notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));
        });
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
   

}
