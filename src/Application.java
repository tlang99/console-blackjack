/**
 * Entry point for the Blackjack and Poker application. This class sets up the game environment by initializing the game
 * table and starting the main game loop. Currently, it launches a Blackjack game by default.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class Application
{
    public static void main(String[] args)
    {
        new BlackJackTable().play();
    }
}
