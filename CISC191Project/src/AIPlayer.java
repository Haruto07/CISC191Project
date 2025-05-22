import javax.swing.*;

public class AIPlayer extends Thread {
    private Player self, opponent;
    private GameGUI gui;

    /**
     * Purpose: Constructor for the AIPlayer class
     * @param self the AI player
     * @param opponent the opponent player
     * @param gui the game GUI
     */
    public AIPlayer(Player self, Player opponent, GameGUI gui) {
        this.self = self;
        this.opponent = opponent;
        this.gui = gui;
    }

    /**
     * Purpose: Method to run the AI player thread
     * @throws InterruptedException if the thread is interrupted
     */
    @Override
    public void run() {
        try {
            Thread.sleep(1000);  // thinking...
            // draw if low cards
            if (self.getHand().isEmpty()) {
                self.drawCardsRecursively(1);
            }
            // play first playable
            for (int i = 0; i < self.getHand().size(); i++) {
                Card c = self.getHand().get(i);
                if (c.getManaCost() <= self.getMana()) {
                    self.playCard(i, opponent);
                    break;
                }
            }
            // end turn: refill & draw
            self.setMana(10);
            self.drawCardsRecursively(1);
            SwingUtilities.invokeLater(() -> gui.updateAll());
        } 
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
