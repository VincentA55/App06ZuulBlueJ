
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
    
    private Room location;
    
    private Room useLocation;
    
    private Game game;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, Room location, Room useLocation)
    {
        this.name = name;
        this.location = location; 
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
     * checks it the current room is the one is associated with
     * 
     */
    public boolean checkIfRightRoom()
    {
        if (game.getCurrentRoom() == useLocation){
        return true;
        }
        else return false;
    }
    
    

    
}
