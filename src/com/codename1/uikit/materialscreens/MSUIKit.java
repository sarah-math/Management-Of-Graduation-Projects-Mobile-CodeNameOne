

package com.codename1.uikit.materialscreens;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;


public class MSUIKit {

    private Form current;
    public static Form hi;
    private Resources theme;
    public static Resources resource;
    public static String host;
    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        Toolbar.setCenteredDefault(false);
        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() {
        if(current != null){
            current.show();
            
            return;
        }
        hi=current;
        host="localhost";
        new LoginForm(theme).show();
        //new ProfileForm(theme).show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }

}
