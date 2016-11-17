package game.states;

import engine.manager.EventManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class LevelClearState extends BasicGameState {

    private State state;
    
    public LevelClearState(State state) {
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

    }

    
}