import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * An abstract class representing a generic player in a card game. Provides core functionality such as managing the
 * player's hand, name, and stash. Intended to be extended by subclasses that implement game-specific
 * scoring rules. This class also provides basic methods to receive cards, clear a hand, and reveal cards.
 * The scoreHand() method is abstract and must be implemented in subclasses.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public abstract class Player
{
    private String name;
    private ArrayList<Card> hand;
    private int stash;
    private static final Logger log = LogHelper.getLogger(Player.class);

    /**
     * Constructs a default Player with the name "Player", an empty hand,
     * a starting stash of 500, and initializes logging support.
     */
    public Player()
    {
        name = "Player";
        hand = new ArrayList<>();

        try
        {
            setStash(500);
        }
        catch (Exception e)
        {
            log.warning(e.getMessage());
        }
    }

    /**
     * Constructs a Player with a custom name and initial stash.
     * Initializes an empty hand and sets up logging support.
     *
     * @param name  The name of the player.
     * @param stash The starting amount of money the player has.
     */
    public Player(String name, int stash)
    {
        hand = new ArrayList<>();
        this.name = name;

        try
        {
            setStash(stash);
        }
        catch (Exception e)
        {
            log.warning(e.getMessage());
        }
    }

    /**
     * Adds a card to the player's hand and sets its visibility.
     *
     * @param card The card to be added.
     * @param visibility If true, the card is shown; otherwise, it is hidden.
     */
    public void receiveCard(Card card, boolean visibility)
    {
        if(visibility)
        {
            card.show();
            hand.add(card);
        }
        else
        {
            card.hide();
            hand.add(card);
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the amount of money the player has for betting.
     *
     * @param stash The amount to set; must be greater than or equal to 0.
     * @throws Exception If the stash is negative.
     */
    public void setStash(int stash)
    {
        if (stash >= 0)
        {
            this.stash = stash;
        }
        else
        {
            System.err.println("The stash cannot be less than 0.");
        }
    }

    /**
     * Returns the current amount of money the player has.
     *
     * @return The player's stash.
     */
    public int getStash()
    {
        return stash;
    }

    /**
     * Returns the list of cards currently in the player's hand.
     *
     * @return An ArrayList of Card objects.
     */
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    private String getName()
    {
        return name;
    }

    /**
     * Removes all cards from the player's hand.
     * Typically used to reset the hand between rounds.
     */
    public void clearHand()
    {
        hand.clear();
    }

    /**
     * Calculates the total score of the player's hand.
     * The exact scoring logic is defined in subclasses based on the specific game.
     *
     * @return The total score of the hand.
     */
    public abstract int scoreHand();

    /**
     * Makes all cards in the player's hand visible (face-up).
     */
    public void showAllCards()
    {
        for(Card card : hand)
        {
            card.show();
        }
    }

    /**
     * Returns a string summarizing the player's name, stash, hand score, and visible cards.
     *
     * @return A formatted string with player information and card details.
     */
    @Override
    public String toString()
    {
        String player = name + " has $" + stash + "\n";
        player += "Current points: " + scoreHand() + "\n";

        for(Card card : hand)
        {
            player += card.toString() + "\n";
        }

        return player;
    }
}