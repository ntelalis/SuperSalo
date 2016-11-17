package engine.manager;

import engine.entity.Entity;

public class EventManager {
    
    private static EventManager _instance = new EventManager();
    
    private static boolean playerKilled=false,levelCleared=false;
    
    private EventManager(){
    }
    
    public static EventManager getInstance(){
        return _instance;
    }
    
    public void resolveEvent(Entity ent, Event e) {
        switch(e){
            case kill:
                EntityManager.getInstance().removeEntity(ent);
                playerKilled = true;
                break;
            case win:
                levelCleared = true;
                break;
        }
    }
    
    public boolean playerKilled(){
        return playerKilled;
    }
    
    public boolean levelCleared(){
        return levelCleared;
    }
}
