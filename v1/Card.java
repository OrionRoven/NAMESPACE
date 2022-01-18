/** Class Card
 * stores suit, number, and value of a card

Ascii art for playing cards:

Suit in the middle:
♥, ♠, ◆, ♣

Face:
 ___________
│ A         │
│           │
│           │
│     ♠     │
│           │
│           │
│         A │
 ‾‾‾‾‾‾‾‾‾‾‾
Back:
 ___________
│░░░░░░░░░░░│
│░░░░░░░░░░░│
│░░░░░░░░░░░│
│░░░░░░░░░░░│
│░░░░░░░░░░░│
│░░░░░░░░░░░│
│░░░░░░░░░░░│
 ‾‾‾‾‾‾‾‾‾‾‾
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Card {

    public String suit;
    public String number;
    public int gameValue;
    public String[] stringy;

    private static final ArrayList<String> NUMBERS = new ArrayList<>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
    private static final ArrayList<Integer> GAMEVALUES = new ArrayList<>(Arrays.asList(11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10));

    public Card() {
        suit = "Spade";
        number = "Ace";
        gameValue = 1;
    }

    public Card(String suit, String number) {
        this.suit = suit;
        this.number = number;
        this.gameValue = GAMEVALUES.get(NUMBERS.indexOf(number));
    }

    public String toString() {
        return number + " of " + suit + ": " + gameValue;
    }


}
