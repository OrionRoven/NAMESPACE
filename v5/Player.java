import java.util.ArrayList;
import java.util.Scanner;

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
        for (int i = 0; i < hand.size(); i++) {
            sum += hand.get(i).gameValue;
            if ((hand.get(i).gameValue == 11) && (sum > 21))
                sum -= 10;
        }
        return sum;
    }

    public void clearHand() {
      hand.clear();
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
      System.out.println("\n======== Your Hand ========");
      for (int x = 0; x < 9; x ++) {
        String lineToPrint = "";
        for(int i = 0; i < hand.size(); i ++) {
          lineToPrint+=(CardArt.makeCard(hand.get(i))[x] + "   ");
        }
        System.out.println(lineToPrint);
      }
    }

    public static boolean insurance(Dealer badGuy) {
  		System.out.println("Dealer has an ace. Do you want insurance?");
      System.out.println("Yes: Y");
      System.out.println("No: N");
      Scanner look = new Scanner(System.in);
      if (look.nextLine().toLowerCase().equals("y")) {
        System.out.println("How much do you want to insure. Remember, you can only insure half your main bet, but if the dealer has BlackJack you get paid back double.");
       int insuranceBet = look.nextInt(); // TODO: String cannot be converted to int
        System.out.println("You have insured for $" + insuranceBet + "");
        if (badGuy.handValue() == 21) {
          System.out.println("Congrats! The Dealer had Blackjack. You have been payed $" + (insuranceBet*2));
          return true;
        } else {
          System.out.println("Sorry, the Dealer did not have blackjack and your insurance bet has been collected");
          return false;
        }
      }
      return false;
  	}

  	public static void split() {

  	}

  	public static void doubleDown() {

  	}


}
