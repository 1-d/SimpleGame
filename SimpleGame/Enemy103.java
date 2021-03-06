import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy103 here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Enemy103 extends Enemy
{
    
    protected int shootSpeed;
    protected int shootCount;
    protected int shootPeriod;
    
    protected int shootTiming=0;
    
    public Enemy103(Game g, ArrayList<String> info) {
        // initialize
        game = g;
        deadAnimation = 1;
        shootSpeed = Integer.parseInt(info.get(0));
        shootCount = Integer.parseInt(info.get(1));
        shootPeriod = Integer.parseInt(info.get(2));
        enemySize = 30;
        
        // add this actor
        game.addObject(this, Integer.parseInt(info.get(3)), -30);
    }
    
    public void attack()
    {
        // shoot projectiles in a circle
        Greenfoot.playSound("bloodShot.mp3");
        for (int i=0; i<shootCount; i++) {
            new Enemy102b(game,
                          getX(),
                          getY(),
                          shootSpeed * Math.sin(Math.PI*2/shootCount * i),
                          shootSpeed * Math.cos(Math.PI*2/shootCount * i));
        }
    }
    
    // replace the getHit code to suit this enemy
    public void getHit()
    {
        // check if capable of attacking
        if (shootPeriod < 1000) attack();
        
        // slow down shoot rate
        shootPeriod = Math.min(10000, shootPeriod*2);
    }
    
    protected void dying() {
        // it shouldn't die
    }
    
    protected void alive() {
        // animations
        if (timer%60 < 10)      setImage("nerveEnding1.png");
        else if (timer%60 < 20) setImage("nerveEnding2.png");
        else if (timer%60 < 30) setImage("nerveEnding3.png");
        else if (timer%60 < 40) setImage("nerveEnding4.png");
        else if (timer%60 < 50) setImage("nerveEnding5.png");
        else                    setImage("nerveEnding6.png");
        
        // shooting
        shootTiming++;
        if (shootPeriod == shootTiming) {
            // attack and reset timing
            attack();
            shootTiming = 0;
        }
        
        // movement
        move(0, 1);
        
        if (game.getPlayerDist(getX(), getY()) <= enemySize) {
            // hit player but no dying
            game.playerLoseLife();
            //kill();
        }
        
        if (outOfBounds(80)) {
            // out of bounds
            removeFast();
        }
    }
}
