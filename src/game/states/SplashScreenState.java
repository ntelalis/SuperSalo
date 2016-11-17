/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package game.states;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.font.effects.EffectUtil;
import org.newdawn.slick.font.effects.OutlineZigzagEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

/**
 *
 * @author ntelalis
 */
public class SplashScreenState extends BasicGameState{
    
    private int id;
    private Image splashscreen;
    private Input input;
    private float x=0;
    private long milliseconds = 0;
    private  TrueTypeFont ttf;
    private Image logo;
    private String startString = "PRESS START";
    int textWidth;
    public SplashScreenState(int id) {
        this.id = id;
    }
    
    @Override
    public int getID() {
        return id;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        splashscreen = new Image("data/splashscreen.png");
        Font font = new Font("Verdana", Font.BOLD, 32);
        ttf = new TrueTypeFont(font, true);
        logo = new Image("data/logo.png");
        textWidth = ttf.getWidth(startString);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawImage(splashscreen,((float)((0.5*Math.sin(x))+0.5)*500)-500,0);
        
        
        ttf.drawString(gc.getWidth()/2 - textWidth/2,gc.getHeight()/2,startString,Color.white);
        logo.draw(gc.getWidth()/2 - logo.getWidth()/2,gc.getHeight()/4-logo.getHeight()/2);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        milliseconds+=i;
        if(milliseconds>10){
            milliseconds=0;
            x+=0.01;
        }
        input = gc.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER)){
            sbg.enterState(1,new EmptyTransition(), new VerticalSplitTransition());
        }
    }
    
    
}

