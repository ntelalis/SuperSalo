package game.states;

import engine.entity.Entity;
import engine.manager.CollisionManager;
import engine.manager.EntityManager;
import engine.manager.EventManager;
import game.Camera;
import game.LevelLoader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

public class GameplayState extends BasicGameState {
    
    private State state;
    private int level;
    private Camera camera;
    private Input input;
    Sound bgsound;
    private int lives;
    
    public GameplayState(State state) {
        super();
        this.state = state;
        this.lives = 2;
        this.level = 1;
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        EventManager.getInstance().resetEvents();
        CollisionManager.getInstance().removeEntities();
        EntityManager.getInstance().removeEntities();
        
        try {
            EntityManager.getInstance().loadEntities(LevelLoader.load(new FileReader("src/data/level"+String.valueOf(level)+".lvl")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameplayState.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException | IOException ex) {
            Logger.getLogger(GameplayState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        camera = new Camera(EntityManager.getInstance().getEntity("greenbox"));
        bgsound = new Sound("data/sounds/BGLevel"+String.valueOf(level)+".ogg");
        
    }
    
    private void resetLevels(){
        level=0;
    }
    
    private void nextLevel(){
        level++;
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        EntityManager.getInstance().render(gc,sbg,grphcs);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        if(!bgsound.playing()){
            bgsound.play(1f,0.7f);
        }
        
        if(EventManager.getInstance().playerKilled()){
            lives--;
            bgsound.stop();
            init(gc,sbg);
            sbg.enterState(State.levelStart.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
        if(lives==0){
            lives = 2;
            bgsound.stop();
            init(gc,sbg);
            sbg.enterState(State.gameOver.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
        if(EventManager.getInstance().levelCleared()){
            sbg.enterState(State.levelClear.ordinal(),new EmptyTransition(), new FadeInTransition());
        }
        
        EntityManager.getInstance().update(gc,sbg,delta);
        CollisionManager.getInstance().update();
        
        camera.update(gc, sbg, delta);
        input = gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(State.pause.ordinal(),new EmptyTransition(), new FadeInTransition());
        }
    }
    
}
