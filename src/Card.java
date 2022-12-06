/**
 * Record showing the card face and value,
 * @param face
 * @param suit
 */
public record Card(Face face, Suit suit) {

    @Override
    public String toString() {
        return face + " of " + suit;
    }

}