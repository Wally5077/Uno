
public class Card {
    private Suit suit;
    private int rank;

    public Card(Suit suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public boolean isTheCardValid(Card tableCard) {
        return (this.suit).equals(tableCard.suit) || this.rank == tableCard.rank;
    }

    @Override
    public String toString() {
        return getSuit() + "-" + getRank();
    }
}
