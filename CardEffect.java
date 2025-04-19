// CardEffect.java
public class CardEffect {
    public enum Type { DRAW, BUFF, DEBUFF }
    private Type type;
    private int value;
    private String description;

    public CardEffect(Type type, int value, String description) {
        this.type = type;
        this.value = value;
        this.description = description;
    }

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

    public String getDescription() { return description; }
}
