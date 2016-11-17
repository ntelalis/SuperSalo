package game;

import game.states.LevelClearState;
import game.states.EndGameState;
import game.states.GameplayState;
import game.states.PauseState;
import game.states.SplashScreenState;
import game.states.DeathState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{

    public final int SplashScreen = 0;
    public final int Gameplay = 1;
    public final int LevelClear = 2;
    public final int Death = 3;
    public final int Pause = 4;
    public final int EndGameState = 5;
    
    public static int WIDTH=1024,HEIGHT=768; 
    public Game(){
        super("SuperSalo");
        //super.enterState(WIDTH, leave, enter);
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setTargetFrameRate(240);
        app.setSmoothDeltas(true);
        app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new SplashScreenState(SplashScreen));
        addState(new GameplayState(Gameplay));
        addState(new LevelClearState(LevelClear));
        addState(new DeathState(Death));
        addState(new PauseState(Pause));
        addState(new EndGameState(EndGameState));
    }


}
