/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Lin-Li
 */
public class World extends JPanel implements Runnable {
    
    TestBall ball = new TestBall();

    public World() {
         
        this.setPreferredSize(new Dimension(1200,1000));
        this.setLayout(null);
        this.add(ball);
        this.setLocation(500, 500);
        
         new Thread(this).start();
         this.setVisible(true);
         this.setBackground(Color.black);
    }

    @Override
    public void run() {
        
        while (true) {
            
            
            
            invalidate();
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Thread Error");
            }
        }
        
    }
    
    
    
}
