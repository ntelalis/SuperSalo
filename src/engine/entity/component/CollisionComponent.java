package engine.entity.component;

import engine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public class CollisionComponent extends Component{
    
    private boolean top,bot,right,left;
    private String type;
    
    
    public CollisionComponent(String id, String type){
        this.id = id;
        top=false;
        bot=false;
        right=false;
        left=false;
        this.type = type;
    }
    
    public void resetAll(){
        setTop(false);
        setBot(false);
        setRight(false);
        setLeft(false);
    }
    
    public void setTop(boolean top) {
        this.top = top;
    }
    
    public void setBot(boolean bot) {
        this.bot = bot;
    }
    
    public void setRight(boolean right) {
        this.right = right;
    }
    
    public void setLeft(boolean left) {
        this.left = left;
    }
    
    public boolean isTop() {
        return top;
    }
    
    public boolean isBot() {
        return bot;
    }
    
    public boolean isRight() {
        return right;
    }
    
    public boolean isLeft() {
        return left;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        owner.setShapeOrigin(owner.getPosition());
    }
    
    public boolean collision(Entity other){
        
        resetAll();
        boolean flag=false;
        
        if(other.getShape().intersects(owner.getShape().getLeftRectangle())){
            setLeft(true);
            flag=true;
        }
        if(other.getShape().intersects(owner.getShape().getRightRectangle())){
            setRight(true);
            flag=true;
        }
        if(other.getShape().intersects(owner.getShape().getBotRectangle())){
            setBot(true);
            flag=true;
        }
        if(other.getShape().intersects(owner.getShape().getTopRectangle())){
            setTop(true);
            flag=true;
        }
        return flag;
    }
}