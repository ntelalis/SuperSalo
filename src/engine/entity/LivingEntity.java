/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.entity;

/**
 *
 * @author ntelalis
 */
public class LivingEntity extends Entity{
    
    private int curHP,maxHP;
    
    public LivingEntity(String id, int HP) {
        super(id);
        this.maxHP = HP;
        this.curHP = HP;
    }
    
    public int getCurHP() {
        return curHP;
    }

    public void setCurHP(int curHP) {
        this.curHP = curHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    
}