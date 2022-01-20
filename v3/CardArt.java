public class CardArt {

  public static String[] makeCard(Card input) {
	  	String[] _data= new String[9];
		if (input.number!="10") {

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

    public static String[] backCard() {
      String[] _data= new String[9];
      _data[0]=" ________ ";
    	_data[1]="|░░░░░░░░|";
  	  _data[2]="|░░░░░░░░|";
     	_data[3]="|░░░░░░░░|";
    	_data[4]="|░░░░░░░░|";
    	_data[5]="|░░░░░░░░|";
    	_data[6]="|░░░░░░░░|";
    	_data[7]="|░░░░░░░░|";
    	_data[8]=" ‾‾‾‾‾‾‾‾ ";
		
		return _data;
    }
}
