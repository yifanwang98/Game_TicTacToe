/**
 * This class represents a possible configuration of the board.
 */
public class GameBoardNode {
	private GameBoard board;
	private boolean isEnd;
	/**
	 * If the game ended in a draw, the value of this variable should be
	 * <code>Box.EMPTY</code>.
	 */
	private Box currentTurn;
	private Box winner;
	/**
	 * Size = 9
	 */
	private GameBoardNode[] config;
	/**
	 * The number of winning leaves/number of total leaves in the subtree from
	 * this node
	 */
	private double winProb;
	/**
	 * The number of losing leaves/number of total leaves in the subtree from
	 * this node
	 */
	private double loseProb;
	/**
	 * The number of draw leaves/number of total leaves in the subtree from this
	 * node
	 */
	private double drawProb;

	/**
	 * The default constructor for the <code>GameBoardNode</code> class. The
	 * configurations of the <code>GameBoard</code> (A.K.A the config
	 * references) should be created based on the configuration of the board as
	 * well as whose turn it currently is. The value of <code>currentTurn</code>
	 * will determine which extra Box will be filled in.
	 * <dt>Preconditions:
	 * <dd><code>currentTurn != Box.EMPTY</code>
	 * <dd><code>board</code> is not null.</dd>
	 * 
	 * @param board
	 * @param currentTurn
	 */
	public GameBoardNode(GameBoard board, Box currentTurn) {
		if (currentTurn == Box.EMPTY || board == null)
			throw new IllegalArgumentException();
		this.board = board;
		this.currentTurn = currentTurn;
		this.config = new GameBoardNode[9];
	}

	/**
	 * Sets all probabilities of winning, losing and drawing from the current
	 * configuration of the GameBoard. This method should not return anything,
	 * but should set the variables winProb, loseProb, and drawProb to their
	 * respective probabilities.
	 */
	public void setProbabilities() {
		if (isEnd())
			return;

		int count = 0; // Number of valid children
		double win = 0; // Winning probability
		double draw = 0; // Draw probability
		double lose = 0; // Loss probability
		for (GameBoardNode g : config) {
			if (g != null) {
				count++;
				win += g.getLoseProb();
				draw += g.getDrawProb();
				lose += g.getWinProb();
			}
		}
		this.winProb = win / count;
		this.drawProb = draw / count;
		this.loseProb = lose / count;
	}

	/**
	 * @return The probability of winning
	 */
	public double getWinProb() {
		return this.winProb;
	}

	/**
	 * @return the probability of lose
	 */
	public double getLoseProb() {
		return this.loseProb;
	}

	/**
	 * @return the probability of draw
	 */
	public double getDrawProb() {
		return this.drawProb;
	}

	/**
	 * @return the board
	 */
	public GameBoard getBoard() {
		return board;
	}

	/**
	 * @return the isEnd
	 */
	public boolean isEnd() {
		Box winner = checkWin();
		if (winner == null)
			this.isEnd = false;
		else {
			if (winner == this.currentTurn)
				this.winProb = 1.0;
			else if (winner == Box.EMPTY)
				this.drawProb = 1.0;
			else
				this.loseProb = 1.0;

			this.isEnd = true;
		}
		return isEnd;
	}

	/**
	 * @return the currentTurn
	 */
	public Box getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @param currentTurn
	 *            the currentTurn to set
	 */
	public void setCurrentTurn(Box currentTurn) {
		this.currentTurn = currentTurn;
	}

	/**
	 * @return the winner
	 */
	public Box getWinner() {
		return winner;
	}

	/**
	 * @param winner
	 *            the winner to set
	 */
	public void setWinner(Box winner) {
		this.winner = winner;
	}

	/**
	 * @return the config
	 */
	public GameBoardNode[] getConfig() {
		return this.config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(GameBoardNode[] config) {
		this.config = config;
	}

	/**
	 * Creates and returns the String representation of the GameBoard
	 * configuration in the current GameBoardNode.
	 */
	@Override
	public String toString() {
		String str = this.board.toString();
		str += "Win: " + this.winProb + "\n";
		str += "Draw: " + this.drawProb + "\n";
		str += "Lose: " + this.loseProb + "\n";
		return str;
	}

	/**
	 * Checks whether or not the passed in GameBoardNode’s configuration is a
	 * winning state or not.
	 * 
	 * @return the winner’s symbol if it is a winning state. If the current
	 *         configuration of the <code>GameBoard</code> in the
	 *         <code>GameBoardNode</code> is not a leaf in the
	 *         <code>GameTree</code>, then this method should return null. If
	 *         the configuration is a draw, this method should return
	 *         <code>Box.EMPTY</code>.
	 */
	public Box checkWin() {
		// Horizontal
		for (int i = 0; i < 7; i = i + 3) {
			if (board.getBoard()[i] != Box.EMPTY) {
				if (board.getBoard()[i] == board.getBoard()[i + 1] && board.getBoard()[i] == board.getBoard()[i + 2]) {
					return board.getBoard()[i];
				}
			}
		}

		// Vertical
		for (int i = 0; i < 3; i++) {
			if (board.getBoard()[i] != Box.EMPTY) {
				if (board.getBoard()[i] == board.getBoard()[i + 3] && board.getBoard()[i] == board.getBoard()[i + 6]) {
					return board.getBoard()[i];
				}
			}
		}

		// Diagonal
		if (board.getBoard()[4] != Box.EMPTY) {
			if (board.getBoard()[0] == board.getBoard()[4] && board.getBoard()[4] == board.getBoard()[8])
				return board.getBoard()[4];
			if (board.getBoard()[2] == board.getBoard()[4] && board.getBoard()[4] == board.getBoard()[6])
				return board.getBoard()[4];
		}

		// Check for Box.EMPTY
		for (Box b : board.getBoard()) {
			if (b == Box.EMPTY)
				return null;
		}

		// Draw
		return Box.EMPTY;
	}

	public void setUpConfig(Box turn) {
		if (this.isEnd()) // There is no need to find config for leaf.
			return;

		// Copy current board to every config
		for (int i = 0; i < this.config.length; i++) {
			config[i] = new GameBoardNode(this.board.clone(), turn);
		}

		int count = 0; // The number of null configuration
		int index = -1; // The index of the only configuration

		// Place Box to every config. If the Box can not be placed, the config
		// is set to null
		for (int i = 0; i < this.config.length; i++) {
			if (this.board.getBoard()[i] == Box.EMPTY) {
				config[i].getBoard().getBoard()[i] = turn;
				index = i;
			} else {
				config[i] = null;
				count++;
			}
		}

		// If this a leaf, set its probabilities
		if (count == 8)
			config[index].setProbabilities();
	}

}
