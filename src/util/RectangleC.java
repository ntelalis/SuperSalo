package util;

import org.newdawn.slick.geom.Rectangle;

public class RectangleC extends Rectangle{

    private Rectangle top,down,left,right;
    
    public RectangleC(float minx, float miny, float maxx, float maxy){
        super(minx,miny,maxx,maxy);
        subrectangles();
    }
    
    private void subrectangles(){
        top = new Rectangle(x+width*0.05f,y,width*0.9f,height*0.05f);
        down = new Rectangle(x+width*0.05f,maxY-height*0.05f,width*0.9f,height*0.05f);
        left = new Rectangle(x,y+height*0.05f,width*0.05f,height*0.9f);
        right = new Rectangle(maxX-width*0.05f,y+height*0.05f,width*0.05f,height*0.9f);
    }
    
    private void update(){
        top.setLocation(x+width*0.05f,y);
        down.setLocation(x+width*0.05f,maxY-height*0.05f);
        left.setLocation(x,y+height*0.05f);
        right.setLocation(maxX-width*0.05f,y+height*0.05f);
    }
    
    public Rectangle getTopRectangle(){
        return top;
    }
    
    public Rectangle getDownRectangle(){
        return down;
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
