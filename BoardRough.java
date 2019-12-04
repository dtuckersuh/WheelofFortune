package finalGroupProject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class BoardRough
{
	public static final String VOWELS = "AEIOU";
	public static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";
	
	public static String puzzleGiven = "";
	public static String gamePuzzle = "";
	public static JFrame frame = new JFrame("Wheel of Fortune");
	public static JLabel puzzle = new JLabel(createBoard());
	public static JPanel vowelPanel = new JPanel();
	public static JPanel consonantPanel = new JPanel();
	public static JButton keyButton;
	
	
	public static int i = 0;
	public static JButton [] vowelButtons = new JButton[VOWELS.length()];
	public static JButton [] consonantButtons = new JButton[CONSONANTS.length()];
	public static boolean [] vowelPermDisable = new boolean[VOWELS.length()];
	public static boolean [] consonantPermDisable = new boolean[CONSONANTS.length()];
	public static boolean permDisable;
	
	
	public static String updateBoard(char i)
	{
		if (VOWELS.indexOf(i) >= 0 || CONSONANTS.indexOf(i) >= 0)
			gamePuzzle = gamePuzzle + ("_ "); //Need space before '_' so it is readable.
		else if (Character.isWhitespace(i) )
			gamePuzzle = gamePuzzle + ("  "); //total of 3 spaces because ("  " + " _") = 3
		//i is a punctuation mark
		else gamePuzzle = gamePuzzle + i + " "; //Need space before '_' so it is readable.

		return gamePuzzle;
	}
	
	public static String createBoard()
	{
		//Puzzle Library
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

		//Generate givenPuzzle for the game.
		Random rand = new Random(); 
		int puzzleGenerator = rand.nextInt(puzzlesLibrary.size()); 
		puzzleGiven = puzzlesLibrary.get(puzzleGenerator).toUpperCase();

		//Create Blank Puzzle
		for (char i:puzzleGiven.toCharArray())
			updateBoard(i);
		return gamePuzzle;
	}
	
	public static String guessLetter(String x)
	{
		x = x.toUpperCase();
		char c = x.charAt(0);
		int index = -1;
		
		for (int i = 0; i<puzzleGiven.length();i++)
		{
			//if c is in puzzle
			if (puzzleGiven.charAt(i) == c)
			{
				index = (i*2);
				gamePuzzle = gamePuzzle.substring(0, (index)) + c + gamePuzzle.substring(index+1);
			}
		}
		puzzle.setText(gamePuzzle);
		puzzle.repaint();
		frame.repaint();
		System.out.println("This is updated gamePuzzle: " + gamePuzzle);
		return gamePuzzle;
	}
	
	public static void enableButtons(JButton [] a)
	{
		if (a == vowelButtons)
		{
			for (i = 0; i < vowelButtons.length; i++)
			{
				if (!vowelPermDisable[i])
					a[i].setEnabled(true);
			}
		}
		else
		{
			for (i = 0; i < consonantButtons.length; i++)
			{
				if (!consonantPermDisable[i])
					a[i].setEnabled(true);
			}
		}
		vowelPanel.repaint();
		frame.repaint();
	}
	
	public static void disableButton(JButton [] a, int letter) //keeps getting 21
	{
		System.out.println("This is a[letter]: " + letter);
		a[letter].setEnabled(false);
		if (a == vowelButtons)
			vowelPermDisable[letter] = true;
		else 
			consonantPermDisable[letter] = true;
	}
	
	public static void main(String[] args) 
	{
		frame.setPreferredSize(new Dimension(800, 550));
		frame.setLayout(new FlowLayout());

		//Puzzle
		puzzle.setFont(new Font("SansSerif", Font.BOLD, 20));
		frame.add(puzzle);

		//Vowels
		vowelPanel.setLayout(new GridLayout(2,3));
		for (i = 0; i < VOWELS.length(); i++)
		{
			final String label = VOWELS.substring(i, i + 1);
			JButton keyButton = new JButton(label);
			vowelButtons[i]=(keyButton);
			
			vowelButtons[i].addActionListener(event ->
			disableButton(vowelButtons,i));
			
			vowelButtons[i].addActionListener(event ->
			guessLetter(label));
			vowelPanel.add(vowelButtons[i]);
			//create array of buttons
		}
		//Consonants
		
		consonantPanel.setLayout(new GridLayout(3,3));
		for (i = 0; i < CONSONANTS.length(); i++)
		{
			final String label = CONSONANTS.substring(i, i + 1);
			JButton keyButton = new JButton(label);
			consonantButtons[i]=(keyButton);
			
			System.out.println("this is the letter i in consonants: " + i);
			consonantButtons[i].addActionListener(event ->
			disableButton(consonantButtons,i));
			System.out.println("this is the letter i in consonants updated: " + i);
			
			consonantButtons[i].addActionListener(event ->
			guessLetter(label));
			consonantPanel.add(consonantButtons[i]);
		}
		
		frame.add(vowelPanel);
		frame.add(consonantPanel);
		
		System.out.println("vowels length: " + vowelButtons.length);	
		System.out.println("consonants length: " + consonantButtons.length);
		System.out.println("consonants at 5: " + CONSONANTS.substring(5, 6));
		
		
		
		
		//Other Buttons
		JButton spinWheelButton = new JButton("Spin Wheel");
		frame.add(spinWheelButton);
		spinWheelButton.addActionListener(event ->
		enableButtons(consonantButtons));
		
		JButton buyVowelButton = new JButton("Buy a Vowel");
		buyVowelButton.addActionListener(event ->
		buyVowelButton.setEnabled(false));
		frame.add(buyVowelButton);
		
		JButton solvePuzzleButton = new JButton("Solve Puzzle");
		frame.add(solvePuzzleButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}