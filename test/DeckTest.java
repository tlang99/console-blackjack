import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * The test class DeckTest.
 *
 * @author Tyler Lang
 * @version 2025.03.22
 */
public class DeckTest
{
    public static Stream deckSource()
    {
        return Stream.of
                (
                Arguments.of(new BlackJackDeck(), new PokerDeck()),
                Arguments.of(new BlackJackDeck(), new PokerDeck())
                );
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
    
    @ParameterizedTest
    @MethodSource("deckSource")
    @DisplayName("Decks should be initialized with 52 cards.")
    public void deckConstructorTest(Deck deck)
    {
        assertEquals(52, deck.cardsLeftInDeck());
    }
    
    @ParameterizedTest
    @MethodSource("deckSource")
    @DisplayName("The deal method should always return a card object")
    public void deckDealTest(Deck deck)
    {
        Card c1 = deck.deal();

        assertTrue(c1 instanceof Card);
    }
    
    @ParameterizedTest
    @MethodSource("deckSource")
    @DisplayName("A shuffled deck should be different than the original.")
    public void deckShuffleTest(Deck deck1, Deck deck2)
    {
        boolean cardsEqual = true;

        for (int i = 0; i < 52; i++)
        {
            Card c1 = deck1.deal();
            Card c2 = deck2.deal();

            if (c1.getValue() != c2.getValue() 
             || c1.getSuit() != c2.getSuit()
             || !c1.getName().equals(c2.getName()))
            {
                cardsEqual = false;
            }
            
            if (cardsEqual == false)
            {
                break;
            }
        }

        assertFalse(cardsEqual);
    }
}