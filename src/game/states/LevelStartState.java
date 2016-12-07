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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

/**
 *
 * @author ntelalis
 */
public class LevelStartState extends BasicGameState{
    
    private State state;
    private long milliseconds = 0;
    UnicodeFont font;
    int levelTextWidth,livesTextWidth;
    private String levelString,livesString;
    private Image playerImage;
    
    public LevelStartState(State state) {
        this.state = state;
    }
    
    @Override
    public int getID() {
        return state.ordinal();
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        OutlineEffect outlineEffect = new OutlineEffect();
        outlineEffect.setWidth(1);
        outlineEffect.setColor(java.awt.Color.black);
        playerImage = new Image("data/player.png");
        font = new UnicodeFont(new Font("Verdana", Font.BOLD, 37));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.getEffects().add(outlineEffect);
        font.addAsciiGlyphs();
        font.loadGlyphs();
        levelString = "LEVEL ";
        livesString = "x ";
        levelTextWidth = font.getWidth(levelString+GameManager.getInstance().getLevel());
        livesTextWidth = font.getWidth(livesString+GameManager.getInstance().getLives());
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        font.drawString(gc.getWidth()/2 - levelTextWidth/2,gc.getHeight()*2/5-font.getHeight(levelString+GameManager.getInstance().getLevel()),levelString+GameManager.getInstance().getLevel(),new Color(1,1,1,1f));
        font.drawString(gc.getWidth()/2 - livesTextWidth/2+playerImage.getWidth()/2,gc.getHeight()*2/5+font.getHeight(levelString)*2-font.getHeight(livesString+GameManager.getInstance().getLives()),livesString+GameManager.getInstance().getLives(),new Color(1,1,1,1f));
        playerImage.draw(gc.getWidth()/2 - livesTextWidth/2-playerImage.getWidth(),gc.getHeight()*2/5+font.getHeight(levelString)*2-font.getHeight(livesString+GameManager.getInstance().getLives()));
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        milliseconds+=i;
        
        if(milliseconds>2000){
            milliseconds=0;
            sbg.enterState(State.gameplay.ordinal(),new EmptyTransition(), new EmptyTransition());
        }
        
    }
    
}
