package engine.manager;

import engine.entity.Entity;
import engine.entity.component.CollisionComponent;
import engine.entity.component.ImageComponent;
import engine.entity.component.MovementComponent;
import engine.entity.component.TopDownMovementComponent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

public class CollisionManager {
    
    private static final CollisionManager _instance = new CollisionManager();
    private static ArrayList<Entity> entities;
    
    private CollisionManager(){
        entities = new ArrayList();
    }
    
    public static CollisionManager getInstance(){
        return _instance;
    }
    
    public void loadEntities(ArrayList<Entity> entities){
        CollisionManager.entities = entities;
    }
    
    public void addEntity(Entity entity){
        CollisionManager.entities.add(entity);
    }
    
    public void removeEntity(Entity entity){
        CollisionManager.entities.remove(entity);
    }
    
    public void update(){
        checkCollisions();
        for(Entity ent:entities){
            ((CollisionComponent)ent.getComponent("col")).resetAll();
        }
    }
    
    public void removeEntities(){
        CollisionManager.entities = null;
    }
    
    public void checkCollisions(){
        for(int i=0;i<entities.size();i++){
            for(int j=i+1;j<entities.size();j++){
                if(entities.get(i).getShape().intersects(entities.get(j).getShape())){
                    checkSubCollisions(entities.get(i),entities.get(j));
                }
            }
        }
    }
    
    private void checkSubCollisions(Entity entity1, Entity entity2) {
        if(((CollisionComponent)entity1.getComponent("col")).collision(entity2) | ((CollisionComponent)entity2.getComponent("col")).collision(entity1) ){
            collisionResolve(entity1,entity2);
            
        }
    }
    
    private void collisionResolve(Entity ent1, Entity ent2){
        
        String ent1type = ent1.getType();
        String ent2type = ent2.getType();
        
        switch(ent1type){
            case "player":
                switch(ent2type){
                    case "enemy":
                    case "solidenemy":
                        EventManager.getInstance().resolveEvent(ent1, Event.kill);
                        break;
                    case "solid":
                        resolvePlayer(ent1,ent2);
                        if(ent2.getId().contains("block")){
                            resolveCoin(ent2);
                        }
                        break;
                    case "flagpole":
                        EventManager.getInstance().resolveEvent(ent2,Event.win);
                }
                break;
            case "enemy":
                switch(ent2type){
                    case "solid":
                        resolvePlayer(ent1,ent2);
                        break;
                }
        }
        switch(ent2type){
            case "player":
                switch(ent1type){
                    case "enemy":
                    case "solidenemy":
                        EventManager.getInstance().resolveEvent(ent2, Event.kill);
                        break;
                    case "solid":
                        resolvePlayer(ent2,ent1);
                        if(ent1.getId().contains("block")){
                            resolveCoin(ent1);
                            
                        }
                        break;
                    case "flagpole":
                        EventManager.getInstance().resolveEvent(ent1,Event.win);
                }
                break;
            case "enemy":
                switch(ent1type){
                    case "solid":
                        resolvePlayer(ent2,ent1);
                        break;
                }
        }
    }
    
    private void resolvePlayer(Entity player, Entity other){
        
        float xspeed = Math.abs(((MovementComponent)player.getComponent("movs")).getVelocity());
        float yspeed = Math.abs(((MovementComponent)player.getComponent("movt")).getVelocity());
        
        if(((CollisionComponent)player.getComponent("col")).isRight() && ((CollisionComponent)player.getComponent("col")).isLeft()){
            topBot(player,other);
        }
        else if(((CollisionComponent)player.getComponent("col")).isTop() && ((CollisionComponent)player.getComponent("col")).isBot()){
            rightLeft(player,other);
        }
        else if(xspeed>yspeed){
            rightLeft(player,other);
            ((CollisionComponent)player.getComponent("col")).collision(other);
            topBot(player,other);
        }
        else{
            topBot(player,other);
            ((CollisionComponent)player.getComponent("col")).collision(other);
            rightLeft(player,other);
        }
    }
    
    private void topBot(Entity player, Entity other){
        if(((CollisionComponent)player.getComponent("col")).isBot() && ((MovementComponent)player.getComponent("movt")).getVelocity() < 0){
            Vector2f v = other.getStartPosition();
            float y = v.y;
            y -= ((ImageComponent)player.getComponent("img")).getImage().getHeight();
            float x = player.getStartPosition().x;
            player.setStartPosition(new Vector2f(x,y));
            ((MovementComponent)player.getComponent("movt")).setVelocity(0f);
            ((TopDownMovementComponent)player.getComponent("movt")).setGravity(true);
        }
        if(((CollisionComponent)player.getComponent("col")).isTop() && ((MovementComponent)player.getComponent("movt")).getVelocity() > 0){
            Vector2f v = other.getStartPosition();
            float y = v.y+((ImageComponent)other.getComponent("img")).getImage().getHeight();
            float x = player.getStartPosition().x;
            player.setStartPosition(new Vector2f(x,y));
            ((MovementComponent)player.getComponent("movt")).setVelocity(0f);
        }
        
    }
    
    private void rightLeft(Entity player, Entity other){
        if(((CollisionComponent)player.getComponent("col")).isRight()){
            Vector2f v = other.getStartPosition();
            float x = v.x;
            x -= ((ImageComponent)player.getComponent("img")).getImage().getWidth();
            float y = player.getStartPosition().y;
            player.setStartPosition(new Vector2f(x,y));
            ((MovementComponent)player.getComponent("movs")).setVelocity(0);
        }
        if(((CollisionComponent)player.getComponent("col")).isLeft()){
            Vector2f v = other.getStartPosition();
            float x = v.x;
            x += ((ImageComponent)other.getComponent("img")).getImage().getWidth();
            float y = player.getStartPosition().y;
            player.setStartPosition(new Vector2f(x,y));
            ((MovementComponent)player.getComponent("movs")).setVelocity(0);
        }
    }
    
    private void resolveCoin(Entity ent) {
        
        if(((CollisionComponent)ent.getComponent("col")).isBot()){
            EntityManager.getInstance().setCoin(ent.getStartPosition());
            EventManager.getInstance().playCoin();
            ent.setId("brick100");
            try {
                ((ImageComponent)ent.getComponent("img")).setImage(new Image("data/brick.png"));
            } catch (SlickException ex) {
                Logger.getLogger(CollisionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            GameManager.getInstance().incScoreBy(100);
        }
    }
}
