/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.sarah;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.DoughnutChart;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.EvaluationService;
import com.mycompagny.Service.EvaluationService.NotesEntreprise;
import com.mycompany.Entities.Evaluation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sarah
 */


public class StatisticsEntrepriseForm  extends AbstractDemoChart {
     Form f;
    SpanLabel lb;
     private Resources theme;
     int id ;
     String nomEntrep ; 

    public StatisticsEntrepriseForm( int id, String nomEntrep) {
        this.id=id ;  
         this.nomEntrep=nomEntrep ;
          theme = UIManager.initNamedTheme("/theme1", "Theme");
        
          //fonction fi west'ha esm l'entreprise 
        f = new Form( nomEntrep , new BoxLayout(BoxLayout.Y_AXIS));
        
        EvaluationService ser = new EvaluationService(); 
    ArrayList<EvaluationService.NotesEntreprise> hello = ser.AfficherNotesEntreprise(id) ;  //exemple Ardia
   
    for (NotesEntreprise n : hello)
    {
     Container C = new Container(BoxLayout.y());  
     
     String S1= "Consistance du Projet" ; 
     String S2= "Qualité de l'Encadrement" ; 
     String S3= "Ambiance de Travail" ; 
     String S4= "Disponibilité Ressources& Docs" ; 
     
       Label Lab1 = new Label(S1) ; 
       Label Lab2 = new Label(S2) ; 
       Label Lab3 = new Label(S3) ; 
       Label Lab4 = new Label(S4) ; 
       
     
        Component Chart1 = execute(S1, n.getMoyC1()*10 , (10-n.getMoyC1())*10  ) ;
     
       C.add(Lab1) ; 
       C.add(Chart1) ; 
        Component Chart2 = execute(S2, n.getMoyC2()*10 , (10-n.getMoyC2())*10 ) ;
        
          C.add(Lab2) ;
        C.add(Chart2) ; 
        Component Chart3 = execute(S3, n.getMoyC3()*10 , (10-n.getMoyC3())*10 ) ;
       
          C.add(Lab3) ;
        C.add(Chart3) ; 
        Component Chart4 = execute(S4, n.getMoyC4()*10 , (10-n.getMoyC4())*10 ) ;
       
          C.add(Lab4) ;
        C.add(Chart4) ; 
        
           f.add(C) ; 
        
    Container OpinListC = new Container(BoxLayout.y()) ; 
    
    Label OpTitre= new Label ("Témoignages des Stagieres") ; 
    
    OpinListC.add(OpTitre) ; 

   
   ArrayList<Evaluation> OpinionList= ser.AfficherOpinionEntreprise(id) ;
       
        for  ( Evaluation op :  OpinionList)  {
               
             //  Container COp = new Container( ) ;
           SpanLabel Op = new SpanLabel(op.getOpinion() ) ;    
         //  COp.add(Op) ; 
               OpinListC.add(Op) ; 
           
                   } 


                  f.add(OpinListC) ; 


    
        
    
  
    }    
        
     f.getToolbar().addCommandToLeftBar("back" , null , (e)->{RankingEntreprisesForm a =new RankingEntreprisesForm() ; a.getF().showBack();} );
        
       
       f.show() ;  
    }
     
     /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Budget chart for several years";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The budget per project for several years (doughnut chart)";
    }

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "Doughnut Chart Demo";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    
    
    
 
    public Component execute(String critere , float val, float val2 ) {
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[]{val , val2});
       // values.add(new double[]{10, 9, 14, 20, 11});
        List<String[]> titles = new ArrayList<String[]>();
        titles.add(new String[]{ critere , "" });
       // titles.add(new String[]{"Project1", "Project2", "Project3", "Project4", "Project5"});
        int[] colors = new int[]{ColorUtil.rgb(55, 0, 0), ColorUtil.GRAY, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setApplyBackgroundColor(true);
        renderer.setLabelsColor(ColorUtil.GRAY);
        initRendererer(renderer);

        DoughnutChart chart = new DoughnutChart(buildMultipleCategoryDataset(critere, titles, values), renderer);
        ChartComponent c = newChart(chart);
        return c;

    } 

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public SpanLabel getLb() {
        return lb;
    }

    public void setLb(SpanLabel lb) {
        this.lb = lb;
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }

    public Font getSmallFont() {
        return smallFont;
    }

    public void setSmallFont(Font smallFont) {
        this.smallFont = smallFont;
    }

    public Font getMedFont() {
        return medFont;
    }

    public void setMedFont(Font medFont) {
        this.medFont = medFont;
    }

    public Font getLargeFont() {
        return largeFont;
    }

    public void setLargeFont(Font largeFont) {
        this.largeFont = largeFont;
    }
  
    
    
}
