import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Gameplay {
	private ArrayList<Player> players;
	private Player currentPlayer;
	private WheelSpace currentSlice;
	private JFrame frame;
	private boolean winner;
	private int turn;

	public static final int AMOUNT_OF_PLAYERS = 3;

	// Player can spin wheel, buy vowel, guess puzzle

	public Gameplay(JFrame frame) {
		this.players = new ArrayList<Player>();
		this.frame = frame;
		this.turn = 0;

		for (int i = 1; i <= AMOUNT_OF_PLAYERS; i++) {
			String name = javax.swing.JOptionPane.showInputDialog("Player " + i + " name");
			if (name != null) {
				Player player = new Player(name);
				players.add(player);
			}
		}
		this.currentPlayer = players.get(0);
	}

	// Initial player setup
	public ArrayList<Player> getPlayers() {
		return players;
		// First player must spin wheel or guess puzzle
		// Execute whichever button player picks
	}

	public void setSlice(WheelSpace slice) {
		this.currentSlice = slice;
		if (slice.getSpaceValue() == -1) { // Lose a Turn
			String prev = currentPlayer.getName();
			nextTurn();
			JOptionPane.showMessageDialog(frame, prev + " lost a turn! " + currentPlayer.getName() + "'s turn.");
		} else if (slice.getSpaceValue() == -2) { // Bankrupt
			currentPlayer.bankrupt();
			String prev = currentPlayer.getName();
			nextTurn();
			JOptionPane.showMessageDialog(frame, prev + " went bankrupt!" + currentPlayer.getName() + "'s turn.");
		}
	}

	public WheelSpace getSlice() {
		return this.currentSlice;
	}

	public boolean buyVowel() {
		// if player balance below $250, cannot buy vowel
		int vowelAmount = 250;
		if (currentPlayer.getBalance() < vowelAmount) {
			javax.swing.JOptionPane.showMessageDialog(frame, "Must have at least $250 to buy vowel!");
			return false;
		}
		return true;
		// Vowel panel is enabled, player selects vowel
		// if vowel is correct, all instances shown and player gets another turn
	}

	public void nextTurn() {
		turn++;
		currentPlayer = players.get(turn % 3);
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
