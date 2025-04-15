import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * The test class CardTest.
 *
 * @author  Tyler Lang
 * @version 2025.03.22
 */
public class CardTest
{
    /**
     * Default constructor for test class CardTest
     */
    public CardTest()
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
    public void cardConstructorTest()
    {
        // Arrange
        Card c1;
        
        // Act
        c1 = new Card(Suit.Clubs, 10, true, "Queen");
        
        // Assert
        assertEquals(c1.getSuit(), Suit.Clubs);
        assertEquals(c1.getValue(), 10);
        assertEquals(c1.getName(), "Queen");
        
    }
    
    @Test
    public void cardShowHideTest()
    {
        // Arrange
        Card c1 = new Card(Suit.Clubs, 10, true, "Jack");
        Card c2 = new Card(Suit.Clubs, 10, false, "Jack");
        
        // Act
        c1.hide();
        c2.show();
        
        // Assert
        assertFalse(c1.isVisible());
        assertTrue(c2.isVisible());
    }

    @Test
    @DisplayName("Two different cards will have different hash number.")
    void hashCodeDoesntEqualTest()
    {
        Card c1 = new Card(Suit.Clubs, 10, true, "Jack");
        Card c2 = new Card(Suit.Spades, 10, true, "Queen");

        int result1 = c1.hashCode();
        int result2 = c2.hashCode();

        assertNotEquals(result1, result2);
    }

    @Test
    @DisplayName("Two identical cards will always have the same hash number.")
    void hashCodeEqualsTest()
    {
        Card c1 = new Card(Suit.Clubs, 10, true, "Jack");
        Card c2 = new Card(Suit.Clubs, 10, true, "Jack");

        int result1 = c1.hashCode();
        int result2 = c2.hashCode();

        assertEquals(result1, result2);
    }

    @Test
    @DisplayName("compareTo() returns zero whenever the card being compared is of the same value.")
    void compareToEqualsTest()
    {
        Card c1 = new Card(Suit.Clubs, 10, true, "Jack");
        Card c2 = new Card(Suit.Clubs, 10, true, "Jack");

        assertEquals(0, c1.compareTo(c2));
    }

    @Test
    @DisplayName("compareTo() returns the difference in the value whenever the card being compared is not the same value")
    void compareToDoesntEqualTest()
    {
        Card c1 = new Card(Suit.Clubs, 10, true, "Jack");
        Card c2 = new Card(Suit.Clubs, 7, true, "Seven");

        assertEquals(3, c1.compareTo(c2));
    }

    @Test
    @DisplayName("equals() always returns true if two cards have the same suit, value and name.")
    void equalsTrueTest()
    {
        Card c1 = new Card(Suit.Clubs, 10, true, "Jack");
        Card c2 = new Card(Suit.Clubs, 10, true, "Jack");

        assertTrue(c1.equals(c2));
    }

    @Test
    @DisplayName("equals() always returns false if any two cards do not have the same suit, value and name.")
    void equalsFalseTest()
    {
        Card c1 = new Card(Suit.Clubs, 10, true, "Jack");
        Card c2 = new Card(Suit.Spades, 10, true, "Jack");

        assertFalse(c1.equals(c2));
    }
}