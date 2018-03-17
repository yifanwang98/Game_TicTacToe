import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Yifan Wang
 * 
 */
public class TicTacToeAI extends Application {

	private static Stage primaryStage;
	private static Pane pane;
	private static Scene scene;

	/**
	 * Game tree for this game.
	 */
	private GameTree gameTree;
	/**
	 * <code>startClicked</code> is <code>true</code> when the game is started
	 * and not ended. Otherwise, it is <code>false</code>.
	 */
	private boolean startClicked = false;
	/**
	 * <code>true</code> if it is AI's turn, otherwise <code>false</code>
	 */
	private boolean AITurn = false;
	/**
	 * Whether <code>gameTree</code> has been built. <code>true</code> if it has
	 * been built, otherwise <code>false</code>
	 */
	private boolean treeBuilt = false;

	/**
	 * Start button
	 */
	@FXML
	private Button start;
	/**
	 * Undo Button
	 */
	@FXML
	private Label undoBTN;
	/**
	 * Game board with index 0
	 */
	@FXML
	private Rectangle box0;
	/**
	 * Game board with index 1
	 */
	@FXML
	private Rectangle box1;
	/**
	 * Game board with index 2
	 */
	@FXML
	private Rectangle box2;
	/**
	 * Game board with index 3
	 */
	@FXML
	private Rectangle box3;
	/**
	 * Game board with index 4
	 */
	@FXML
	private Rectangle box4;
	/**
	 * Game board with index 5
	 */
	@FXML
	private Rectangle box5;
	/**
	 * Game board with index 6
	 */
	@FXML
	private Rectangle box6;
	/**
	 * Game board with index 7
	 */
	@FXML
	private Rectangle box7;
	/**
	 * Game board with index 8
	 */
	@FXML
	private Rectangle box8;
	@FXML
	private Text text0;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	@FXML
	private Text text3;
	@FXML
	private Text text4;
	@FXML
	private Text text5;
	@FXML
	private Text text6;
	@FXML
	private Text text7;
	@FXML
	private Text text8;
	@FXML
	private Text winProbText;
	@FXML
	private Text drawProbText;
	@FXML
	private Text loseProbText;
	@FXML
	private Text xWins;
	@FXML
	private Text oWins;
	@FXML
	private Text xoDraw;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		TicTacToeAI.primaryStage = primaryStage;
		TicTacToeAI.primaryStage.setFullScreen(false);
		TicTacToeAI.primaryStage.setResizable(false);
		TicTacToeAI.primaryStage.setTitle("CSE 214 Homework5 by Yifan Wang :)");
		showStartPage();
	}

	/**
	 * Load the <code>FXML</code> file and display.
	 */
	@FXML
	private void showStartPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TicTacToeAI.class.getResource("MainView.fxml"));
			pane = loader.load();
			scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

	/**
	 * Quit the program. The method is linked with the <code>Label</code>
	 * "QUIT".
	 */
	@FXML
	private void quit() {
		System.exit(0);
	}

	/*
	 * @FXML private void xSelected() { this.gameTree = new GameTree(Box.X); }
	 * 
	 * @FXML private void oSelected() { this.gameTree = new GameTree(Box.O); }
	 */

	/**
	 * Start button clicked
	 */
	@FXML
	private void startClicked() {
		this.startClicked = true;
		this.start.setDisable(true);
	}

	/**
	 * Make move on index 0
	 */
	@FXML
	private void box0Clicked() {
		if (startClicked) {
			this.text0.setLayoutX(223);
			this.text0.setLayoutY(178);

			if (treeBuilt)
				this.gameTree.makeMove(0); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(0);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text0.setText("O");
			nextStep(0);
		}
	}

	/**
	 * Make move on index 1
	 */
	@FXML
	private void box1Clicked() {
		if (startClicked) {
			this.text1.setLayoutX(374);
			this.text1.setLayoutY(178);

			if (treeBuilt)
				this.gameTree.makeMove(1); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(1);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text1.setText("O");
			nextStep(1);
		}

	}

	/**
	 * Make move on index 2
	 */
	@FXML
	private void box2Clicked() {
		if (startClicked) {
			this.text2.setLayoutX(526);
			this.text2.setLayoutY(178);

			if (treeBuilt)
				this.gameTree.makeMove(2); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(2);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text2.setText("O");
			nextStep(2);
		}
	}

	/**
	 * Make move on index 3
	 */
	@FXML
	private void box3Clicked() {
		if (startClicked) {
			this.text3.setLayoutX(223);
			this.text3.setLayoutY(329);

			if (treeBuilt)
				this.gameTree.makeMove(3); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(3);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text3.setText("O");
			nextStep(3);
		}
	}

	/**
	 * Make move on index 4
	 */
	@FXML
	private void box4Clicked() {
		if (startClicked) {
			this.text4.setLayoutX(374);
			this.text4.setLayoutY(329);

			if (treeBuilt)
				this.gameTree.makeMove(4); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(4);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text4.setText("O");
			nextStep(4);
		}
	}

	/**
	 * Make move on index 5
	 */
	@FXML
	private void box5Clicked() {
		if (startClicked) {
			this.text5.setLayoutX(526);
			this.text5.setLayoutY(329);

			if (treeBuilt)
				this.gameTree.makeMove(5); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(5);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text5.setText("O");
			nextStep(5);
		}
	}

	/**
	 * Make move on index 6
	 */
	@FXML
	private void box6Clicked() {
		if (startClicked) {
			this.text6.setLayoutX(223);
			this.text6.setLayoutY(482);

			if (treeBuilt)
				this.gameTree.makeMove(6); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(6);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text6.setText("O");
			nextStep(6);
		}
	}

	/**
	 * Make move on index 7
	 */
	@FXML
	private void box7Clicked() {
		if (startClicked) {
			this.text7.setLayoutX(373);
			this.text7.setLayoutY(482);

			if (treeBuilt)
				this.gameTree.makeMove(7); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(7);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text7.setText("O");
			nextStep(7);
		}
	}

	/**
	 * Make move on index 8
	 */
	@FXML
	private void box8Clicked() {
		if (startClicked) {
			this.text8.setLayoutX(526);
			this.text8.setLayoutY(482);

			if (treeBuilt)
				this.gameTree.makeMove(8); // Advance cursor
			else {
				treeBuilt = !treeBuilt;
				if (this.gameTree == null)
					this.gameTree = new GameTree();
				this.gameTree.buildTree(8);
			}

			if (this.gameTree.getCursor().getCurrentTurn() == Box.O)
				this.text8.setText("O");
			nextStep(8);
		}
	}

	/**
	 * Make AI move
	 */
	private void nextStep(int n) {
		// Check if it is the end of the game
		Box winner = GameTree.checkWin(this.gameTree.getCursor());
		if (winner != null) {
			if (winner == Box.EMPTY)
				this.xoDraw.setVisible(true);
			else if (winner == Box.X)
				this.xWins.setVisible(true);
			else
				this.oWins.setVisible(true);
			startClicked = !startClicked; // Prevent user make further moves
			this.undoBTN.setDisable(true);
			return; // End the method
		}

		AITurn = !AITurn;
		if (!AITurn)
			return; // If there no need to make AI move

		// Find the best move
		double winProb = 0;
		int indexW = 0;
		double drawProb = 0;
		int indexD = -1;
		double loseProb = 1;
		int indexL = 1;
		int index = 0;
		
		for (int i = 0; i < 9; i++) {
			if (this.gameTree.getCursor().getConfig()[i] != null) {
				
				// Find the highest winning probability
				double w = this.gameTree.getCursor().getConfig()[i].getWinProb();
				if (winProb < w) {
					indexW = i;
					winProb = w;
				}

				// Find the highest draw probability
				double d = this.gameTree.getCursor().getConfig()[i].getDrawProb();
				if (drawProb < d) {
					indexD = i;
					drawProb = d;
				}

				// Find the lowest loss probability
				double l = this.gameTree.getCursor().getConfig()[i].getLoseProb();
				if (loseProb > l) {
					indexL = i;
					loseProb = l;
				}
			}
		}

		if (loseProb == 0) {
			if (winProb < drawProb)
				index = indexD;
			else
				index = indexW;
		} else
			index = indexL;

		// Block easy win
		try {
			for (int i = 0; i < 9; i++) {
				if (this.gameTree.getCursor().getConfig()[index].getConfig()[i] != null)
					if (this.gameTree.getCursor().getConfig()[index].getConfig()[i].isEnd())
						index = i;
			}
			
			if(this.gameTree.getTotalNumMoves() == 3){
				if(this.gameTree.isDiagnoal())
					index = 1;
			}
		} catch (Exception e) {
		}

		if (winProb == 1)
			index = indexW;
		else if (drawProb == 1)
			index = indexD;

		// Make best AI move
		if (AITurn) {
			this.undoBTN.setDisable(false);
			switch (index) {
			case 0:
				this.box0Clicked();
				break;
			case 1:
				this.box1Clicked();
				break;
			case 2:
				this.box2Clicked();
				break;
			case 3:
				this.box3Clicked();
				break;
			case 4:
				this.box4Clicked();
				break;
			case 5:
				this.box5Clicked();
				break;
			case 6:
				this.box6Clicked();
				break;
			case 7:
				this.box7Clicked();
				break;
			case 8:
				this.box8Clicked();
				break;
			}
			
			// Show probabilities
			this.winProbText.setText("" + this.gameTree.getCursor().getLoseProb());
			this.drawProbText.setText("" + this.gameTree.getCursor().getDrawProb());
			this.loseProbText.setText("" + this.gameTree.getCursor().getWinProb());
		}
	}

	/**
	 * Undo button clicked
	 */
	@FXML
	private void undo() {
		try {
			// Undo AI move
			int i = this.gameTree.undo();
			undo(i);

			// Undo human move
			i = this.gameTree.undo();
			undo(i);

			// If there is no move left, tree needs to be rebuilt.
			if (this.gameTree.getTotalNumMoves() == 0)
				this.treeBuilt = !this.treeBuilt;

		} catch (Exception e) {
			undoBTN.setDisable(true);
		}
	}

	/**
	 * Undo corresponding <code>Text</code>
	 * 
	 * @param index
	 */
	private void undo(int index) {
		switch (index) {
		case 0:
			text0.setText("X");
			text0.setLayoutX(14.0);
			text0.setLayoutY(554.0);
			break;
		case 1:
			text1.setText("X");
			text1.setLayoutX(14.0);
			text1.setLayoutY(554.0);
			break;
		case 2:
			text2.setText("X");
			text2.setLayoutX(14.0);
			text2.setLayoutY(554.0);
			break;
		case 3:
			text3.setText("X");
			text3.setLayoutX(14.0);
			text3.setLayoutY(554.0);
			break;
		case 4:
			text4.setText("X");
			text4.setLayoutX(14.0);
			text4.setLayoutY(554.0);
			break;
		case 5:
			text5.setText("X");
			text5.setLayoutX(14.0);
			text5.setLayoutY(554.0);
			break;
		case 6:
			text6.setText("X");
			text6.setLayoutX(14.0);
			text6.setLayoutY(554.0);
			break;
		case 7:
			text7.setText("X");
			text7.setLayoutX(14.0);
			text7.setLayoutY(554.0);
			break;
		case 8:
			text8.setText("X");
			text8.setLayoutX(14.0);
			text8.setLayoutY(554.0);
			break;
		}
	}

}
