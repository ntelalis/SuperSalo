/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package engine.manager;

import engine.entity.Entity;
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
    
    
    private EntityManager(){
        entityList = new ArrayList();
    }
    
    public static EntityManager getInstance(){
        return _instance;
    }
    
    public void loadEntities(ArrayList<Entity> entityList){
        this.entityList = entityList;
        updateCol();
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
        return null;
    }
    
    private void resolveEvent(Event e){
        
    }
    
    private void updateCol(){
        ArrayList<Entity> colList = new ArrayList(); 
        for(Entity entity : entityList){
            if(entity.getComponent("col")!=null)
                colList.add(entity);
        }
        CollisionManager.getInstance().loadEntities(colList);
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        for(Entity entity : entityList){
            entity.update(gc, sbg, delta);
        }
        if(EventManager.getInstance().isUnresolvedEvent()){
            updateCol();
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
