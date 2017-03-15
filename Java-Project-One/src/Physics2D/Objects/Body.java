/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Objects;

import Physics2D.Vector2;

/**
 *
 * @author bowen
 */
public class Body implements LinearMotion {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 force;
    final private double mass;
    
    public Body() {
        position = new Vector2(0);
        velocity = new Vector2(0);
        acceleration = new Vector2(0);
        force = new Vector2(0);
        mass = 1;
    }
    public Body(double mass) {
        position = new Vector2(0);
        velocity = new Vector2(0);
        acceleration = new Vector2(0);
        force = new Vector2(0);
        this.mass = mass;
    }
    public Body(Vector2 position, double mass) {
        this.position = position.clone();
        velocity = new Vector2(0);
        acceleration = new Vector2(0);
        force = new Vector2(0);
        this.mass = mass;
    }
    public Body(Vector2 position, Vector2 velocity, double mass) {
        this.position = position.clone();
        this.velocity = velocity.clone();
        acceleration = new Vector2(0);
        force = new Vector2(0);
        this.mass = mass;
    }
    
    public void update(double time) {
        Vector2 newAcceleration = force.clone();
        newAcceleration.div(mass);
        
        Vector2 changeVelocity = acceleration.clone();
        changeVelocity.prod(time);
        
        Vector2 changePosition = velocity.clone();
        changePosition.prod(time);
        
        acceleration.set(newAcceleration);
        velocity.add(changeVelocity);
        position.add(changePosition);
    }
    
    @Override
    public double position(int i) {
        return position.get(i);
    }
    public Vector2 position() {
        return position;
    }

    @Override
    public double velocity(int i) {
        return velocity.get(i);
    }

    @Override
    public double acceleration(int i) {
        return acceleration.get(i);
    }

    @Override
    public double momentum() {
        return velocity.norm() * mass;
    }

    @Override
    public double mass() {
        return mass;
    }

    @Override
    public double force(int i) {
        return force.get(i);
    }
    public void setForce(Vector2 vector2) {
        force.set(vector2);
    }

    
}
