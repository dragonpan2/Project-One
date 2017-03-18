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
import Physics.Integrators.Integrator.IntegratorType;
import Physics2D.NBodyFuturePath;
import Physics2D.Objects.SpaceObject;
import Physics2D.NBodySimulation;
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
        static double AU = 1.496e+11; //AU/m
        static double DAY = 86400D;
        static NBodySimulation mainsimulation;
    public static void main(String[] args) {
        
        
        SpaceObject sun = generateBody("Sun", 0, 0, 0, 0, 1.989E30);
        SpaceObject mercury = generateBody("Mercury", 2.805339263696457E-01, 1.727431750445399E-01, -2.010150137126407E-02, 2.529075820940590E-02, 3.285E23);
        SpaceObject venus = generateBody("Venus", -7.028941647603416E-01, 1.359581091434492E-01, -3.813062436826709E-03, -1.996801334623488E-02, 4.867E24);
        SpaceObject earth = generateBody("Earth", -9.882510901700633E-01, 8.499778853173919E-02, -1.680240369278054E-03, -1.719988462359221E-02, 5.972E24);
        SpaceObject mars = generateBody("Mars", 7.780694849748854E-01, 1.279727996521010E+00, -1.143145066701317E-02, 8.466471250421175E-03, 6.39E23);
        SpaceObject jupiter = generateBody("Mercury", 0, 0, 0, 0, 0);
        SpaceObject saturn = generateBody("Mercury", 0, 0, 0, 0, 0);
        SpaceObject uranus = generateBody("Mercury", 0, 0, 0, 0, 0);
        SpaceObject neptune = generateBody("Mercury", 0, 0, 0, 0, 0);
        
        
        NBodyFuturePath futureIntegrator = new NBodyFuturePath(IntegratorType.SYMPLECTIC4, 1E5, 100, 10, 5, sun, mercury, venus, earth, mars);
        NBodySimulation space = new NBodySimulation(IntegratorType.SYMPLECTIC4, 1E5, 15, 1, futureIntegrator, sun, mercury, venus, earth, mars);
        
        Scene scene = new Scene(60, 1920, 1080, space);
        Viewport viewport = new Viewport(1920, 1080, scene);
        
        
        scene.setDisplayObjects(space.getDisplayObjects());
        
        scene.start();
        space.start();
        
        mainsimulation = space;
        
    }
    
    public static SpaceObject generateBody(String name, double xAU, double yAU, double vxAUDay, double vyAUDay, double massKg) {
        Vector2 pos = new Vector2(new double[]{xAU*AU, yAU*AU});
        Vector2 vel = new Vector2(new double[]{vxAUDay*AU/DAY, vyAUDay*AU/DAY});
        return new SpaceObject(name, pos, vel, massKg);
    }
    
}
