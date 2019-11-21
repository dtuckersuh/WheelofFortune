
import java.util.Random;

import javax.swing.JFrame;

public class WheelOfFortune {

	// Wheel of Fortune Main Frame
	public static void main(String[] args) {

		WheelOfFortuneFrame gameFrame;

		gameFrame = new WheelOfFortuneFrame(new Random());
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.pack();

		// Show the game interface and start the game!
		gameFrame.setVisible(true);
	}
}
