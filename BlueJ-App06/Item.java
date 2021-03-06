
/**
 * A class for the various items a player must collect to complete
 * the game
 *
 * @author Vincent A.
 * @version 15/12/20
 */
public class Item
{
    private String name;
    
    private String description;
    
    private Room useLocation;
    
    private Game game;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name,Room useLocation)
    {
        this.name = name;
         
        this.useLocation = useLocation;
    }
    
    /**
     * sets the descrpition of an item
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
     * gets the description of the item
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * gets the name of the item
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Returns the item as a string
     */
    public String intoString()
    {
        return (name + " " + description);
    }
    
    /**
     * checks it the current room is the one is associated with
     * 
     */
    public boolean checkIfRightRoom(Room currentRoom)
    {
        if (currentRoom == useLocation){
        return true;
        }
        else return false;
    }
}
