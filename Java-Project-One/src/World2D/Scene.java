/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author bowen
 */
public class Scene extends JPanel implements Runnable {
    private Thread thread;
    private Boolean isActive;
    
    public Scene() {
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.black);
        
        thread = new Thread(this);
        thread.start();
    }
    
    public void deactivate() {
        isActive = false;
    }
    public void activate() {
        isActive = true;
    }
    
    @Override
    public void run() {
        if (isActive) {
            
        }
    }
}
