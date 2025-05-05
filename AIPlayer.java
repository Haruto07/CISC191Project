import javax.swing.*;
import java.util.*;

public class AIPlayer extends Thread {
    private Player self, opponent;
    private GameGUI gui;

    public AIPlayer(Player self, Player opponent, GameGUI gui) {
        this.self = self;
        this.opponent = opponent;
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);  // thinking...
            // draw if low cards
            if (self.getHand().isEmpty()) self.drawCard();
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
            self.drawCard();
            SwingUtilities.invokeLater(() -> gui.updateAll());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
