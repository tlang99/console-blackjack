/**
 * Represents the dealer in a game of Blackjack. The dealer extends the functionality of a Blackjack player by managing
 * the game deck, dealing cards, and resetting the deck when needed.
 *
 * This class uses a BlackJackDeck to draw cards from a full, shuffled 52-card deck.
 * It follows standard Blackjack rules for card dealing.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class BlackJackDealer extends BlackJackPlayer
{
    private BlackJackDeck deck;

    /**
     * Constructs a Blackjack dealer with a given stash amount. Initializes the dealer's name and a new shuffled deck.
     *
     * @param stash The starting amount of money the dealer has.
     */
    public BlackJackDealer(int stash)
    {
        super("Dealer", stash);
        deck = new BlackJackDeck();
    }

    /**
     * Deals a card from the deck. If the deck is empty, it is automatically reset and shuffled.
     *
     * @return A card drawn from the top of the deck.
     */
    public Card deal()
    {
        if(deck.deal() != null)
        {
            return deck.deal();
        }
        else
        {
            resetDeck();
        }

        return deck.deal();
    }

    /**
     * Resets the deck by creating a new shuffled 52-card Blackjack deck.
     */
    public void resetDeck()
    {
        // Initialize the deck as a new instance of the Deck to reset it to 52 random cards again.
        this.deck = new BlackJackDeck();
    }
}