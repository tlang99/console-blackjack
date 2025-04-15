import java.util.ArrayList;

public class BlackJackPlayer extends Player
{
    /**
     * Represents a player in a game of Blackjack. Extends the base Player class and implements Blackjack-specific
     * scoring rules, including logic for Aces and detecting a natural Blackjack.
     *
     * @author Tyler Lang
     * @version 2025.04.03
     */

    /**
     * Constructs a Blackjack player with default name and stash values.
     */
    public BlackJackPlayer()
    {
        super();
    }

    /**
     * Constructs a Blackjack player with a specific name and stash amount.
     *
     * @param name  The name of the player.
     * @param stash The starting money the player has.
     */
    public BlackJackPlayer(String name, int stash)
    {
        super(name, stash);
    }

    /**
     * Calculates the score of the player's hand according to Blackjack rules.
     * Aces are counted as 11 or 1 depending on whether the total exceeds 21.
     * If the hand consists of exactly two cards that sum to 21, it's a Blackjack.
     *
     * Only visible cards are scored. If the score exceeds 21, Aces are downgraded
     * from 11 to 1 to avoid busting, if possible.
     *
     * @return The total score of the hand.
     */
    @Override
    public int scoreHand()
    {
        int score = 0;
        int aceCount = 0;
        ArrayList<Card> hand = super.getHand();

        for (Card card : hand)
        {
            if (card.isVisible())
            {
                score += card.getValue();
            }
            if(card.getName().toLowerCase().contains("ace"))
            {
                aceCount++;
            }
        }

        while(score > 21 && aceCount > 0)
        {
            score -= 10;
        }

        return score;
    }

    public boolean hasBlackJack()
    {
        ArrayList<Card> hand = getHand();

        return hand.size() == 2 && scoreHand() == 21;
    }
}