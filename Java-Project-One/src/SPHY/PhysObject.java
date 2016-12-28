/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPHY;

/**
 *
 * @author bowen
 */
public class PhysObject {
    
    private Vector2 position, velocity, momentum, acceleration;
    private VectorR rotation, angspeed, angaccel;
    private Vector2[] forces;
    private double[] torques;
    
    private double mass;
    private double moment;
    
    private boolean immovable; //can give out infinite normal force
    
    
    public PhysObject() {
        
    }
}
