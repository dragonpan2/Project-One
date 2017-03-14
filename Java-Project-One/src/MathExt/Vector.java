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
}
