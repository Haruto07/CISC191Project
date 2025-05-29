/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: SpecialCard represents a special card in the Card Clash game that has a unique effect.
 * 
 */

public class SpecialCard extends Card {
    private CardEffect effect;

    /**
     * Purpose: Constructor for the SpecialCard class
     * @param name name of the card
     * @param elementType element type of the card
     * @param manaCost mana cost of the card
     * @param effect special effect of the card
     */
    public SpecialCard(String name, String elementType, int manaCost, CardEffect effect) {
        super(name, elementType, manaCost);
        this.effect = effect;
    }

    /**
     * Purpose: Method to get the description of the card
     * @return the description of the card
     */
    @Override
    public String getDescription() {
        return effect.getDescription();
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

        effect.apply(self, opponent);
        System.out.println(name + " triggered special effect: " + effect.getDescription());
    }
}
