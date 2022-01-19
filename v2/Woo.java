/** Class Woo
 * Main game loop
 */

 import java.util.Scanner;

public class Woo {
  public static void main(String[] args) {
    // init gameOver state
    boolean gameOver = false;

    // init player and dealer
    Player player = new Player();
    // Dealer dealer = new Dealer();

    // init deck
    Deck deck = new Deck(1);

    // init scanner for usr input
    Scanner scanner = new Scanner(System.in);

    // setup game:
    deck.shuffle();
    // player is dealt, then dealer, player, dealer (face-down)
    player.hit(deck);
    // dealer.hit(deck);
    player.hit(deck);
    // dealer.hit(deck); // face-down

    // game loop
    while (!gameOver) {
      if (player.handValue() > 21) {
        System.out.println("Over 21, game over");
        break;
      }

      player.display();
      System.out.println("Value of hand " + player.handValue());

      System.out.print("\nHit or Stand?\t");
      String usrInpt = scanner.next().toLowerCase();

      if (usrInpt.equals("hit")) {
        player.hit(deck);
      }
      else if (usrInpt.equals("stand")) {
        gameOver = true;
      }

    }
  }
}