
public class Gameplay {
	private Player[] players;
	private Board board;
	private Player currentPlayer;
	private WheelSpace currentSlice;

	public static final int AMOUNT_OF_PLAYERS = 3;

	// Player can spin wheel, buy vowel, guess puzzle

	public void begin() {
		// Game start:
		// Input three player's name
		for (int i = 0; i < AMOUNT_OF_PLAYERS; i++) {
			// Player player = new Player(name);
			// players.add(player);
		}
		// First player must spin wheel or guess puzzle
		// Execute whichever button player picks
	}

	public void spinWheel(WheelSpace slice) {
		int amount = slice.getSpaceValue();
		// If wheel lands on monetary amount
		// player guesses consonant
		// if guess is correct
		// all instances of letter displayed, player awarded amount of money
	}

	public void buyVowel() {
		// if player balance below $250, cannot buy vowel
		int vowelAmount = 250;
		if (currentPlayer.getBalance() < vowelAmount) {
			// Error message: Player does not have funds for vowel
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

	public void setCurrentPlayer(Player player) {
		currentPlayer = player;
	}
}
