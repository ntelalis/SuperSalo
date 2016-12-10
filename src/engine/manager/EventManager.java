package engine.manager;

import engine.entity.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class EventManager {
    
    private static EventManager _instance = new EventManager();
    
    private static boolean playerKilled=false,levelCleared=false;
    private Sound sndCoin;
    
    private EventManager(){
        try {
            sndCoin = new Sound("data/sounds/coin.ogg");
        } catch (SlickException ex) {
            Logger.getLogger(EventManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetEvents(){
        playerKilled = false;
        levelCleared = false;
    }
    
    public static EventManager getInstance(){
        return _instance;
    }
    
    public void resolveEvent(Entity ent, Event e) {
        switch(e){
            case kill:
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

    void playCoin() {
        sndCoin.play();
    }
}
