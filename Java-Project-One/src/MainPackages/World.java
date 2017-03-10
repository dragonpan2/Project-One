/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import SPHY.Vector2;

/**
 *
 * @author Lin-Li
 */
public class World extends JPanel implements Runnable {
    
    TestBall ball = new TestBall();
    
    Vector2 vec1 = new Vector2(new double[]{35, 100});
    Vector2 vec2 = new Vector2(new double[]{90, 30});
    Vector2 vec3 = vec1.getProjVec(vec2);
    Vector2 vec4 = vec1.getRejVec(vec2);
    
    TestLine line1 = new TestLine(vec1, Color.red, 2);
    TestLine line2 = new TestLine(vec2, Color.red, 2);
    TestLine line3 = new TestLine(vec3, Color.green, 3);
    TestLine line4 = new TestLine(vec4, Color.yellow, 3);
    
    Thread thread;

    public World() {
         
        this.setPreferredSize(new Dimension(1200,1000));
        this.setLayout(null);
        //this.add(ball);
        this.add(line3);
        this.add(line4);
        this.add(line1);
        this.add(line2);
        this.setLocation(500, 500);
        
        this.thread = new Thread(this);
        this.setVisible(true);
        this.setBackground(Color.black);
    }

    @Override
    public void run() {
        
        while (true) {
            
            
            //vec1.addToRot(Math.PI/200);
            vec3.setElements(vec1.getProjVec(vec2));
            vec4.setElements(vec1.getRejVec(vec2));
            
            
            
            
            invalidate();
            repaint();
            try {
                Thread.sleep(10);
                
            } catch (InterruptedException ex) {
                System.out.println("Thread Error");
            }
        }
        
    }
    
    
    
}
