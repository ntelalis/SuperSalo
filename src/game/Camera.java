package game;

import engine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Camera {
    
    private static int height = Game.HEIGHT;
    private static int width = Game.WIDTH;
    private static Rectangle camerabox;
    private static Entity cameraentity;
    
    public Camera(Entity entity){
        cameraentity = entity;
        camerabox = new Rectangle((cameraentity.getShape().getCenterX()-width/2),0,width,height);
    }
    
    public static Vector2f getCamera(){
        return new Vector2f(camerabox.getX(),camerabox.getY());
    }
    
    public static int getHeight(){
        return height;
    }
    
    public static int getWidth(){
        return width;
    }
    
    public static void setCameraEntity(Entity entity){
        cameraentity = entity;
    }
    
    public static Entity getCameraEntity(){
        return cameraentity;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        camerabox = new Rectangle((cameraentity.getShape().getCenterX()-width/2),0,width,height);
    }
}