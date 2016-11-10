package util;

import org.newdawn.slick.geom.Rectangle;

public class RectangleC extends Rectangle{

    private Rectangle top,down,left,right;
    
    public RectangleC(float minx, float miny, float maxx, float maxy){
        super(minx,miny,maxx,maxy);
        subrectangles();
    }
    
    private void subrectangles(){
        top = new Rectangle(x+10,y+1,width-20,9);
        down = new Rectangle(x+10,maxY-11,width-20,11);
        left = new Rectangle(x,y+10,10,height-20);
        right = new Rectangle(maxX-10,y+10,10,height-20);
    }
    
    private void update(){
        top.setLocation(x+10,y+1);
        down.setLocation(x+10,maxY-11);
        left.setLocation(x,y+10);
        right.setLocation(maxX-10,y+10);
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
