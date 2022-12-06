public enum Face {
    /**
     * Value of card faces in Blackjack
     */
    ACE(1), DEUCE(2), THREE(3), FOUR(4),
    FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(10), QUEEN(10),
    KING(10);

    private final int value;

    /**
     * Returns card face value
     * @param value
     */
    private Face(int value) {
        this.value = value;
    }

    /**
     * Returns value of the card
     */
    public int getValue() {
        return value;
    }

}