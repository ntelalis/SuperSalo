package engine.entity.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Desktop
 */
public abstract class MovementComponent extends Component{

    protected float velocity,acceleration;

    public float getVelocity(){
        return velocity;
    }
    
    public float getAcceleration(){
        return acceleration;
    }
    
    public void setVelocity(float velocity){
        this.velocity = velocity;
    }
    
    public void setAcceleration(float acceleration){
        this.acceleration = acceleration;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        
    }
}
