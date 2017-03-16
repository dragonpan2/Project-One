/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Objects;

import MathExt.Algebra.Tensor;
import MathExt.Algebra.Tensors;
import MathExt.Algebra.Vectors;
import Physics2D.Vector2;
import Physics2D.Vectors2;

/**
 *
 * @author bowen
 */
public class PointBody implements LinearMotion {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 force;
    final private double mass;
    
    private Tensor lastTime; //backup of position, velocity and acceleration in case of physics error
    
    public PointBody() {
        position = new Vector2(0);
        velocity = new Vector2(0);
        acceleration = new Vector2(0);
        force = new Vector2(0);
        mass = 1;
        
        lastTime = Tensors.join(this.position, this.velocity, this.acceleration);
    }
    public PointBody(double mass) {
        position = new Vector2(0);
        velocity = new Vector2(0);
        acceleration = new Vector2(0);
        force = new Vector2(0);
        this.mass = mass;
        
        lastTime = Tensors.join(this.position, this.velocity, this.acceleration);
    }
    public PointBody(Vector2 position, double mass) {
        this.position = position.clone();
        velocity = new Vector2(0);
        acceleration = new Vector2(0);
        force = new Vector2(0);
        this.mass = mass;
        
        lastTime = Tensors.join(this.position, this.velocity, this.acceleration);
    }
    public PointBody(Vector2 position, Vector2 velocity, double mass) {
        this.position = position.clone();
        this.velocity = velocity.clone();
        acceleration = new Vector2(0);
        force = new Vector2(0);
        this.mass = mass;
        
        lastTime = Tensors.join(this.position, this.velocity, this.acceleration);
    }
    
    @Override
    public void update(double time) {
        semiImplicitEuler(time);
    }
    private void semiImplicitEuler(double time) {
        lastTime = Tensors.join(this.position, this.velocity, this.acceleration);
        
        Vector2 newAcceleration = force.clone();
        newAcceleration.div(mass);
        acceleration.set(newAcceleration);
        
        Vector2 changeVelocity = acceleration.clone();
        changeVelocity.prod(time);
        velocity.add(changeVelocity);
        
        Vector2 changePosition = velocity.clone();
        changePosition.prod(time);
        position.add(changePosition);
    }
    private void explicitEuler(double time) {
        lastTime = Tensors.join(this.position, this.velocity, this.acceleration);
        
        Vector2 newAcceleration = force.clone();
        newAcceleration.div(mass);
        acceleration.set(newAcceleration);
        
        Vector2 changeVelocity = acceleration.clone();
        Vector2 changePosition = velocity.clone();
        changeVelocity.prod(time);
        changePosition.prod(time);
        
        velocity.add(changeVelocity);
        position.add(changePosition);
    }
    private void leapFrog(double time) {
        lastTime = Tensors.join(this.position, this.velocity, this.acceleration);
        
        Vector2 newAcceleration = force.clone();
        newAcceleration.div(mass);
        acceleration.set(newAcceleration);
        
        
        Vector2 velocity12 = Vectors2.add(velocity, Vectors2.prod(acceleration, time/2));
        
        position.add(Vectors.prod(velocity12, time));
        //velocity = Vectors2.add(velocity12, )
        
        
        Vector2 changeVelocity = acceleration.clone();
        Vector2 changePosition = velocity.clone();
        changeVelocity.prod(time);
        changePosition.prod(time);
        
        velocity.add(changeVelocity);
        position.add(changePosition);
    }
    @Override
    public void revert() {
        Tensor[] backup = Tensors.split(lastTime);
        Vector2 lastPosition = Vectors2.tensorToVector2(backup[0], position.isCartesian());
        Vector2 lastVelocity = Vectors2.tensorToVector2(backup[1], velocity.isCartesian());
        Vector2 lastAcceleration = Vectors2.tensorToVector2(backup[2], acceleration.isCartesian());
        position.set(lastPosition);
        velocity.set(lastVelocity);
        acceleration.set(lastAcceleration);
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
    public double momentum(int i) {
        return velocity(i) * mass;
    }
    
    public double futureMomentum(int i, double time) {
        return (velocity(i)+acceleration(i)*time) * mass;
    }
    public double speed() {
        return velocity.norm();
    }
    
    public double kineticEnergy() {
        double speed = velocity.norm();
        return 0.5*mass*speed*speed;
    }

    @Override
    public double mass() {
        return mass;
    }

    @Override
    public double force(int i) {
        return force.get(i);
    }
    @Override
    public void setForce(Vector2 vector2) {
        force.set(vector2);
    }

    
}
