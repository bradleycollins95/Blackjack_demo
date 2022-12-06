import java.util.ArrayList;
import java.util.Collections;


/**
 * Class that creates a deck
 *
 * @author 20108508
 */
public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        //Add all the unique cards to the deck
        for (Suit suit:Suit.values()) {
            for (Face face:Face.values()) {
                cards.add(new Card(face, suit));
            }
        }
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Removes the first card on the deck Array
     * @return cards[0]
     */
    public Card draw() {
        return cards.remove(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(i + 1);
            sb.append('/');
            sb.append(cards.size());
            sb.append(' ');
            sb.append(cards.get(i));
            sb.append('\n');
        }
        return sb.toString();
    }

}