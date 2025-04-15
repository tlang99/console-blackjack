import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a full 52-card deck tailored for Blackjack. Extends the generic Deck class and customizes card values and
 * names to follow Blackjack rules. Face cards (Jack, Queen, King) are valued at 10, and Aces are valued at 11.
 * The deck is automatically shuffled upon creation.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class BlackJackDeck extends Deck
{
    /**
     * Constructs a Blackjack deck by building and shuffling a full 52-card set.
     */
    public BlackJackDeck()
    {
        super();
        buildDeck();
    }

    /**
     * Creates a full deck of 52 cards by looping through each suit and rank, assigning Blackjack-specific values:
     * - 2 through 10 are worth their face value.
     * - Jack, Queen, and King are worth 10.
     * - Ace is worth 11.
     * The deck is then shuffled.
     */
    @Override
    public void buildDeck()
    {
        String cardName = "";
        int cardValue = 0;
        ArrayList<Card> cards = new ArrayList<Card>();

        for (Suit suit : Suit.values())
        {
            for (int i = 2; i <= 14; i++)
            {
                if (i <= 10)
                {
                    cardName = String.valueOf(i);
                    cardValue = i;
                }
                else
                {
                    switch (i)
                    {
                        case 11 -> cardName = "Jack";
                        case 12 -> cardName = "Queen";
                        case 13 -> cardName = "King";
                        case 14 -> cardName = "Ace";
                    }

                    cardValue = (i == 14) ? 11 : 10;
                }

                cards.add(new Card(suit, cardValue, false, cardName));
            }
        }

        Collections.shuffle(cards);
        super.setCards(cards);
    }
}
