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

    private Animation imgLeft;
    private Animation imgRight;
    private Animation imgCurrent;
    private SpriteSheet a,a1;
    
    public AnimationComponent(String id, Image img, int depth) {
        super(id, img.getSubImage(0, 0, 32, 49),depth);
        a = new SpriteSheet(img,32,49);
        a1 = new SpriteSheet(img.getFlippedCopy(true, false),32,49);
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
        pos = owner.getPosition();
        scale = owner.getScale();
        gr.translate(-Camera.getCamera().x, -Camera.getCamera().y);
        imgCurrent.draw(pos.x,pos.y);
        gr.translate(Camera.getCamera().x, Camera.getCamera().y);
    }
}
