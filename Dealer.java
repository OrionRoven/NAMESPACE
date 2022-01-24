import java.util.ArrayList;

public class Dealer {
  public ArrayList<Card> hand;


  public Dealer() {
      hand = new ArrayList<>();
  }

  // pick topmost card from deck, add to hand, returns updated hand
  public ArrayList<Card> hit(Deck deck, boolean faceDown) {
      hand.add(deck.get(0));
      if (faceDown) {
        hand.get(hand.size()-1).faceDown = true;
      }
      deck.remove(0);
      return hand;
  }

  // ace has value 1 or 11
  // ace default gameValue is 11
  public int handValue() {
    int sum = 0;
    int aceCtr = 0;
    for (int i = 0; i < hand.size(); i++) {
        if (hand.get(i).gameValue == 11)
          aceCtr++;
        sum += hand.get(i).gameValue;
    }
    if (aceCtr > 0) {
      while (sum > 21 && aceCtr > 0) {
        // System.out.println("THE SUM IS === " + sum);
        for (int j = 0; j < hand.size(); j++) {
          if (hand.get(j).gameValue == 11) {
            sum -= 10;
            aceCtr--;
            break;
          }
        }
      }
    }
    return sum;
}

  public void clearHand() {
    hand.clear();
  }

  public void display() {
    System.out.println("\n~~~~~~ Dealer's Hand ~~~~~~");
    for (int x = 0; x < 9; x ++) {
      String lineToPrint = "";
      for(int i = 0; i < hand.size(); i ++) {
        if (hand.get(i).faceDown == false) {
          lineToPrint+=(CardArt.makeCard(hand.get(i))[x] + "   ");
        } else {
          lineToPrint += (CardArt.backCard()[x] + "   ");
        }
      }
      System.out.println(lineToPrint);
    }
  }
}
