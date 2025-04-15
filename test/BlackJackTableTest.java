import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test class TableTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BlackJackTableTest
{
    private BlackJackTable table;
    private BlackJackPlayer player;
    private BlackJackDealer dealer;

    /**
     * Default constructor for test class TableTest
     */
    public BlackJackTableTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        table = new BlackJackTable();
        player = table.getPlayer();
        dealer = table.getDealer();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        table = null;
        player = null;
        dealer = null;
    }

    // Score game dealer Blackjack
    @Test
    public void scoreGameDealerBlackjackTest()
    {
        // Arrange
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 11, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 11, true, "Ace"), false); // First card face down
        dealer.receiveCard(new Card(Suit.values()[2], 10, true, "10"), true);
        
        // Act
        player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        table.scoreGame();
        
        // Assert
        assertTrue(dealer.getStash() == (dealerStartStash + 10));
    }
    
    // Score game player Blackjack
    @Test
    public void scoreGamePlayerBlackjackTest()
    {
        // Arrange
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 11, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 10, true, "10"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 11, true, "Ace"), false); // First card face down
        dealer.receiveCard(new Card(Suit.values()[2], 2, true, "2"), true);
        
        // Act
        player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        table.scoreGame();

        // Assert
        assertTrue(player.getStash() == (playerStartStash + 10));
    }    
    
    // Score game dealer wins
    @Test
    public void scoreGameDealerWinsTest()
    {
        // Arrange
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 11, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 11, true, "Ace"), false); // First card face down
        dealer.receiveCard(new Card(Suit.values()[2], 6, true, "6"), true);
        
        // Act
        player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        table.scoreGame();
        
        // Assert
        assertTrue(dealer.getStash() == (dealerStartStash + 10));
    }

    // Score game player wins
    @Test
    public void scoreGamePlayerWinsTest()
    {
        // Arrange
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 11, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 6, true, "6"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 11, true, "Ace"), false); // First card face down
        dealer.receiveCard(new Card(Suit.values()[2], 2, true, "2"), true);
        
        // Act
        player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        table.scoreGame();
        
        // Assert
        assertTrue(player.getStash() == (playerStartStash + 10));
    }    

    // Score game push
    @Test
    public void scoreGamePushTest()
    {
        // Arrange
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 11, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 6, true, "6"), true);
        player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 11, true, "Ace"), false);
        dealer.receiveCard(new Card(Suit.values()[2], 6, true, "6"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 2, true, "2"), true);

        // Act
        player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        try
        {
            table.setBet(10);
            table.setDealerBet(10);
        }
        catch (IllegalBetException e)
        {

        }
        table.setPot(20);
        dealer.showAllCards();
        table.scoreGame();

        // Assert
        assertTrue(player.getStash() == playerStartStash);
        assertTrue(dealer.getStash() == dealerStartStash);
        assertTrue(table.getPot() == 0);
    }

    // Score game push
    @Test
    public void scoreGamePushAllInTest()
    {
        // Arrange
        player.setStash(500);
        dealer.setStash(100);
        
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 11, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 6, true, "6"), true);
        player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 11, true, "Ace"), false);
        dealer.receiveCard(new Card(Suit.values()[2], 6, true, "6"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 2, true, "2"), true);

        // Act - dealr goes all-in because can't match 200
        player.setStash(player.getStash() - 200);
        dealer.setStash(0);

        try
        {
            table.setBet(200);
            table.setDealerBet(100);
        }
        catch (IllegalBetException e)
        {

        }

        table.setPot(300);
        dealer.showAllCards();
        table.scoreGame();

        // Assert
        assertTrue(player.getStash() == playerStartStash);
        assertTrue(dealer.getStash() == dealerStartStash);
        assertTrue(table.getPot() == 0);
    }    
    
    // Score game player bust
    @Test
    public void scoreGamePlayerBustTest()
    {
        // Arrange
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 10, true, "King"), true);
        player.receiveCard(new Card(Suit.values()[1], 10, true, "Queen"), true);
        player.receiveCard(new Card(Suit.values()[1], 6, true, "6"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 11, true, "Ace"), false); // First card face down
        dealer.receiveCard(new Card(Suit.values()[2], 6, true, "6"), true);
        
        // Act
        player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        table.scoreGame();

        // Assert
        assertTrue(dealer.getStash() == (dealerStartStash + 10));
    }    
    
    // Score game dealer bust
    @Test
    public void scoreGameDealerBustTest()
    {
        // Arrange
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 11, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 6, true, "6"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 10, true, "King"), false); // First card face down
        player.receiveCard(new Card(Suit.values()[2], 10, true, "Queen"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 6, true, "6"), true);
        
        // Act
        player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        table.scoreGame();

        // Assert
        assertTrue(player.getStash() == (playerStartStash + 10));
    }
}
