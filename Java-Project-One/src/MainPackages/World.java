/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Lin-Li
 */
public class World extends JPanel implements Runnable {

    public World() {
         
        this.setPreferredSize(new Dimension(1200,1000));
        
        
        
         new Thread(this).start();
    }

    @Override
    public void run() {
        
        while (true) {
            
            
            
        }
        
    }
    
    
    
}
