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
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.Transition;

/**
 *
 * @author ntelalis
 */
public class PauseState extends BasicGameState{
    
    private int id;
    private  TrueTypeFont ttf;
    
    Input input;
    
    public PauseState(int id){
        this.id = id;
    }
    
    @Override
    public int getID() {
        return id;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Font font = new Font("Verdana", Font.BOLD, 32);
        ttf = new TrueTypeFont(font, true);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        String paused = "PAUSED";
        int textWidth = ttf.getWidth(paused);
        ttf.drawString(gc.getWidth()/2 - textWidth/2,gc.getHeight()/2-ttf.getLineHeight(),paused,Color.white);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input = gc.getInput();
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(1,new EmptyTransition(), new FadeInTransition());
            
        }
    }
    
}
