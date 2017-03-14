/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import MathExt.Algebra.Matrices;
import MathExt.Algebra.Tensor;
import MathExt.Algebra.Matrix;
import MathExt.Algebra.Tensors;
import java.util.Arrays;
import static java.lang.Math.*;
/**
 *
 * @author Lin-Li
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Window window = new Window();
        //window.world.thread.start();
        //System.out.println("W,A,S,D,UP,DOWN,LEFT,RIGHT to move the vectors");
        //System.out.println("E to change Math mode, fastMath is currently WIP");
        sin(3);
        
        double[] d1 = new double[] {1,2,3};
        double[] d2 = new double[] {2,4,6};
        double[] d3 = new double[] {5,10,15};
        double[][] d4 = new double[][] {d1,{4,5,6},{7,8,9}};
        double[][] d5 = new double[][] {d2,d1,d3};
        double[][] d6 = new double[][] {{3},{3},{3}};
        
        
        double starttime;
        double endtime;
        
        Tensor tensor = new Tensor(d4);
        
            Matrix m1a = new Matrix(d4);
        starttime = System.nanoTime();
        Tensor t1 = new Tensor(d4);
        Tensor t2 = new Tensor(d5);
        Tensor ta = new Tensor(d1);
        Tensor tb = new Tensor(d2);
        Tensor tc = new Tensor(d3);
        Object obj = new Object();
        for (int i=0; i<100; i++) {
            Matrix test1 = new Matrix(d4);
        }
        Matrix m1 = new Matrix(d4);
        Matrix m2 = new Matrix(d6);
        Matrix m12 = Matrices.mult(m1, m2);
        
        
        Tensor tj = Tensors.join(ta, tb, tc);
        Tensor tj2 = Tensors.join(t1, t2);
        Tensor tadd = Tensors.add(tj, tj, ta);
        Tensor[] tsplit = Tensors.split(tj2);
        
        endtime = System.nanoTime() - starttime;
        
        
        System.out.println(m12);
        //System.out.println(m1.getDimensionSize(1));
        //System.out.println(m2.getDimensionSize(2));
        //System.out.println(m1);
        //System.out.println(m2);
        //System.out.println(Matrix.transpose(m2));
        //System.out.println(Matrix.transpose(m1));
        System.out.println("Tensor operations took "+ endtime/1000000 + "ms");
        
        
        
        
        starttime = System.nanoTime();
        double[] audiotest = new double[26460000];
        endtime = System.nanoTime() - starttime;
        System.out.println("Initialising array takes "+ endtime/1000000 + "ms");
        
        
        starttime = System.nanoTime();
        for (int i=0; i<audiotest.length; i++) {
            audiotest[i] = 2.4;
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Copying array takes "+ endtime/1000000 + "ms");
    }
    
    
}
