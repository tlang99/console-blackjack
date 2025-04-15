import java.util.ArrayList;

/**
 * Abstract class representing a standard playing card deck. Provides core functionality for managing a deck of cards,
 * such as dealing, setting, and retrieving cards. Subclasses must implement the buildDeck() method to populate
 * the deck with specific cards (e.g., Blackjack-specific cards).
 *
 * Used as a base class for game-specific decks.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public abstract class Deck
{
    private ArrayList<Card> cards = new ArrayList<>();

    /**
     * Constructs an empty deck. Subclasses should call buildDeck() to populate it.
     */
    public Deck()
    {

    }

    /**
     * Retrieves the current list of cards in the deck.
     *
     * @return An ArrayList of Card objects.
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    /**
     * Sets the internal list of cards in the deck.
     *
     * @param cards The new list of cards to use.
     */
    public void setCards(ArrayList<Card> cards)
    {
        this.cards = cards;
    }

    /**
     * Deals (removes and returns) the top card from the deck.
     * Returns null if the deck is empty.
     *
     * @return The top Card in the deck, or null if the deck is empty.
     */
    public Card deal()
    {
        if (cardsLeftInDeck() != 0)
        {
            return cards.remove(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the number of cards remaining in the deck.
     *
     * @return The number of cards left.
     */
    public int cardsLeftInDeck()
    {
        return cards.size();
    }

    /**
     * Returns a string listing all cards currently in the deck.
     *
     * @return A newline-separated string of all card representations.
     */
    @Override
    public String toString()
    {
        StringBuilder allCards = new StringBuilder();

        for (Card card : cards)
        {
            allCards.append(card.toString()).append("\n");
        }

        return allCards.toString();
    }

    /**
     * Abstract method for building a complete deck of cards.
     * Subclasses must implement this to define the contents and structure of the deck.
     */
    public abstract void buildDeck();

}