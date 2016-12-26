/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

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
    public Vector2(double r, double phi, boolean isSpherical) {
        this.x = r*Math.cos(phi);
        this.y = r*Math.sin(phi);
        this.r = r;
        this.phi = phi;
    }
    public Vector2 projVec(Vector2 vec2) {
        double scalar = (this.x*vec2.getX()+this.y*vec2.getY())/vec2.getR();
        return new Vector2(scalar, vec2.getPhi(), false);
    }
    public void updatePolar() {
        this.r = Math.sqrt(Math.exp(this.x)+Math.exp(this.y));
        this.phi = Math.atan2(this.y, this.x);
    }
    public void updateSpherical() {
        this.x = this.r*Math.cos(this.phi);
        this.y = this.r*Math.sin(this.phi);
    }
    public void setX(double x) {
        this.x = x;
        this.updateSpherical();
    }

    public void setY(double y) {
        this.y = y;
        this.updateSpherical();
    }

    public void setR(double r) {
        this.r = r;
        this.updatePolar();
    }

    public void setPhi(double phi) {
        this.phi = phi;
        this.updatePolar();
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
