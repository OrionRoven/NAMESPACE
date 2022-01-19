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

    // game loop
    while (!gameOver) {
      if (player.handValue() == 21) {
        player.display();
        System.out.println("BLACKJACK");
        break;
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

      if (usrInpt.equals("hit")) {
        player.hit(deck);
      }
      else if (usrInpt.equals("stand")) {
        // dealer plays game now
        while (true) {
          if (dealer.handValue() > 21) { // TODO: bug, ace logic (1 or 11) doesnt work, always 11
            dealerLose = true;
            break;
          }
          if (dealer.handValue() < 16) {
            dealer.hit(deck);
            dealer.display();
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
