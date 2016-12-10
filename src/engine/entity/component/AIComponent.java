/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.entity.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ntelalis
 */
public class AIComponent extends Component{

    private String enemyType;
    private long milliseconds;
    private boolean flag = false;
    private boolean fflag = true;
    private int timer;
    public AIComponent(String id, String enemyType){
        this.id = id;
        this.enemyType = enemyType;
        switch(enemyType){
            case "simple":
                timer = 1000;
                break;
            case "advanced":
                timer = 2000;
                break;
            case "complex":
                timer = 3000;
                break;
            default:
                timer = 5000;
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        milliseconds+=delta;
        if(fflag){
            moveRight();
            fflag=false;
        }
        if(milliseconds>timer){
            milliseconds=0;
            toggleMove();
        }
    }
    
    private void toggleMove(){
        if(flag){
            moveRight();
            flag=false;
        }
        else{
            moveLeft();
            flag=true;
        }
    }
    
    private void moveLeft(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(-((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getRightAnimation().start();
    }
    
    private void moveRight(){
        ((SideMovementComponent)owner.getComponent("movs")).setAcceleration(((SideMovementComponent)owner.getComponent("movs")).getMaxAcceleration());
        ((AnimationComponent)owner.getComponent("img")).getLeftAnimation().start();
    }
    
}
