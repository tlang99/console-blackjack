import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


/**
 * The test class PlayerTest.
 *
 * @author Tyler Lang
 * @version 2025.03.22
 */
public class PlayerTest
{
    /**
     * Passes 2 instances of PokerPlayer and BlackJackPlayer each to be used with various tests.
     * @return 2 instances of both PokerPlayer and BlackJackPlayer.
     */
    static Stream testSource()
    {
        return Stream.of
                (
                Arguments.of(new PokerPlayer(), new BlackJackPlayer()),
                Arguments.of(new PokerPlayer(), new BlackJackPlayer())
                );
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    void tearDown()
    {
    }
    
    @ParameterizedTest
    @MethodSource("testSource")
    @DisplayName("The default constructor for player initializes the object with a stash of 500.")
    void playerConstructorTest(Player p)
    {
        assertEquals(p.getStash(), 500);
    }
    
    // receiveCard test
    @ParameterizedTest
    @MethodSource("testSource")
    @DisplayName("When receiveCard is called, the player's hand will now contain the card specified.")
    void playerReceiveCardTest(Player p)
    {
        Card c1 = new Card(Suit.Clubs, 6, true, "6");

        p.receiveCard(c1, true);

        assertTrue(p.getHand().contains(c1));
    }

    // get/set stash test
    @ParameterizedTest
    @MethodSource("testSource")
    @DisplayName("setStash sets the value of the player's stash to the value given.")
    void playerManageStashTest(Player p)
    {
        // Act
        try
        {
            p.setStash(1000);
        }
        catch (Exception e)
        {

        }
        
        // Assert
        assertEquals(p.getStash(), 1000);
    }
    
    // scoreBlackjack test
    @Test
    @DisplayName("When scoring a hand in a game of blackjack, the score is the sum of the values of the cards in the " +
            "player's hand.")
    void blackJackPlayerScoreHandTest()
    {
        Player p = new BlackJackPlayer();

        p.receiveCard(new Card(Suit.Clubs, 10, true, "Queen"), true);
        p.receiveCard(new Card(Suit.Clubs, 11, true, "Ace"), true);

        assertEquals(p.scoreHand(), 21);
    }
    
    // scoreHand test
    @Test
    @DisplayName("When a straight is the highest rank in a poker player's hand, the player gains 600 points plus the sum" +
            "of the values of the cards in their hand.")
    void pokerPlayerScoreHandTest_Straight()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 6, true, "6"), true);
        p.receiveCard(new Card(Suit.Spades, 7, true, "7"), true);
        p.receiveCard(new Card(Suit.Hearts, 8, true, "Ace"), true);

        assertEquals(621, p.scoreHand());
    }

    @Test
    @DisplayName("When an ace is present in a players hand at the same time as a queen and king, it will form a straight " +
            "as the high card.")
    void pokerPlayerScoreHandTest_AceHigh()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 12, true, "Queen"), true);
        p.receiveCard(new Card(Suit.Spades, 13, true, "King"), true);
        p.receiveCard(new Card(Suit.Hearts, 14, true, "Ace"), true);

        assertEquals(639, p.scoreHand());
    }

    @Test
    @DisplayName("When an ace is present in a player's hand at the same time as a 2 and a 3, it will form a straight " +
            "as the low card.")
    void pokerPlayerScoreHandTest_AceLow()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 12, true, "Queen"), true);
        p.receiveCard(new Card(Suit.Spades, 13, true, "King"), true);
        p.receiveCard(new Card(Suit.Hearts, 14, true, "Ace"), true);

        assertEquals(639, p.scoreHand());
    }

    @Test
    @DisplayName("When a flush is the highest rank in a poker player's hand, the player gains 400 points plus the sum" +
            "of the values of the cards in their hand.")
    void pokerPlayerScoreHandTest_Flush()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 6, true, "6"), true);
        p.receiveCard(new Card(Suit.Clubs, 9, true, "7"), true);
        p.receiveCard(new Card(Suit.Clubs, 2, true, "8"), true);

        assertEquals(417, p.scoreHand());
    }

    @Test
    @DisplayName("When a straight flush is the highest rank in a poker player's hand, the player gains 1,000 points in" +
            "addition to the sum of the value of the cards in their hand.")
    void pokerPlayerScoreHandTest_StraightFlush()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 6, true, "6"), true);
        p.receiveCard(new Card(Suit.Clubs, 7, true, "7"), true);
        p.receiveCard(new Card(Suit.Clubs, 8, true, "8"), true);

        assertEquals(1021, p.scoreHand());
    }

    @Test
    @DisplayName("When a three of a kind is the highest rank in a poker player's hand, the player gains 800 points in addition" +
            "to the sum of the value of the cards in their hand.")
    void pokerPlayerScoreHandTest_ThreeOfAKind()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 6, true, "6"), true);
        p.receiveCard(new Card(Suit.Hearts, 6, true, "6"), true);
        p.receiveCard(new Card(Suit.Spades, 6, true, "6"), true);

        assertEquals(818, p.scoreHand());
    }

    @Test
    @DisplayName("When a pair is the highest rank in a poker player's hand, the player gains 200 points in addition" +
            "to the sum of the value of cards in their hand.")
    void pokerPlayerScoreHandTest_Pair()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 6, true, "6"), true);
        p.receiveCard(new Card(Suit.Spades, 7, true, "7"), true);
        p.receiveCard(new Card(Suit.Hearts, 6, true, "6"), true);

        assertEquals(219, p.scoreHand());
    }

    @Test
    @DisplayName("When a high card is the highest rank in poker player's hand, the player gains 100 points in addition to the " +
            "sum of the value of the cards in their hand.")
    void pokerPlayerScoreHandTest_HighCard()
    {
        Player p = new PokerPlayer();

        p.receiveCard(new Card(Suit.Clubs, 3, true, "6"), true);
        p.receiveCard(new Card(Suit.Hearts, 7, true, "7"), true);
        p.receiveCard(new Card(Suit.Clubs, 13, true, "King"), true);

        assertEquals(123, p.scoreHand());
    }

    // clear hand test
    @ParameterizedTest
    @MethodSource("testSource")
    @DisplayName("clearHand removes all cards from the player's hand.")
    void playerClearHandTest(Player p1, Player p2)
    {
        // Act - both receive the same cards.  p2 will be empty after clear hand, while p1 will not
        p1.receiveCard(new Card(Suit.Clubs, 10, true, "Queen"), true);
        p1.receiveCard(new Card(Suit.Clubs, 11, true, "Ace"), true);
        
        p2.receiveCard(new Card(Suit.Clubs, 10, true, "Queen"), true);
        p2.receiveCard(new Card(Suit.Clubs, 11, true, "Ace"), true);
        
        p2.clearHand();

        // Assert
        assertTrue(p2.getHand().isEmpty());
        assertFalse(p1.getHand().isEmpty());
    }

    // show all cards test
    @ParameterizedTest
    @MethodSource("testSource")
    @DisplayName("showAllCards will make every card in the player's hand visible.")
    void playerShowAllCardsTest(Player p1)
    {
        // Player receives a card face down
        p1.receiveCard(new Card(Suit.Clubs, 6, true, "6"), false);

        // isVisible returns false as the card is not visible
        assertFalse(p1.getHand().getFirst().isVisible());

        // showAllCards makes the card in the player's hand visible
        p1.showAllCards();

        // isVisible now returns true
        assertTrue(p1.getHand().getFirst().isVisible());
    }
}