/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D.Objects;

import World2D.Camera;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author bowen
 */
public class Circle implements DisplayObject {

    double x;
    double y;
    double radius;
    
    long stepsWithoutUpdate;
    
    double vx;
    double vy;
    double ax;
    double ay;
    double dst;
    double dft;
    
    double xoffset;
    double yoffset;
    double scaleoffset;
    
    int dispx;
    int dispy;
    
    Color color;
    
    public Circle(int r) {
        //this.setSize(r*2, r*2);
        this.radius = r;
        color = Color.white;
    }
    /*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        
        int r = (int)this.radius;
        
        g.fillOval(0, 0, r*2, r*2);
        this.setLocation(dispx, dispy);
    }*/
    private void interpolationStep() {
        int r = (int)this.radius;
        double idt = dst * dft;
        
        double ivx = vx + (0.5)*ax*(idt*stepsWithoutUpdate*idt*stepsWithoutUpdate);
        double ivy = vy + (0.5)*ay*(idt*stepsWithoutUpdate*idt*stepsWithoutUpdate);
        
        double ipx = x + stepsWithoutUpdate * idt * ivx;
        double ipy = y + stepsWithoutUpdate * idt * ivy;
        
        dispx = (int)(((ipx-xoffset)*scaleoffset)-r+0.5);
        dispy = (int)(((ipy-yoffset)*scaleoffset)-r+0.5);
        stepsWithoutUpdate++;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void updateCoordinates(double x, double y, double vx, double vy, double ax, double ay) {
        stepsWithoutUpdate = 0;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ax = ax;
        this.ay = ay;
    }
    @Override
    public void setInterpolationFrameTime(double dft) {
        this.dft = dft;
    }
    @Override
    public void setInterpolationSimulationTime(double dst) {
        this.dst = dst;
    }
    /*
    @Override
    public JComponent getJComponent() {
        return this;
    }*/

    @Override
    public void update(Camera camera) {
        xoffset = camera.getxPos();
        yoffset = camera.getyPos();
        scaleoffset = camera.getScale();
        interpolationStep();
    }

    @Override
    public DisplayObjectType getType() {
        return DisplayObjectType.Circle;
    }

    
}
