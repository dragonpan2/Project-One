/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import Physics2D.Objects.TestObject;
import Physics2D.NBodyIntegrator;
import Physics2D.NBodyIntegrator.Integrator;
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
    
    Vector2 vecPos1 = new Vector2(new double[]{900, 430});
    Vector2 vecVel1 = new Vector2(new double[]{-100, 0});
    TestObject obj1 = new TestObject(vecPos1, vecVel1, 1E17);
    Vector2 vecPos2 = new Vector2(new double[]{900, 570});
    Vector2 vecVel2 = new Vector2(new double[]{100, 0});
    TestObject obj2 = new TestObject(vecPos2, vecVel2, 1E17);
    Vector2 vecPos3 = new Vector2(new double[]{900, 200});
    Vector2 vecVel3 = new Vector2(new double[]{-220, 0});
    TestObject obj3 = new TestObject(vecPos3, vecVel3, 1E8);
    Vector2 vecPos4 = new Vector2(new double[]{0, 0});
    Vector2 vecVel4 = new Vector2(new double[]{0, 0});
    TestObject obj4 = new TestObject(vecPos4, vecVel4, 1E9);
    
    TestObject obj1a = obj1.clone();
    TestObject obj2a = obj2.clone();
    TestObject obj3a = obj3.clone();
    TestObject obj4a = obj4.clone();
    
    
    TestObject obj1b = obj1.clone();
    TestObject obj2b = obj2.clone();
    TestObject obj3b = obj3.clone();
    TestObject obj4b = obj4.clone();
    
    
    NBodyIntegrator int1 = new NBodyIntegrator(obj1, obj2, obj3, obj4);
    NBodyIntegrator int2 = new NBodyIntegrator(obj1a, obj2a, obj3a, obj4a);
    NBodyIntegrator int3 = new NBodyIntegrator(obj1b, obj2b, obj3b, obj4b);
    
    Thread thread;

    public World() {
         
        obj1a.setColour(Color.yellow);
        obj2a.setColour(Color.yellow);
        obj3a.setColour(Color.yellow);
        obj4a.setColour(Color.yellow);
        obj1b.setColour(Color.red);
        obj2b.setColour(Color.red);
        obj3b.setColour(Color.red);
        obj4b.setColour(Color.red);
        
        //this.setPreferredSize(new Dimension(120,1000));
        this.setLayout(null);
        //this.add(ball);
        this.add(obj1.displayComponent);
        this.add(obj2.displayComponent);
        this.add(obj3.displayComponent);
        this.add(obj4.displayComponent);
        this.add(obj1a.displayComponent);
        this.add(obj2a.displayComponent);
        this.add(obj3a.displayComponent);
        this.add(obj4a.displayComponent);
        this.add(obj1b.displayComponent);
        this.add(obj2b.displayComponent);
        this.add(obj3b.displayComponent);
        this.add(obj4b.displayComponent);
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
        
        int steps = 0;
        
        Vector2 v1 = new Vector2(new double[] {4,9});
        Vector2 v2 = new Vector2(new double[] {100, 10});
        Vector2 v3 = v2.clone();
        v3.add(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(Vectors2.prod(v1, v2, v1));
        System.out.println(Vectors2.prod(v1, 0));
        System.out.println(Vectors2.add(v1, v2, v1));
        
        
        while (true) {
            
            
            vec1.addRot(Math.PI/200);
            vec3.set(Vectors2.proj(vec1, vec2));
            vec4.set(Vectors2.rej(vec1, vec2));
            vec5.set(Vectors2.proj(vec2, vec1));
            vec6.set(Vectors2.rej(vec2, vec1));
            
            startTime = System.nanoTime();
            if (steps%1000 < 500) {
                int1.update(desiredSleepsec, 500, Integrator.RK4);
                int2.update(desiredSleepsec, 1, Integrator.LEAPFROG);
                int3.update(desiredSleepsec, 1, Integrator.EXPLICITMIDPOINT);
            } else {
                int1.update(-desiredSleepsec, 500, Integrator.RK4);
                int2.update(-desiredSleepsec, 1, Integrator.LEAPFROG);
                int3.update(-desiredSleepsec, 1, Integrator.EXPLICITMIDPOINT);
            }
            steps++;
            if (steps > 1000) {
                steps = 0;
            }
            System.out.println(steps);
            invalidate();
            repaint();
            endTime = System.nanoTime();
            
            
            sleepTime = (long)(desiredSleepms*1000000) - (endTime-startTime);
            if (sleepTime < 0) {
                sleepTime = 0;
                System.out.println("Thread Overload");
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
