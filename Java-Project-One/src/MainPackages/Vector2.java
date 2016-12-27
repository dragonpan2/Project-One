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
//public class Vector2 { //2D Vector class
//    private double x, y, r, phi; //x,y for Cartesian Coordinates, r,phi for Polar Coordinates
//
//    public Vector2(double x, double y) { //Create vector from Cartesian Coordinates
//        this.x = x;
//        this.y = y;
//        this.r = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
//        this.phi = Math.atan2(y, x);
//    }
//    public Vector2(double r, double phi, boolean isPolar) { //Create vector from Polar Coordinates
//        this.x = r*Math.cos(phi);
//        this.y = r*Math.sin(phi);
//        this.r = r;
//        this.phi = phi;
//    }
//    public Vector2 getAddVec(Vector2 vec2) { //Gets the vector sum of two vectors
//        return new Vector2(this.x+vec2.getX(), this.y+vec2.getY());
//    }
//    public Vector2 getMultSca(double scalar) { //Gets the scalar multiplication
//        return new Vector2(this.x*scalar, this.y*scalar);
//    }
//    public double getProdSca(Vector2 vec2) { //Gets the scalar product of two vectors
//        return (this.x*vec2.getX()+this.y*vec2.getY());
//    }
//    public double getProjSca(Vector2 vec2) { //Gets the scalar projection of two vectors
//        return this.getProdSca(vec2)/vec2.getR();
//    }
//    public Vector2 getProjVec(Vector2 vec2) { //Gets the vector projection of two vectors
//        double scalar = this.getProjSca(vec2);
//        return new Vector2(scalar, vec2.getPhi(), false);
//    }
//    public Vector2 getRejVec(Vector2 vec2) { //Gets the vector rejection of two vectors
//        Vector2 vec3 = this.getProjVec(vec2);
//        return new Vector2(this.x-vec3.getX(), this.y-vec3.getY());
//    }
//    public void updatePolar() { //Updates polar coordinates from cartesian coordinates
//        this.r = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
//        this.phi = Math.atan2(this.y, this.x);
//    }
//    public void updateCartesian() { //Updates cartesian coordinates from polar coordinates
//        this.x = this.r*Math.cos(this.phi);
//        this.y = this.r*Math.sin(this.phi);
//    }
//    public void setX(double x) {
//        this.x = x;
//        this.updatePolar();
//    }
//
//    public void setY(double y) {
//        this.y = y;
//        this.updatePolar();
//    }
//
//    public void setR(double r) {
//        this.r = r;
//        this.updateCartesian();
//    }
//
//    public void setPhi(double phi) {
//        this.phi = phi;
//        this.updateCartesian();
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public double getR() {
//        return r;
//    }
//
//    public double getPhi() {
//        return phi;
//    }
//    
//}
