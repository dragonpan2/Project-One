/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

import Physics2D.Objects.Body;

/**
 *
 * @author bowen
 */
public class SpaceIntegrator {
    final public static double G = 6.67408E-11; //m^3 * kg^-1 * s^-2
    private Body[] objects;
    public SpaceIntegrator(Body... objects) {
        this.objects = objects;
    }
    
    public static Vector2 getForce(Body other, Body target) {
        Vector2 forceVector = Vectors2.sub(other.position(), target.position());
        double normSquared = forceVector.normSquared();
        forceVector.setNorm(1);
        double scalar = (-G * other.mass() * target.mass())/normSquared;
        forceVector.prod(scalar);
        return forceVector;
    }
    
    public void update(double time) {
        for (int i=0; i<objects.length; i++) {
            Vector2[] forceVectors = new Vector2[objects.length];
            for (int n=0; n<objects.length; i++) {
                if (i != n) {
                    forceVectors[n] = getForce(objects[n], objects[i]);
                } else {
                    forceVectors[n] = new Vector2(0);
                }
            }
            objects[i].setForce(Vectors2.add(forceVectors));
        }
        for (int i=0; i<objects.length; i++) {
            objects[i].update(time);
        }
    }
}
