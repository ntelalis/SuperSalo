package game.states;

import java.awt.Font;
import org.newdawn.slick.Color;
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
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;

public class SplashScreenState extends BasicGameState{
    
    
    private State state;
    private Image splashscreen;
    private Input input;
    private float x=0;
    private long milliseconds = 0;
    private UnicodeFont font;
    private Image logo;
    private String startString = "PRESS START";
    private int textWidth;
    private Sound snd;
    
    public SplashScreenState(State state) {
        this.state = state;
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        splashscreen = new Image("data/splashscreen.png");

        OutlineEffect outlineEffect = new OutlineEffect();
        outlineEffect.setWidth(1);
        outlineEffect.setColor(java.awt.Color.black);

        font = new UnicodeFont(new Font("Verdana", Font.BOLD, 37));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.getEffects().add(outlineEffect);
        font.addAsciiGlyphs();
        font.loadGlyphs();

        logo = new Image("data/logo.png");
        textWidth = font.getWidth(startString);
        snd = new Sound("data/sounds/splashscreen.ogg");
        
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawImage(splashscreen,((float)((0.5*Math.sin(x))+0.5)*500)-500,0);
        font.drawString(gc.getWidth()/2 - textWidth/2,gc.getHeight()*3/4,startString,new Color(1,1,1,(float)Math.sin(x*7)+1f));
        logo.draw(gc.getWidth()/2 - logo.getWidth()/2,gc.getHeight()/4-logo.getHeight()/2);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(!snd.playing()){
            snd.play(1f,0.7f);
        }
        milliseconds+=i;
        if(milliseconds>10){
            milliseconds=0;
            x+=0.01;
        }
        input = gc.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER)){
            snd.stop();
            sbg.enterState(State.levelStart.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
    }
    
    
}

