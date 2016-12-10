package game;

import game.states.LevelClearState;
import game.states.EndGameState;
import game.states.GameplayState;
import game.states.PauseState;
import game.states.SplashScreenState;
import game.states.LevelStartState;
import game.states.GameOverState;
import game.states.State;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{

    public static int WIDTH=1024,HEIGHT=768; 
    
    public Game(){
        super("SuperSalo");
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setTargetFrameRate(240);
        app.setSmoothDeltas(true);
        app.setAlwaysRender(true);
        app.setShowFPS(false);
        app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new SplashScreenState(State.splashScreen));
        addState(new GameplayState(State.gameplay));
        addState(new LevelClearState(State.levelClear));
        addState(new LevelStartState(State.levelStart));
        addState(new PauseState(State.pause));
        addState(new GameOverState(State.gameOver));
        addState(new EndGameState(State.endGameState));
    }
    
}