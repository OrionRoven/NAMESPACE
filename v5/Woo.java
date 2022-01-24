/** Class Woo
 * Main game loop
 */

import java.util.Scanner;

public class Woo {

  private static final String CLEAR_SCREEN =  "\033[2J";

  private static void wait(int millis)
  {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public static void main(String[] args) {
    // init gameOver state
    boolean gameOver = false;
    // init roundOver state
    boolean roundOver = false;

    // init dealer lose state
    boolean dealerLose = false;

    boolean hasHit = false;

    // init player and dealer
    Player player = new Player();
    Dealer dealer = new Dealer();

    int roundCounter = 0;
    int gameCounter = 0;
    int turnCounter = 0;

    while (!roundOver) {

      System.out.println("\n === Welcome to Blackjack ===\n");

      // init deck
      Deck deck = new Deck(6);

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
        // System.out.println("THIS IS GAME COUNTER: " + gameCounter);

        if (gameCounter != 0) {
          System.out.println("\nDo you want to keep on playing (y/n)?");
          String quit = scanner.next().toLowerCase();
          if (quit.equals("n")) {
            roundOver = true;
            break;
          } else if (quit.equals("y")) {
            gameCounter = 0;
            turnCounter = 0;
            player.clearHand();
            dealer.clearHand();
            break;
          }
        }
        
        while (turnCounter == 0) {
          System.out.println("Your balance is: " + player.getBalance());
          System.out.println("What is your bet");
          int amount = scanner.nextInt();
          if (amount <= player.getBalance()) {
            player.bet(amount);
            break;
          } else {
            System.out.println("\nYou don't have enough balance to bet that amount, try again.\n");
            continue;
          }
        }



        if (player.handValue() == 21 && dealer.handValue() != 21) {
          player.display();
          System.out.println("BLACKJACK");
          gameCounter++;
          player.clearHand();
          dealer.clearHand();
          player.addBalance((int) ((player.betAmt) * 1.5));
          System.out.println("Your balance is: " + player.getBalance());
          break;
        } else if (player.handValue() == 21 && dealer.handValue() == 21 && turnCounter == 0) {
          dealer.display();
          player.display();
          System.out.println("Both you and the dealer were dealt Blackjack. All bets cancelled");
          player.addBalance(player.betAmt);
          System.out.println("Your balance is: " + player.getBalance());
          player.clearHand();
          dealer.clearHand();
          break;
        }
        if (dealer.hand.get(0).number == "A" && insure) {
          if (player.insurance(dealer)) {
            player.clearHand();
            dealer.clearHand();
            break;
          }
        }
        
        dealer.display();
        player.display();

        insure = false;

        // if (turnCounter == 0) {
        //   System.out.println("Would you like to surrender?\n Yes: Y\n No: N");
        //   String ender = scanner.next().toLowerCase();
        //   if (ender.equals("y")) {
        //     System.out.println("You have surrendered and lost half your bet");
        //     player.addBalance(player.betAmt / 2);
        //     System.out.println("Your balance is: " + player.getBalance());
        //     gameCounter++;
        //     player.clearHand();
        //     dealer.clearHand();
        //     break;
        //   }
        // }

        // if (turnCounter == 0 && (player.betAmt*2 <= player.getBalance())) {
        //   System.out.println("Would you like to double down (y/n)?");
        //   String dragon = scanner.next().toLowerCase();
        //   if (dragon.equals("y")) {
        //     player.bet(player.betAmt);
        //     System.out.println("You have doubled down. You new bet is " + player.betAmt );
        //   }
        // }

        if (player.handValue() > 21) {
          player.display();
          System.out.println("Value of hand: " + player.handValue() + ". That's over 21... GAME OVER");
          System.out.println("Your balance is: " + player.getBalance());
          gameCounter++;
          break;
        }

        // dealer.display();
        // player.display();

        System.out.println("Value of hand: " + player.handValue());

        System.out.print("\nHit or Stand?\n");
        String usrInpt = scanner.next().toLowerCase();
        turnCounter++;
        wait(500);
        // gameCounter++; // TODO: CHANGE THIS, hitting just asks if you want to play again
        if (usrInpt.equals("hit")) {
          player.hit(deck);
          hasHit = true;
          wait(500);
          System.out.println(CLEAR_SCREEN);
        } else if (usrInpt.equals("stand")) {
          // boolean dealerHasHit = false;
          // dealer plays game now
          wait(500);
          dealer.hand.get(1).faceDown = false;
          dealer.display();
          wait(2000);
          while (true) {
            if (dealer.handValue() > 21) { // TODO: bug, ace logic (1 or 11) doesnt work, always 11
              gameCounter++;
              break;
            } else if (dealer.handValue() <= 16) {
              dealer.hit(deck, false);
              // dealerHasHit = true;
              dealer.display();
              System.out.println("Value of hand: " + dealer.handValue());
              wait(2000);
            } else if (dealer.handValue() > 16) {
              // if (!dealerHasHit)
              //   dealer.display();
              gameCounter++;
              break;
            }
          }
          // see who won
          if (player.handValue() > dealer.handValue() || dealer.handValue() > 21) {
            System.out.println("\nYOU WIN!");
            player.addBalance((int) ((player.betAmt) * 1.5));
            System.out.println("Your balance is: " + player.getBalance());
          } else if (player.handValue() < dealer.handValue()) {
            System.out.println("\nYou lose :(");
            if (player.getBalance() == 0) {
              System.out.println("Your balance is $0. Game Over");
              roundOver = true;
              break;
            } else {
              System.out.println("Your balance is: " + player.getBalance());
            }
          } else {
            System.out.println("\nTIE");
            player.addBalance(player.betAmt);
            System.out.println("Your balance is: " + player.getBalance());
          }
        }
      }
    }
    System.out.println("\nThanks for playing!");
  }
}
