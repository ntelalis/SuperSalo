package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{

    public final int GAMESTATE = 1;
    public static int WIDTH=1024,HEIGHT=768; 
    public Game(){
        super("SuperSalo");
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setTargetFrameRate(120);
        app.setSmoothDeltas(true);
        app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new GameplayState(GAMESTATE));
    }


}
