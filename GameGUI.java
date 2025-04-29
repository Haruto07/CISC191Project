// GameGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameGUI extends JFrame {
    private Player p1, p2;
    private JProgressBar hp1, hp2;
    private JLabel mana1, mana2, deck1, deck2;
    private JPanel handPanel1, handPanel2;
    private boolean singlePlayer;
    /**
     * Purpose: Constructor for the GameGUI class
     * @param p1 player 1
     * @param p2 player 2
     */
    public GameGUI(Player p1, Player p2) {
        this.p1 = p1; this.p2 = p2;
        this.singlePlayer = p2.getName().equals("Computer");

        setTitle("Card Clash");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // top: opponent
        hp2 = new JProgressBar(0, 100); hp2.setValue(p2.getHealth());
        mana2 = new JLabel("Mana: " + p2.getMana());
        deck2 = new JLabel("Deck: " + p2.getDeckSize());
        handPanel2 = new JPanel();
        add(topBar(), BorderLayout.NORTH);
        add(handPanel2(), BorderLayout.CENTER);
        add(bottomBar(), BorderLayout.SOUTH);
    }
    private JPanel topBar() {
        JPanel p = new JPanel();
        p.add(new JLabel(p2.getName()));
        p.add(hp2);
        p.add(mana2);
        p.add(deck2);
        return p;
    }
    private JScrollPane handPanel2() {
        handPanel2.setLayout(new FlowLayout());
        refreshHand(p2, handPanel2);
        JScrollPane sp = new JScrollPane(handPanel2);
        sp.setPreferredSize(new Dimension(780, 120));
        return sp;
    }

    private JPanel bottomBar() {
        JPanel p = new JPanel(new BorderLayout());
        // p1 stats & hand
        JPanel stats = new JPanel();
        hp1 = new JProgressBar(0, 100); hp1.setValue(p1.getHealth());
        mana1 = new JLabel("Mana: " + p1.getMana());
        deck1 = new JLabel("Deck: " + p1.getDeckSize());
        stats.add(new JLabel(p1.getName()));
        stats.add(hp1);
        stats.add(mana1);
        stats.add(deck1);

        handPanel1 = new JPanel(new FlowLayout());
        refreshHand(p1, handPanel1);
        JScrollPane sp1 = new JScrollPane(handPanel1);
        sp1.setPreferredSize(new Dimension(780, 120));

        JButton endTurn = new JButton("End Turn");
        endTurn.addActionListener(e -> endTurn());

        p.add(stats, BorderLayout.NORTH);
        p.add(sp1,   BorderLayout.CENTER);
        p.add(endTurn, BorderLayout.SOUTH);
        return p;
    }

    private void refreshHand(Player pl, JPanel panel) {
        panel.removeAll();
        for (int i = 0; i < pl.getHand().size(); i++) {
            Card c = pl.getHand().get(i);
            JButton btn = new JButton(c.getName() + " (" + c.getManaCost() + ")");
            int idx = i;
            btn.addActionListener(e -> {
                pl.playCard(idx, pl == p1 ? p2 : p1);
                updateAll();
            });
            panel.add(btn);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void updateAll() {
        hp1.setValue(p1.getHealth());
        hp2.setValue(p2.getHealth());
        mana1.setText("Mana: " + p1.getMana());
        mana2.setText("Mana: " + p2.getMana());
        deck1.setText("Deck: " + p1.getDeckSize());
        deck2.setText("Deck: " + p2.getDeckSize());
        refreshHand(p1, handPanel1);
        if (!singlePlayer) refreshHand(p2, handPanel2);
        GameEngine.checkWin();
    }

    private void endTurn() {
        // simple: refill mana & draw
        Player cur = p1; Player opp = p2;
        // swap for p2 turn
        p1.setMana(10); p1.drawCard();
        if (singlePlayer) {
            // AI move on separate thread
            new AIPlayer(p2, p1, this).start();
        } else {
            p2.setMana(10); p2.drawCard();
        }
        updateAll();
    }
}
