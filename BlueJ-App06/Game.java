
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
 *  rooms by calling on the map class, creates the parser and starts the game.  It also evaluates and
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
    private Map map;

    private TextSpeed textSpeed;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        this.map = new Map();
        map.createRooms();
        this.currentRoom = map.currentRoom;
        parser = new Parser();
        textSpeed = new TextSpeed();
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
        textSpeed.slowText("Welcome to the World of Zuul inspired text based adventure game!");
        textSpeed.slowText("World of Vuul is a new, incredibly boring adventure game.");
        textSpeed.slowText("You awaken in your childhood home, you have coursework due, girl troubles, and no friends");
        textSpeed.slowText("But most importantly, you have an empty stomach.");
        textSpeed.fastText("Time for breakfast");
        System.out.println();
        textSpeed.fastText("Make your way to the kitchen and make something to eat");
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

            case LOOK:
            //look();
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
     * gets the current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * checks the room for items and returns the description if 
     * there is an item
     *
    public void look()
    {
        if (room.itemsInRoom
    }
    /

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
        System.out.println("     ░└──┘░░└─┬┬─┘░░ N ░░");
        System.out.println("     ░░░░░░░░░││░░░W * E ░");
        System.out.println("     ░░░░░░░┌─┴┴─┐░░ S ░░");
        System.out.println("     ░░░░░░░│░░░░│░░░░░░░");
        System.out.println("     ░░░░░░░└─┬┬─┘░░░░░░░");
        System.out.println("     ░░░░░░░░░││░░░░░░░░░");
        System.out.println("     ░░░░░░░┌─┴┴─┐░░┌──┐░");
        System.out.println("     ░░░░░░░│░░░░├──┤░░│░");
        System.out.println("     ░░░░░░░└────┘░░└──┘░");
        System.out.println("     ░░░░░░░░░░░░░░░░░░░░");

    }
}
