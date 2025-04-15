import java.util.Comparator;

/**
 * A comparator for sorting cards by value and suit. Useful in games like Poker or Blackjack where card order matters.
 * Cards are compared by numeric value first, then by suit order if values match.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class CardComparator implements Comparator<Card>
{

    /**
     * Compares two cards by value, and if equal, by suit.
     *
     * @param card1 the first card to be compared
     * @param card2 the second card to be compared
     * @return a negative integer if card1 is less than card2, zero if equal, or a positive integer if card1 is greater
     * than card2
     */
    @Override
    public int compare(Card card1, Card card2)
    {
        int cardValue = card1.getValue() - card2.getValue();

        if(cardValue != 0)
        {
            return cardValue;
        }

        return card1.getSuit().ordinal() - card2.getSuit().ordinal();
    }
}
