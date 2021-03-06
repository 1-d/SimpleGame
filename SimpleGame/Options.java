import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * The Options World contains one option.
 * You can choose to play/mute the background music.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class Options extends World
{
    
    private GameSettings gameSettings;
    
    // debug
    private final boolean DEBUG = false;
    
    // constants
    private final String BG_IMAGE_PATH = "OptionScreen.png";
    
    // variables
    private boolean musicOn;
    
    // displayers
    // all of the buttons are transparent and set to the top
    private DisplayerTop backButton;
    private DisplayerTop yesButton;
    private DisplayerTop noButton;
    private DisplayerMiddle fly;        // not a button
    
    /**
     * Constructor for the Options world.
     */
    public Options(GameSettings gs)
    {
        // set screen to 512x512 with 1x1 pixels
        super(512, 512, 1);
        
        // set paint order for the title screen
        // earlier class is drawn on a later class
        setPaintOrder(DisplayerTop.class,
                      DisplayerMiddle.class,
                      GifDisplayer.class);
        
        // read game settings
        gameSettings = gs;
        
        // make the background
        addObject(new GifDisplayer(BG_IMAGE_PATH), 256, 256);
        
        // make buttons
        GreenfootImage temp;
        
        // back button
        temp = new GreenfootImage(90, 120);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        backButton = new DisplayerTop(temp);
        addObject(backButton,45,452);
        
        // yes button
        temp = new GreenfootImage(22, 36);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        yesButton = new DisplayerTop(temp);
        addObject(yesButton,286,225);
        
        // no button
        temp = new GreenfootImage(22, 36);
        if (DEBUG) temp.setColor(new Color(1f,1f,1f,0.5f));
        if (DEBUG) temp.fill();
        noButton = new DisplayerTop(temp);
        addObject(noButton,333,225);
        
        // fly
        musicOn = (gameSettings.getMusicVolume() > 0);
        fly = new DisplayerMiddle("100flies1.png");
        addObject(fly,0,0);
        moveFly();
    }
    
    /**
     * Choose position of fly, based on musicOn.
     */
    private void moveFly()
    {
        if (musicOn) {
            // above yes button
            fly.setLocation(yesButton.getX(), yesButton.getY()-25);
        }
        else {
            // above no button
            fly.setLocation(noButton.getX(), noButton.getY()-25);
        }
    }
    
    /**
     * Every act, this World reads the user input and responds appropriately.
     */
    public void act()
    {
        // back button
        if (Greenfoot.isKeyDown("escape") || Greenfoot.mouseClicked(backButton)) {
            Greenfoot.setWorld(new Title(gameSettings));
        }
        
        // yes button
        if (Greenfoot.isKeyDown("left") || Greenfoot.mouseClicked(yesButton)) {
            musicOn = true;
            gameSettings.setMusicVolume(100);
            moveFly();
        }
        
        // no button
        if (Greenfoot.isKeyDown("right") || Greenfoot.mouseClicked(noButton)) {
            musicOn = false;
            gameSettings.setMusicVolume(0);
            moveFly();
        }
    }
}
