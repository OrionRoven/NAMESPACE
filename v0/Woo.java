public class Woo {

    public static void main(String[] args) {
        Deck deck = new Deck(1);
        // System.out.println(deck);

        Player player = new Player();
        System.out.println(player.hit(deck));
    }

}