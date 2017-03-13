/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MathExt;

/**
 *
 * @author bowen
 */
public class Vector extends Tensor {
    public Vector(int n) {
        super(new int[] {n});
    }
    public Vector(int n, double fill) {
        super(new int[] {n}, fill);
    }
    public Vector(double[] elementArray) {
        super(elementArray);
    }
    protected Vector(Tensor tensor) {
        super(tensor);
    }
    public static Vector tensorToVector(Tensor tensor1) {
        if (tensor1.getDimensions() > 1) {
            throw new IndexOutOfBoundsException("Cannot convert 2rd or higher order tensor into vector.");
        }
        return new Vector(tensor1);
    }
    
}
