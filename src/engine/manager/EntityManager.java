/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package engine.manager;

import engine.entity.Entity;
import engine.entity.Player;
import engine.entity.component.ImageComponent;
import engine.entity.component.SideMovementComponent;
import engine.entity.component.TopDownMovementComponent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ntelalis
 */
public class EntityManager {
    private static EntityManager _instance = new EntityManager();
    private ArrayList<Entity> entityList;
    private ArrayList<Entity> cloneList;
    private Entity player;
    private Entity coin;
    
    private EntityManager(){
        entityList = new ArrayList();
    }
    
    public static EntityManager getInstance(){
        return _instance;
    }
    
    public void loadEntities(ArrayList<Entity> entityList){
        this.entityList = entityList;
        for(Entity entity : entityList){
            if(entity.getId().equals("greenbox")){
                this.player = entity;
            }
        }
        loadCollisionEntities();
    }
    
    
    public void addEntity(Entity e) {
        entityList.add(e);
    }
    
    public void removeEntity(Entity e){
        entityList.remove(e);
    }
    
    public Entity getEntity(String id){
        for(Entity entity : entityList){
            if(entity.getId().equals(id)){
                return entity;
            }
        }
        return null;
    }
    
    public void setCoin(Vector2f pos){
        coin = new Entity("coin");
        coin.setType("solid");
        coin.AddComponent(new TopDownMovementComponent("movt",50f,12f));
        coin.AddComponent(new SideMovementComponent("movs",0f,0f));
        try {
            coin.AddComponent(new ImageComponent("img",new Image("data/coin.png"), 1));
        } catch (SlickException ex) {
            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        coin.setStartPosition(new Vector2f(pos.x+10,pos.y-40));
        ((TopDownMovementComponent)coin.getComponent("movt")).setVelocity(200);
        addEntity(coin);
    }
    
    public boolean isCoin(){
        return coin != null;
    }
    
    public void delCoin(){
        removeEntity(coin);
        coin = null;
    }
    
    public Entity getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    private void loadCollisionEntities(){
        ArrayList<Entity> colList = new ArrayList();
        for(Entity entity : entityList){
            if(entity.getComponent("col")!=null)
                colList.add(entity);
            //if(entity.getType().equals("player"))
            //player = (Player)entity;
        }
        CollisionManager.getInstance().loadEntities(colList);
    }
    
    public void removeEntities(){
        this.entityList = null;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        for(Entity entity : entityList){
            entity.update(gc, sbg, delta);
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException{
        for(int i=0;i<3;i++){
            for(Entity entity : entityList){
                entity.render(gc, sbg, grphcs ,i);
            }
        }
    }
    
}
