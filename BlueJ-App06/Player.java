import java.util.HashMap;
import java.util.Collection; 
/**
 * A player class that represents the user
 * it contains the players Energy, inventory
 *
 * @author Vincent A.
 * @version 15/12/20
 */
public class Player
{
    private int energy;

    public HashMap<String, Item> inventory;

    private String inventoryList;
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
     * only checks if energy falls below 0
     */
    public boolean checkEnergy(){
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

        for (Item item : items){
            inventoryList += (" , " + item.getName());
        }
        
        return inventoryList;
    }

    /**
     * kills the player by draining their energy past 0
     */
    public void kill()
    {
        this.energy = energy - 101;
    }
    
    /**
     * wins the game by increasing the energy to 100
     */
    public void win()
    {
    this.energy = 100;
    }
}

