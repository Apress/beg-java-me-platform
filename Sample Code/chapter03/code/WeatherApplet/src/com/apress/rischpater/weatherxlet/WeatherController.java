/*
 * WeatherController.java
 *
 * Created on November 24, 2007, 8:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.apress.rischpater.weatherxlet;
import javax.swing.JPanel;
import org.jdesktop.layout.GroupLayout;

/**
 *
 * @author kf6gpe
 */
public class WeatherController {
    private JPanel mainPanel, settingPanel;
    WeatherXlet xlet;
    
    GroupLayout layout;

    /** Creates a new instance of WeatherController */
    public WeatherController( WeatherXlet x) {
        xlet = x;
        layout = (org.jdesktop.layout.GroupLayout)xlet.getContentPane().getLayout();
    }
    
    public void setMainPanel( JPanel m ) {
        mainPanel = m;
    }
    
    public void setSettingPanel( JPanel s ) {
        settingPanel = s;
    }
    
    public void showMainPanel() {
        layout.replace( settingPanel, mainPanel );
        xlet.pack();
    }
    
    public void showSettingPanel() {
        layout.replace( mainPanel, settingPanel );
        xlet.pack();
    }
}
