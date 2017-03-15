/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D.Objects;

/**
 *
 * @author bowen
 */
public interface LinearMotion {
    double position(int i); //m
    double velocity(int i); //m/s
    double acceleration(int i); //m/s^2
    double force(int i); //N
    
    double momentum(); //kg*m/s
    double mass(); //kg
    
}
