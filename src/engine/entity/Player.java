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
public class Player extends LivingEntity{
    
    private static final int startingHP = 5;
    private static final int startingLives = 3;
    
    private int lives;
    private Weapon weapon;
    private boolean invincible;
    
    
    public Player(String id) {
        super(id,startingHP);
        this.lives = startingLives;
        this.invincible = false;
    }
    
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void incLives(){
    this.lives++;
    }
    
    public void decLives(){
        this.lives--;
    }
    
    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invicibility) {
        this.invincible = invicibility;
    }   
    
}
