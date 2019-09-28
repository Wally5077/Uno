import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSystem {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameSystem Uno = new GameSystem();
        Uno.start();
    }

    private void start() {
        Deck deck = new Deck();
        List<CardPlayer> cardPlayers = new ArrayList<>(4);
        int playerAmount = setPlayerAmount();

        for (int playerIndex = 0; playerIndex < playerAmount; playerIndex++) {
            System.out.print("[輸入] 第 " + (playerIndex + 1) + " 位玩家名字 : ");
            cardPlayers.add(new CardPlayer(scanner.nextLine()));
            dealHandCards(cardPlayers.get(playerIndex), deck);
        }
        boolean isGameOver = false;
        while (!isGameOver) {
            for (CardPlayer player : cardPlayers) {
                deck.setDiscards(playCard(player, deck));
                if (player.isWin()) {
                    System.out.println("[遊戲結束] " + player.getName() + " 獲勝" + "\n");
                    isGameOver = true;
                    break;
                }
            }
        }
    }

    private int setPlayerAmount() {
        do {
            try {
                System.out.print("[輸入] 輸入玩家數量 : ");
                String selectPlayerAmount = scanner.nextLine();
                return (Integer.parseInt(selectPlayerAmount));
            } catch (NumberFormatException e) {
                System.out.println("[例外] 請輸入數字");
            }
        } while (true);
    }

    private void dealHandCards(CardPlayer player, Deck deck) {
        System.out.println("[發牌] " + player.getName() + " 拿手牌");
        for (int drawTimes = 0; drawTimes < 7; drawTimes++) {
            player.drawCard(deck.dealCard());
        }
    }

    private Card playCard(CardPlayer player, Deck deck) {
        while (true) {
            try {
                Card playCard = player.playCard(deck.getTopTableCard());
                System.out.println("[出牌] " + player.getName() + " 打出" + playCard);
                return playCard;

            } catch (TopTableCardNotFoundException e) {
                return player.playCard(null);

            } catch (NoCardIsValidException e) {
                System.out.println("[例外] " + player.getName() + " 無牌可出");
                drawCard(player, deck);

            } catch (NoUnoCallException e) {
                for (int punishTimes = 1; punishTimes <= 2; punishTimes++) {
                    drawCard(player, deck);
                }
            }
        }
    }

    private void drawCard(CardPlayer player, Deck deck) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        System.out.println("[抽牌] " + player.getName() + " 抽牌");
        player.drawCard(deck.dealCard());
    }

}
