package engine.entity.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class TopDownMovementComponent extends MovementComponent {
    
    private boolean gravity;
    private float maxspeed;
    private float gravitation=10f;
    
    public TopDownMovementComponent(String id, float maxspeed, float acceleration){
        this.id = id;
        this.acceleration = acceleration;
        this.gravity = false;
        this.maxspeed = maxspeed;
    }

    public void setMaxSpeed(float maxspeed) {
        this.maxspeed = maxspeed;
    }

    public float getMaxSpeed() {
        return maxspeed;
    }
    
    public boolean getGravity(){
        return gravity;
    }
    
    public void setGravity(boolean gravity){
        this.gravity = gravity;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        
        Input input = gc.getInput();
        Vector2f pos = owner.getPosition();
        
        if(!gravity)
            ((SideMovementComponent)owner.getComponent("movs")).setFriction(0.1f);
        else
            ((SideMovementComponent)owner.getComponent("movs")).setFriction(0.99f);
        
        pos.y+=(gravitation-velocity)*delta/200f;
        velocity-=gravitation*delta/20f;
        
        owner.setPosition(pos);
    }
}
