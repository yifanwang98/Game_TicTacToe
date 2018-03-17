/**
 * This class represents the tree of possible moves in the game of Tic-Tac-Toe.
 * This class is a 9-ary tree of <code>GameBoardNodes</code>, which are
 * structured as a continuous chain of possible moves the user may make to reach
 * the end of the game (any leaf node).
 * 
 * @author Yifan Wang
 * 
 */
public class GameTree {
	/**
	 * A reference to the root of the <code>GameBoardNode</code> of this tree.
	 * All <code>GameTree</code> instances should have the same root node with
	 * the initial empty configuration. Even if the GameTree is empty and
	 * contains no <code>GameBoardNodes</code>, the root variable should always
	 * reference an object with these attributes. This is the starting
	 * configuration of the <code>GameBoard</code>.
	 */
	private GameBoardNode root;

	/**
	 * A reference to the currently selected <code>GameBoardNode</code> in the
	 * tree. The cursor should never be null and should select the root node by
	 * default.
	 */
	private GameBoardNode cursor;

	/**
	 * Starting player
	 */
	public final Box TURN;

	/**
	 * The moves that has been made
	 */
	private int[] movesMade;

	/**
	 * Total numbers of move that has been made
	 */
	private int totalNumMoves;

	/**
	 * The default constructor for the <code>GameTree</code> class. An empty
	 * <code>GameTree</code> should still have a root with the default initial
	 * configuration. By default, the starter player is <code>Box.X</code>.
	 */
	public GameTree() {
		GameBoard board = new GameBoard();
		root = new GameBoardNode(board, Box.X);
		cursor = root;
		TURN = Box.X;
		movesMade = new int[9];
		totalNumMoves = 0;
	}

	/**
	 * Constructor for the <code>GameTree</code> class with specific starter
	 * player.
	 */
	public GameTree(Box starter) {
		GameBoard board = new GameBoard();
		root = new GameBoardNode(board, starter);
		cursor = root;
		TURN = starter;
		totalNumMoves = 0;
	}

	/**
	 * @return the root
	 */
	public GameBoardNode getRoot() {
		return root;
	}

	/**
	 * @return the cursor
	 */
	public GameBoardNode getCursor() {
		return cursor;
	}

	/**
	 * @return the totalNumMoves
	 */
	public int getTotalNumMoves() {
		return totalNumMoves;
	}

	/**
	 * Attempts to make the move on the position passed in as a parameter.
	 * 
	 * <dt>Preconditions:
	 * <dd>0 <= position <= 9</dd>
	 * 
	 * @throws IllegalArgumentException
	 *             If position is out of range or if the move the user would
	 *             like to make is illegal.
	 * @param position
	 *            the position to play
	 */
	public void makeMove(int position) {
		if (position < 0 || position > 8)
			throw new IllegalArgumentException();

		if (cursor.getConfig()[position] == null)
			throw new IllegalArgumentException();

		cursor = cursor.getConfig()[position]; // Advance cursor
		this.movesMade[totalNumMoves++] = position; // Store index
	}

	/**
	 * Builds the <code>GameTree</code> from the current state the game is in
	 * using nested loops.
	 * 
	 * @return The root of the <code>GameTree</code> built from the current game
	 *         state.
	 */
	public GameBoardNode buildTree(int index) {
		root.setCurrentTurn(this.TURN);
		root.setUpConfig(this.TURN);

		// Build the tree from the giving index
		return GameTree.buildTree(cursor, TURN, index);
	}

	/**
	 * Builds the <code>GameTree</code> from the current state the game is in
	 * using nested loops. The root represents the current state of the game and
	 * the turn represents the turn to be made when building the
	 * <code>GameTree</code>.
	 * 
	 * @param root
	 * @param turn
	 * @return The root of the GameTree built from the current game state.
	 */
	public static GameBoardNode buildTree(GameBoardNode root, Box turn, int index) {
		Box turnT = Box.X;
		if (turn == Box.X)
			turnT = Box.O;

		// Build the tree level by level and set probabilities
		for (int a = 0; a < 9; a++) {
			// Level 1
			GameBoardNode nodeA = root.getConfig()[a];
			if (nodeA != null) {
				nodeA.setUpConfig(turnT);
				for (int b = 0; b < 9; b++) {
					// Level 2
					GameBoardNode nodeB = nodeA.getConfig()[b];
					if (nodeB != null) {
						nodeB.setUpConfig(turn);
						for (int c = 0; c < 9; c++) {
							// Level 3
							GameBoardNode nodeC = nodeB.getConfig()[c];
							if (nodeC != null) {
								nodeC.setUpConfig(turnT);
								for (int d = 0; d < 9; d++) {
									// Level 4
									GameBoardNode nodeD = nodeC.getConfig()[d];
									if (nodeD != null) {
										nodeD.setUpConfig(turn);
										for (int e = 0; e < 9; e++) {
											// Level 5
											GameBoardNode nodeE = nodeD.getConfig()[e];
											if (nodeE != null) {
												nodeE.setUpConfig(turnT);
												for (int f = 0; f < 9; f++) {
													// Level 6
													GameBoardNode nodeF = nodeE.getConfig()[f];
													if (nodeF != null) {
														nodeF.setUpConfig(turn);
														for (int g = 0; g < 9; g++) {
															// Level 7
															GameBoardNode nodeG = nodeF.getConfig()[g];
															if (nodeG != null) {
																nodeG.setUpConfig(turnT);
																for (int h = 0; h < 9; h++) {
																	// Level 8
//																	if (index >= 0 && index < 9)
//																		h = 10;
//																	else {
																		GameBoardNode nodeH = nodeG.getConfig()[h];
																		if (nodeH != null) {
																			nodeH.setUpConfig(turn);
																			nodeH.setProbabilities();
																		}
//																	}
																}
																nodeG.setProbabilities();
															}
														}
														nodeF.setProbabilities();
													}
												}
												nodeE.setProbabilities();
											}
										}
										nodeD.setProbabilities();
									}
								}
								nodeC.setProbabilities();
							}
						}
						nodeB.setProbabilities();
					}
				}
				nodeA.setProbabilities();
			}
		}
		//root.setProbabilities();

		return root;
	}

