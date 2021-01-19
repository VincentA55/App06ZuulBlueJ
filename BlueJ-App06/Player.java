import java.util.HashMap;
/**
 * A player class that represents the user
 * it contains the players Energy, inventory, and location
 *
 * @author Vincent A.
 * @version 15/12/20
 */
public class Player
{
    private int energy;

    private HashMap<String, Item> inventory;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        energy = 20;

        inventory = new HashMap<String, Item>();
    }

    /**
     * adds an item to the inventory
     */
    public void addInventory()
    {
        
    }
}
