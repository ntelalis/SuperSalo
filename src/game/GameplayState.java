package game;

import engine.entity.Entity;
import engine.manager.CollisionManager;
import engine.manager.EntityManager;
import engine.manager.EventManager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class GameplayState extends BasicGameState {
    
    private int id;
    
    private Entity ent,p1,p2,p3;
    private Entity patwma,gooby;
    private Camera camera;
    
    
    public GameplayState(int GAMESTATE) {
        super();
        id = GAMESTATE;
    }
    
    @Override
    public int getID() {
        return id;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        try {
            EntityManager.getInstance().loadEntities(LevelLoader.load(new FileReader("src/data/level1.lvl")));
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
        
        
        EntityManager.getInstance().update(gc,sbg,delta);
        CollisionManager.getInstance().update();
        EventManager.getInstance().update();
        
        camera.update(gc, sbg, delta);
    }
    
}
