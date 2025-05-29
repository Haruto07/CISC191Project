/**
 * Lead Author(s):
 * @author Haruto Yunawan
 * Other contributors:
 * None
 * References:
 * Version/date: 5/29/2025
 * 
 * Responsibilities of class: AttackCard represents a card in the Card Clash game that deals damage to the opponent. It extends the Card class and implements the playEffect method to apply the card's effect.
 * 
 */

public class AttackCard extends Card {
    private int damage;

    /**
     * Purpose: Constructor for the AttackCard class
     * @param name name of the card
     * @param elementType element type of the card
     * @param manaCost mana cost of the card
     * @param damage damage dealt by the card
     */ 
    public AttackCard(String name, String elementType, int manaCost, int damage) {
        super(name, elementType, manaCost);
        this.damage = damage;
    }

    /**
     * Purpose: Method to get the description of the card
     * @return the description of the card
     */
    @Override
    public String getDescription() {
        return "Deal " + damage + " damage.";
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

        double typeMult = typeMultiplier(this.elementType, opponent.getAffinity());

        double affinityMult = this.elementType.equals(self.getAffinity()) ? 1.25 : 1.0;

        double outMult = self.getOutgoingMultiplier();

        double inMult = opponent.getIncomingMultiplier();

        double raw = damage * typeMult * affinityMult * outMult * inMult;
        int dealt = (int)Math.round(raw);

        opponent.takeDamage(dealt);
    }

    /**
     * Purpose: Method to get the type multiplier based on the attack and defense types
     * @param atk attack type
     * @param def defense type
     * @return the type multiplier
     */
    private double typeMultiplier(String atk, String def) {
        if (atk.equals("Fire") && def.equals("Earth")
         || atk.equals("Earth") && def.equals("Water")
         || atk.equals("Water") && def.equals("Fire")) {
            return 1.5;
        }
        if (atk.equals("Fire") && def.equals("Water")
         || atk.equals("Earth") && def.equals("Fire")
         || atk.equals("Water") && def.equals("Earth")
         ||(atk.equals("Fire") && def.equals("Fire")
         || atk.equals("Earth") && def.equals("Earth")
         || atk.equals("Water") && def.equals("Water"))){
            return 0.5;
        }
        return 1.0;
    }
}
