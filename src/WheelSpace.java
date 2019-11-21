import javax.swing.ImageIcon;

// Class that represents a space on the wheel
public class WheelSpace {
	private int spaceValue;
	private ImageIcon wheelImage;

	public WheelSpace(int spaceValue, ImageIcon wheelImage) {
		this.spaceValue = spaceValue;
		this.wheelImage = wheelImage;
	}

	public int getSpaceValue() {
		return spaceValue;
	}

	public ImageIcon getWheelImage() {
		return wheelImage;
	}
}
