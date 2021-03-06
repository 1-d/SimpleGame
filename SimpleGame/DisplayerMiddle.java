import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayerMiddle here.
 * 
 * @author Jason Yuen
 * @version a0.01
 */
public class DisplayerMiddle extends Displayer
{
    /**
     * Initialize the DisplayerMiddle's image.
     * The given String is the path to an image.
     */
    public DisplayerMiddle(String imagePath) {
        setImage(imagePath);
    }
    
    /**
     * Alternate initialization.
     * The actor's image will be set to the specific GreenfootImage
     */
    public DisplayerMiddle(GreenfootImage gfImage) {
        setImage(gfImage);
    }
}
