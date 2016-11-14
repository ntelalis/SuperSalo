/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package game;

import engine.entity.Entity;
import engine.entity.component.AnimationComponent;
import engine.entity.component.CollisionComponent;
import engine.entity.component.ImageComponent;
import engine.entity.component.SideMovementComponent;
import engine.entity.component.SimpleCollision;
import engine.entity.component.TopDownMovementComponent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.parser.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Desktop
 */
public class LevelLoader {
    
    public static ArrayList load(FileReader fr) throws ParseException, IOException, SlickException{
        
        ArrayList<Entity> entitiesArray = new ArrayList();
        
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fr);
        JSONObject jsonobj = (JSONObject)obj;
        
        for(Iterator entities = jsonobj.keySet().iterator(); entities.hasNext();){
            
            String name = (String) entities.next();
            Entity entity = new Entity(name);
            
            JSONObject enty = (JSONObject)jsonobj.get(name);
            
            String type = (String)enty.get("type");
            //TO-DO
            
            long x = (long)(((JSONArray)enty.get("pos")).get(0));
            long y = (long)(((JSONArray)enty.get("pos")).get(1));
            entity.setPosition(new Vector2f(x,y));
            
            JSONObject compies = (JSONObject)enty.get("comps");
            
            for(Iterator comps = compies.keySet().iterator(); comps.hasNext();){
                
                String key = (String) comps.next();
                
                switch(key){
                    case "Image":
                        entity.AddComponent(new ImageComponent("img", new Image((String)compies.get(key))));
                        break;
                    case "Animation":
                        entity.AddComponent(new AnimationComponent("img", new Image((String)compies.get(key))));
                        break;
                    case "SideMovement":
                        double smaxspeed = (double)(((JSONArray)compies.get("SideMovement")).get(0));
                        double smaxaccel = (double)(((JSONArray)compies.get("SideMovement")).get(1));
                        entity.AddComponent(new SideMovementComponent("movs",(float)smaxspeed,(float)smaxaccel));
                        break;
                    case "TopDownMovement":
                        double tmaxspeed = (double)(((JSONArray)compies.get("TopDownMovement")).get(0));
                        double tmaxaccel = (double)(((JSONArray)compies.get("TopDownMovement")).get(1));
                        entity.AddComponent(new TopDownMovementComponent("movt",(float)tmaxspeed,(float)tmaxaccel));
                        break;
                    case "Collision":
                        if(((String)compies.get(key)).equals("true"))
                            entity.AddComponent(new CollisionComponent("col"));
                        else if(((String)compies.get(key)).equals("simple"))
                            //entity.AddComponent(new SimpleCollision("scol"));
                            entity.AddComponent(new CollisionComponent("col"));
                        break;
                }
            }
            entitiesArray.add(entity);
        }
        return entitiesArray;
    }
}
