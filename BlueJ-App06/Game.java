import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;

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
    private Player player;
    private boolean gameOver;
    private TextSpeed textSpeed;
    private int winCondition;

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
        player = new Player();
        winCondition = 0;
        gameOver = false;
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
            if(!player.checkEnergy() && player.getEnergy() > 99){
                System.out.println(" ");
                System.out.println(player.getEnergy());
                textSpeed.fastText("You have filled your stomach and are ready for the day ahead!");
                textSpeed.fastText("Congratulations, you are Win!");
                finished = true;
            }
            else if (player.getEnergy() < 1){
                textSpeed.fastText("You have run out of enery and passed out!");
                finished = true;
            }
            else {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
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
        textSpeed.slowText("But more importantly, you have an empty stomach.");
        textSpeed.slowText("Time for breakfast");
        System.out.println();
        map();
        System.out.println();
        textSpeed.fastText("Make your way to the kitchen and get something to eat");
        textSpeed.fastText("Fill your energy up to 100%, and you will be ready for the day ahead");
        textSpeed.fastText("Dont let it reach 0!, or youll pass out and die.");
        textSpeed.fastText("No pressure! Good luck!");
        System.out.println();
        textSpeed.slowText("Tip: dont forget to `look´ around each room");
        textSpeed.fastText("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        printStats();
        textSpeed.fastText(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game,or the player reacher 0 energy,  false otherwise.
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
            useItem(command);
            break;

            case TAKE:
            takeItem();
            break;

            case LOOK:
            look();
            break;

            case STATS:
            printStats();
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
            System.out.println("You can not go that way!");
        }
        else {
            currentRoom = nextRoom;
            this.gameOver = player.drainEnergy();
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
     * adds an item to the players inventory
     */
    public void takeItem()
    {
        this.player.inventory.putAll(currentRoom.itemsInRoom);

        Collection<Item> items = currentRoom.itemsInRoom.values();
        if(items.isEmpty()){
            System.out.println("There is nothing here to take!"); 
        }
        else{
            for (Item item : items)
            {
                currentRoom.itemsInRoom.get(item);
                String things = (item.getName() + " added to inventory");
                System.out.println(things);
            }
            currentRoom.itemsInRoom.clear();
        }
    }

    /**
     * checks the room for items and returns the description if 
     * there is an item
     */
    public void look()
    {
        currentRoom.printItems();
    }

    /**
     * checks if the item name matches the input
     * then if it does, then it checks if the room matches its "use room"
     */
    private void useItem(Command command)
    {
        String itemName = command.getSecondWord();

        Collection<Item> heldItems = player.inventory.values();

        boolean finished = false;

        for (Item item : heldItems){
            while (finished == false){
                if (itemName != null && itemName.equals(item.getName())){
                    if (item.checkIfRightRoom(currentRoom) == true){
                        System.out.println(item.getName() + " has been used");

                        if(item.getName().equals("Toilet-Paper")){
                            System.out.println("The dog grabs the Toilet-Paper and goes crazy, lucky it didnt grab you");
                            System.out.println("You may now pass safely");
                            player.inventory.remove("Toilet-Paper");
                        }
                        if(item.getName().equals("Spoon")){
                            System.out.println("You put the Spoon in the bowl");
                            player.inventory.remove("Spoon");
                            winCondition += 1;
                        }
                        if(item.getName().equals("Milk")){
                            System.out.println("You pour the Milk into the bowl");
                            player.inventory.remove("Milk");
                            winCondition += 1;
                        }
                        if(item.getName().equals("Poison")){
                            System.out.println("You ingest the Poison and die!");
                            player.kill();
                        }
                        if (winCondition == 2){
                            player.win();
                        }
                        finished = true;
                        break;
                    }
                    else if (item.checkIfRightRoom(currentRoom) == false){
                        System.out.println(item.getName() + " cant be used here!");
                        finished = true;
                        break;
                    }
                }
                else
                {
                    System.out.println("Use what?");
                    finished = true;
                    break;
                }
            }
            break;
        }

    }

    /**
     * prints out the players current stats and items
     */
    public void printStats()
    {
        System.out.println(" ");
        System.out.println("Energy : " + player.getEnergy() + "/100");
        System.out.println("Inventory : " + player.getInventory());
        System.out.println(" ");
    }

    /**
     * 
     * place holder for map item
     */
    private void map()
    {
        System.out.println();
        System.out.println("     ________________Map______________");
        textSpeed.superFastText("     ░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        textSpeed.superFastText("     ┌─────┐░░┌──────┐░░┌──────┐░");
        textSpeed.superFastText("     │fridge├──┤kitchen├──┤outside│░");
        textSpeed.superFastText("     └─────┘░░└─┬┬───┘░░└──────┘░");
        textSpeed.superFastText("     ░░░░░░░░░░░││░░░░░░░░░░░░░░░");
        textSpeed.superFastText("     ░┌────┐░░┌─┴┴─┐░░░░░░░░░░░░░");
        textSpeed.superFastText("     ░│Spare├──┤░░░░│░░░░░░░░░░░░░");
        textSpeed.superFastText("     ░│room │░░└─┬┬─┘░░░░░░░░ N ░░");
        textSpeed.superFastText("     ░└────┘░░░░││░░░░░░░░░W * E ░");
        textSpeed.superFastText("     ░░░░░░░░░┌─┴┴─┐░░░░░░░░ S ░░");
        textSpeed.superFastText("     ░░░░░░░░░│░░░░│░░░░░░░░░░░░░");
        textSpeed.superFastText("     ░░░░░░░░░└─┬┬─┘░░░░░░░░░░░░░");
        textSpeed.superFastText("     ░░░░░░░░░░░││░░░░░░░░░░░░░░░");
        textSpeed.superFastText("     ░░░░░░░░░┌─┴┴───┐░░┌───────┐");
        textSpeed.superFastText("     ░░░░░░░░░│bedroom├──┤bathroom│");
        textSpeed.superFastText("     ░░░░░░░░░└──────┘░░└───────┘");
        textSpeed.superFastText("     ░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
    }
}
