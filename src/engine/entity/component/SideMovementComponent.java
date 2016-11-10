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
        this.friction = 0.97f;
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
        
        Input input = gc.getInput();  
        
        if(Math.abs(velocity)>maxspeed)
            if(velocity>0)
                velocity=maxspeed;
            else
                velocity=-maxspeed;
        
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_A)){
            acceleration=0;
        }
        else if(input.isKeyDown(Input.KEY_D)){
            acceleration=maxacceleration;
            ((AnimationComponent)owner.getComponent("img")).getRightAnimation().start();
        }
        else if(input.isKeyDown(Input.KEY_A)){
            acceleration=-maxacceleration;
            ((AnimationComponent)owner.getComponent("img")).getLeftAnimation().start();
        }
        else{
            acceleration=0f;
            ((AnimationComponent)owner.getComponent("img")).stopAnimation();
        }
        
        velocity += acceleration;
        f=friction*velocity;
        velocity-=f*delta*0.01f;
        Vector2f pos = owner.getPosition();
        pos.x += velocity*delta*0.01f;
        owner.setPosition(pos);
        
    }
    
}
