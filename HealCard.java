// HealCard.java
public class HealCard extends Card {
    private int healAmount;

    public HealCard(String name, String elementType, int manaCost, int healAmount) {
        super(name, elementType, manaCost);
        this.healAmount = healAmount;
    }

    @Override
    public void playEffect(Player self, Player opponent) throws InvalidPlayException {
        if (self.getMana() < manaCost)
            throw new InvalidPlayException("Not enough mana to play " + name);
        self.setMana(self.getMana() - manaCost);

        self.heal(healAmount);
        System.out.println(name + " healed " + healAmount + " health!");
    }
}
