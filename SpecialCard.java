// SpecialCard.java
public class SpecialCard extends Card {
    private CardEffect effect;

    public SpecialCard(String name, String elementType, int manaCost, CardEffect effect) {
        super(name, elementType, manaCost);
        this.effect = effect;
    }

    @Override
    public void playEffect(Player self, Player opponent) throws InvalidPlayException {
        if (self.getMana() < manaCost)
            throw new InvalidPlayException("Not enough mana to play " + name);
        self.setMana(self.getMana() - manaCost);

        effect.apply(self, opponent);
        System.out.println(name + " triggered special effect: " + effect.getDescription());
    }
}
