import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameGUI extends JFrame {
    private Player p1, p2;
    private JProgressBar hpBar1, hpBar2;
    private JLabel hpLabel1, hpLabel2;
    private JLabel mana1, mana2, deck1, deck2;
    private JPanel handPanel1, handPanel2;
    private boolean singlePlayer;
    private Player currentPlayer;
    private HashMap<String, ImageIcon> elementIcons;

    /**
     * Purpose: Constructor for the GameGUI class
     * @param p1 player 1
     * @param p2 player 2
     */
    public GameGUI(Player p1, Player p2) {

        this.p1 = p1; 
        this.p2 = p2;
        this.singlePlayer = p2.getName().equals("Computer");
        this.currentPlayer = p1;

        elementIcons = new HashMap<>();
        elementIcons.put("Fire",new ImageIcon(new ImageIcon("CISC191Project/assets/fire.png").getImage().getScaledInstance(64, 96, Image.SCALE_SMOOTH)));
        elementIcons.put("Water",new ImageIcon(new ImageIcon("CISC191Project/assets/water.png").getImage().getScaledInstance(64, 96, Image.SCALE_SMOOTH)));
        elementIcons.put("Earth",new ImageIcon(new ImageIcon("CISC191Project/assets/earth.png").getImage().getScaledInstance(64, 96, Image.SCALE_SMOOTH)));

        setTitle("Card Clash");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        mana2 = new JLabel("Mana: " + p2.getMana());
        deck2 = new JLabel("Deck: " + p2.getDeckSize());
        handPanel2 = new JPanel();
        add(topBar(), BorderLayout.NORTH);
        add(handPanel2(), BorderLayout.CENTER);
        add(bottomBar(), BorderLayout.SOUTH);
    }

    /**
     * Purpose: Method to create the top bar of the GUI
     * @return the top bar panel
     */
    private JPanel topBar() {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel name2 = new JLabel(p2.getName() + " (" + p2.getAffinity() + ")");
        hpBar2 = new JProgressBar(0, 100);
        hpBar2.setValue(p2.getHealth());
        hpLabel2 = new JLabel(p2.getHealth() + "/100");
        mana2 = new JLabel("Mana: " + p2.getMana());
        deck2 = new JLabel("Deck: " + p2.getDeckSize());

        p.add(name2);
        p.add(hpBar2);
        p.add(hpLabel2);
        p.add(mana2);
        p.add(deck2);
        return p;
    }

    /**
     * Purpose: Method to create the hand panel for player 2
     * @return the hand panel for player 2
     */
    private JScrollPane handPanel2() {
        handPanel2 = new JPanel(new FlowLayout());
        refreshHand(p2, handPanel2);
        JScrollPane sp = new JScrollPane(handPanel2);
        sp.setPreferredSize(new Dimension(780, 120));
        return sp;
    }

    /**
     * Purpose: Method to create the bottom bar of the GUI
     * @return the bottom bar panel
     */
    private JPanel bottomBar() {
        JPanel p = new JPanel(new BorderLayout());
        JPanel stats = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel name1 = new JLabel(p1.getName() + " (" + p1.getAffinity() + ")");
        hpBar1 = new JProgressBar(0, 100);
        hpBar1.setValue(p1.getHealth());
        hpLabel1 = new JLabel(p1.getHealth() + "/100");
        mana1 = new JLabel("Mana: " + p1.getMana());
        deck1 = new JLabel("Deck: " + p1.getDeckSize());

        stats.add(name1);
        stats.add(hpBar1);
        stats.add(hpLabel1);
        stats.add(mana1);
        stats.add(deck1);

        handPanel1 = new JPanel(new FlowLayout());
        refreshHand(p1, handPanel1);
        JScrollPane sp1 = new JScrollPane(handPanel1);
        sp1.setPreferredSize(new Dimension(780, 120));

        JButton endTurn = new JButton("End Turn");
        endTurn.addActionListener(e -> endTurn());

        p.add(stats, BorderLayout.NORTH);
        p.add(sp1, BorderLayout.CENTER);
        p.add(endTurn, BorderLayout.SOUTH);
        return p;
    }

    /**
     * Purpose: Method to refresh the hand of a player
     * @param pl the player
     * @param panel the panel to refresh
     */
    public void refreshHand(Player pl, JPanel panel) {
        panel.removeAll();
        for (int i = 0; i < pl.getHand().size(); i++) {
            Card c = pl.getHand().get(i);
            JButton btn = new JButton(c.getName(), elementIcons.get(c.getElementType()));
            btn.setIconTextGap(4);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
           btn.setToolTipText(c.getDescription()+ " (Element: " + c.getElementType()+  ", Mana: " + c.getManaCost() + ")");
            btn.setEnabled(pl == currentPlayer);
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

    /**
     * Purpose: Method to update the GUI
     */
    public void updateAll() {
        hpBar1.setValue(p1.getHealth());
        hpLabel1.setText(p1.getHealth() + "/100");
        mana1.setText("Mana: " + p1.getMana());
        deck1.setText("Deck: " + p1.getDeckSize());

        hpBar2.setValue(p2.getHealth());
        hpLabel2.setText(p2.getHealth() + "/100");
        mana2.setText("Mana: " + p2.getMana());
        deck2.setText("Deck: " + p2.getDeckSize());

        refreshHand(p1, handPanel1);
        if (!singlePlayer) refreshHand(p2, handPanel2);

        GameEngine.checkWin();
    }
    /**
     * Purpose: Method to end the turn
     */
    public void endTurn() {
        Player prev = (currentPlayer == p1) ? p1 : p2;
        prev.resetEndOfTurn();

        if (currentPlayer == p1) {
            if (singlePlayer) {
                new AIPlayer(p2, p1, this).start();
            } 
            else {
                currentPlayer = p2;
                currentPlayer.drawCardsRecursively(2);
            }
        } 
        else {
            currentPlayer = p1;
            currentPlayer.drawCardsRecursively(2);
        }
        updateAll();
    }
}
