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
        
        if(input.isKeyPressed(Input.KEY_SPACE) && gravity==true){
            velocity=maxspeed;
            gravity=false;
        }
        pos.y+=(gravitation-velocity)*delta/200f;
        velocity-=gravitation*delta/20f;
        
        owner.setPosition(pos);
    }
}
