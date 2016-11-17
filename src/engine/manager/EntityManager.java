/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package engine.manager;

import engine.entity.Entity;
import engine.entity.Player;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ntelalis
 */
public class EntityManager {
    private static EntityManager _instance = new EntityManager();
    private ArrayList<Entity> entityList;
    private ArrayList<Entity> cloneList;
    private Player player;

    
    
    private EntityManager(){
        entityList = new ArrayList();
    }
    
    public static EntityManager getInstance(){
        return _instance;
    }
    
    public void loadEntities(ArrayList<Entity> entityList){
        this.entityList = entityList;
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
            if(entity.getId().equals("greenbox")){
                return entity;
            }
        }
        entityList = cloneList;
        return null;
    }
    
    public Player getPlayer() {
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
