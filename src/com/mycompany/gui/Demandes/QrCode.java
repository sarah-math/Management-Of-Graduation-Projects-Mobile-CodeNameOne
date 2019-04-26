/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Demandes;

import com.codename1.ui.Container;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.materialscreens.MSUIKit.hi;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.mycompany.gui.qrcode.StateMachine;

/**
 *
 * @author yahia
 */
public class QrCode {
     public QrCode() {

         showQrCode();

    }
private void showQrCode()
    {
        Container ct = new Container();
    //  camera = new Resources
  //    if 
     new StateMachine("/qrcode");
     // new StateMachine("/theme");
   //  ct.add(new StateMachine("/qrcode"));
  // ct.addComponent(new StateMachine("/qrcode"));
  hi.removeAll();
  hi.refreshTheme();
     hi.show();
 

 
     
    }
   
    
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }

   

}
