// Deck.java
import java.util.*;

public class Deck {
    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() throws EmptyDeckException {
        if (cards.isEmpty())
            throw new EmptyDeckException("Deck is empty!");
        return cards.remove(0);
    }

    public int size() { 
        return cards.size(); 
    }
}
