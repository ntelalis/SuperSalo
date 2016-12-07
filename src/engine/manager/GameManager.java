package engine.manager;

public class GameManager {

    private static GameManager _instance = new GameManager();

    private static int level;
    private static int lives;
    private static int score;
    private static int time;

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }
    
    public void resetTime(){
        GameManager.time = 300;
    }
    
    public void decTime(){
        GameManager.time -=1;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        GameManager.level = level;
    }

    public void incLevel() {
        GameManager.level++;
    }
    
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        GameManager.lives = lives;
    }
    
    public void decLives() {
        GameManager.lives--;
    }
    
    public void incLives(){
        GameManager.lives++;
    }
    
    public void incScoreBy(int score){
        GameManager.score += score;
    }
    
    public void reset(){
        GameManager.level = 1;
        GameManager.lives = 3;
        GameManager.score = 0;
        resetTime();
    }
    
    private GameManager(){
        reset();
    }
    
    public static GameManager getInstance(){
        return _instance;
    }
    
}
