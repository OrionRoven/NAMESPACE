public class CardArt {

  public static String[] makeCard(Card input) {
	if (input.number != 10) {
	    String[] _data= new String[9];
   	 _data[0]=" ________ ";
   	 _data[1]="| " + input.number + "      |";
 	 _data[2]="|        |";
    	 _data[3]="|        |";
   	 _data[4]="|   " + input.suit + "    |";
   	 _data[5]="|        |";
   	 _data[6]="|        |";
   	 _data[7]="|      " + input.number + " |";
   	 _data[8]=" ‾‾‾‾‾‾‾‾ ";
	} else {
		String[] _data= new String[9];
   	 _data[0]=" ________ ";
   	 _data[1]="| " + input.number + "     |";
 	 _data[2]="|        |";
    	 _data[3]="|        |";
   	 _data[4]="|   " + input.suit + "    |";
   	 _data[5]="|        |";
   	 _data[6]="|        |";
   	 _data[7]="|     " + input.number + " |";
   	 _data[8]=" ‾‾‾‾‾‾‾‾ ";
	}

    return _data;

  }

}
