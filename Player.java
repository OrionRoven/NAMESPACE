import java.util.ArrayList;
import java.util.Scanner;

/** Class Player
 * stores player stats:
 * money balance, number of wins, number of losses
 * 
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
    public int betAmt;

    private int wins;
    private int losses;

    private ArrayList<Card> hand;


    public Player() {
        // players start with $1,000 balance
        balance = 1_000;
        betAmt = 0;
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
        int aceCtr = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).gameValue == 11)
              aceCtr++;
            sum += hand.get(i).gameValue;
        }
        if (aceCtr > 0) {
          while (sum > 21 && aceCtr > 0) {
            // System.out.println("THE SUM IS === " + sum);
            for (int j = 0; j < hand.size(); j++) {
              if (hand.get(j).gameValue == 11) {
                sum -= 10;
                aceCtr--;
                break;
              }
            }
          }
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
            betAmt = amount;
            return balance;
        }
        return balance;
    }

    // returns new balance
    public int addBalance(int amount) {
        balance += amount;
        return balance;
    }

    public int getBalance() {
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

    public boolean insurance(Dealer badGuy) {
  		System.out.println("Dealer has an ace. Do you want insurance?");
      System.out.println("Yes: Y");
      System.out.println("No: N");
      Scanner look = new Scanner(System.in);
      if (look.nextLine().toLowerCase().equals("y")) {
        System.out.println("How much do you want to insure. Remember, you can only insure half your main bet, but if the dealer has BlackJack you get paid back double.");
        int insuranceBet = look.nextInt();
        this.bet(insuranceBet);
        System.out.println("You have insured for $" + insuranceBet + "");
        if (badGuy.handValue() == 21) {
          System.out.println("Congrats! The Dealer had Blackjack. You have been payed $" + (insuranceBet*2));
          this.addBalance(insuranceBet*2);
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

    // FOR TESTING PURPOSES
    public void addCard(Card card) {
      hand.add(card);
    }


}
