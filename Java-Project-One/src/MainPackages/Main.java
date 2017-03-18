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
import MathExt.Approx;
import java.util.Arrays;
import MathExt.Ext;
import MathExt.Fast;
import Physics2D.Integrators.Integrator.IntegratorType;
import Physics2D.Objects.SpaceObject;
import Physics2D.Simulation;
import Physics2D.Vector2;
import World2D.Scene;
import World2D.Viewport;
/**
 *
 * @author Lin-Li
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Vector2 vecPos1 = new Vector2(new double[]{900, 430});
        Vector2 vecVel1 = new Vector2(new double[]{-100, 0});
        SpaceObject obj1 = new SpaceObject(vecPos1, vecVel1, 1E17);
        Vector2 vecPos2 = new Vector2(new double[]{900, 570});
        Vector2 vecVel2 = new Vector2(new double[]{100, 0});
        SpaceObject obj2 = new SpaceObject(vecPos2, vecVel2, 1E17);
        Vector2 vecPos3 = new Vector2(new double[]{900, 200});
        Vector2 vecVel3 = new Vector2(new double[]{-220, 0});
        SpaceObject obj3 = new SpaceObject(vecPos3, vecVel3, 1E8);
        Vector2 vecPos4 = new Vector2(new double[]{0, 0});
        Vector2 vecVel4 = new Vector2(new double[]{0, 0});
        SpaceObject obj4 = new SpaceObject(vecPos4, vecVel4, 1E9);
        
        
        
        
        
        
        Scene scene = new Scene(60);
        Viewport viewport = new Viewport(scene);
        
        Simulation space = new Simulation(IntegratorType.SYMPLECTIC1, 1D/10, 1D/20, obj1, obj2, obj3, obj4);
        
        scene.setDisplayObjects(space.getDisplayObjects());
        
        scene.start();
        space.start();
        
        
        
        //Window window = new Window();
        //System.out.println("W,A,S,D,UP,DOWN,LEFT,RIGHT to move the vectors");
        //System.out.println("E to change Math mode, fastMath is currently WIP");
        /*
        System.out.println(5.53263465e-105);
        double base = 51.4;
        double exp = 7856.1;
        System.out.println(Math.pow(base, exp));
        System.out.println(Ext.hugePow(base, exp));
        double fac = 15;
        System.out.println(Ext.factorial(fac));
        System.out.println(Ext.log10Factorial(fac));
        double sin = 0.7;
        System.out.println(Fast.sin(sin));
        System.out.println(Math.sin(sin));
        
        
        
        
        double starttime;
        double endtime;
        
        for (int i=0; i<10000000; i++) {
            Math.tan(Math.random());
            Fast.tan(Math.random());
        }
        
        starttime = System.nanoTime();
        for (int i=0; i<100000000; i++) {
            Fast.cos(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Fast Cos took "+ endtime/1000000 + "ms");
        
        
        starttime = System.nanoTime();
        for (int i=0; i<100000000; i++) {
            Approx.cos(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Approx Cos took "+ endtime/1000000 + "ms");
        
        starttime = System.nanoTime();
        for (int i=0; i<100000000; i++) {
            Fast.tan(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Fast Tan took "+ endtime/1000000 + "ms");
        
        starttime = System.nanoTime();
        for (int i=0; i<100000000; i++) {
            Math.tan(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Math Tan took "+ endtime/1000000 + "ms");
        
        starttime = System.nanoTime();
        for (int i=0; i<100000000; i++) {
            Fast.sqrt(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Fast Sqrt took "+ endtime/1000000 + "ms");
        
        starttime = System.nanoTime();
        for (int i=0; i<100000000; i++) {
            Approx.sqrt(Math.random());
        }
        endtime = System.nanoTime() - starttime;
        System.out.println("Approx Sqrt took "+ endtime/1000000 + "ms");
        
        
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
        */
    }
    
    
}
