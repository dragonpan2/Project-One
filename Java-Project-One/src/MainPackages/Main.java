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
        double fac = 15;
        System.out.println(Ext.factorial(fac));
        System.out.println(Ext.log10Factorial(fac));
        double sin = 0.7;
        System.out.println(Fast.cos(sin));
        System.out.println(Fast.cos2(sin));
        System.out.println(Math.cos(sin));
        
        
        
        
        double starttime;
        double endtime;
        /*
        for (int i=0; i<10000000; i++) {
            Math.tan(Math.random());
            Fast.tan(Math.random());
        }*/
        
        starttime = System.nanoTime();
        for (int i=0; i<1000000; i++) {
            Fast.cos2(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Fast operations took "+ endtime/1000000 + "ms");
        
        
        starttime = System.nanoTime();
        for (int i=0; i<1000000; i++) {
            Fast.cos(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Math operations took "+ endtime/1000000 + "ms");
        
        starttime = System.nanoTime();
        for (int i=0; i<1000000; i++) {
            Fast.cos(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Math operations took "+ endtime/1000000 + "ms");
        
        starttime = System.nanoTime();
        for (int i=0; i<1000000; i++) {
            Fast.cos2(Math.random());
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
