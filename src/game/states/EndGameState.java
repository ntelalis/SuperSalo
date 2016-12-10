/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;




public class EndGameState extends BasicGameState{
    
    private State state;
    private long milliseconds;
    private Sound snd;
    
    public EndGameState(State state) {
        this.state = state;
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        snd = new Sound("data/sounds/gameEnd.ogg");
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        if(!snd.playing()){
            snd.play();
        }
        
        milliseconds+=i;
        
        if(milliseconds>20000){
            milliseconds=0;
            init(gc,sbg);
            sbg.enterState(State.splashScreen.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
    }
    
}
