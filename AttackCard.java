// AttackCard.java
public class AttackCard extends Card {
    private int damage;

    public AttackCard(String name, String elementType, int manaCost, int damage) {
        super(name, elementType, manaCost);
        this.damage = damage;
    }

    @Override
    public void playEffect(Player self, Player opponent) throws InvalidPlayException {
        if (self.getMana() < manaCost)
            throw new InvalidPlayException("Not enough mana to play " + name);
        self.setMana(self.getMana() - manaCost);

        double mult = typeMultiplier(this.elementType, opponent.getAffinity());
        int finalDmg = (int)(damage * mult);
        opponent.takeDamage(finalDmg);
        System.out.println(name + " dealt " + finalDmg + " damage!");
    }

    // Fire > Earth, Earth > Water, Water > Fire
    private double typeMultiplier(String atk, String def) {
        if (atk.equals("Fire") && def.equals("Earth")
         || atk.equals("Earth") && def.equals("Water")
         || atk.equals("Water") && def.equals("Fire")) {
            return 1.5;
        }
        if (atk.equals("Fire") && def.equals("Water")
         || atk.equals("Earth") && def.equals("Fire")
         || atk.equals("Water") && def.equals("Earth")) {
            return 0.75;
        }
        return 1.0;
    }
}
