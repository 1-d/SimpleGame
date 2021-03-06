import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Scanner;

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * The LevelReader class reads in a Scanner and starts events at certain times.
 * Currently, this class spawns images and enemies.
 * 
 * @author Jason Yuen
 * @version a0.1
 */
public class LevelReader
{
    
    // variables
    private final Game game;
    private int time = 0;
    
    // queues
    private Queue<Integer>            eventTime = new LinkedList<Integer>();
    private Queue<Integer>            eventID   = new LinkedList<Integer>();
    private Queue<ArrayList<String> > eventInfo = new LinkedList<ArrayList<String> >();
    
    /**
     * Constructor for objects of class LevelReader.
     * Converts the Scanner's input into 3 queues for easier processing.
     */
    public LevelReader(Game g, Scanner sc)
    {
        game = g;
        
        // read through the entire Scanner
        while (sc.hasNextInt()) {
            // read in a new event
            // do basic info first
            eventTime.add(sc.nextInt());
            eventID.add(sc.nextInt());
            
            // do ArrayList info
            int listLength = sc.nextInt();
            ArrayList<String> al = new ArrayList<String>();
            // add in the ArrayList elements
            for (int i=0; i<listLength; i++) {
                al.add(sc.next());
            }
            // done creation
            eventInfo.offer(al);
        }
    }
    
    /**
     * Advance this object's time by 1.
     * If there are events at this time, do the events.
     */
    public void tick() {
        // increase time
        time++;
        
        // do events that occur at this time
        while (!eventTime.isEmpty() && eventTime.peek() < time) {
            // get event info
            eventTime.poll();
            int id = eventID.poll();
            ArrayList<String> al = eventInfo.poll();
            
            // do the appropriate action
            switch (id) {
                case 10:
                    new DisplayerMove(game, al);
                    break;
                case 100:
                    new Enemy100(game, al);
                    break;
                case 101:
                    new Enemy101(game, al);
                    break;
                case 102:
                    new Enemy102(game, al);
                    break;
                case 103:
                    new Enemy103(game, al);
                    break;
                case 104:
                    new Enemy104(game, al);
                    break;
                case 110:
                    new Enemy110(game, al);
                    break;
                default:
                    System.out.printf("Ignored unrecognized event ID %d\n", id);
            }
        }
    }
}
