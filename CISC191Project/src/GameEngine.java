import javax.swing.*;


public class GameEngine {
    public static Player player1, player2;

    /**
     * Purpose: Method to get the player1
     * @return player1
     */
    public static Player getPlayer1() { 
        return player1; 
    }

    /**
     * Purpose: Method to get the player2
     * @return player2
     */
    public static Player getPlayer2() { 
        return player2; 
    }

    public static void main(String[] args) {
        java.util.List<Card> cards = CardLoader.loadCards("CISC191Project//src//cards.txt");
        Deck d1 = new Deck(cards), d2 = new Deck(cards);

        player1 = new Player("P1", "Fire", d1);
        player2 = new Player("P2", "Water", d2);

        player1.drawStartingHand();
        player2.drawStartingHand();

        SwingUtilities.invokeLater(() -> {
            GameGUI gui = new GameGUI(player1, player2);
            gui.setVisible(true);
        });
    }

    /**
     * Purpose: Method to check if a player has won
     */
    public static void checkWin() {
        if (player1.isDefeated()) {
            JOptionPane.showMessageDialog(null, player2.getName() + " wins!");
            System.exit(0);
        }
        if (player2.isDefeated()) {
            JOptionPane.showMessageDialog(null, player1.getName() + " wins!");
            System.exit(0);
        }
    }
}
