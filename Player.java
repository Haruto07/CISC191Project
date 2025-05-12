// Player.java
import java.io.*;
import java.util.*;

public class Player {
    private String name;
    private String affinity;     
    private int health;
    private int mana;
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
        this.deck = deck;
    }

    /**
     * Purpose: Method to draw a starting hand of 5 cards from the deck
     */
    public void drawStartingHand() {
        for (int i = 0; i < 5; i++) drawCard();
    }

    /**
     * Purpose: Method to draw a card from the deck and add it to the hand
     */
    public void drawCard() {
        try {
            Card c = deck.draw();
            hand.add(c);
        } catch (EmptyDeckException e) {
            System.out.println(name + ": " + e.getMessage());
        }
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
    public  void takeDamage(int dmg) {
        health = Math.max(0, health - dmg);
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
     * Purpose: Method to save the player's profile to a file
     * @param filename name of the file to save to
     */
    public void saveProfile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(name);
            pw.println(affinity);
            pw.println(health);
            pw.println(mana);
        }
    }

    /**
     * Purpose: Method to load a player's profile from a file
     * @param filename name of the file to load from
     * @param deck deck of cards for the player
     * @return a Player object with the loaded profile
     */
    public static Player loadProfile(String filename, Deck deck) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String n = br.readLine();
            String a = br.readLine();
            int h = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            Player p = new Player(n, a, deck);
            p.health = h; p.mana = m;
            return p;
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
     * Purpose: Method to set the player's mana
     * @param m mana to set
     */
    public void setMana(int m){ 
        mana = m; 
    }
}
