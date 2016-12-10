package util;

import org.newdawn.slick.geom.Rectangle;

public class RectangleC extends Rectangle{

    private Rectangle top,bot,left,right;
    
    public RectangleC(float minx, float miny, float maxx, float maxy){
        super(minx,miny,maxx,maxy);
        subrectangles();
    }
    
    private void subrectangles(){
        top = new Rectangle(x+width*0.1f,y,width*0.8f,height*0.1f);
        bot = new Rectangle(x+width*0.1f,maxY-height*0.1f,width*0.8f,height*0.1f);
        left = new Rectangle(x,y+height*0.1f,width*0.1f,height*0.8f);
        right = new Rectangle(maxX-width*0.1f,y+height*0.1f,width*0.1f,height*0.8f);
    }
    
    private void update(){
        top.setLocation(x+width*0.1f,y);
        bot.setLocation(x+width*0.1f,maxY-height*0.1f);
        left.setLocation(x,y+height*0.1f);
        right.setLocation(maxX-width*0.1f,y+height*0.1f);
    }
    
    public Rectangle getTopRectangle(){
        return top;
    }
    
    public Rectangle getBotRectangle(){
        return bot;
    }
    
    public Rectangle getLeftRectangle(){
        return left;
    }
    
    public Rectangle getRightRectangle(){
        return right;
    }

    @Override
    public void setX(float x){
        super.setX(x);
        update();
    }
    
    @Override
    public void setY(float y){
        super.setY(y);
        update();
    }
    
    @Override
    public void setWidth(float width){
        super.setWidth(width);
        subrectangles();
    }
    
    @Override
    public void setHeight(float height){
        super.setHeight(height);
        subrectangles();
    }
    
}
