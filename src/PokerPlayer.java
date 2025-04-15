import java.util.ArrayList;

/**
 * Represents a player in a game of Poker, responsible for evaluating and scoring the player's hand. This class extends
 * Player and implements logic to determine hand rankings such as straight, flush, three of a kind, pair, and
 * combinations like straight flush. Each hand is scored based on its ranking and the total value of the cards.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class PokerPlayer extends Player
{
    private CardComparator cardComparator = new CardComparator();

    /**
     * Constructs a new PokerPlayer with an empty hand.
     */
    public PokerPlayer()
    {
        super();
    }

    /**
     * Constructs a new PokerPlayer with a specified name and starting stash amount.
     * Initializes the player's hand and sets up the card comparator used for hand evaluation.
     *
     * @param name  The name of the player.
     * @param stash The amount of money the player starts with.
     */
    public PokerPlayer(String name, int stash)
    {
        super(name, stash);
    }

    /**
     * Evaluates the player's hand and assigns a score based on Poker hand rankings.
     * Higher scores are given to stronger hands such as a straight flush or three of a kind.
     *
     * @return An integer score representing the score of the hand.
     */
    @Override
    public int scoreHand()
    {
        int score = 0;

        if(isStraightFlush())
        {
            score = 1000 + calculateCardRankSum();
        }
        else if(isThreeOfKind())
        {
            score = 800 + calculateCardRankSum();
        }
        else if(isStraight())
        {
            score = 600 + calculateCardRankSum();
        }
        else if(isFlush())
        {
            score = 400 + calculateCardRankSum();
        }
        else if(isPair())
        {
            score = 200 + calculateCardRankSum();
        }
        else
        {
            score = 100 + calculateCardRankSum();
        }

        return score;
    }

    /**
     * Determines if the player's hand is a straight flush.
     * A straight flush is three sequential cards of the same suit.
     *
     * @return true if the hand is a straight flush; false otherwise.
     */
    private boolean isStraightFlush()
    {
        if(isStraight() && isFlush()) return true;

        return false;
    }

    /**
     * Checks if the player's hand contains three cards of the same rank.
     *
     * @return true if the hand has three cards of the same value; false otherwise.
     */
    private boolean isThreeOfKind()
    {
        ArrayList<Card> sortedCards = getHand();
        sortedCards.sort(cardComparator);
        int value;

        for(int i = 0; i < sortedCards.size() - 2; i++)
        {
            value = sortedCards.get(i).getValue();

            if(value == sortedCards.get(i+1).getValue() && value == sortedCards.get(i+2).getValue()) return true;
        }

        return false;
    }

    /**
     * Checks if the player's hand contains five cards in a sequential order.
     * Also accounts for the Ace acting as either the highest or lowest card.
     *
     * @return true if the hand is a straight; false otherwise.
     */
    private boolean isStraight()
    {
        ArrayList<Card> sortedCards = getHand();
        sortedCards.sort(cardComparator);
        int count = 1;
        boolean hasAce = false;

        for(int i = 0; i < sortedCards.size() - 1; i++)
        {
            if(sortedCards.get(i).compareTo(sortedCards.get(i+1)) == -1)
            {
                count++;
            }
            else if(sortedCards.get(i + 1).getValue() == 14)
            {
                hasAce = true;
            }
            else
            {
                return false;
            }
        }

        // Checks that the hand contains a 2, has had at least 2 consecutive cards, and that an ace is present.
        // Possibly flawed logic but seems to work for now!
        if(sortedCards.stream().anyMatch(card -> card.getValue() == 2) && count == 2 && hasAce)
        {
            count++;
        }

        // If three consecutive cards in a row, return true.
        if(count == 3) return true;

        return false;
    }

    /**
     * Checks if the player's hand contains three cards of the same suit.
     *
     * @return true if the hand is a flush; false otherwise.
     */
    private boolean isFlush()
    {
        ArrayList<Card> sortedCards = getHand();
        sortedCards.sort(cardComparator);
        int count = 1;

        for(int i = 0; i < sortedCards.size() - 1; i++)
        {
            if(sortedCards.get(i).getSuit() == sortedCards.get(i+1).getSuit())
            {
                count++;
            }
            else
            {
                count = 1;
            }
            if(count == 3)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the player's hand contains a pair of cards with the same value.
     *
     * @return true if a pair is found; false otherwise.
     */
    private boolean isPair()
    {
        ArrayList<Card> sortedCards = getHand();
        sortedCards.sort(cardComparator);

        for(int i = 0; i < sortedCards.size() - 1; i++)
        {
            if(sortedCards.get(i).getValue() == sortedCards.get(i+1).getValue()) return true;
        }

        return false;
    }

    /**
     * Calculates the total sum of all card values in the player's hand.
     *
     * @return the sum of card values.
     */
    private int calculateCardRankSum()
    {
        int sum = 0;

        for(Card card : getHand())
        {
            sum += card.getValue();
        }

        return sum;
    }
}