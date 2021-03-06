
/** Class Woo
 * Main game loop
 */

import java.util.Scanner;

public class Woo {
  public static void main(String[] args) {
    // init gameOver state
    boolean gameOver = false;
    // init roundOver state
    boolean roundOver = false;

    // init dealer lose state
    boolean dealerLose = false;

    // init player and dealer
    Player player = new Player();
    Dealer dealer = new Dealer();

    int roundCounter = 0;
    int gameCounter = 0;

    while (!roundOver) {

      // init deck
      Deck deck = new Deck(1);

      // init scanner for usr input
      Scanner scanner = new Scanner(System.in);

      // setup game:
      deck.shuffle();
      // player is dealt, then dealer, player, dealer (face-down)
      player.hit(deck);
      dealer.hit(deck, false);
      player.hit(deck);
      dealer.hit(deck, true); // face-down
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
      while (!gameOver) {

        if (gameCounter != 0) {
          System.out.println("Do you want to keep on playing?\n Yes: Y\n No: N");
          String temp = scanner.nextLine();
          String quit = scanner.nextLine().toLowerCase();
          System.out.println("THE SCANNER INPUT IS: " + quit);
          if (quit.equals("n")) {
            System.out.println("No was chosen");
            roundOver = true;
            break;
          } else if (quit.equals("y")) {
            System.out.println("Yes was chosen");
            gameCounter = 0;
            player.clearHand();
            dealer.clearHand();
            break;
          }
        }
        
        if (gameCounter == 0)
          System.out.println("Here we go, what is your bet");

        if (player.handValue() == 21 && dealer.handValue() != 21) {
          player.display();
          System.out.println("BLACKJACK");
          break; // TODO: if you get blackjack, breaks game loop but not round loop IT BREAKY
        } else if (player.handValue() == 21 && dealer.handValue() == 21 && gameCounter == 0) {
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
        if (gameCounter == 0) {
          System.out.println("Would you like to surrender?\n Yes: Y\n No: N");
          String ender = scanner.nextLine().toLowerCase();
          if (ender.equals("y")) {
            System.out.println("You have surrendered and lost half your bet");
            System.out.println("CTR before " + gameCounter);
            gameCounter++;
            System.out.println("CTR after " + gameCounter);
            break;
          }
        }
        if (gameCounter == 0) {
          System.out.println("Would you like to double down?\n Yes: Y\n No: N");
          String dragon = scanner.nextLine().toLowerCase();
          if (dragon.equals("y")) {
            System.out.println("You have doubled down. You new bet is " );//Add main bet
          }
        }

        if (player.handValue() > 21) {
          player.display();
          System.out.println("Value of hand: " + player.handValue() + ". That's over 21... GAME OVER");
          break;
        }

        dealer.display();
      
        player.display();
        System.out.println("Value of hand: " + player.handValue());

        System.out.print("\nHit or Stand?\t");
        String usrInpt = scanner.next().toLowerCase();
        gameCounter++; // TODO: CHANGE THIS, hitting just asks if you want to play again
        if (usrInpt.equals("hit")) {
          player.hit(deck);
        } else if (usrInpt.equals("stand")) {
          // dealer plays game now
          dealer.hand.get(1).faceDown = false;
          while (true) {
            if (dealer.handValue() > 21) { // TODO: bug, ace logic (1 or 11) doesnt work, always 11
              dealerLose = true;
              break;
            }
            if (dealer.handValue() <= 16) {
              dealer.hit(deck, false);
              dealer.display();
              System.out.println("Value of hand: " + dealer.handValue());
            } else if (dealer.handValue() > 16) {
              break;
            }
          }
          // see who won
          if (player.handValue() > dealer.handValue() || dealerLose) {
            // dealer.display();
            System.out.println("YOU WIN!");
          } else if (player.handValue() < dealer.handValue()) {
            // dealer.display();
            System.out.println("you lose");
          } else {
            // dealer.display();
            System.out.println("TIE");
          }
        }
      }
    }
    System.out.println("U STOPPED PLAYING!!!!");
  }
}
