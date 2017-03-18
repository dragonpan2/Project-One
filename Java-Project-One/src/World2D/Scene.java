/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D;

import World2D.Objects.DisplayObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author bowen
 */
public class Scene extends JPanel implements Runnable {
    private Thread thread;
    private Boolean isActive;
    private int desiredFPS;
    
    private DisplayObject[] displayObjects = new DisplayObject[0];
    
    private Camera camera;
    
    private Boolean keyW = false;
    private Boolean keyS = false;
    private Boolean keyA = false;
    private Boolean keyD = false;
    
    
    public Scene() {
        this(60);
    }
    
    public Scene(int desiredFPS) {
        this.isActive = false;
        this.desiredFPS = desiredFPS;
        this.camera = new Camera(this);
        
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.black);
        
        thread = new Thread(this);
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                 switch(e.getKeyCode()) {
                    case KeyEvent.VK_W :
                        keyW = true;
                        break;
                    case KeyEvent.VK_S :
                        keyS = true;
                        break;
                    case KeyEvent.VK_A :
                        keyA = true;
                        break;
                    case KeyEvent.VK_D :
                        keyD = true;
                        break;
                    default :
                        break;
                 }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                 switch(e.getKeyCode()) {
                    case KeyEvent.VK_W :
                        keyW = false;
                        break;
                    case KeyEvent.VK_S :
                        keyS = false;
                        break;
                    case KeyEvent.VK_A :
                        keyA = false;
                        break;
                    case KeyEvent.VK_D :
                        keyD = false;
                        break;
                    default :
                        break;
                 }
            }
        });
        
    }
    
    public void setDisplayObjects(DisplayObject... displayObjects) {
        this.displayObjects = new DisplayObject[displayObjects.length];
        for (int i=0; i<displayObjects.length; i++) {
            this.displayObjects[i] = displayObjects[i];
            this.displayObjects[i].setInterpolationFrameTime(1D/desiredFPS); //TODO Calculate FPS to interpolate when frametime changes
            //this.add(displayObjects[i].getJComponent());
        }
    }
    private void updateCameraToObjects() {
        for (int i=0; i<displayObjects.length; i++) {
            this.displayObjects[i].update(camera);
        }
    }
    
    public void deactivate() {
        isActive = false;
    }
    public void activate() {
        isActive = true;
    }
    public void start() {
        this.thread.start();
        activate();
    }
    
    public void checkKeys() {
        if (keyW) {
            camera.addyPos(-20);
        }
        if (keyS) {
            camera.addyPos(20);
        }
        if (keyA) {
            camera.addxPos(-20);
        }
        if (keyD) {
            camera.addxPos(20);
        }
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        
        
        
        /*g.setColor(Color.RED);
        g.drawRect(150,10,100,20);  
        g.fillRect(150,10,100,20);
        g.drawLine(200,10 , 200, 2000);
        g.setColor(Color.BLACK);
        g.drawString("UE",190 ,25 );
         ... All drawing code ... */
    }
    
    @Override
    public void run() {
        
        double desiredSleepms = 1000D/desiredFPS;
        
        long startTime;
        long endTime;
        long sleepTime;
        
        while (true) {
            if (isActive) {
                
                startTime = System.nanoTime();
                updateCameraToObjects();
                invalidate();
                repaint();
                checkKeys();
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


}
