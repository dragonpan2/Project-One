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
public class Vector extends Matrix {
    
    public Vector(int rows) {
        super(rows, 1);
    }
    public Vector(double[] array) {
        super(array);
    }
}
