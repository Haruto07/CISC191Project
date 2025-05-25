import java.io.*;
import java.util.*;

public class CardLoader {
    /**
     * Purpose: Method to load cards from a file
     * @param filename name of the file to load cards from
     * @return list of cards loaded from the file
     */
    public static List<Card> loadCards(String filename) {
        List<Card> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                // format: type,name,element,mana,value,desc?
                // e.g. ATTACK,Fireball,Fire,3,20
                String[] p = line.split(",");
                switch(p[0]) {
                    case "ATTACK":
                        list.add(new AttackCard(p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4])));
                        break;
                    case "HEAL":
                        list.add(new HealCard(p[1], p[2], Integer.parseInt(p[3]), Integer.parseInt(p[4])));
                        break;
                    case "SPECIAL":
                        String et = p[4].toUpperCase();
                        CardEffect.Type t;
                        switch (et) {
                        case "DRAW":
                            t = CardEffect.Type.DRAW;
                            break;
                        case "DEBUFF":
                        case "BUFF":
                        case "DEBUFF_MANA":
                            t = CardEffect.Type.DEBUFF_MANA;
                            break;
                        case "SHIELD":
                            t = CardEffect.Type.SHIELD;
                            break;
                        case "BUFF_DAMAGE":
                            t = CardEffect.Type.BUFF_DAMAGE;
                            break;
                        case "DEBUFF_INCOMING":
                            t = CardEffect.Type.DEBUFF_INCOMING;
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown effect type: " + et);
                        }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load cards: " + e.getMessage());
        }
        return list;
    }
}
