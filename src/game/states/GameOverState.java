/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package game.states;

import engine.manager.GameManager;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;




public class GameOverState extends BasicGameState{
    
    private State state;
    private Sound sound;
    private boolean played;
    private long milliseconds = 0;
    
    private UnicodeFont font;
    private int textWidth;
    private String levelString;
    
    public GameOverState(State state) throws SlickException {
        this.state = state;
        sound = new Sound("data/sounds/gameover.ogg");
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        played = false;
        OutlineEffect outlineEffect = new OutlineEffect();
        outlineEffect.setWidth(1);
        outlineEffect.setColor(java.awt.Color.black);

        font = new UnicodeFont(new Font("Verdana", Font.BOLD, 37));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.getEffects().add(outlineEffect);
        font.addAsciiGlyphs();
        font.loadGlyphs();
        levelString = "GAME OVER";
        textWidth = font.getWidth(levelString);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        font.drawString(gc.getWidth()/2 - textWidth/2,gc.getHeight()/2-font.getHeight(levelString),levelString,new Color(1,1,1,1f));
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        milliseconds+=i;
        if(milliseconds>300){
            milliseconds=0;
            if(!played){
                played = true;
                sound.play(1f,1f);
            }
        }
        if(played && !sound.playing()){
            init(gc,sbg);
            sbg.enterState(State.splashScreen.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
    }
    
}
