
/** Class Woo
 * Main game loop
 */

import java.util.Scanner;

public class Woo {
  public static void main(String[] args) {
    // init gameOver state
    boolean gameOver = false;

    // init dealer lose state
    boolean dealerLose = false;

    // init player and dealer
    Player player = new Player();
    Dealer dealer = new Dealer();

    // init deck
    Deck deck = new Deck(1);

    // init scanner for usr input
    Scanner scanner = new Scanner(System.in);

    // setup game:
    deck.shuffle();
    // player is dealt, then dealer, player, dealer (face-down)
    player.hit(deck);
    dealer.hit(deck);
    player.hit(deck);
    dealer.hit(deck); // face-down
    boolean insure = true;

    /*
     * TODO: if dealer draws blackjack end game right away
     * Deal player, dealer, player, dealer (FACEDOWN)
     * Ask player hit or stand, continue until player > 21 (lose), = 21 (win), or
     * stand
     * if stand, flip over facedown dealer card, if <= 16, dealer hit, go until > 16
     * (stand) or = 21 (dealer win) or > 21 (player win)
     * if stand, compare player & dealer hands, determine winner
     * if tie, return bet to player
     * if player win (Blackjack pays 3 to 2) return to player bet * 1.5
     */

    // TODO: maybe clearer way to show which cards are the player's and which are
    // the dealer's

    // game loop

    int playCounter = 0;
    System.out.println("Here we go, what is your bet");
    while (!gameOver) {

      if (player.handValue() == 21 && dealer.handValue() != 21) {
        player.display();
        System.out.println("BLACKJACK");
        break; // TODO: if you get blackjack, breaks game loop but not round loop
      } else if (player.handValue() == 21 && dealer.handValue() == 21 && playCounter == 0) {
        dealer.display();
        player.display();
        System.out.println("Both you and the dealer were dealt Blackjack. All bets cancelled");
      }
      if (dealer.hand.get(0).number == "A" && insure) {
        if (player.insurance(dealer)) {
          break;
        }
      }

      dealer.display();
      player.display();
      insure = false;
      if (playCounter == 0) {
        System.out.println("Would you like to surrender?\n Yes: Y\n No: N");
        String ender = scanner.nextLine().toLowerCase();
        if (ender.equals("y")) {
          System.out.println("You have surrendered and lost half your bet");
          break;
        }
      }
      if (player.handValue() > 21) {
        player.display();
        System.out.println("Value of hand: " + player.handValue() + ". That's over 21... GAME OVER");
        break;
      }

      dealer.display();
      System.out.println("Value of hand: " + dealer.handValue());
      player.display();
      System.out.println("Value of hand: " + player.handValue());

      System.out.print("\nHit or Stand?\t");
      String usrInpt = scanner.next().toLowerCase();
      playCounter++;
      if (usrInpt.equals("hit")) {
        player.hit(deck);
      } else if (usrInpt.equals("stand")) {
        // dealer plays game now
        while (true) {
          if (dealer.handValue() > 21) { // TODO: bug, ace logic (1 or 11) doesnt work, always 11
            dealerLose = true;
            break;
          }
          if (dealer.handValue() <= 16) {
            dealer.hit(deck);
            dealer.display();
            System.out.println("Value of hand: " + dealer.handValue());
          } else if (dealer.handValue() > 16) {
            break;
          }
        }
        // see who won
        if (player.handValue() > dealer.handValue() || dealerLose) {
          System.out.println("YOU WIN!");
        } else if (player.handValue() < dealer.handValue()) {
          System.out.println("you lose");
        } else {
          System.out.println("TIE");
        }
        // end game
        break;

      }
    }
  }
}
