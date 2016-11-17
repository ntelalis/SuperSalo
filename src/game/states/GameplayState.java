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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

public class GameplayState extends BasicGameState {
    
    private int id;
    private int level;
    private Camera camera;
    private Input input;
    
    public GameplayState(int GAMESTATE) {
        super();
        id = GAMESTATE;
        level=0;
    }
    
    @Override
    public int getID() {
        return id;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        level++;
        
        try {
            EntityManager.getInstance().loadEntities(LevelLoader.load(new FileReader("src/data/level"+String.valueOf(level)+".lvl")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameplayState.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException | IOException ex) {
            Logger.getLogger(GameplayState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        camera = new Camera(EntityManager.getInstance().getEntity("greenbox"));
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        EntityManager.getInstance().render(gc,sbg,grphcs);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        
        if(EventManager.getInstance().playerKilled()){
            
        }
        if(EventManager.getInstance().levelCleared()){
            
        }
        
        EntityManager.getInstance().update(gc,sbg,delta);
        CollisionManager.getInstance().update();
        
        camera.update(gc, sbg, delta);
        input = gc.getInput();  
        
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
          sbg.enterState(4,new EmptyTransition(), new FadeInTransition());
        }
    }
    
}
