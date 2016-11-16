package engine.entity;

import engine.entity.component.Component;
import engine.entity.component.ImageComponent;
import game.Camera;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import util.RectangleC;

public class Entity {
    
    private String id;
    private ArrayList<Component> components;
    private ImageComponent imageComponent;
    private Vector2f position;
    private RectangleC shape;
    private float rotation, scale;
    private String type;
    
    public Entity(String id) {
        
        this.id = id;
        
        components = new ArrayList<>();
        
        imageComponent = null;
        
        position = new Vector2f(0, 0);
        scale = 1f;
        rotation = 0f;
        shape = new RectangleC(0, 0, 0, 0);
        type = "";
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public void AddComponent(Component component) {
        
        if (ImageComponent.class.isInstance(component)) {
            imageComponent = (ImageComponent) component;
            shape.setX(position.x);
            shape.setY(position.y);
            shape.setWidth(imageComponent.getImage().getWidth());
            shape.setHeight(imageComponent.getImage().getHeight());
        }
        component.setOwnerEntity(this);
        components.add(component);
    }
    
    public Component getComponent(String id) {
        for (Component component : components) {
            if (component.getId() != null && component.getId().equalsIgnoreCase(id)) {
                return component;
            }
        }
        return null;
    }
    
    public Vector2f getPosition() {
        return position;
    }
    
    public float getScale() {
        return scale;
    }
    
    public float getRotation() {
        return rotation;
    }
    
    public RectangleC getShape() {
        return shape;
    }
    
    public String getId() {
        return id;
    }
    
    public void setPosition(Vector2f position) {
        this.position = position;
    }
    
    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    
    public void setShapeOrigin(Vector2f origin) {
        this.shape.setX(origin.x);
        this.shape.setY(origin.y);
    }
    
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        for (Component component : components) {
            component.update(gc, sb, delta);
        }
        //test code
        
        
        
        //shape.setX(shape.getX()+Camera.getCamera().x);
        //shape.setY(shape.getY()+Camera.getCamera().y);
    }
    
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr, int depth) {
        
        Component cmp = getComponent("img");
        
        if (imageComponent != null && ((ImageComponent)cmp).getDepth()==depth){
            imageComponent.render(gc, sb, gr);
            
            //debug code
            /*gr.setColor(Color.red);
            gr.draw(shape.getBotRectangle());
            gr.draw(shape.getTopRectangle());
            gr.draw(shape.getLeftRectangle());
            gr.draw(shape.getRightRectangle());*/
            
        }
        
    }
}