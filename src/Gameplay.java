import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Gameplay {
	private ArrayList<Player> players;
	private Board board;
	private Player currentPlayer;
	private WheelSpace currentSlice;
	private JFrame frame;
	private boolean winner;

	public static final int AMOUNT_OF_PLAYERS = 3;

	// Player can spin wheel, buy vowel, guess puzzle

	public Gameplay(JFrame frame) {
		this.players = new ArrayList<Player>();
		this.board = board;
		this.currentSlice = currentSlice;
		this.frame = frame;

		for (int i = 1; i <= AMOUNT_OF_PLAYERS; i++) {
			String name = javax.swing.JOptionPane.showInputDialog("Player " + i + " name");
			if (name != null) {
				Player player = new Player(name);
				players.add(player);
			}
		}
		this.currentPlayer = players.get(0);
	}

	public void begin() {
		int turn = 0;
		while (!getWinner()) {
			JOptionPane.showMessageDialog(frame, currentPlayer.getName() + "'s turn!");
			turn++;
			currentPlayer = players.get(turn % 3);
		}
	}

	// Initial player setup
	public ArrayList<Player> getPlayers() {
		return players;
		// First player must spin wheel or guess puzzle
		// Execute whichever button player picks
	}

	public void setSlice(WheelSpace slice) {
		this.currentSlice = slice;
	}

	public WheelSpace getSlice() {
		return this.currentSlice;
	}

	public void buyVowel() {
		// if player balance below $250, cannot buy vowel
		int vowelAmount = 250;
		if (currentPlayer.getBalance() < vowelAmount) {
			javax.swing.JOptionPane.showMessageDialog(frame, "Must have at least $250 to buy vowel!");
		}
		// Vowel panel is enabled, player selects vowel
		// if vowel is correct, all instances shown and player gets another turn
	}

	// Popup window asking for text input for player's guess
	public void guessPuzzle() {
		// If guess is correct, game over
		// else next turn
	}

	public void nextTurn() {

	}

	// Message telling player they get another turn
	public void repeatTurn() {

	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public WheelSpace currentSlice() {
		return currentSlice;
	}

	public boolean getWinner() {
		return winner;
	}

	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
}
