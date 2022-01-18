import java.util.ArrayList;

/** Class Player
 * stores player stats:
 * money balance, number of wins, number of losses
 * TODO: other stats?
 *
 * methods:
 * hit() - draw card from deck, add to hand
 * handValue()
 * stand()
 * bet()
 * addBalance()
 *
 */

public class Player {

    private int balance;
    private int wins;
    private int losses;

    private ArrayList<Card> hand;


    public Player() {
        // players start with $1,000 balance
        balance = 1_000;
        wins = 0;
        losses = 0;
        hand = new ArrayList<>();
    }

    // pick topmost card from deck, add to hand, returns updated hand
    public ArrayList<Card> hit(Deck deck) {
        hand.add(deck.get(0));
        deck.remove(0);
        return hand;
    }

    // ace has value 1 or 11
    // ace default gameValue is 11
    public int handValue() {
        int sum = 0;
        boolean hasAce = false;
        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).gameValue;
            if (hand.get(i).gameValue == 11)
                hasAce = true;
        }
        // if over 21, but has ace, ace becomes value of 1 (same as subtracting 10)
        if (sum > 21 && hasAce)
            sum -= 10;
        return sum;
    }

    // returns new balance
    public int bet(int amount) {
        // can't bet more than you have
        if (amount <= balance) {
            balance -= amount;
            return balance;
        }
        return balance;
    }

    // returns new balance
    public int addBalance(int amount) {
        balance += amount;
        return balance;
    }

    public void display() {
      for (int x = 0; x < 9; x ++) {
        String lineToPrint = "";
        for(int i = 0; i < hand.size(); i ++) {
          lineToPrint+=(CardArt.makeCard(hand.get(i))[x] + "   ");
        }
        System.out.println(lineToPrint);
      }
    }
}
