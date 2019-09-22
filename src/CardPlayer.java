import java.util.ArrayList;
import java.util.List;

public class CardPlayer {
    private String name;
    private List<Card> myCards = new ArrayList<>();

    public CardPlayer(String playerName) {
        name = playerName;
    }

    private List<Card> checkUno(Card tableCard) {
        List<Card> handCards = new ArrayList<>();
        myCards.forEach(card -> {
                    if (card.isTheCardValid(tableCard)) {
                        handCards.add(card);
                    }
                }
        );
        return handCards;
    }

    public void drawCard(Card card) {
        myCards.add(card);
    }

    public Card playCard(Card tableCard) {
        List<Card> handCards = (tableCard == null) ? myCards : checkUno(tableCard);
        assert handCards != null;
        Card pickCard = chooseCard(handCards);
        myCards.remove(pickCard);
        return pickCard;
    }

    private Card chooseCard(List<Card> handCards) {
        do {
            try {
                if (handCards.isEmpty()) {
                    throw new NoCardIsValidException();
                }
                System.out.print("[牌面] ");
                for (int cardIndex = 1; cardIndex < handCards.size() + 1; cardIndex++) {
                    if (cardIndex % 10 == 0) {
                        System.out.print("\n[牌面] ");
                    }
                    System.out.print("(" + cardIndex + ") " + handCards.get(cardIndex - 1) + " ");
                }
                if (isUno(myCards) && isUno(handCards)) {
                    if (sayUno()) {
                        System.out.print("[聽牌] " + getName() + " 喊出Uno");
                    } else {
                        System.out.println("[罰抽] 抽牌");
                        return null;
                    }
                }
                System.out.print("\n[輸入] " + "玩家 " + getName() + " 請輸入要打出的牌 : ");
                return handCards.get(Integer.parseInt(GameSystem.scanner.nextLine()) - 1);
            } catch (NumberFormatException e) {
                System.out.println("[例外] 輸入了文字，請輸入數字");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("[例外] 無此張牌，請重新選牌");
            }
        } while (true);
    }

    public String getName() {
        return name;
    }

    private boolean isUno(List<Card> handCards) {
        return handCards.size() == 2;
    }

    private boolean sayUno() {
        System.out.print("\n[Uno] 是否喊出Uno : ");
        return ((GameSystem.scanner.nextLine()).toUpperCase()).equals("UNO");
    }

    public boolean isWin() {
        return myCards.isEmpty();
    }

}
