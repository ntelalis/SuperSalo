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
        ent = new Entity("greenbox");
        p1 = new Entity("p1");
        p2 = new Entity("p2");
        p3 = new Entity("p3");
        patwma = new Entity("patwm");
        gooby = new Entity("gooby");
        camera = new Camera(ent);
        
        ent.setPosition(new Vector2f(200,250));
        p1.setPosition(new Vector2f(100,360));
        p2.setPosition(new Vector2f(730,80));
        p3.setPosition(new Vector2f(50,250));
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
        
        Entity[] enta = new Entity[5];
        enta[0] = ent;
        enta[1] = p1;
        enta[2] = p2;
        enta[3] = patwma;
        enta[4] = p3;
        CollisionManager.getInstance().loadEntities(enta);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        ent.render(gc, sbg, grphcs);
        p1.render(gc, sbg, grphcs);
        p2.render(gc, sbg, grphcs);
        p3.render(gc, sbg, grphcs);
        patwma.render(gc, sbg, grphcs);
        gooby.render(gc, sbg, grphcs);
        //grphcs.drawRect(Camera.getCamera().x,Camera.getCamera().y,Camera.getWidth(),Camera.getHeight());
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        ent.update(gc, sbg, delta);
        camera.update(gc, sbg, delta);
        p1.update(gc, sbg, delta);
        p2.update(gc, sbg, delta);
        p3.update(gc, sbg, delta);
        patwma.update(gc, sbg, delta);
        
        CollisionManager.getInstance().update();
        if(EventManager.getInstance().update()){
             //gooby.AddComponent(new ImageComponent("img", new Image("data/sssss.png")));
             //((ImageComponent)gooby.getComponent("img")).setFS();
        }
    }

}
