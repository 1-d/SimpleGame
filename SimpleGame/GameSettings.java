import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The GameSettings object stores all of the game's information in a single object.
 * This object will be passed around all of the Worlds.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class GameSettings
{
    
    /**
     * Constructor for the GameSettings object.
     * Nothing needs to be initialized since the variables are already set.
     */
    public GameSettings()
    {
        // do nothing
    }
    
    
    // level variable
    private int level = 1;
    
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    
    // highest level variable
    private int highestLevel = 1;
    
    public void setHighestLevel(int highestLevel)
    {
        this.highestLevel = highestLevel;
    }
    
    public int getHighestLevel()
    {
        return highestLevel;
    }
    
    
    // difficulty variable
    private int difficulty = 1;
    
    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }
    
    public int getDifficulty()
    {
        return difficulty;
    }
    
    
    // current music that is playing
    GreenfootSound bgMusic = null;
    String musicPath = null;
    
    public void setMusic(String music)
    {
        // check if already playing this song
        if (music.equals(musicPath)) {
            return;
        }
        
        // remove existing music
        if (bgMusic != null) {
            bgMusic.stop();
        }
        
        // add new music
        musicPath = music;
        bgMusic = new GreenfootSound(music);
        bgMusic.playLoop();
        bgMusic.setVolume(musicVolume);
    }
    
    
    // musicVolume variable
    private int musicVolume = 100;
    
    public void setMusicVolume(int musicVolume)
    {
        this.musicVolume = musicVolume;
        
        // change volume
        if (bgMusic != null) {
            bgMusic.setVolume(musicVolume);
        }
    }
    
    public int getMusicVolume()
    {
        return musicVolume;
    }
    
}
