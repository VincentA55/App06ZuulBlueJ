
/**
 * This class creates the rooms and sets the
 * items and objects within
 *
 * @author Vincent A.
 * @version 12/01/2021
 */
public class Map
{
    
    public Room currentRoom;

    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
        Room outside,bedroom, bathroom, hallway1, hallway2, spareroom, kitchen, fridge;

        // create the rooms
        bedroom = new Room("Bedroom", "the room that you sleep in");
        bathroom = new Room("Bathroom", "placeholder text toiletpaper is here");
        hallway1 = new Room("Hallway1", "the hallway outside your room, there is a dog here");
        hallway2 = new Room("Hallway2", "leads to the spare room and kitchen");
        spareroom = new Room("Spare room", " this is for guests");
        kitchen = new Room("Kitchen", "food is here, hopefully");
        fridge = new Room ("Walk in Fridge", "a walkin fridge");
        outside = new Room("Outside", "the outside world");
        
        Item toiletPaper;// creates the items 
        
        toiletPaper = new Item("Toilet Paper",bathroom, hallway1);

        // initialise room exits
        bedroom.setExit("east", bathroom);
        bedroom.setExit("north", hallway1);
        
        bathroom.setExit("west", bedroom);
        bathroom.setItems("Toilet Paper",toiletPaper);
        
        hallway1.setExit("south", bedroom);
        hallway1.setExit("north", hallway2);
        
        hallway2.setExit("south", hallway1);
        hallway2.setExit("west", spareroom);
        hallway2.setExit("north", kitchen);
        
        spareroom.setExit("east", hallway2);
        
        kitchen.setExit("east", outside);
        kitchen.setExit("west", fridge);
        
        fridge.setExit("east", kitchen);
        
        
        outside.setExit("west", kitchen);
        

        this.currentRoom = bedroom;  // start game in bedroom
        
        
       
    }

    
}
