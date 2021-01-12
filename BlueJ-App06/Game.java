/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 01/12/2020
 * 
 * Modified and extended by Vincent Assolutissimamente
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Map houseMap;
    
    private TextSpeed textSpeed;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        houseMap.createRooms();
        parser = new Parser();
        textSpeed = new TextSpeed();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
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
        

        currentRoom = bedroom;  // start game in bedroom
        
        
       
    }
    
    

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;

        while (! finished) 
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        textSpeed.fastText("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        textSpeed.slowText("            ");
        System.out.print("Loading");
        textSpeed.superSlowText(".....");
        textSpeed.slowText("                       ");
        textSpeed.slowText("Welcome to the World of Zuul!");
        textSpeed.slowText("World of Zuul is a new, incredibly boring adventure game.");
        textSpeed.fastText("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        textSpeed.fastText(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
            System.out.println("I don't know what you mean...");
            break;

            case HELP:
            printHelp();
            break;

            case GO:
            goRoom(command);
            break;
            
            case USE: //testing 
            map();
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        textSpeed.fastText(currentRoom.getLongDescription());
        System.out.println();
        textSpeed.fastText("Your command words are:");
        parser.showCommands();
        map();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            textSpeed.fastText(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            textSpeed.fastText("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * 
     * place holder for map item
     */
    private void map()
    {
        System.out.println();
        System.out.println("     __________Map___________");
        System.out.println("     ░░░░░░░░░░░░░░░░░░░░");
        System.out.println("     ░┌──┐░░┌────┐░░┌──┐░");
        System.out.println("     ░│░░├──┤░░░░├──┤░░│░");
        System.out.println("     ░└──┘░░└─┬┬─┘░░└──┘░");
        System.out.println("     ░░░░░░░░░││░░░░░░░░░");
        System.out.println("     ░┌──┐░░┌─┴┴─┐░░░░░░░");
        System.out.println("     ░│░░├──┤░░░░│░░░░░░░");
        System.out.println("     ░└──┘░░└─┬┬─┘░░░░░░░");
        System.out.println("     ░░░░░░░░░││░░░░░░░░░");
        System.out.println("     ░░░░░░░┌─┴┴─┐░░░░░░░");
        System.out.println("     ░░░░░░░│░░░░│░░░░░░░");
        System.out.println("     ░░░░░░░└─┬┬─┘░░░░░░░");
        System.out.println("     ░░░░░░░░░││░░░░░░░░░");
        System.out.println("     ░░░░░░░┌─┴┴─┐░░┌──┐░");
        System.out.println("     ░░░░░░░│░░░░├──┤░░│░");
        System.out.println("     ░░░░░░░└────┘░░└──┘░");
        System.out.println("     ░░░░░░░░░░░░░░░░░░░░");
        
    }
}
