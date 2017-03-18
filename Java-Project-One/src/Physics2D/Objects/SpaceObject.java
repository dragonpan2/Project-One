/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Objects;

import Physics2D.Vector2;
import World2D.Objects.Circle;
import World2D.Objects.DisplayObject;
import java.awt.Color;

/**
 *
 * @author bowen
 */
public class SpaceObject extends PointBody {
    
    public Circle displayComponent;
    
    public SpaceObject() {
    }

    public SpaceObject(double mass) {
        super(mass);
        displayComponent = new Circle((int)Math.log10(mass)+1);
        update();
    }

    public SpaceObject(Vector2 position, double mass) {
        super(position, mass);
        displayComponent = new Circle((int)Math.log10(mass)+1);
        update();
    }

    public SpaceObject(Vector2 position, Vector2 velocity, double mass) {
        super(position, velocity, mass);
        displayComponent = new Circle((int)Math.log10(mass)+1);
        update();
    }
    @Override
    public final void update() {
        displayComponent.updateCoordinates(position().get(0), position().get(1), velocity().get(0), velocity().get(1));
    }
    public void setColour(Color color) {
        displayComponent.setColor(color);
    }
    @Override
    public DisplayObject getDisplayObject() {
        return displayComponent;
    }
    @Override
    public SpaceObject clone() {
        SpaceObject newTestObject = new SpaceObject(this.position(), this.velocity(), this.mass());
        return newTestObject;
    }
}
