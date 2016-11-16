package engine.manager;

import engine.entity.Entity;
import java.util.ArrayList;
import engine.manager.Event;

public class EventManager {
    
    private static EventManager _instance = new EventManager();
    private ArrayList<ArrayList<Event>> eventList;
    private boolean eventFlag;
    
    private EventManager(){
        eventList = new ArrayList();
        eventFlag = false;
    }
    
    public static EventManager getInstance(){
        return _instance;
    }
    
    public void update() {
        if(eventFlag){
            while(eventList.size()>0){
                
            }
            eventFlag = false;
        }
    }
    
    public void addEvent(Entity ent, Event e) {
        eventFlag = true;
        //eventList.add(ent,e);
    }
    
    public boolean isUnresolvedEvent(){
        return eventFlag;
    }
    
    private void resolveEvent(Event e){
        switch(e){
            case killPlayer:
                
                break;
        }
    }
    
    
}
