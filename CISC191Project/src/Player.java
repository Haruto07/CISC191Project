import java.io.*;
import java.util.*;

public class Player {
    private String name;
    private String affinity;     
    private int health;
    private int mana;
    private boolean shieldActive;                          
    private double outgoingMultiplier;           
    private double incomingMultiplier;           
    private Deck deck;
    private List<Card> hand = new ArrayList<>();

    /**
     * Purpose: Constructor for the Player class
     * @param name name of the player
     * @param affinity element affinity of the player
     * @param deck deck of cards for the player
     */
    public Player(String name, String affinity, Deck deck) {
        this.name = name;
        this.affinity = affinity;
        this.health = 100;
        this.mana = 10;
        this.shieldActive = false;
        this.outgoingMultiplier = 1.0;
        this.incomingMultiplier = 1.0;
        this.deck = deck;
    }

    /**
     * Purpose: Method to draw a starting hand of 5 cards from the deck
     */
    public void drawStartingHand() {
        drawCardsRecursively(5);
    }

    /**
     * Recursively draws n cards one at a time.
     * Base case: stop when n <= 0 or deck is empty.
     */
    public void drawCardsRecursively(int n) {
        if (n <= 0) {
            return;
        }
        try {
            Card c = deck.draw();
            hand.add(c);
        } 
        catch (EmptyDeckException e) {
            System.out.println(name + ": " + e.getMessage());
            return;        
        }
        drawCardsRecursively(n - 1);
    }

    /**
     * Purpose: Method to play a card from the hand
     * @param idx index of the card in the hand
     * @param opponent opponent player
     */
    public void playCard(int idx, Player opponent) {
        try {
            if (idx < 0 || idx >= hand.size())
                throw new InvalidPlayException("Invalid card index");
            Card c = hand.get(idx);
            c.playEffect(this, opponent);
            hand.remove(idx);
        } catch (InvalidPlayException ex) {
            System.out.println(name + ": " + ex.getMessage());
        }
    }

    /**
     * Purpose: Method to discard a card from the hand
     * @param idx index of the card in the hand
     */
    public void takeDamage(int dmg) {
    if (shieldActive) {
        shieldActive = false;
        System.out.println(name + " blocked all damage with a shield!");
        return;
    }

    // We already baked in incomingMultiplier on the caller side,
    // but let’s also log and then reset it here.
    // apply damage
    health = Math.max(0, health - dmg);
    // then reset for next turn
    incomingMultiplier = 1.0;
    System.out.printf("%s now has %d health%n", name, health);
}
    /**
     * Purpose: Method to heal the player
     * @param amt amount of health to restore
     */
    public void heal(int amt) {
        health = Math.min(100, health + amt);
    }

    /**
     * Purpose: Method to check if the player is defeated
     * @return true if the player is defeated, false otherwise
     */
    public boolean isDefeated() { 
        return health <= 0; 
    }

    /**
     * Purpose: Method to add a shield to the player
     */
    public void activateShield() {
        shieldActive = true;
    }

    /**
     * Purpose: Method to set the player's outgoing damage multiplier
     * @param multiplier multiplier to set
     */
    public void buffOutgoing(double factor) {
        outgoingMultiplier *= factor;
    }

    /**
     * Purpose: Method to debuff incoming damage to this player
     * @param factor the factor by which to reduce incoming damage
     */
    public void debuffIncoming(double factor) {
        incomingMultiplier *= factor;
    }

    public void resetOutgoingMultiplier() {
        if (outgoingMultiplier != 1.0) {
            outgoingMultiplier = 1.0;
        }
    }

    /**
     * Purpose: Method to get the player's name, affinity, health, mana, hand, and deck size
     * @return the player's name, affinity, health, mana, hand, and deck size
     */
    public String getName(){ 
        return name; 
    }
    /**
     * Purpose: Method to get the player's affinity
     * @return the player's affinity
     */
    public String getAffinity(){
        return affinity; 
    }

    /**
     * Purpose: Method to get the player's health
     * @return the player's health
     */
    public int getHealth(){
        return health; 
    }

    /**
     * Purpose: Method to get the player's mana
     * @return the player's mana
     */
    public int getMana(){ 
        return mana; 
    }

    /**
     * Purpose: Method to get the player's hand of cards
     * @return the player's hand of cards
     */
    public List<Card> getHand(){ 
        return hand; 
    }

    /**
     * Purpose: Method to get the player's deck size
     * @return the player's deck size
     */
    public int getDeckSize(){ 
        return deck.size();
    }

    /**
     * Purpose: Method to check if the player has an active shield
     * @return true if the player has an active shield, false otherwise
     */
    public boolean hasShield() { 
        return shieldActive; 
    }

    /**
     * Purpose: Method to get the player's outgoing damage multiplier
     * @return the player's outgoing damage multiplier
     */
    public double getOutgoingMultiplier() { 
        return outgoingMultiplier;
    }

    /**
     * Purpose: Method to get the player's incoming damage multiplier
     * @return the player's incoming damage multiplier
     */
    public double getIncomingMultiplier() { 
        return incomingMultiplier; 
    }

    /**
     * Purpose: Method to set the player's mana
     * @param m mana to set
     */
    public void setMana(int m){ 
        mana = m; 
    }
}
