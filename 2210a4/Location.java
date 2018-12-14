//This class represents the position (x, y) of a pixel
public class Location {

	int x;
	int y;

	//constructor, innitializes the location object with the specified coordinates
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//Returns the x coordinate of this Location
	public int xCoord () {
		return x;
	}

	//Returns the y coordinate of this Location
	public int yCoord () {
		return y;
	}

	//Compares current Location with p
	public int compareTo (Location p) {
		if (x == p.xCoord() && y == p.yCoord()){
			return 0;
		}
		else if ((x > p.xCoord())||((x == p.xCoord()) && (y > p.yCoord()))){
			return  1;
		}
		else{
			return -1;
		}
	}
}