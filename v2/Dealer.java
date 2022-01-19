public class Dealer {
  private ArrayList<Card> hand;


  public Dealer() {
      // players start with $1,000 balance

      hand = new ArrayList<>();
  }

  // pick topmost card from deck, add to hand, returns updated hand
  public ArrayList<Card> hit(Deck deck) {
      hand.add(deck.get(0));
      deck.remove(0);
      return hand;
  }

  // ace has value 1 or 11
  // ace default gameValue is 11
  public int handValue() {
      int sum = 0;
      for (int i = 0; i < hand.size(); i++) {
          sum += hand.get(i).gameValue;
          if ((hand.get(i).gameValue == 11) && (sum > 21))
              sum -= 10;
      }
      return sum;
  }


  public void display() {
    for (int x = 0; x < 9; x ++) {
      String lineToPrint = "";
      for(int i = 0; i < hand.size(); i ++) {
        lineToPrint+=(CardArt.makeCard(hand.get(i))[x] + "   ");
      }
      System.out.println(lineToPrint);
    }
  }


  }
}
