/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: Playable is an interface that defines the contract for classes that can be played in the Card Clash game, requiring them to implement the playEffect method.
 * 
 */

public interface Playable {
        /**
         * Purpose: Abstract method to be implemented by subclasses to play the effect of the card
         * @param self the player playing the card
         * @param opponent the opponent player
         * @throws InvalidPlayException if the play is invalid
         */
        void playEffect(Player self, Player opponent) throws InvalidPlayException;
}



