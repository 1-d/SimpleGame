import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Enemy110 here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Enemy110 extends Enemy
{
    
    protected int enemySize = 60;
    protected int enemySpeed = -30;
    protected int shootSpeed = 2;
    private int jumpFrame = 0;
    private int jumps = 5;
    private double bulletAccel = 0.002;
    
    public Enemy110(Game g, ArrayList<Integer> info) {
        // initialize
        game = g;
        deadAnimation = 1;
        setScaledImage("101santaPoop1.png");
        
        // add this actor
        game.addObject(this, 256, -100);
    }
    
    protected void dying() {
    }
    
    private void setScaledImage(String imageFile) {
        // set image first
        setImage(imageFile);
        // double the image size
        getImage().scale(getImage().getWidth()*2, getImage().getHeight()*2);
        //setImage(getImage());
    }
    
    protected void alive() {
        // animations and movement
        if (jumpFrame == 0) {
            // get ready to jump
            if (getY() > 384 && jumps > 0) {
                setScaledImage("101santaPoop2.png");
                jumpFrame = 1;
                jumps--;
            }
            move(0, 1);
        }
        else if (jumpFrame < 10) {
            // do nothing right now
            jumpFrame++;
            move(0, 1);
        }
        else if (jumpFrame < 20) {
            // set image
            if (jumpFrame == 10) setScaledImage("101santaPoop3.png");
            // do the actual jump
            jumpFrame++;
            move(0, enemySpeed);
        }
        else if (jumpFrame < 40) {
            // set image
            if (jumpFrame == 20) {
                setScaledImage("101santaPoop4.png");
                
                // plus some bullets
                if (jumps > 0) {
                    // spawn tears
                    for (int i=-20; i<20; i++) {
                        // get constants
                        int delayTime = Math.abs(i*8);
                        double phase = i*0.3 + jumps*2;
                        
                        // horizontal bullets
                        new Enemy102c(game, getX()+i*32+16, getY(), bulletAccel/2 * Math.sin(phase), bulletAccel * Math.cos(phase), delayTime);
                        new Enemy102c(game, getX()+i*32, getY(), -bulletAccel/2 * Math.sin(phase), -bulletAccel * Math.cos(phase), delayTime);
                        // vertical bullets
                        new Enemy102c(game, getX(), getY()+i*32, -bulletAccel/2 * Math.sin(phase), -bulletAccel * Math.cos(phase), delayTime);
                    }
                }
            }
            // do nothing
            jumpFrame++;
            move(0, 1);
        }
        else {
            // cycle is done
            jumpFrame = 0;
            setScaledImage("101santaPoop1.png");
        }
        
        if (game.getPlayerDist(getX(), getY()) <= enemySize) {
            // hit player
            game.playerLoseLife();
        }
        
        if (outOfBounds(150)) {
            // out of bounds
            killFast();
        }
    }
}