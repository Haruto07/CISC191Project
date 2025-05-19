public interface Playable {
        /**
         * Purpose: Abstract method to be implemented by subclasses to play the effect of the card
         * @param self the player playing the card
         * @param opponent the opponent player
         * @throws InvalidPlayException if the play is invalid
         */
        void playEffect(Player self, Player opponent) throws InvalidPlayException;
}



