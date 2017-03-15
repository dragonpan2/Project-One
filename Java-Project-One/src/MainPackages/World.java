/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import Physics2D.Objects.Body;
import Physics2D.Objects.TestObject;
import Physics2D.SpaceIntegrator;
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
    
    Vector2 vecPos1 = new Vector2(new double[]{400, 400});
    Vector2 vecVel1 = new Vector2(new double[]{30, -60});
    TestObject obj1 = new TestObject(vecPos1, vecVel1, 1E17D);
    Vector2 vecPos2 = new Vector2(new double[]{600, 600});
    Vector2 vecVel2 = new Vector2(new double[]{0, 80});
    TestObject obj2 = new TestObject(vecPos2, vecVel2, 1E17D);
    Vector2 vecPos3 = new Vector2(new double[]{800, 800});
    Vector2 vecVel3 = new Vector2(new double[]{0, -80});
    TestObject obj3 = new TestObject(vecPos3, vecVel3, 1E16D);
    
    
    SpaceIntegrator int1 = new SpaceIntegrator(obj1, obj2, obj3);
    
    Thread thread;

    public World() {
         
        this.setPreferredSize(new Dimension(1200,1000));
        this.setLayout(null);
        //this.add(ball);
        this.add(obj1.displayComponent);
        this.add(obj2.displayComponent);
        this.add(obj3.displayComponent);
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
        int desiredFPS = 60;
        double desiredSleepsec = 1D/desiredFPS;
        double desiredSleepms = 1000D/desiredFPS;
        long startTime;
        long endTime;
        long sleepTime;
        
        while (true) {
            
            
            vec1.addRot(Math.PI/200);
            vec3.set(Vectors2.proj(vec1, vec2));
            vec4.set(Vectors2.rej(vec1, vec2));
            vec5.set(Vectors2.proj(vec2, vec1));
            vec6.set(Vectors2.rej(vec2, vec1));
            
            startTime = System.nanoTime();
            int1.update(desiredSleepsec, 1E17);
            invalidate();
            repaint();
            endTime = System.nanoTime();
            
            sleepTime = (long)(desiredSleepms*1000000) - (endTime-startTime);
            if (sleepTime < 0) {
                sleepTime = 0;
            }
            long sleepms = Math.floorDiv(sleepTime, 1000000);
            int sleepns = (int)Math.floorMod(sleepTime, 1000000);
            
            try {
                Thread.sleep(sleepms, sleepns);
                
            } catch (InterruptedException ex) {
                System.out.println("Thread Error");
            }
        }
        
    }
    
    
    
}
