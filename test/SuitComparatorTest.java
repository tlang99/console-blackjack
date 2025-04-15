import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuitComparatorTest
{
    private CardComparator cardComparator;
    private Card c1;
    private Card c2;
    private Card c3;

    @BeforeEach
    public void setUp()
    {
        cardComparator = new CardComparator();
        c1 = new Card(Suit.Clubs, 10, true, "Jack");
        c2 = new Card(Suit.Diamonds, 6, true, "Six");
        c3 = new Card(Suit.Clubs, 10, true, "Jack");
    }

    @AfterEach
    public void tearDown()
    {
        cardComparator = null;
        c1 = null;
        c2 = null;
        c3 = null;
    }

    @Test
    @DisplayName("If the suit and the value of a card are the same, compare() will return 0.")
    void compareEqualsTest()
    {
        int value = cardComparator.compare(c1, c3);

        assertEquals(0, value);
    }

    @Test
    @DisplayName("If either the suit or the value of the card being compared is different, compare() will return the difference")
    void compareDoesntEqualTest()
    {
        int value = cardComparator.compare(c1, c2);

        assertEquals(4, value);
    }
}
