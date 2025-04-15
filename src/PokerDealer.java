/**
 * The PokerDealer class represents the dealer in a poker game. It extends PokerPlayer and manages a PokerDeck to deal
 * cards to players.The dealer can also reset the deck when needed.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class PokerDealer extends PokerPlayer
{
    private PokerDeck deck;

    /**
     * Constructs a PokerDealer with the specified initial stash.
     * Initializes a new PokerDeck and sets the dealer's name.
     *
     * @param stash the amount of money the dealer starts with.
     */
    public PokerDealer(int stash)
    {
        super("Dealer", stash);
        deck = new PokerDeck();
    }

    /**
     * Deals a card from the deck. If the deck is empty, it resets the deck and then deals a new card.
     *
     * @return the dealt Card
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
     * Resets the dealer's deck by creating a new instance of PokerDeck.
     */
    public void resetDeck()
    {
        this.deck = new PokerDeck();
    }
}
