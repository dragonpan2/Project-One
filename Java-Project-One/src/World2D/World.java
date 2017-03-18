/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D;

import World2D.Objects.DisplayObject;

/**
 *
 * @author bowen
 */
public interface World {
    public DisplayObject[] getDisplayObjects();
    public long getTicks();
    public double getSpeed();
    public void speedUp();
    public void speedDown();
}
