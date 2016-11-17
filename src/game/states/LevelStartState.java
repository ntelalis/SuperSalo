/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

/**
 *
 * @author ntelalis
 */
public class LevelStartState extends BasicGameState{
    
    private State state;
    private long milliseconds = 0;
    
    public LevelStartState(State state) {
        this.state = state;
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        milliseconds+=i;
        if(milliseconds>2000){
            milliseconds=0;
            sbg.enterState(State.gameplay.ordinal(),new EmptyTransition(), new VerticalSplitTransition());
        }
        
    }
    
}
