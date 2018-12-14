//This class represents the data items to be stored in the binary search tree. 
//Each data item consists of two parts: a Location and an int color
public class Pixel{

	private Location location;
	private int value;

	//A constructor which initializes the new Pixel with the
	//specified coordinates and color. Location p is the key for the Pixel.
	Pixel(Location p, int color) {
		location = p;
		value = color;
	}

	//Returns the Location of the Pixel
	public Location getLocation() {
		return location;
	}

	//Returns the color of the Pixel
	public int getColor () {
		return value;
	}
}