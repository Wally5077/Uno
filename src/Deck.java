import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deck = new ArrayList<>(76);
    private List<Card> discardDeck = new ArrayList<>(76);

    public Deck() {
        createDeck();
    }

    private void createDeck() {
        for (Suit suit : Suit.values()) {
            deck.add(new Card(suit, 0));
            for (int rank = 1; rank < 10; rank++) {
                deck.add(new Card(suit, rank));
                deck.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    private void shuffle() {
        Card[] cards = deck.toArray(new Card[0]);
        deck.clear();

        for (int cardIndex = 0; cardIndex < 76; cardIndex++) {
            int randNum = new Random().nextInt(76);
            Card temp = cards[cardIndex];
            cards[cardIndex] = cards[randNum];
            cards[randNum] = temp;
            deck.add(cards[cardIndex]);
        }
    }

    public Card dealCard() {
        if (deck.isEmpty()) {
            deck.addAll(discardDeck);
            discardDeck.clear();
            shuffle();
        }
        int removeCardIndex = (deck.size() - 1);
        Card removeCard = deck.get(removeCardIndex);
        deck.remove(removeCardIndex);
        return removeCard;
    }

    public void setDiscards(Card playCard) {
        discardDeck.add(playCard);
    }

    public Card getTopTableCard() {
        try {
            return discardDeck.get(discardDeck.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TopTableCardNotFoundException();
        }
    }
}
