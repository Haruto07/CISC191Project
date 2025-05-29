/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: CardEffect represents an effect that can be applied to a player or opponent in the Card Clash game.
 * 
 */

public class CardEffect {
    public enum Type { DRAW,DEBUFF_MANA,SHIELD,BUFF_DAMAGE,DEBUFF_INCOMING } 

    private Type type;
    private int value;
    private double factor;
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
        this.factor = 1.0;
        this.description = description;
    }

    public CardEffect(Type type, double value, String description) {
        this.type = type;
        this.factor = value;
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
                self.drawCardsRecursively(value);
                break;
            case DEBUFF_MANA:
                opponent.debuffMana(value);
                break;
            case SHIELD:
                self.activateShield();
                break;
            case BUFF_DAMAGE:
                self.buffOutgoing(factor);
                break;
            case DEBUFF_INCOMING:
                opponent.debuffIncoming(factor);
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
