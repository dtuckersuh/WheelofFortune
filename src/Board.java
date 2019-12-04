import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Board {
	public static final String VOWELS = "AEIOU";
	public static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";

	public static String puzzleGiven = "";
	public static String gamePuzzle = "";
	public static JFrame frame = new JFrame("Wheel of Fortune");
	public static JLabel puzzle = new JLabel(createBoard());
	public static JButton keyButton;
	private static WheelOfFortuneFrame gameFrame;

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

	public static String createBoard() {
		setGameFrame(new WheelOfFortuneFrame(new Random()));

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

	public static String guessLetter(String x) {
		x = x.toUpperCase();
		char c = x.charAt(0);
		int index = -1;

		for (int i = 0; i < puzzleGiven.length(); i++) {
			// if c is in puzzle
			if (puzzleGiven.charAt(i) == c) {
				index = (i * 2);
				gamePuzzle = gamePuzzle.substring(0, (index)) + c + gamePuzzle.substring(index + 1);
			}
		}
		puzzle.setText(gamePuzzle);
		puzzle.repaint();
		frame.repaint();
		System.out.println("This is updated gamePuzzle: " + gamePuzzle);
		return gamePuzzle;
	}

	public static void main(String[] args) {
		frame.setPreferredSize(new Dimension(800, 550));
		frame.setLayout(new FlowLayout());

		// Puzzle
		puzzle.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.add(puzzle);

		// Vowels
		JPanel vowelPanel = new JPanel();
		vowelPanel.setLayout(new GridLayout(2, 3));
		for (int i = 0; i < VOWELS.length(); i++) {
			final String label = VOWELS.substring(i, i + 1);
			JButton keyButton = new JButton(label);
			keyButton.addActionListener(event -> keyButton.setEnabled(false));

			keyButton.addActionListener(event -> guessLetter(label));
			vowelPanel.add(keyButton);
			// create array of buttons

			vowelPanel.add(keyButton);
			// System.out.println(gamePuzzle);
		}
		// Consonants
		JPanel consonantPanel = new JPanel();
		consonantPanel.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < CONSONANTS.length(); i++) {
			final String label = CONSONANTS.substring(i, i + 1);
			JButton keyButton = new JButton(label);

			keyButton.addActionListener(event -> keyButton.setEnabled(false));

			keyButton.addActionListener(event -> guessLetter(label));
			consonantPanel.add(keyButton);
		}

		frame.add(vowelPanel);
		frame.add(consonantPanel);

		// Spin Wheel Button
		JButton spinWheelButton = new JButton("Spin Wheel");
		JLabel sliceLabel = new JLabel();
		sliceLabel.setBounds(10, 10, 400, 400);
		frame.add(sliceLabel, java.awt.BorderLayout.SOUTH);
		spinWheelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WheelSpace slice = gameFrame.spinWheel();
				ImageIcon image = slice.getWheelImage();
				sliceLabel.setIcon(image);
				sliceLabel.setVisible(true);
			}

		});
		frame.add(spinWheelButton);

		JButton buyVowelButton = new JButton("Buy a Vowel");
		buyVowelButton.addActionListener(event -> buyVowelButton.setEnabled(false));
		frame.add(buyVowelButton);
		JButton solvePuzzleButton = new JButton("Solve Puzzle");
		frame.add(solvePuzzleButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static WheelOfFortuneFrame getGameFrame() {
		return gameFrame;
	}

	public static void setGameFrame(WheelOfFortuneFrame gameFrame) {
		Board.gameFrame = gameFrame;
	}
}
