// Player.java
import java.io.*;
import java.util.*;

public class Player {
    private String name;
    private String affinity;      // Fire, Water, or Earth
    private int health;
    private int mana;
    private Deck deck;
    private List<Card> hand = new ArrayList<>();

    public Player(String name, String affinity, Deck deck) {
        this.name = name;
        this.affinity = affinity;
        this.health = 100;
        this.mana   = 10;
        this.deck   = deck;
    }

    public void drawStartingHand() {
        for (int i = 0; i < 5; i++) drawCard();
    }

    public void drawCard() {
        try {
            Card c = deck.draw();
            hand.add(c);
        } catch (EmptyDeckException e) {
            System.out.println(name + ": " + e.getMessage());
        }
    }

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

    public void takeDamage(int dmg) {
        health = Math.max(0, health - dmg);
    }

    public void heal(int amt) {
        health = Math.min(100, health + amt);
    }

    public boolean isDefeated() { return health <= 0; }

    // --- file I/O for profiles & logs ---
    public void saveProfile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(name);
            pw.println(affinity);
            pw.println(health);
            pw.println(mana);
        }
    }
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
    // ---------------
    public String getName()       { return name; }
    public String getAffinity()   { return affinity; }
    public int    getHealth()     { return health; }
    public int    getMana()       { return mana; }
    public List<Card> getHand()   { return hand; }
    public int    getDeckSize()   { return deck.size(); }
    public void   setMana(int m)  { mana = m; }
}
