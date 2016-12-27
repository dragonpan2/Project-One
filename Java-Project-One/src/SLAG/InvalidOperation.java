/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLAG;

/**
 *
 * @author bowen
 */
public class InvalidOperation extends Exception {
    
    public InvalidOperation(String message){
       super("SLAG Invalid Operation, " + message);
    }

    
}
