/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.project.one;

/**
 *
 * @author bowen
 */
public class Vector2 {
    private double x, y, r, phi;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
        this.r = Math.sqrt(Math.exp(x)+Math.exp(y));
        this.phi = Math.atan2(y, x);
    }
    public Vector2(double r, double phi, boolean cw) {
        this.x = r*Math.cos(phi);
        this.y = r*Math.sin(phi);
        this.r = r;
        this.phi = phi;
    }
    public Vector2 projVec(Vector2 vec1, Vector2 vec2) {
        double scalar = (vec1.getX()*vec2.getX()+vec1.getY()*vec2.getY())/vec2.getR();
        Vector2 vec3 = new Vector2(1, vec2.getPhi(), false);
        
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public double getPhi() {
        return phi;
    }
    
}
