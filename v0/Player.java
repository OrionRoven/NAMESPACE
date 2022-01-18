/** Class Player
 * stores player stats:
 * money balance, number of wins, number of losses
 * TODO: other stats?
 * 
 * methods:
 * hit() - draw card from deck
 * stand()
 * 
 */

public class Player {

    private int balance;
    private int wins;
    private int losses;

    public Player() {
        // players start with $1,000 balance
        balance = 1_000;
        wins = 0;
        losses = 0;
    }

    // pick topmost card from deck, returns card drawn
    public Card hit(Deck deck) {
        return deck.get(0);
    }
}
