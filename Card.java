public abstract class Card implements Playable {
    protected String name;
    protected String elementType;
    protected int manaCost;

    public Card(String name, String elementType, int manaCost) {
        this.name = name;
        this.elementType = elementType;
        this.manaCost = manaCost;
    }

    public abstract void playEffect(Player self, Player opponent) throws InvalidPlayException;

    public String getName() {
        return name;
    }
    public String getElementType() {
        return elementType;
    }

    public int getManaCost() {
        return manaCost;
    }
}
