import java.util.Scanner;

public class Woo {

    public static void main(String[] args) {
        Deck deck = new Deck(1);
        Scanner yuki = new Scanner(System.in);
        // System.out.println(deck);

        Player player = new Player();
        Player dealer = new Player();
        // System.out.println(player.bet(100));

        // System.out.println(player.addBalance(500));

        deck.shuffle();
        // System.out.println(deck);

//        while (player.handValue() < 21) {
//          System.out.println(player.hit(deck));
//          System.out.println(player.handValue());
//      }
        player.hit(deck);

        player.display();

        System.out.println("Hit or stand");
        if (yuki.nextLine().toLowerCase().equals("hit")) {
          player.hit(deck);
          player.display();
        }
        else if (yuki.nextLine().toLowerCase().equals("stand")) {
          dealer.hit(deck);
          dealer.display();
        }


        // player.hit(deck);
        // player.hit(deck);
        // player.display();
        // player.hit(deck);
        // player.hit(deck);
        // player.display();

        // Player p2 = new Player();
        // p2.addCard(new Card("♠", "A"));
        // System.out.println(p2.handValue());
        // p2.addCard(new Card("♥", "A"));
        // System.out.println(p2.handValue());
        // p2.addCard(new Card("♣", "A"));
        // System.out.println(p2.handValue());



/**
Game Plan:
Show player balance and ask for bet
Initialize deck
Have player and dealer hit
Check for naturals
Offer props
hit and stand
pay out
restart
**/
    }

}
