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
import MathExt.Ext;
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
        
        System.out.println(5.53263465e-105);
        System.out.println(Math.pow(51.4, 7854));
        System.out.println(Ext.hugePowString(51.4, 7854));
        double fac = 300000;
        System.out.println(Ext.factorial(fac));
        System.out.println(Ext.hugeFactorialString(fac));
        
        
        
        
        
        
        
        
        
        double starttime;
        double endtime;
        
        starttime = System.nanoTime();

        
        endtime = System.nanoTime() - starttime;
        
        
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
