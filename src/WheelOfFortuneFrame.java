
import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.Random;
import javax.swing.ImageIcon;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class WheelOfFortuneFrame extends JFrame {
	// Number of wheel spaces in the game.
	public static final int NUM_WHEEL_SPACES = 24;

	// Path to images folder.
	public static final String IMAGES_PATH = "images";
	// File extension for images.
	public static final String IMAGE_EXTENSION = "jpg";

	private Random generator;

	/**
	 * Loads wheel-space images from the images/ directory.
	 *
	 * Looks for files that follow the naming pattern <spaceNumber>_<value>.jpg.
	 * Ignores all other files in the directory. Assumes that there are exactly
	 * NUM_WHEEL_SPACES images, numbered from 1 to NUM_WHEEL_SPACES.
	 *
	 * @return array of WheelSpace objects representing the images
	 */
	static WheelSpace[] loadImages() {
		File[] fileList;
		File myDir = null;

		// Allocate array for number of spaces, which is set to a constant
		WheelSpace[] wheelSpaces = new WheelSpace[NUM_WHEEL_SPACES];

		// Get a File object for the directory containing the images
		try {
			myDir = new File(WheelOfFortuneFrame.class.getClassLoader().getResource(IMAGES_PATH).toURI());
		} catch (URISyntaxException uriExcep) {
			System.out.println("Caught a URI syntax exception");
			System.exit(4); // Just bail
		}

		// Loop from 1 to the number of spaces expected
		// Note: Space numbers in image filenames are 1-based, NOT 0-based.
		for (int i = 1; i <= NUM_WHEEL_SPACES; i++) {
			// Get a listing of files named appropriately for an image for
			// wheel space #i. There should only be one, and this will be
			// checked below.
			fileList = myDir.listFiles(new WheelSpaceImageFilter(i));

			if (fileList.length == 1) {
				// Index starts at 0, space numbers start at 1: hence the - 1
				wheelSpaces[i - 1] = new WheelSpace(WheelSpaceImageFilter.getSpaceValue(fileList[0]),
						new ImageIcon(fileList[0].toString()));
			} else {
				System.out.println("ERROR: Invalid number of images for space: " + i);
				System.out.println("       Expected 1, but found " + fileList.length);
			}
		}

		return wheelSpaces;
	}

	// Helper nested class to filter images used for wheel spaces, based
	// on specifically expected filename format.
	private static class WheelSpaceImageFilter implements FileFilter {
		/** Prefix of the requested filename. */
		private String prefix; // The prefix of the filename we're looking
		// for - what comes before the first underscore

		/**
		 * Constructs a filter with the given prefix.
		 *
		 * @param inPref integer corresponding to the prefix
		 */
		WheelSpaceImageFilter(int inPref) {
			// Sets the prefix member to string version of space number
			prefix = ((Integer) inPref).toString();
		}

		/**
		 * Tests whether the file provided should be accepted by our file filter. In the
		 * FileFilter interface.
		 */
		@Override
		public boolean accept(File imageFile) {
			boolean isAccepted = false;
			// Accepted if matched "<prefix>_<...>.jpg"
			// IMAGE_EXTENSION is assumed to be "jpg"
			if (imageFile.getName().startsWith(prefix + "_") && imageFile.getName().endsWith("." + IMAGE_EXTENSION)) {
				isAccepted = true;
			}

			return isAccepted;
		}

		/**
		 * Parses a wheel space image's filename to determine the dollar value
		 * associated with it.
		 *
		 * @param imageFile the wheel space image
		 * @return the dollar value associated with the image
		 */
		public static int getSpaceValue(File imageFile) {
			int dollar = 0;
			String fileName = imageFile.getName();
			if (fileName.substring(0, 2).equals("1_")) {
				dollar = -1; // Lose a Turn
			} else if (fileName.substring(0, 2).equals("21")) {
				dollar = -2; // Bankrupt
			}
			String[] tokens = fileName.split(("\\.(?=[^\\.]+$)"));
			int index = tokens[0].indexOf("_");
			if (index != -1) {
				try {
					dollar = Integer.parseInt(tokens[0].substring(index + 1));
				} catch (NumberFormatException e) {
				}
			}
			return dollar;
		}
	}

	/**
	 * Create and start a game of Wheel of Fortune.
	 *
	 * @param generator the random-number generator to use
	 */
	public WheelOfFortuneFrame(Random generator) {
		this.generator = generator;

	}

	// Randomly generates slice wheel will land on
	public WheelSpace spinWheel() {
		WheelSpace[] wheel = loadImages();
		int wheelSpace = generator.nextInt(NUM_WHEEL_SPACES);
		return wheel[wheelSpace];
	}

}
