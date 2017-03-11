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
public abstract class Matrix extends Tensor {
    public Matrix(int m, int n) {
        super(new int[] {m,n});
    }
}
