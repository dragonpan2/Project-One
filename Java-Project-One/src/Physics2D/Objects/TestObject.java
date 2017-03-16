/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Objects;

import MainPackages.TestBall;
import Physics2D.Vector2;

/**
 *
 * @author bowen
 */
public class TestObject extends PointBody {
    
    public TestBall displayComponent = new TestBall();
    
    public TestObject() {
    }

    public TestObject(double mass) {
        super(mass);
    }

    public TestObject(Vector2 position, double mass) {
        super(position, mass);
    }

    public TestObject(Vector2 position, Vector2 velocity, double mass) {
        super(position, velocity, mass);
    }
    @Override
    public void update(double time) {
        super.update(time);
        displayComponent.setPos(position(0), position(1));
    }
}
