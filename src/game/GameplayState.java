package game;

import engine.entity.Entity;
import engine.entity.component.AnimationComponent;
import engine.entity.component.CollisionComponent;
import engine.entity.component.ImageComponent;
import engine.entity.component.SideMovementComponent;
import engine.entity.component.TopDownMovementComponent;
import engine.manager.CollisionManager;
import engine.manager.EventManager;
import game.Camera;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class GameplayState extends BasicGameState {

    private int id;

    private Entity ent,p1,p2,p3;
    private Entity patwma,gooby;
    private Camera camera;

    private ArrayList<Entity> entityArray;
    
    public GameplayState(int GAMESTATE) {
        super();
        id = GAMESTATE;
        entityArray = new ArrayList();
    }

    @Override
    public int getID() {
        return id;
    }
    
    public void loadEntities(ArrayList<Entity> entities){
        this.entityArray = entities;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        try {
            loadEntities(LevelLoader.load(new FileReader("src/data/level1.lvl")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameplayState.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GameplayState.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameplayState.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*ent = new Entity("greenbox");
        p1 = new Entity("p1");
        p2 = new Entity("p2");
        p3 = new Entity("p3");
        patwma = new Entity("patwm");
        gooby = new Entity("gooby");
        */camera = new Camera(entityArray.get(3));/*
        
        entityArray.add(ent);
        entityArray.add(p1);
        entityArray.add(p2);
        entityArray.add(p3);
        entityArray.add(patwma);
        entityArray.add(gooby);
        //entityArray.add(camera);
        
        ent.setPosition(new Vector2f(100,250));
        p1.setPosition(new Vector2f(200,360));
        p2.setPosition(new Vector2f(730,80));
        p3.setPosition(new Vector2f(450,250));
        patwma.setPosition(new Vector2f(50,540));
        gooby.setPosition(new Vector2f(0,0));
        ent.AddComponent(new AnimationComponent("img", new Image("data/test2.png")));
        p1.AddComponent(new ImageComponent("img",new Image("data/p1.png")));
        p2.AddComponent(new ImageComponent("img", new Image("data/gooby.png")));
        p3.AddComponent(new ImageComponent("img", new Image("data/p2.png")));
        patwma.AddComponent(new ImageComponent("img", new Image("data/patwma.png")));
        
        ent.AddComponent(new SideMovementComponent("movs",20f,5f));
        ent.AddComponent(new TopDownMovementComponent("movt",200f,12f));
        
        ent.AddComponent(new CollisionComponent("col"));
        */
        ArrayList<Entity> arent = new ArrayList();
        for(Entity ent:entityArray){
            if(ent.getComponent("col")!=null || ent.getComponent("scol")!=null)
                arent.add(ent);
        }
        CollisionManager.getInstance().loadEntities(arent);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        for(Entity entity : entityArray){
            entity.render(gc, sbg, grphcs);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
        for(Entity entity : entityArray){
            entity.update(gc, sbg, delta);
        }
        
        camera.update(gc, sbg, delta);
        
        CollisionManager.getInstance().update();
        if(EventManager.getInstance().update()){
             gooby.AddComponent(new ImageComponent("img", new Image("data/sssss.png")));
             ((ImageComponent)gooby.getComponent("img")).setFS();
        }
    }

}
