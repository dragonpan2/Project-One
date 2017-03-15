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
    private double momentumSum;
    
    public SpaceIntegrator(Body... objects) {
        this.objects = objects;
        momentumSum = getMomentumSum(objects);
    }
    
    public static Vector2 getForce(Body other, Body target) {
        Vector2 forceVector = Vectors2.sub(target.position(), other.position());
        double normSquared = forceVector.normSquared();
        forceVector.setNorm(1);
        double scalar = (-G * other.mass() * target.mass())/normSquared;
        forceVector.prod(scalar);
        return forceVector;
    }
    
    private static double getMomentumSum(Body[] objects) {
        double sum = 0;
        for (int i=0; i<objects.length; i++) {
            sum += objects[i].momentum();
        }
        return sum;
    }
    
    private double getMomentumSum() {
        return getMomentumSum(objects);
    }
    public void update(double time) {
        update(time, 1E20);
    }
    
    public void update(double time, double precision) {
        double remainingTime = time;
        while (remainingTime > 0) {
            for (int i=0; i<objects.length; i++) {
                Vector2[] forceVectors = new Vector2[objects.length];
                for (int n=0; n<objects.length; n++) {
                    if (i != n) {
                        forceVectors[n] = getForce(objects[n], objects[i]);
                    } else {
                        forceVectors[n] = new Vector2(0);
                    }
                }
                Vector2 sumForces = Vectors2.add(forceVectors);
                objects[i].setForce(sumForces);
            }

            for (int i=0; i<objects.length; i++) {
                objects[i].update(remainingTime);
            }

            double newMomentum = getMomentumSum();
            if (Math.abs(momentumSum - newMomentum) > precision) {
                
                if (time == remainingTime) System.out.print("|Oops, timeStep too big, using Ministeps!|");
                
                double newTime = remainingTime;
                while (Math.abs(momentumSum - newMomentum) > precision) {
                    //System.out.println(Math.abs(momentumSum - newMomentum));
                    for (int i=0; i<objects.length; i++) {
                        objects[i].revert();
                    }
                    newTime /= 2;
                    //System.out.println(newTime);
                    for (int i=0; i<objects.length; i++) {
                        objects[i].update(newTime);
                    }
                    newMomentum = getMomentumSum();
                }
                remainingTime -= newTime;
                System.out.print("|Ministep|");
                //System.out.print(newTime + "|");
                //System.out.println("|Ministep, remaining time: " + remainingTime + "|");
            } else {
                remainingTime = 0;
                System.out.println("|Full Step|");
            }
            momentumSum = newMomentum;
        }
    }
}
