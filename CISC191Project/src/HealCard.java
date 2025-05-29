/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: HealCard represents a card in the Card Clash game that restores health to the player.
 * 
 */

public class HealCard extends Card {
    private int healAmount;

    /**
     * Purpose: Constructor for the HealCard class
     * @param name name of the card
     * @param elementType element type of the card
     * @param manaCost mana cost of the card
     * @param healAmount  amount of health restored by the card
     */
    public HealCard(String name, String elementType, int manaCost, int healAmount) {
        super(name, elementType, manaCost);
        this.healAmount = healAmount;
    }

    /**
     * Purpose: Method to get the description of the card
     * @return the description of the card
     */
    @Override
    public String getDescription() {
        return "Heal " + healAmount + " health.";
    }

    /**
     * Purpose: Method to play the effect of the card
     * @param self the player playing the card
     * @param opponent the opponent player
     * @throws InvalidPlayException if the play is invalid
     */
    @Override
    public void playEffect(Player self, Player opponent) throws InvalidPlayException {
        if (self.getMana() < manaCost)
            throw new InvalidPlayException("Not enough mana to play " + name);
        self.setMana(self.getMana() - manaCost);

        self.heal(healAmount);
        System.out.println(name + " healed " + healAmount + " health!");
    }
}
