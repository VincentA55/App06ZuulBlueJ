import java.util.HashMap;
import java.util.Collection; 
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

    public HashMap<String, Item> inventory;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        energy = 20;

        inventory = new HashMap<String, Item>();
    }

    /**
     * gets the players current energy level
     */
    public int getEnergy()
    {
        return energy;
    }

    /**
     * drains the payers energy by 1 and checks if it falls below 0
     */
    public boolean drainEnergy()
    {
        energy -= 1;
        if (this.energy < 1){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * gets the players inventory
     */
    public String getInventory()
    {
        Collection<Item> items = inventory.values();
        String inventoryList = null;
        if (inventory.isEmpty()){
            inventoryList = "empty";
        }
        else {
            for (Item item : items){
                inventoryList += (" , " + item.getName());
            }
        }
        return inventoryList;
    }

}

