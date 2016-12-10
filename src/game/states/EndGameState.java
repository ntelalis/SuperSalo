/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package game.states;

import engine.manager.CollisionManager;
import engine.manager.EntityManager;
import engine.manager.EventManager;
import engine.manager.GameManager;
import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;




public class EndGameState extends BasicGameState{
    
    private State state;
    private long milliseconds;
    private Sound snd;
    private UnicodeFont font;
    private String endString;
    private int endStringTextWidth;
    private int scroll;
    
    public EndGameState(State state) {
        this.state = state;
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        snd = new Sound("data/sounds/gameEnd.ogg");
        OutlineEffect outlineEffect = new OutlineEffect();
        outlineEffect.setWidth(1);
        outlineEffect.setColor(java.awt.Color.black);
        font = new UnicodeFont(new Font("Verdana", Font.BOLD, 37));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.getEffects().add(outlineEffect);
        font.addAsciiGlyphs();
        font.loadGlyphs();
        endString = "ENDING CREDITS\n\n\n Developed by:\n George Paschos\n\n\n\n\n\n\n\n\n\n\n Thanks for playing!";
        scroll=0;
        endStringTextWidth = font.getWidth(endString+GameManager.getInstance().getLevel());
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        font.drawString(gc.getWidth()/2 - endStringTextWidth/2,gc.getHeight()-scroll, endString);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(scroll<1100){
            scroll++;
        }
        
        if(!snd.playing()){
            snd.play();
        }
        
        milliseconds+=i;
        
        if(milliseconds>60000){
            milliseconds=0;
            snd.stop();
            GameManager.getInstance().reset();
            EventManager.getInstance().resetEvents();
            init(gc,sbg);
            sbg.enterState(State.splashScreen.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
    }
    
}
