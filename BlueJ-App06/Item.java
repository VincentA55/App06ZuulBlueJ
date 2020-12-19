
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
    
    private Room location;
    
    private Room useLocation;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, Room location, Room useLocation)
    {
        this.name = name;
        this.location = location; 
        this.useLocation = useLocation;
    }
    
    
    

    
}
