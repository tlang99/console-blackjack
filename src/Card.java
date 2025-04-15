import java.util.Objects;

/**
 * Represents a single playing card in a standard deck, used in card games like Blackjack or Poker. Each card has a
 * suit, a value (2–14), a visibility status, and a display name. Implements Comparable for sorting cards by value.
 * Value should be between 2 (Two) and 14 (Ace), inclusive. Cards can be shown or hidden, and only visible cards
 * display their full name.
 *
 * @author Tyler Lang
 * @version 2025.04.03
 */
public class Card implements Comparable<Card>
{
    private Suit suit;
    private int value;
    private boolean visible;
    private String name;

    /**
     * Constructs a card with the specified suit, value, visibility, and display name.
     *
     * @param suit    The suit the card belongs to.
     * @param value   The value assigned to the card (2–14).
     * @param visible Whether the card is face-up (true) or face-down (false).
     * @param name    The display name of the card, e.g., "Ace".
     */
    public Card(Suit suit, int value, boolean visible, String name)
    {
        //Assign properties, and ensure the value is between 2 and 11, inclusive
        this.suit = suit;
        this.visible = visible;
        setValue(value);
        this.name = name;
    }

    /**
     * Returns the suit of the card.
     *
     * @return The card's suit.
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * Returns the numerical value of the card.
     *
     * @return The card's value (2–14).
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Returns the display name of the card.
     *
     * @return The card's name (e.g., "King").
     * */
    public String getName()
    {
        return name;
    }

    /**
     * Checks if the card's face is visible.
     *
     * @return true if the card is face-up, false if face-down.
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Sets the card's value if it's between 2 and 14, inclusive.
     *
     * @param value The value to assign to the card.
     */
    public void setValue(int value)
    {
        if (value >= 2 && value <= 14)
        {
            this.value = value;
        }
        else
        {
            System.err.println("Received card value is invalid. Value must be no less than 2 and no greater than 14.");
        }
    }

    /**
     * Makes the card visible (face-up).
     */
    public void show()
    {
        visible = true;
    }

    /**
     * Hides the card (makes it face-down).
     */
    public void hide()
    {
        visible = false;
    }

    /**
     * Returns a string representation of the card. If the card is visible, returns its name and suit
     * (e.g., "Queen of Hearts"). If hidden, returns "Hidden Card".
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString()
    {
        if (visible)
        {
            return getName() + " of " + getSuit();
        }
        else
        {
            return "Hidden Card";
        }
    }

    /**
     * Checks if this card is equal to another object. Cards are equal if they have the same suit, value, and name.
     *
     * @param obj The object to compare.
     * @return true if the cards are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        Card card;

        if (obj == null || getClass() != obj.getClass()) return false;

        card = (Card) obj;
        return value == card.value && suit == card.suit && name.equals(card.name);
    }

    /**
     * Returns a hash code for this card based on its suit, value, and name.
     *
     * @return The hash code of the card.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(value, suit, name);
    }

    /**
     * Compares this card to another card based on value.
     *
     * @param card The card to compare with.
     * @return A negative integer, zero, or a positive integer as this card's value is less than, equal to, or greater
     * than the specified card's value.
     */
    @Override
    public int compareTo(Card card)
    {
        return this.value - card.value;
    }
}