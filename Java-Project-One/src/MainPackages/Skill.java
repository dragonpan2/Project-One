/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

/**
 *
 * @author Lin-Li
 */
public abstract class Skill {
    
    private String name;
    private String disc;
    private int level;
    private int requLevelUp;
    private int exp;
    private enum Type {
        GATHER,REFINE
    }
    private Type type;

    public Skill() {
        this.name = name;
        this.disc = disc;
        this.level = 0;
        this.exp = 0;
        this.requLevelUp = 1;
        this.type = type;
        
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public void skillUsed(int expToAdd) {
        
        if (exp+expToAdd >= requLevelUp) {
            level++;
            exp = exp+expToAdd-requLevelUp;
            //
        }
        else {
            exp= exp+expToAdd;
        }
    }
    
    
    
    
    
}
