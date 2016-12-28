/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.util.Arrays;
import SLAG.Matrix;
import SLAG.Vector;
import SPHY.FMath;
import SPHY.Vector2;

/**
 *
 * @author Lin-Li
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window window = new Window();
        window.world.thread.start();
        
        System.out.println("W,A,S,D,UP,DOWN,LEFT,RIGHT to move the vectors");
        System.out.println("E to change Math mode, fastMath is currently WIP");
        
    }
    
}
