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
    double dst;
    double dft;
    
    double xoffset;
    double yoffset;
    double scaleoffset;
    
    double xscroffset;
    double yscroffset;
    
    int dispx;
    int dispy;
    
    String name;
    Color color;
    
    public Circle(String name, int r) {
        this(name, Color.WHITE, r);
    }
    public Circle(String name, Color color, int r) {
        //this.setSize(r*2, r*2);
        this.name = name;
        this.radius = r;
        this.color = color;
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
        int r = (int)(this.radius*(scaleoffset)+5);
        double idt = dst * dft;
        
        
        double ipx = x + stepsWithoutUpdate * idt * vx;
        double ipy = y + stepsWithoutUpdate * idt * vy;
        
        dispx = (int)(((ipx-xoffset)*scaleoffset)+xscroffset-r+0.5);
        dispy = (int)(((ipy-yoffset)*scaleoffset)+yscroffset-r+0.5);
        stepsWithoutUpdate++;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void updateCoordinates(double x, double y, double vx, double vy) {
        stepsWithoutUpdate = 0;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
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
        xscroffset = camera.getxScrOffset();
        yscroffset = camera.getyScrOffset();
    }

    @Override
    public DisplayObjectType getType() {
        return DisplayObjectType.Circle;
    }
    public int getDispx() {
        return dispx;
    }
    public int getDispy() {
        return dispy;
    }
    public int getRadius() {
        return (int)(radius*(scaleoffset)+5);
    }
    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean isInView(int x0, int y0, int x1, int y1) {
        return (dispx >= x0 && dispx <= x1 && dispy >= y0 && dispy <= y1);
    }

    
}