	/**
	 * Builds the <code>GameTree</code> from the current state the game is in
	 * <b>recursively</b>. The root represents the current state of the game and
	 * the turn represents the turn to be made when building the
	 * <code>GameTree</code>.
	 * 
	 * @param root
	 *            Current node
	 * @param turn
	 *            Current player
	 * @param count
	 *            the index for the <code>config[]</code>
	 * @return The root of the GameTree built from the current game state.
	 */
	public static GameBoardNode buildTree(GameBoardNode parent, GameBoardNode node, Box turn) {

		/*
		 * This method has never been used!!!!!! But it is correct.
		 */

		parent.setUpConfig(turn);
		Box turnT;
		if (turn == Box.X)
			turnT = Box.O;
		else
			turnT = Box.X;

		if (parent.getConfig()[0] != null) {
			parent.getConfig()[0].setUpConfig(turn);
			node = parent.getConfig()[0];
			buildTree(node, node.getConfig()[0], turnT);
		}

		if (parent.getConfig()[1] != null) {
			parent.getConfig()[1].setUpConfig(turn);
			node = parent.getConfig()[1];
			buildTree(node, node.getConfig()[1], turnT);
		}

		if (parent.getConfig()[2] != null) {
			parent.getConfig()[2].setUpConfig(turn);
			node = parent.getConfig()[2];
			buildTree(node, node.getConfig()[2], turnT);
		}

		if (parent.getConfig()[3] != null) {
			parent.getConfig()[3].setUpConfig(turn);
			node = parent.getConfig()[3];
			buildTree(node, node.getConfig()[3], turnT);
		}

		if (parent.getConfig()[4] != null) {
			parent.getConfig()[4].setUpConfig(turn);
			node = parent.getConfig()[4];
			buildTree(node, node.getConfig()[4], turnT);
		}

		if (parent.getConfig()[5] != null) {
			parent.getConfig()[5].setUpConfig(turn);
			node = parent.getConfig()[5];
			buildTree(node, node.getConfig()[5], turnT);
		}

		if (parent.getConfig()[6] != null) {
			parent.getConfig()[6].setUpConfig(turn);
			node = parent.getConfig()[6];
			buildTree(node, node.getConfig()[6], turnT);
		}

		if (parent.getConfig()[7] != null) {
			parent.getConfig()[7].setUpConfig(turn);
			node = parent.getConfig()[7];
			buildTree(node, node.getConfig()[7], turnT);
		}

		if (parent.getConfig()[8] != null) {
			parent.getConfig()[8].setUpConfig(turn);
			node = parent.getConfig()[8];
			buildTree(node, node.getConfig()[8], turnT);
		}

		parent.setProbabilities();

		return parent;
	}

	/**
	 * Checks whether or not the passed in GameBoardNode’s configuration is a
	 * winning state or not.
	 * 
	 * @param node
	 *            the node to be checked
	 * @return the winner’s symbol if it is a winning state. If the current
	 *         configuration of the <code>GameBoard</code> in the
	 *         <code>GameBoardNode</code> is not a leaf in the
	 *         <code>GameTree</code>, then this method should return
	 *         <code>null</code>. If the configuration is a draw, this method
	 *         should return <code>Box.EMPTY</code>.
	 */
	public static Box checkWin(GameBoardNode node) {
		return node.checkWin();
	}

	/**
	 * Calculate current probability of winning for the
	 * <code>GameBoardNode</code> configuration at the cursor
	 * 
	 * @return the current probability of winning for the
	 *         <code>GameBoardNode</code> configuration at the cursor.
	 */
	public double cursorProbability() {
		return this.cursor.getWinProb();
	}

	/**
	 * Undo a move
	 * 
	 * @return the index of the move that has been undid
	 */
	public int undo() {
		// Reset cursor
		this.cursor = this.root;
		int[] a = new int[9];
		System.arraycopy(movesMade, 0, a, 0, totalNumMoves);

		int num = movesMade[totalNumMoves - 1]; // The index that to be undid
		int index = totalNumMoves - 1;

		// Reset variables
		movesMade = new int[9];
		totalNumMoves = 0;

		// Advance cursor
		for (int i = 0; i < index; i++) {
			this.makeMove(a[i]);
		}

		return num;
	}

	/**
	 * @return Whether current game state is diagonal 
	 */
	public boolean isDiagnoal() {
		if (this.totalNumMoves != 3)
			return false;
		if (this.movesMade[1] != 4)
			return false;
		if (this.movesMade[0] == 0 && this.movesMade[2] == 8)
			return true;
		if (this.movesMade[0] == 8 && this.movesMade[2] == 0)
			return true;
		if (this.movesMade[0] == 2 && this.movesMade[2] == 6)
			return true;
		if (this.movesMade[0] == 6 && this.movesMade[2] == 2)
			return true;
		return false;
	}

}
