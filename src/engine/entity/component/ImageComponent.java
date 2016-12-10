package engine.entity.component;

import game.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class ImageComponent extends Component{
    
    protected Image img;
    protected Image imgf;
    protected int depth;
    protected Vector2f pos;
    protected float scale;
    
    public ImageComponent(String id, Image img, int depth){
        this.id = id;
        this.img = img;
        this.depth = depth;
    }
    
    public Image getImage(){
        return img;
    }
    
    public void setImage(Image img){
        this.img = img;
    }
    
    public int getDepth(){
        return depth;
    }
    
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
        pos = owner.getStartPosition();
        scale = owner.getScale();
        
        gr.translate(-Camera.getCamera().x, -Camera.getCamera().y);
        img.draw(pos.x,pos.y, scale);
        if(owner.getId().equalsIgnoreCase("bg")){
            imgf = img.getFlippedCopy(true, false);
            for(int i =1; i<6;i++){
                if(i%2==1)
                    imgf.draw(pos.x+img.getWidth()*i,pos.y,scale);
                else
                    img.draw(pos.x+img.getWidth()*i,pos.y, scale);
            }
        }
        gr.translate(Camera.getCamera().x, Camera.getCamera().y);
        
    }
    
    public void setFS(){
        owner.setScale(4);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta){
        img.rotate(owner.getRotation() - img.getRotation());
    }
    
}
