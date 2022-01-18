public class Woo {

    public static void main(String[] args) {
        Deck deck = new Deck(1);
        // System.out.println(deck);

        Player player = new Player();
        // System.out.println(player.bet(100));

        // System.out.println(player.addBalance(500));

        deck.shuffle();
        // System.out.println(deck);

        while (player.handValue() < 21) {
            System.out.println(player.hit(deck));
            System.out.println(player.handValue());
        }
        player.hit(deck);
        player.hit(deck);
        player.display();
        player.hit(deck);
        player.hit(deck);
        player.display();

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
