/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package engine.entity.component;

import game.Camera;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Desktop
 */
public class AnimationComponent extends ImageComponent{
    
    private static final int tw = 32;
    private static final int th = 49;
    
    private Animation imgLeft;
    private Animation imgRight;
    private Animation imgCurrent;
    private SpriteSheet a,a1;
    
    public AnimationComponent(String id, Image img, int depth) {
        super(id, img.getSubImage(0, 0, tw, th),depth);
        a = new SpriteSheet(img,tw,th);
        a1 = new SpriteSheet(img.getFlippedCopy(true, false),tw,th);
        this.imgLeft = new Animation(a,100);
        this.imgRight = new Animation(a1,100);
        this.imgCurrent = imgRight;
    }
    
    public Animation getRightAnimation(){
        imgCurrent = imgRight;
        return this.imgRight;
    }
    
    public Animation getLeftAnimation(){
        imgCurrent = imgLeft;
        return this.imgLeft;
    }
    
    public void stopAnimation(){
        imgCurrent.stop();
        imgCurrent.setCurrentFrame(0);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
        pos = owner.getStartPosition();
        scale = owner.getScale();
        gr.translate(-Camera.getCamera().x, -Camera.getCamera().y);
        imgCurrent.draw(pos.x,pos.y);
        gr.translate(Camera.getCamera().x, Camera.getCamera().y);
    }
}
