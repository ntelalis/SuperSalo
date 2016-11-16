/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package engine.entity.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ntelalis
 */
public class ControlComponent extends Component{
    
    private Input input;
    
    public ControlComponent(String id){
        this.id = id;
    }
    
    public void moveStop(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(0f);
        ((AnimationComponent)owner.getComponent("img")).stopAnimation();
    }
    
    public void moveLeft(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(-((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getLeftAnimation().start();
    }
    
    public void moveFastLeft(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(-5*((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getLeftAnimation().start();
    }
    
    public void moveRight(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getRightAnimation().start();
    }
    
    public void moveFastRight(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(5*((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getRightAnimation().start();
    }
    
    public void jump(){
        if(((TopDownMovementComponent)owner.getComponent("movt")).getGravity()){
            ((TopDownMovementComponent)owner.getComponent("movt")).setVelocity(((TopDownMovementComponent)owner.getComponent("movt")).getMaxSpeed());
            ((TopDownMovementComponent)owner.getComponent("movt")).setGravity(false);
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        
        input = gc.getInput();
        
        if(input.isKeyDown(Input.KEY_D) && input.isKeyDown(Input.KEY_A))
            moveStop();
        else if(input.isKeyDown(Input.KEY_D))
            if(input.isKeyDown(Input.KEY_LSHIFT))
                moveFastRight();
            else
                moveRight();
        else if(input.isKeyDown(Input.KEY_A))
            if(input.isKeyDown(Input.KEY_LSHIFT))
                moveFastLeft();
            else
                moveLeft();
        else
            moveStop();
        
        if(input.isKeyPressed(Input.KEY_SPACE))
            jump();
        
    }
    
}
