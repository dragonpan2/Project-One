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
    
    public static double prodSca(Vector... args) {
        if (!checkSameSize(args)) {
            return 0;
        }
        Vector newvector = new Vector(args[0].getComponents().length);
        for (int k=0; k<args.length; k++) {
            for (int i=0; i<args[0].getRows(); i++) {
                
            }
        }
    }
    
    
    
}
