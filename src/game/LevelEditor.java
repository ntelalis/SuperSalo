package game;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LevelEditor {
    public static void main(String[] args) {
        
        JSONObject entity = new JSONObject();
        JSONObject ent = new JSONObject();
        JSONObject pos = new JSONObject();
        JSONObject comps = new JSONObject();
        JSONObject movs = new JSONObject();
        JSONObject movt = new JSONObject();
        
        entity.put("name", "player");
        entity.put("pos", pos);
        entity.put("comps", comps);
        comps.put("anim","data/test2.png");
        comps.put("movs",movs);
        comps.put("movt",movt);
        comps.put("col",true);
        pos.put("posx", 200);
        pos.put("posy", 250);
        movs.put("maxspeed", 20f);
        movs.put("maxaccel", 5f);
        movt.put("maxspeed", 200f);
        movt.put("maxaccel", 12f);
        
        
        
        try {
            
            try (FileWriter file = new FileWriter("src/data/level1.lvl")) {
                file.write(entity.toJSONString());
                file.flush();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.print(entity);
        
    }
    
}