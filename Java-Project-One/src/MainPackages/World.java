/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import Physics2D.Objects.TestObject;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import Physics2D.Vector2;
import Physics2D.Vectors2;

/**
 *
 * @author Lin-Li
 */
public class World extends JPanel implements Runnable {
    
    TestBall ball = new TestBall();
    
    Vector2 vec1 = new Vector2(new double[]{35, 100}, false);
    Vector2 vec2 = new Vector2(new double[]{90, 30}, false);
    Vector2 vec3 = Vectors2.proj(vec1, vec2);
    Vector2 vec4 = Vectors2.rej(vec1, vec2);
    Vector2 vec5 = Vectors2.proj(vec2, vec1);
    Vector2 vec6 = Vectors2.rej(vec2, vec1);
    
    TestLine line1 = new TestLine(vec1, Color.red, 2);
    TestLine line2 = new TestLine(vec2, Color.red, 2);
    TestLine line3 = new TestLine(vec3, Color.green, 3);
    TestLine line4 = new TestLine(vec4, Color.yellow, 3);
    TestLine line5 = new TestLine(vec5, Color.green, 3);
    TestLine line6 = new TestLine(vec6, Color.yellow, 3);
    
    Vector2 vecPos = new Vector2(new double[]{200, 200});
    Vector2 vecVel = new Vector2(new double[]{100, 50});
    TestObject obj1 = new TestObject(vecPos, vecVel, 1);
    
    Thread thread;

    public World() {
         
        this.setPreferredSize(new Dimension(1200,1000));
        this.setLayout(null);
        //this.add(ball);
        this.add(obj1.displayComponent);
        this.add(line1);
        this.add(line2);
        this.add(line3);
        this.add(line4);
        this.add(line5);
        this.add(line6);
        this.setLocation(500, 500);
        
        this.thread = new Thread(this);
        this.setVisible(true);
        this.setBackground(Color.black);
        thread.start();
    }

    @Override
    public void run() {
        
        while (true) {
            
            
            vec1.addRot(Math.PI/200);
            vec3.set(Vectors2.proj(vec1, vec2));
            vec4.set(Vectors2.rej(vec1, vec2));
            vec5.set(Vectors2.proj(vec2, vec1));
            vec6.set(Vectors2.rej(vec2, vec1));
            obj1.update(10F/1000);
            
            
            
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
