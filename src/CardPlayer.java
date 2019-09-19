import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        try {
            List<Card> handCards = checkUno(tableCard);
            Card pickCard = chooseCard(handCards);
            myCards.remove(pickCard);
            return pickCard;
        } catch (NullPointerException e) {
            System.out.println("[錯誤] 無牌可出");
        }
        return null;
    }

    private Card chooseCard(List<Card> handCards) {
        boolean isInputWrong = true;

        do {
            try {
                System.out.print("[牌面] ");
                for (int cardIndex = 1; cardIndex < handCards.size() + 1; cardIndex++) {
                    if (cardIndex % 10 == 0) {
                        System.out.print("\n[牌面] ");
                    }
                    System.out.print("(" + cardIndex + ") " + handCards.get(cardIndex) + " ");
                }
                if (isUno(handCards)) {
                    if (sayUno()) {
                        System.out.println("[聽牌] " + getName() + "喊出Uno");
                    } else {
                        System.out.println("[罰抽] 抽牌");
                        return null;
                    }
                }
                String pickCard = new Scanner(System.in).nextLine();
                isInputWrong = (Integer.parseInt(pickCard) > handCards.size());
                return handCards.get(Integer.parseInt(pickCard) - 1);
            } catch (NumberFormatException e) {
                System.out.println("[錯誤] 輸入錯誤，請重新輸入");
            }
        } while (isInputWrong);
        return null;
    }

    public String getName() {
        return name;
    }

    private boolean isUno(List<Card> handCards) {
        return handCards.size() == 2;
    }

    private boolean sayUno() {
        System.out.println("[Uno] 是否喊出Uno");
        return ((new Scanner(System.in).nextLine()).toUpperCase()).equals("UNO");
    }

    public boolean isWin() {
        return myCards.isEmpty();
    }

}
