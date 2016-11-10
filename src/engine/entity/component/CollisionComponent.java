package engine.entity.component;

import engine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class CollisionComponent extends Component{
    
    public CollisionComponent(String id){
        this.id =id;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        owner.setShapeOrigin(owner.getPosition());
    }

    public void collision(Entity other){
        
        
        float xspeed = ((MovementComponent)owner.getComponent("movs")).getVelocity();
        float yspeed = ((MovementComponent)owner.getComponent("movt")).getVelocity();
        
        
            if(other.getShape().intersects(owner.getShape().getDownRectangle())){

                Vector2f v = other.getPosition();
                float y = v.y;
                y -= ((ImageComponent)owner.getComponent("img")).getImage().getHeight();
                float x = owner.getPosition().x;
                owner.setPosition(new Vector2f(x,y));
                ((MovementComponent)owner.getComponent("movt")).setVelocity(0);
                ((TopDownMovementComponent)owner.getComponent("movt")).setGravity(true);
                if(yspeed<0)
                    return;
            }
            
            if(other.getShape().intersects(owner.getShape().getTopRectangle())){

                Vector2f v = other.getPosition();
                float y = v.y+((ImageComponent)other.getComponent("img")).getImage().getHeight();
                float x = owner.getPosition().x;
                owner.setPosition(new Vector2f(x,y));
                ((MovementComponent)owner.getComponent("movt")).setVelocity(0f);
                if(yspeed>0)
                    return;
            }
            
        if(other.getShape().intersects(owner.getShape().getLeftRectangle())){
            
            Vector2f v = other.getPosition();
            float x = v.x;
            x += ((ImageComponent)other.getComponent("img")).getImage().getWidth();
            float y = owner.getPosition().y;
            owner.setPosition(new Vector2f(x,y));
            ((MovementComponent)owner.getComponent("movs")).setVelocity(0);
        }
        
        if(other.getShape().intersects(owner.getShape().getRightRectangle())){
            Vector2f v = other.getPosition();
            float x = v.x;
            x -= ((ImageComponent)owner.getComponent("img")).getImage().getWidth();
            float y = owner.getPosition().y;
            owner.setPosition(new Vector2f(x,y));
            ((MovementComponent)owner.getComponent("movs")).setVelocity(0);
        }
    }
}
