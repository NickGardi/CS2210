//This class implements all the methods needed by algorithm computerPlay
public class BlockedTicTacToe {
	
	private char[][] gameBoard;
	private int inline;
	private int max_levels;
	private int size;
	
	//constructor
	public BlockedTicTacToe(int board_size, int inline, int max_levels) {
		gameBoard = new char[board_size][board_size];

		for (int i = 0; i < board_size; ++i)
			for (int j = 0; j < board_size; ++j)
				gameBoard[i][j] = ' ';
		inline = this.inline;
		max_levels = this.max_levels;
		size = board_size;
	}
	
	//returns an empty dictionary
	public TTTDictionary createDictionary() {
		int dictSize = 4509;
		TTTDictionary dict = new TTTDictionary(dictSize);
		return dict;
	}
	
	// changes the board to a configuration string
	private String getConfString() {
		String result = "";

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				result += Character.toString(gameBoard[i][j]);
			}
		}

		return result;
	}
	
	//This method first represents
	//the gameBoard as a string as described above; then it checks whether the string representing
	//the gameBoard is in the configurations dictionary: If it is in the dictionary this
	//method returns its associated score, otherwise it returns the value -1.
	public int repeatedConfig(TTTDictionary configurations) {
		
		String board = getConfString();
		if  (configurations.get(board) != null)
			return 1;
		else
			return -1;
	}

	//This method first represents the content of gameBoard as a string as described above; then it
	//inserts this string, score and level in the configurations dictionary.
	public void insertConfig(TTTDictionary configurations, int score, int level) {

		String board = getConfString();
		
		TTTRecord record = new TTTRecord(board, score, level);
		try {
			configurations.put(record);
		} catch (DuplicatedKeyException e) {
			System.out.println("Duplicated Key Exception");
		}		
	}
	
	//Stores the character symbol in gameBoard[row][col]
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	//This method returns true if gameBoard[row][col] is ’ ’; otherwise it returns false
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == ' ')
			return true;
		else
			return false;
	}

	//Returns true if there are k adjacent occurrences of
	//symbol in the same row, column, or diagonal of gameBoard, where k is the number of required
	//symbols in-inline needed to win the game
	// doesnt work at all :/
	public boolean wins(char symbol) {
		int x = 0;
		int y = 0;

		boolean result = false;

		for (int i = 0; (i < size) && !result; ++i) {  		 //row and column
			for (int j = 0; j < size; ++j) {
				if (gameBoard[i][j] == symbol)  		//check row
					++x;
				else
					x = 0;
				if (gameBoard[j][i] == symbol) 		//check column
					++y;
				else
					y = 0;
				if (x == inline || y == inline) { 		// check if either row or column are = to inline (if the right amount are in a row)
					result = true;
					break;
				}
			}
			x = 0; //reset temp variables to 0 to check next column and row
			y = 0;

			if (result == true)  //if win
				break;
		}
		if (!result) { 		//if no win in the columns or rows, check diagonals

			int i = 0;
			int j = 0;
			while (i < size) {
				++i;
				while (j < size) {
					++j;
					if (gameBoard[i][j] != symbol)
						break;
					++x;
					++y;
					int z = 1;
					while ( z < inline) {
						++z;
						if ( (j - z) >= 0 && (i + z) < size){
							if (gameBoard[i + z][j - z] == symbol) 	//increment x everytime theres more than 1 x in a row
								++x;								// once theres a different char x=o
							else
								x = 0;
						}

						if ((j + z) < size && (i + z) < size ) {
							if (gameBoard[i + z][j + z] == symbol)  //increment x everytime theres more than 1 y in a row
								++y;								// once theres a different char y=o
							else
								y = 0;
						}
					}
					if (x == inline || y == inline) {
						result = true;						//if x or y = inline then there is a win, if not set x and y back to 0
						break;
					}

					x = 0;
					y = 0;
				}

				if (result)
					break;		//if result is true, stop searching for wins
			}
		}
		return result;
	}


	//Returns true if gameBoard has no empty positions left and no player has won the game
	public boolean isDraw() {
		
		String board = getConfString();
		for (char x : board.toCharArray()) {
			if (board == " ") {
				return false;
			}
		}
		
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 3; ++j)
				if (gameBoard[i][j] == ' ') return false;
		return true;

	}
	
	//returns a 0-3 value depending on the current state of the game(won, tie, still playing)
	public int evalBoard() {
		int x = 2;

		if (wins('o'))
			x = 3;
		else if (wins('x'))
			x = 0;
		else if (isDraw())
			x = 1;

		return x;
	}

}