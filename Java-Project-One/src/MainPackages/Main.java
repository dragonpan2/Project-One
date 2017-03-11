/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.util.Arrays;
import SLAG.Matrix;
import SLAG.Vector;
import SPHY.FMath;
import SPHY.Vector2;
import MathExt.Tensor;

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
        
        double[] d1 = new double[] {1,2,3};
        double[] d2 = new double[] {2,4,6};
        double[] d3 = new double[] {5,10,15};
        double[][] d4 = new double[][] {d1,{4,5,6},{7,8,9}};
        
        
        
        Tensor t1 = new Tensor(d4);
        
        Tensor ta = new Tensor(d1);
        Tensor tb = new Tensor(d2);
        Tensor tc = new Tensor(d3);
        
        Tensor tj = Tensor.join(ta, tb, tc);
        Tensor tj2 = Tensor.join(t1, t1);
        System.out.println(ta);       
        System.out.println(t1);       
        System.out.println(tj);      
        System.out.println(tj2);
        
    }
    
}
