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




public class GameOverState extends BasicGameState{
    
    private State state;
    private Sound sound;
    private boolean played;
    private long milliseconds = 0;
    
    public GameOverState(State state) throws SlickException {
        this.state = state;
        sound = new Sound("data/sounds/gameover.ogg");
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        played = false;
        this.leave(gc, sbg);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        milliseconds+=i;
        if(milliseconds>300){
            milliseconds=0;
            if(!played){
                played = true;
                sound.play(1f,0.7f);
            }
        }
        if(played && !sound.playing()){
            init(gc,sbg);
            sbg.enterState(State.splashScreen.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
    }
    
}
