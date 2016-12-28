/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPHY;
/**
 *
 * @author bowen
 */
public class Vector2 {
    private double[] elements = new double[2];
    public static boolean useFastTrig = true;
    
    public Vector2() {
    }
    public Vector2(double scalar) {
        this.elements = new double[]{scalar,scalar};
    }
    public Vector2(double[] array) {
        this.elements[0] = array[0];
        this.elements[1] = array[1];
    }
    public Vector2(double x, double y) {
        this.elements = new double[]{x,y};
    }
    public Vector2(double r, double phi, boolean isPolar) {
        if (useFastTrig) {
            this.elements[0] = r*FMath.bhaskaraCos(phi);
            this.elements[1] = r*FMath.bhaskaraSin(phi);
        } else {
            this.elements[0] = r*Math.cos(phi);
            this.elements[1] = r*Math.sin(phi);
        }
    }
    
    public double getElement(int i) {
        return this.elements[i];
    }
    public double getNorm() {
        double a = this.elements[0]*this.elements[0];
        double b = this.elements[1]*this.elements[1];
        return Math.sqrt(a + b);
    }
    public double getRot() {
        if (useFastTrig) {
            return FMath.taylorAtan(this.elements[1] / this.elements[0]);
        }
        return Math.atan(this.elements[1] / this.elements[0]);
    }
    public Vector2 getFill(double scalar) {
        return new Vector2(scalar, scalar);
    }
    public Vector2 getAdd(Vector2 vector) {
        return new Vector2(this.elements[0] + vector.elements[0], this.elements[1] + vector.elements[1]);
    }
    public Vector2 getAdd(double scalar) {
        return new Vector2(this.elements[0] + scalar, this.elements[1] + scalar);
    }
    public Vector2 getSub(Vector2 vector) {
        return new Vector2(this.elements[0] - vector.elements[0], this.elements[1] - vector.elements[1]);
    }
    public Vector2 getNeg() {
        return new Vector2(-this.elements[0], -this.elements[1]);
    }
    public Vector2 getMult(double scalar) {
        return new Vector2(this.elements[0] * scalar, this.elements[1] * scalar);
    }
    public double getMult(Vector2 vector) {
        return this.elements[0] * vector.elements[0] + this.elements[1] * vector.elements[1];
    }
    public double getProjSca(Vector2 vector) {
        return this.getMult(vector)/vector.getNorm();
    }
    public Vector2 getProjVec(Vector2 vector) {
        double scalar = this.getProjSca(vector);
        return new Vector2(scalar, vector.getRot(), false);
    }
    public Vector2 getRejVec(Vector2 vector) {
        return this.getSub(this.getProjVec(vector));
    }
    
    public void add(Vector2 vector) {
        this.elements[0] += vector.elements[0];
        this.elements[1] += vector.elements[1];
    }
    public void add(double scalar) {
        this.elements[0] += scalar;
        this.elements[1] += scalar;
    }
    public void sub(Vector2 vector) {
        this.elements[0] -= vector.elements[0];
        this.elements[1] -= vector.elements[1];
    }
    public void neg() {
        this.elements[0] = -this.elements[0];
        this.elements[1] = -this.elements[1];
    }
    public void mult(double scalar) {
        this.elements[0] *= scalar;
        this.elements[1] *= scalar;
        
    }
    public void proj(Vector2 vector) {
        double r = this.getProjSca(vector);
        double phi = vector.getRot();
        this.elements[0] = r*Math.cos(phi);
        this.elements[1] = r*Math.sin(phi);
    }
    public void rej(Vector2 vector) {
        double r = this.getProjSca(vector);
        double phi = vector.getRot();
        this.elements[0] -= r*Math.cos(phi);
        this.elements[1] -= r*Math.sin(phi);
    }
    public void fill(double scalar) {
        this.elements[0] = scalar;
        this.elements[1] = scalar;
    }
    public void setElement(int i, double scalar) {
        this.elements[i] = scalar;
    }
    public void setElements(Vector2 vector) {
        this.elements[0] = vector.elements[0];
        this.elements[1] = vector.elements[1];
    }
    public void setElements(double[] array) {
        this.elements[0] = array[0];
        this.elements[1] = array[1];
    }
    
    
}
