import java.util.ArrayList;

/**
 * Class that defines a Player and Dealer in the console application
 * Values include their name, hand (by adding cards)
 *
 * @author 20108508
 */
public class Player {

    private final String name;
    private final ArrayList<Card> hand;

    /**
     * Constructor that defines the Player's name and hand
     *
     * @param hand    the players hand (ArrayList)
     * @param name    the name of the player
     * @param balance the player's balance ($)
     */
    public Player(String name, int balance) {
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    /**
     * Grabs the user's name in the console
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Removes the top card from the deck ArrayList and adds it to the user's hand
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Adds the total face values of the cards in the player's hand
     * @return handSum
     */
    public int getHandSum() {
        int handSum = 0;
        for (Card card : hand) {
            handSum += card.face().getValue();
        }
        return handSum;
    }

    /**
     * If the hand is the player's, show the 2 dealt cards and every card drawn
     * If the hand is the dealer's, show 1 card, and hide the other. Show the drawn cards.
     * @param hideCard
     * @return sb
     */
    public String getHandAsString(boolean hideCard) {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("'s current hand:"); //add to entered name's display when hand is dealt/added to
        sb.append('\n');
        for (int i = 0; i < hand.size(); i++) {
            //if the hand size is 0 for the dealer, hide the first drawn card added to their hand
            if (i == 0 && hideCard) {
                sb.append("[Hidden card]");
                sb.append('\n');
            } else {
                sb.append(hand.get(i));
                sb.append('\n');
            }
        }
        return sb.toString(); //return the card
    }

}