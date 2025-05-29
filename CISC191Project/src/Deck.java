import java.util.*;

/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: Deck represents a deck of cards in the Card Clash game, allowing for shuffling and drawing cards.
 * 
 */

public class Deck {
    private List<Card> cards;

    /**
     * Purpose: Constructor for the Deck class
     * @param cards list of cards in the deck
     */
    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        shuffle();
    }

    /**
     * Purpose: Constructor for the Deck class with a default set of cards
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Purpose: Method to draw a card from the deck
     * @return Card drawn from the deck
     */
    public Card draw() throws EmptyDeckException {
        if (cards.isEmpty())
            throw new EmptyDeckException("Deck is empty!");
        return cards.remove(0);
    }

    /**
     * Purpose: Method to get the number of cards in the deck
     * @return number of cards in the deck
     */
    public int size() { 
        return cards.size(); 
    }
}
