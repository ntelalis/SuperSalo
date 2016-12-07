package game;

import engine.manager.GameManager;
import game.states.State;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.StateBasedGame;

public class HUD {
    
    private int time = 0;
    private int score = 0;
    
    private UnicodeFont font;
    private int scoreTextWidth,timeTextWidth;
    private String scoreString,timeString;
    
    
    public HUD() throws SlickException{
        OutlineEffect outlineEffect = new OutlineEffect();
        outlineEffect.setWidth(1);
        outlineEffect.setColor(java.awt.Color.black);
        font = new UnicodeFont(new Font("Verdana", Font.BOLD, 24));
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.getEffects().add(outlineEffect);
        font.addAsciiGlyphs();
        font.loadGlyphs();
        scoreString = "SCORE \n";
        timeString = "TIME \n ";
        scoreString += String.format("%1$06d", GameManager.getInstance().getScore());
        timeString += String.format("%1$03d", GameManager.getInstance().getTime());
        scoreTextWidth = font.getWidth(scoreString);
        timeTextWidth = font.getWidth(timeString);
    }
    
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
        update();
        font.drawString(5,5,scoreString,new Color(1,1,1,1f));
        font.drawString(gc.getWidth()-timeTextWidth,5,timeString,new Color(1,1,1,1f));
    }
    
    private void update(){
        scoreString = "SCORE \n" + String.format("%1$06d", GameManager.getInstance().getScore());
        timeString = "TIME \n " + String.format("%1$03d", GameManager.getInstance().getTime());
    }
    
}
