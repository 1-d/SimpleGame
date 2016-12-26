import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class Player extends Actor
{
    
    private final int SPEED_NORMAL = 5;
    private final int SPEED_SLOW = 2;
    private final int INVINCIBILITY_FRAMES = 50;
    protected Game game;
    
    public Player(Game g)
    {
        // get this game
        game = g;
        
        setImage("Isaac/backwards1.png");
    }
    
    
    // invincibility methods
    private int noHitFrames = 0;
    
    public boolean invincible()
    {
        return noHitFrames > 0;
    }
    
    public void makeInvincible()
    {
        noHitFrames = INVINCIBILITY_FRAMES;
    }
    
    
    // methods for coordinate geometry
    
    public int getDist(int x, int y)
    {
        return (int) Math.sqrt(Math.pow(x-getX(), 2) + Math.pow(y-getY(), 2));
    }
    
    public void displacePlayer(int dx, int dy)
    {
        int newX = getX()+dx;
        int newY = getY()+dy;
        
        if (newX<32)  newX=32;
        if (newX>480) newX=480;
        if (newY<32)  newY=32;
        if (newY>464) newY=464;
        
        setLocation(newX, newY);
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // check if dead
        if (game.isFading()) {
            return;
        }
        
        // read movements
        int moveSpeed;
        if (game.keyS()) moveSpeed = SPEED_SLOW;
        else moveSpeed = SPEED_NORMAL;
        
        // read the left/right keys
        if (game.keyLeft()) {
            if (!game.keyRight()) {
                displacePlayer(-moveSpeed, 0);
            }
        }
        else if (game.keyRight()) {
            displacePlayer(moveSpeed, 0);
        }
        
        // read the up/down keys
        if (game.keyUp()) {
            if (!game.keyDown()) {
                displacePlayer(0, -moveSpeed);
            }
        }
        else if (game.keyDown()) {
            displacePlayer(0, moveSpeed);
        }
        
        // invincibility frame transparency
        if (noHitFrames > 0) {
            // count down
            noHitFrames--;
            
            // do transparency
            if (noHitFrames == 0) {
                getImage().setTransparency(255);
            }
            else {
                // transparency will be a number between 64 and 128
                getImage().setTransparency(128 - (noHitFrames*64)/INVINCIBILITY_FRAMES);
            }
            setImage(getImage());
        }
    }
}
