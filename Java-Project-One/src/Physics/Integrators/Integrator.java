/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics.Integrators;

import Physics2D.Objects.PointBody;
import Physics2D.Vector2;

/**
 *
 * @author bowen
 */
public interface Integrator {
    public enum IntegratorType {
        NOFORCE, EXPLICITEULER, EXPLICITMIDPOINT, LEAPFROG, RK4, 
        SYMPLECTIC1, SYMPLECTIC2, SYMPLECTIC3, SYMPLECTIC4
    }
    public void apply(PointBody[] bodies, double dt);
    public Vector2[] getCurrentAccelerations();
    public PointBody[] get(PointBody[] bodies, double dt, int steps);
    public Vector2[][] getFuture(PointBody[] bodies, double dt, int steps);
    public IntegratorType type();
}
