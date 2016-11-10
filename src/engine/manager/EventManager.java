package engine.manager;


public class EventManager {

    private static EventManager _instance = new EventManager();
    private boolean flag=false;
    private EventManager(){
        
    }
    
    public static EventManager getInstance(){
        return _instance;
    }

    public boolean update() {
        return updateEvents();
    }

    private boolean updateEvents() {
         if(flag){
             return true;
         }
         return false;
    }

    void addEvent() {
        flag=true;
    }

    
    
}
