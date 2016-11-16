package engine.entity.component;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class SideMovementComponent extends MovementComponent {
    
    private float maxspeed;
    private float maxacceleration;
    private float friction,f;
    
    public SideMovementComponent(String id, float maxspeed, float maxacceleration){
        this.id = id;
        this.maxspeed = maxspeed;
        this.maxacceleration = maxacceleration;
        this.friction = 1;
    }
    
    
    public float getMaxSpeed(){
        return maxspeed;
    }
    
    public float getMaxAcceleration(){
        return maxacceleration;
    }
    
    public float getFriction(){
        return friction;
    }
    
    public void setMaxSpeed(float maxspeed){
        this.maxspeed = maxspeed;
    }
    
    public void setFriction(float friction){
        this.friction = friction;
    }
    
    public void setMaxAcceleration(){
        this.maxacceleration = acceleration;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        
        if(Math.abs(velocity)>maxspeed)
            if(velocity>0)
                setVelocity(maxspeed);
            else
                setVelocity(-maxspeed);
        
        velocity += acceleration;
        f=friction*velocity;
        velocity-=f*delta*0.01f;
        Vector2f pos = owner.getPosition();
        pos.x += velocity*delta*0.01f;
        owner.setPosition(pos);
        
    }
    
}
