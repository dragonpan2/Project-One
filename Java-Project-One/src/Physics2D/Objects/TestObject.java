/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Objects;

import MainPackages.TestBall;
import Physics2D.Vector2;
import java.awt.Color;

/**
 *
 * @author bowen
 */
public class TestObject extends PointBody {
    
    public TestBall displayComponent;
    
    public TestObject() {
    }

    public TestObject(double mass) {
        super(mass);
        displayComponent = new TestBall();
        update();
    }

    public TestObject(Vector2 position, double mass) {
        super(position, mass);
        displayComponent = new TestBall();
        update();
    }

    public TestObject(Vector2 position, Vector2 velocity, double mass) {
        super(position, velocity, mass);
        displayComponent = new TestBall();
        update();
    }
    @Override
    public void update() {
        displayComponent.setPos(position().get(0), position().get(1));
    }
    public void setColour(Color color) {
        displayComponent.setColor(color);
    }
    public TestObject clone() {
        TestObject newTestObject = new TestObject(this.position(), this.velocity(), this.mass());
        return newTestObject;
    }
}
