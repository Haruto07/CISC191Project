public abstract class Card implements Playable {
    protected String name;
    protected String elementType;
    protected int manaCost;

    /**
     * Purpose: Constructor for the Card class
     * @param name name of the card
     * @param elementType element type of the card
     * @param manaCost mana cost of the card
     */
    public Card(String name, String elementType, int manaCost) {
        this.name = name;
        this.elementType = elementType;
        this.manaCost = manaCost;
    }

    /**
     * Purpose: Abstract method to be implemented by subclasses to play the effect of the card
     * @param self the player playing the card
     * @param opponent the opponent player
     * @throws InvalidPlayException if the play is invalid
     */
    public abstract void playEffect(Player self, Player opponent) throws InvalidPlayException;

    /**
     * Purpose: Abstract method to be implemented by subclasses to get the card type
     * @return the type of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Purpose: Abstract method to be implemented by subclasses to get the card type
     * @return the type of the card
     */
    public String getElementType() {
        return elementType;
    }

    /**
     * Purpose: Abstract method to be implemented by subclasses to get the card type
     * @return the type of the card
     */
    public int getManaCost() {
        return manaCost;
    }

    /** Returns a short description of what this card does */
    public abstract String getDescription();
}
