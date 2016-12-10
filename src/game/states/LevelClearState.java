package game.states;

import engine.manager.EventManager;
import engine.manager.GameManager;
import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;


public class LevelClearState extends BasicGameState {
    
    private State state;
    private long milliseconds = 0;
    UnicodeFont font;
    int levelTextWidth,scoreTextWidth;
    private String levelString,scoreString;
    private Sound snd;
    private boolean played;
    public LevelClearState(State state) {
        this.state = state;
    }

    @Override
    public int getID() {
        return state.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        snd = new Sound("data/sounds/win.ogg");
        OutlineEffect outlineEffect = new OutlineEffect();
        outlineEffect.setWidth(1);
        outlineEffect.setColor(java.awt.Color.black);
        
        font = new UnicodeFont(new Font("Verdana", Font.BOLD, 37));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.getEffects().add(outlineEffect);
        font.addAsciiGlyphs();
        font.loadGlyphs();
        levelString = "LEVEL "+GameManager.getInstance().getLevel()+" CLEARED!!";
        scoreString = "SCORE "+GameManager.getInstance().getScore();
        levelTextWidth = font.getWidth(levelString+GameManager.getInstance().getLevel());
        scoreTextWidth = font.getWidth(scoreString+GameManager.getInstance().getScore());
        played=false;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        font.drawString(gc.getWidth()/2 - levelTextWidth/2,gc.getHeight()*2/5-font.getHeight(levelString+GameManager.getInstance().getLevel()), levelString);
        font.drawString(gc.getWidth()/2 - scoreTextWidth/2,gc.getHeight()*2/5+font.getHeight(scoreString+GameManager.getInstance().getScore()), scoreString);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(!snd.playing() && !played){
            snd.play();
            played=true;
            scoreString = "SCORE "+GameManager.getInstance().getScore();
        }
        milliseconds+=i;
        
        if(milliseconds>7000){
            snd.stop();
            milliseconds=0;
            init(gc,sbg);
            if(GameManager.getInstance().getLevel()==4){
                sbg.enterState(State.endGameState.ordinal(),new EmptyTransition(), new EmptyTransition());
            }
            sbg.enterState(State.levelStart.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
    }

    
}