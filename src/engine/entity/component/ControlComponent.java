/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package engine.entity.component;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ntelalis
 */
public class ControlComponent extends Component{
    
    private Input input;
    private Sound jmp;
    
    public ControlComponent(String id){
        this.id = id;
        try {
            jmp = new Sound("data/sounds/jump.ogg");
        } catch (SlickException ex) {
            Logger.getLogger(ControlComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(-3*((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getLeftAnimation().start();
    }
    
    public void moveRight(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getRightAnimation().start();
    }
    
    public void moveFastRight(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(3*((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getRightAnimation().start();
    }
    
    public void jump(){
        if(((TopDownMovementComponent)owner.getComponent("movt")).getGravity()){
            ((TopDownMovementComponent)owner.getComponent("movt")).setVelocity(((TopDownMovementComponent)owner.getComponent("movt")).getMaxSpeed());
            ((TopDownMovementComponent)owner.getComponent("movt")).setGravity(false);
            jmp.play(0.9f,0.9f );
        }
        
    }
    
    public void forceJump(){
            ((TopDownMovementComponent)owner.getComponent("movt")).setVelocity(((TopDownMovementComponent)owner.getComponent("movt")).getMaxSpeed());
            ((TopDownMovementComponent)owner.getComponent("movt")).setGravity(false);
            jmp.play(0.9f,0.9f );
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
