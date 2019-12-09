import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Board {
	public static final String VOWELS = "AEIOU";
	public static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";

	public static String puzzleGiven = "";
	public static String gamePuzzle = "";
	private JFrame frame;
	public static JLabel puzzle = new JLabel(createPuzzle());
	public static JButton keyButton;
	private Gameplay game;

	public Board(JFrame frame) {
		this.frame = frame;
		this.game = game;
	}

	public static String updateBoard(char i) {
		if (VOWELS.indexOf(i) >= 0 || CONSONANTS.indexOf(i) >= 0)
			gamePuzzle = gamePuzzle + ("_ "); // Need space before '_' so it is readable.
		else if (Character.isWhitespace(i))
			gamePuzzle = gamePuzzle + ("  "); // total of 3 spaces because (" " + " _") = 3
		// i is a punctuation mark
		else
			gamePuzzle = gamePuzzle + i + " "; // Need space before '_' so it is readable.

		return gamePuzzle;
	}

	public static String createPuzzle() {
		// Puzzle Library
		ArrayList<String> puzzlesLibrary = new ArrayList<>();
		puzzlesLibrary.add("Object Oriented Programming!");
		puzzlesLibrary.add("CS ONE FIFTY ONE");
		puzzlesLibrary.add("SAN JOSE STATE UNIVERSITY");
		puzzlesLibrary.add("Debugging is not fun.");
		puzzlesLibrary.add("Tom Hanks is a wonderful actor.");
		puzzlesLibrary.add("The Student Union has okay food.");
		puzzlesLibrary.add("There aren't enough sections for cs classes.");
		puzzlesLibrary.add("Hello World!");
		puzzlesLibrary.add("Computer Science.");
		puzzlesLibrary.add("Solve this puzzle.");

		// Generate givenPuzzle for the game.
		Random rand = new Random();
		int puzzleGenerator = rand.nextInt(puzzlesLibrary.size());
		puzzleGiven = puzzlesLibrary.get(puzzleGenerator).toUpperCase();

		// Create Blank Puzzle
		for (char i : puzzleGiven.toCharArray())
			updateBoard(i);
		return gamePuzzle;
	}

	public int guessLetter(String x) {
		x = x.toUpperCase();
		char c = x.charAt(0);
		int index = -1;
		int instances = 0;
		for (int i = 0; i < puzzleGiven.length(); i++) {
			// if c is in puzzle
			if (puzzleGiven.charAt(i) == c) {
				index = (i * 2);
				gamePuzzle = gamePuzzle.substring(0, (index)) + c + gamePuzzle.substring(index + 1);
				instances++;
			}
		}
		puzzle.setText(gamePuzzle);
		puzzle.repaint();
		frame.repaint();
		return instances;
	}

	public JLabel getPuzzle() {
		return puzzle;
	}
}