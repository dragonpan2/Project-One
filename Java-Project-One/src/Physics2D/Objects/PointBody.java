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
    final private double mass;
    
    public PointBody() {
        position = new Vector2(0);
        velocity = new Vector2(0);
        mass = 1;
        
    }
    public PointBody(double mass) {
        position = new Vector2(0);
        velocity = new Vector2(0);
        this.mass = mass;
        
    }
    public PointBody(Vector2 position, double mass) {
        this.position = position.clone();
        velocity = new Vector2(0);
        this.mass = mass;
        
    }
    public PointBody(Vector2 position, Vector2 velocity, double mass) {
        this.position = position.clone();
        this.velocity = velocity.clone();
        this.mass = mass;
        
    }
    /*
    @Override
    public void update(double time) {
        semiImplicitEuler(time);
    }
    private void semiImplicitEuler(double time) {
        
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
        
        Vector2 newAcceleration = force.clone();
        newAcceleration.div(mass);
        acceleration.set(newAcceleration);
        
        
        Vector2 velocity12 = Vectors2.add(velocity, Vectors2.prod(acceleration, time/2));
        
        position.add(Vectors.prod(velocity12, time));
        //velocity = Vectors2.add(velocity12, )
        
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
    }*/
    
    @Override
    public Vector2 position() {
        return position;
    }

    @Override
    public Vector2 velocity() {
        return velocity;
    }


    @Override
    public double momentum(int i) {
        return velocity.get(i) * mass;
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
    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    @Override
    public void addPosition(Vector2 position) {
        this.position.add(position);
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    
    @Override
    public void addVelocity(Vector2 velocity) {
        this.velocity.add(velocity);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("No graphic component to update."); //To change body of generated methods, choose Tools | Templates.
    }
}
