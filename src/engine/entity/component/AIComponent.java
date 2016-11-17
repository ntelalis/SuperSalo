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
    
    public AIComponent(String id, String enemyType){
        this.id = id;
        this.enemyType = enemyType;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        
    }
    
}