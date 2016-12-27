/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import SLAG.Vector;

/**
 *
 * @author Lin-Li
 */
public class World extends JPanel implements Runnable {
    
    TestBall ball = new TestBall();
    
    Vector vec1 = new Vector(new double[]{35, 100});
    Vector vec2 = new Vector(new double[]{90, 30});
    Vector vec3 = Vector.projVec(vec1,vec2);
    Vector vec4 = Vector.rejVec(vec1,vec2);
    
    TestLine line1 = new TestLine(vec1, Color.red, 2);
    TestLine line2 = new TestLine(vec2, Color.red, 2);
    TestLine line3 = new TestLine(vec3, Color.green, 3);
    TestLine line4 = new TestLine(vec4, Color.yellow, 3);

    public World() {
         
        this.setPreferredSize(new Dimension(1200,1000));
        this.setLayout(null);
        //this.add(ball);
        this.add(line3);
        this.add(line4);
        this.add(line1);
        this.add(line2);
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
