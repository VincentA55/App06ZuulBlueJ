
/**
 * Takes a string and outputs it slowly one letter at a time
 *
 * @author Vincent Assolutissimamente 
 * @version 01/12/2020
 */
public class TextSpeed
{
    public void slowText(String input)
    {
        for (int index = 0; index < input.length(); index++) {

            System.out.print(input.charAt(index));
            try {
                Thread.sleep(60);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("");
    }
}
