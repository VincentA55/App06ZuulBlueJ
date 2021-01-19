
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
     * Create all the rooms and items within and link their exits together.
     */
    public void createRooms()
    {
        Room outside,bedroom, bathroom, hallway1, hallway2, spareroom, kitchen, fridge;

        // create the rooms
        bedroom = new Room("Bedroom", "your bedroom. A simple room with a little bit too much lego");
        bathroom = new Room("Bathroom", "the bathroom where you take your business calls. Also plenty of toilet paper");
        hallway1 = new Room("Hallway1", "the hallway outside your room, there is a dog here");
        hallway2 = new Room("Hallway2", "the same hallway? This part leads to the spare room and kitchen");
        spareroom = new Room("Spare room", "the spare room. This is for guests");
        kitchen = new Room("Kitchen", "your kitchen. Food is here, hopefully");
        fridge = new Room ("Walk in Fridge", "a walkin fridge. Have you ever seen Ratatouille? Its like that");
        outside = new Room("Outside", "the outside world, breathe it in");
        
        
        Item toiletPaper, milk, spoon;// creates the items and sets their room
        
        toiletPaper = new Item("Toilet Paper", hallway1);
        toiletPaper.setDescription("Just your standard bog roll. Dont let your dog get a hold of it");
        bathroom.setItems("Toilet Paper",toiletPaper);
        
        milk = new Item("Milk", kitchen);
        milk.setDescription("white and creamy, just like mama used to make");
        fridge.setItems("Milk", milk);
        
        spoon = new Item("Spoon", kitchen);
        spoon.setDescription("Like a fork but for liquids");
        spareroom.setItems("Spoon", spoon);
        
        // initialise room exits
        bedroom.setExit("east", bathroom);
        bedroom.setExit("north", hallway1);
        
        bathroom.setExit("west", bedroom);
        
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
