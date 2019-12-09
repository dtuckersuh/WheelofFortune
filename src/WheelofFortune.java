import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WheelofFortune extends JFrame {
	public static final String VOWELS = "AEIOU";
	public static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Board board = new Board(frame);
		WheelOfFortuneFrame gameFrame = new WheelOfFortuneFrame(new Random());
		Gameplay game = new Gameplay();

		frame.setPreferredSize(new Dimension(800, 550));

		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Player Panel
		JPanel playerPanel = new JPanel();
		playerPanel.setBorder(BorderFactory.createTitledBorder("Players"));
		mainPanel.add(playerPanel);

		// Spin Wheel Button
		JButton spinWheelButton = new JButton("Spin Wheel");
		JLabel sliceLabel = new JLabel();
		sliceLabel.setSize(400, 400);
		spinWheelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WheelSpace slice = gameFrame.spinWheel();
				ImageIcon image = slice.getWheelImage();
				sliceLabel.setIcon(image);
				sliceLabel.setVisible(true);
				// game.spinWheel(slice); // Refer to Gameplay class
			}

		});
		// Panel containing buyVowel, spinWheel, solvePuzzle buttons and wheel image
		JPanel centerPanel = new JPanel(new FlowLayout());
		centerPanel.setPreferredSize(new Dimension(800, 225));
		// Buy Vowel Button
		JButton buyVowelButton = new JButton("Buy a Vowel");
		buyVowelButton.addActionListener(event -> buyVowelButton.setEnabled(true));
		buyVowelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Deduct $250 from currentPlayer's account
				// Player selects vowel
				// If vowel is correct, all instances revealed and player gets another turn
				// game.buyVowel();
			}

		});
		
		//Solve Puzzle Button
		JButton solvePuzzleButton = new JButton("Solve Puzzle");
		solvePuzzleButton.addActionListener(new ActionListener() {
			// Popup window where currentPlayer types in guess
			// If guess is correct, player wins all money in their balance
			// Else next turn
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// window to type in the answer
				String solve = JOptionPane.showInputDialog(null, "Please solve the puzzle as displayed:",
						"Solve the Puzzle", JOptionPane.PLAIN_MESSAGE);

				String phrase = "Hello"; // change to the actual puzzleGiven
				StringBuilder puzzlePhrase = new StringBuilder(); // to compare the phrase with the answer
				StringBuilder answer = new StringBuilder(); //for the input

				for (int i = 0; i < phrase.length(); i++) {
					if (phrase.charAt(i) != ' ') {
						puzzlePhrase.append(phrase.charAt(i));
					}
				}
				
				if (solve != null) {
					for (int i = 0; i < solve.length(); ++i) {
						if (solve.charAt(i) != ' ') {
							answer.append(solve.charAt(i));
						}
					}
				}
				// if the answer is correct
				if (answer.toString() != "") {
					if (answer.toString().compareToIgnoreCase(puzzlePhrase.toString()) == 0) {
						JOptionPane.showMessageDialog(null, "Congratulations, Player win $$$", // change the "Player" to the actual name 
																								//and "$$$" to the actual amount of current player
								"Game Over. You Win!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					// wrong answer message window
					else {
						JOptionPane.showMessageDialog(null, "Guess by Player was incorrect!", // change "Player" to the current player
								"Wrong Answer!", JOptionPane.ERROR_MESSAGE);
					}
				}

			}

		});
		
		
		
		
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		buttonPanel.add(buyVowelButton);
		buttonPanel.add(spinWheelButton);
		buttonPanel.add(solvePuzzleButton);

		centerPanel.add(buttonPanel);
		centerPanel.add(sliceLabel);
		mainPanel.add(centerPanel);

		// Vowel Panel
		JPanel vowelPanel = new JPanel();
		vowelPanel.setBorder(BorderFactory.createTitledBorder("Vowels"));
		vowelPanel.setLayout(new GridLayout(2, 3));
		for (int i = 0; i < VOWELS.length(); i++) {
			final String label = VOWELS.substring(i, i + 1);
			JButton keyButton = new JButton(label);
			keyButton.addActionListener(event -> keyButton.setEnabled(true));
			keyButton.addActionListener(event -> board.guessLetter(label));
			vowelPanel.add(keyButton);
			// create array of buttons
			vowelPanel.add(keyButton);
		}
		// Consonant Panel
		JPanel consonantPanel = new JPanel();
		consonantPanel.setBorder(BorderFactory.createTitledBorder("Consonants"));
		consonantPanel.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < CONSONANTS.length(); i++) {
			final String label = CONSONANTS.substring(i, i + 1);
			JButton keyButton = new JButton(label);

			keyButton.addActionListener(event -> keyButton.setEnabled(true));

			keyButton.addActionListener(event -> board.guessLetter(label));
			consonantPanel.add(keyButton);
		}

		// Panel containing vowel and consonants
		JPanel letterContainer = new JPanel();
		letterContainer.setLayout(new GridLayout(1, 2));
		letterContainer.add(vowelPanel);
		letterContainer.add(consonantPanel);
		letterContainer.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(letterContainer);

		// Puzzle Panel
		JPanel puzzlePanel = new JPanel();
		JLabel puzzle = board.getPuzzle();
		puzzle.setFont(new Font("SansSerif", Font.BOLD, 20));
		puzzlePanel.add(puzzle);
		puzzlePanel.setBorder(BorderFactory.createTitledBorder("Puzzle"));
		mainPanel.add(puzzlePanel);

		frame.add(mainPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}