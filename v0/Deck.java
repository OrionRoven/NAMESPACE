/** Class Deck
 * ArrayList of Cards
 * shuffle method
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Deck {

    private int _size;
    private ArrayList<Card> _data;

    private static final ArrayList<String> NUMBERS = new ArrayList<>(Arrays.asList("Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"));
    private static final ArrayList<String> SUITS = new ArrayList<>(Arrays.asList("Spades", "Clubs", "Diamonds", "Hearts"));

    public Deck() {
        _size = 0;
        _data = new ArrayList<>();
    }
    // num = number of decks of 52 to add
    public Deck(int num) {
        this();
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 13; k++) {
                    _data.add(new Card(SUITS.get(j), NUMBERS.get(k)));
                }
            }
        }
        _size = _data.size();
    }

    public String toString() {
        for (int i = 0; i < _size; i++) {
            System.out.println(_data.get(i));
        }
        return "Deck with size of " + _size + " cards";
    }

    // pass thru functionality
    public Card get(int index) {
        return _data.get(index);
    }

    public void shuffle() {
        //TODO: write method
        return;
    }
}
