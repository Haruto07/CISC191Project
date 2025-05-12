public class CardEffect {
    public enum Type { DRAW, BUFF, DEBUFF }
    private Type type;
    private int value;
    private String description;

    /**
     * Purpose: Constructor for the CardEffect class
     * @param type type of the effect (DRAW, BUFF, DEBUFF)
     * @param value value of the effect (number of cards to draw, amount to buff/debuff)
     * @param description description of the effect
     */
    public CardEffect(Type type, int value, String description) {
        this.type = type;
        this.value = value;
        this.description = description;
    }

    /**
     * Purpose: Method to apply the effect to the player and opponent
     * @param self the player playing the card
     * @param opponent the opponent player
     */
    public void apply(Player self, Player opponent) {
        switch (type) {
            case DRAW:
                for (int i = 0; i < value; i++) self.drawCard();
                break;
            case BUFF:
                self.setMana(self.getMana() + value);
                break;
            case DEBUFF:
                opponent.setMana(Math.max(0, opponent.getMana() - value));
                break;
        }
    }

    /**
     * Purpose: Method to get the description of the effect
     * @return the description of the effect
     */
    public String getDescription() { 
        return description; 
    }
}
