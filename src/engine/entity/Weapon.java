package engine.entity;

public class Weapon extends Entity{
    
    private int damage;
    
    public Weapon(String id,int damage) {
        super(id);
        this.damage = damage;
    }
    
    public int getDamage(){
        return damage;
    }
    
}
