/**
 * This class represents the board the game Tic-Tac-Toe is being played on. Each
 * game board will only contain one private member variable which is an array of
 * Boxes. This will be the representation of the Tic-Tac-Toe game board.
 * 
 * @author Yifan Wang
 * 
 */
public class GameBoard implements Cloneable {
	private Box[] board;
	private final int boardSize = 9;

	/**
	 * The default constructor for the <code>GameBoard</code> class. This
	 * constructor initialize the array of Boxes to size <code>boardSize</code>.
	 */
	public GameBoard() {
		this.board = new Box[this.boardSize];
		for (int i = 0; i < this.boardSize; i++) {
			this.board[i] = Box.EMPTY;
		}
	}

	/**
	 * @return the board
	 */
	public Box[] getBoard() {
		return board;
	}

	/**
	 * @param board
	 *            the board to set
	 */
	public void setBoard(Box[] board) {
		this.board = board;
	}

	@Override
	public String toString() {
		String str = "";

		for (int i = 0; i < this.boardSize; i++) {
			if (this.board[i] == Box.EMPTY)
				str += "_" + "\t";
			else
				str += this.board[i] + "\t";
			if (i == 2 || i == 5 || i == 8)
				str += "\n";
		}

		return str;
	}

	@Override
	public GameBoard clone() {
		GameBoard temp = new GameBoard();
		Box[] b = new Box[this.boardSize];
		for (int i = 0; i < this.boardSize; i++) {
			b[i] = this.board[i];
		}
		temp.setBoard(b);
		return temp;
	}

}
