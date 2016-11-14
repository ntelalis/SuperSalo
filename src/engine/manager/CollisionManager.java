package engine.manager;

import engine.entity.Entity;
import engine.entity.component.CollisionComponent;
import java.util.ArrayList;

public class CollisionManager {
    
    private static CollisionManager _instance = new CollisionManager();
    private Entity[] entities;
    
    private CollisionManager(){
    }
    
    public static CollisionManager getInstance(){
        return _instance;
    }
    
    public void loadEntities(Entity[] entities){
        this.entities = entities;
    }
    
    public void updateCollisions(){
        for(int i=0;i<entities.length;i++){
                for(int j=i;j<entities.length;j++){
                    if(!(entities[i].getId().equals(entities[j].getId()))){
                        if(entities[i].getShape().intersects(entities[j].getShape())) {
                            if(entities[i].getId().equalsIgnoreCase("greenbox") && entities[j].getId().equalsIgnoreCase("p2")){
                                EventManager.getInstance().addEvent();
                            }
                            collisionResponse(entities[i],entities[j]);
                        }
                        
                    }
                }
        }
    }
    
    public void update(){
        updateCollisions();
    }

    private void collisionResponse(Entity entity1, Entity entity2) {
        if(entity1.getComponent("col")!=null) {
            ((CollisionComponent)entity1.getComponent("col")).collision(entity2);
        }
        if(entity2.getComponent("col")!=null) {
            ((CollisionComponent)entity2.getComponent("col")).collision(entity1);
        }
    }

    public void loadEntities(ArrayList<Entity> entityArray) {
        entities = new Entity[entityArray.size()];
        int i=0;
        for(Object ent: entityArray.toArray()){
            this.entities[i++] = Entity.class.cast(ent);
        }
    }
}
