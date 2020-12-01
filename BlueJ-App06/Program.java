
/**
 * This class creates an instance of the Game
 * class and then calls on its run method.
 *
  * @version 01/12/2020
 * 
 * Modified and extended by Vincent Assolutissimamente
 */
public class Program
{
    private static Game game;

    /**
     * This class creates and runs an instance of
     * the Game class
     */
    public static void main()
    {
        game = new Game();
        game.play();
    }
}
