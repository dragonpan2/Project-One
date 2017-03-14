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
import MathExt.Fast;
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
        double base = 51.4;
        double exp = 7856.1;
        System.out.println(Math.pow(base, exp));
        System.out.println(Ext.hugePow(base, exp));
        double fac = 143;
        System.out.println(Ext.factorial(fac));
        System.out.println(Ext.hugeFactorial(fac));
        double sin = -0.2;
        System.out.println(Fast.sinAlt(sin));
        System.out.println(Fast.sin(sin));
        
        
        
        
        
        double starttime;
        double endtime;
        for (int i=0; i<100000; i++) {
            Math.sqrt(Math.random());
            Fast.sqrt(Math.random());
        }
        
        starttime = System.nanoTime();
        for (int i=0; i<100000; i++) {
            Math.sqrt(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Math operations took "+ endtime/1000000 + "ms");
        
        starttime = System.nanoTime();
        for (int i=0; i<100000; i++) {
            Fast.sqrt(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Fast operations took "+ endtime/1000000 + "ms");
        
        

        
        
        
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
