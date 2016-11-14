package engine.entity.component;

import engine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class CollisionComponent extends SimpleCollision{
    
    boolean leftcol=false,rightcol=false,topcol=false,downcol=false;
    
    public CollisionComponent(String id){
        super(id);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        owner.setShapeOrigin(owner.getPosition());
        downcol=false;
    }
    
    public void collision(Entity other){
        
        leftcol=false;rightcol=false;topcol=false;
        
        if(other.getShape().intersects(owner.getShape().getLeftRectangle())){
            leftcol=true;
        }
        if(other.getShape().intersects(owner.getShape().getRightRectangle())){
            rightcol=true;
        }
        if(other.getShape().intersects(owner.getShape().getDownRectangle())){
            downcol=true;
        }
        if(other.getShape().intersects(owner.getShape().getTopRectangle())){
            topcol=true;
        }
        
        if(leftcol && rightcol){
            topdowncol(other);
            return;
        }
        if(topcol && downcol){
            leftrightcol(other);
            return;
        }
        
        float xspeed = Math.abs(((MovementComponent)owner.getComponent("movs")).getVelocity());
        float yspeed = Math.abs(((MovementComponent)owner.getComponent("movt")).getVelocity());
        
        if(xspeed>yspeed){
            
            if(!leftrightcol(other))
                topdowncol(other);
        }
        else{
            if(!topdowncol(other))
                leftrightcol(other);
        }
    }
    
    private boolean topdowncol(Entity other){
        if(downcol && ((MovementComponent)owner.getComponent("movt")).getVelocity() < 0){
            Vector2f v = other.getPosition();
            float y = v.y;
            y -= ((ImageComponent)owner.getComponent("img")).getImage().getHeight();
            float x = owner.getPosition().x;
            owner.setPosition(new Vector2f(x,y));
            ((MovementComponent)owner.getComponent("movt")).setVelocity(0);
            ((TopDownMovementComponent)owner.getComponent("movt")).setGravity(true);
            return true;
        }
        if(topcol&& ((MovementComponent)owner.getComponent("movt")).getVelocity() > 0){
            Vector2f v = other.getPosition();
            float y = v.y+((ImageComponent)other.getComponent("img")).getImage().getHeight();
            float x = owner.getPosition().x;
            owner.setPosition(new Vector2f(x,y));
            ((MovementComponent)owner.getComponent("movt")).setVelocity(0f);
            return true;
        }
        return false;
    }
    
    private boolean leftrightcol(Entity other){
        if(leftcol){
            Vector2f v = other.getPosition();
            float x = v.x;
            x += ((ImageComponent)other.getComponent("img")).getImage().getWidth();
            float y = owner.getPosition().y;
            owner.setPosition(new Vector2f(x,y));
            ((MovementComponent)owner.getComponent("movs")).setVelocity(0);
            return true;
        }
        if(rightcol){
            Vector2f v = other.getPosition();
            float x = v.x;
            x -= ((ImageComponent)owner.getComponent("img")).getImage().getWidth();
            float y = owner.getPosition().y;
            owner.setPosition(new Vector2f(x,y));
            ((MovementComponent)owner.getComponent("movs")).setVelocity(0);
            return true;
        }
        return false;
    }
    
}
