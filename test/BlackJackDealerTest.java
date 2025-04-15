import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class DealerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BlackJackDealerTest
{
    /**
     * Default constructor for test class DealerTest
     */
    public BlackJackDealerTest()
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
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void dealerDefaultConstructorTest()
    {
        // Arrange
        BlackJackDealer d;
        
        // Act - this also tests the Player overloaded constructor
        d = new BlackJackDealer(500);
        
        // Assert
        assertEquals(d.getStash(), 500);
        assertTrue(d.deal()  instanceof Card);
    }
    
    @Test
    public void dealerInheritanceTest()
    {
        // Arrange
        BlackJackPlayer d;
        
        // Act - Only works through polymorphism
        d = new BlackJackDealer(500);
        
        // Assert
        assertTrue(d instanceof BlackJackPlayer);
    }
    
    @Test
    public void dealerResetDeckTest()
    {
        // Arrange
        BlackJackDealer d = new BlackJackDealer(500);
        
        // Act - get all the cards, reset the deck, and get another.  If the deck isn't reset, it'll return null
        for (int i = 0; i < 52; i++)
        {
            d.deal();            
        }
        
        d.resetDeck();
        
        Card c = d.deal();
        
        // Assert
        assertNotNull(c);
    }
    
        @Test
    public void dealerDealTest()
    {
        // Arrange
        BlackJackDealer d = new BlackJackDealer(500);

        // Act
        Card c1 = d.deal();
        
        // Assert
        assertTrue(c1 instanceof Card);
    }
}