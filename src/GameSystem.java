import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSystem {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameSystem Uno = new GameSystem();
        Uno.start();
    }

    private void start() {
        Deck deck = new Deck();
        List<CardPlayer> cardPlayers = createPlayer();

        for (int playerAmount = 0; playerAmount < cardPlayers.size(); playerAmount++) {
            cardPlayers.add(new CardPlayer(scanner.nextLine()));
            dealHandCards(cardPlayers.get(playerAmount), deck);
        }
        while (true) {
            for (CardPlayer player : cardPlayers) {
                playCard(player, deck);
                if (player.isWin()){
                    System.out.println("[遊戲結束] "+player.getName()+"獲勝");
                    return;
                }
            }
        }
    }

    private List<CardPlayer> createPlayer() {
        int playerAmout = 0;
        String selectPlayerAmoint;
        boolean isInputWrong = false;

        do {
            try {
                System.out.print("[設定] 設定玩家數量：");
                selectPlayerAmoint = scanner.nextLine();
                playerAmout = Integer.parseInt(selectPlayerAmoint);
            } catch (NumberFormatException e) {
                isInputWrong = true;
                System.out.println("[錯誤] 請輸入數字");
            }
        } while (isInputWrong);
        return new ArrayList<>(playerAmout);
    }

    private void dealHandCards(CardPlayer player, Deck deck) {
        System.out.println("[發牌] " + player.getName() + "拿手牌");
        for (int drawTimes = 0; drawTimes < 7; drawTimes++) {
            player.drawCard(deck.dealCard());
        }
    }

    private void playCard(CardPlayer player, Deck deck) {
        while (true) {
            try {
                Card playCard = player.playCard(deck.getTableCard());
                System.out.println("[出牌] " + player.getName() + "打出" + playCard);
                deck.setDiscards(playCard);
                return;
            } catch (NullPointerException e) {
                System.out.println("[抽牌] " + player.getName() + "抽牌");
                player.drawCard(deck.dealCard());
            }
        }
    }

}
