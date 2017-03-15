/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

/**
 *
 * @author bowen
 */
public class Vectors2 {
    
    public static Vector2 proj(Vector2... vector2Array) {
        Vector2 newVector2 = vector2Array[0].clone();
        for (int n=1; n<vector2Array.length; n++) {
            newVector2.proj(vector2Array[n]);
        }
        return newVector2;
    }
    public static Vector2 rej(Vector2... vector2Array) {
        Vector2 newVector2 = vector2Array[0].clone();
        for (int n=1; n<vector2Array.length; n++) {
            newVector2.rej(vector2Array[n]);
        }
        return newVector2;
    }
}
