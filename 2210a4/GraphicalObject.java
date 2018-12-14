//this class represents a graphical object, its size, location and type
public class GraphicalObject implements GraphicalObjectADT {

	BinarySearchTree tree;
	int id, width, height;
	String type;
	Location pos;

	//constructor, creates an empty BST where the pixels of the graphical object are stored
	public GraphicalObject(int id, int width, int height, String type, Location pos){
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		tree = new BinarySearchTree();
	}
	
	//Sets the type of this graphical object to the specified value
	public void setType(String type) {
		this.type = type;
	} 
	
	//Returns the width of the enclosing rectangle for this graphical object
	public int getWidth() {
		return width;
	}
	
	//Returns the height of the enclosing rectangle for this graphical object
	public int getHeight() {
		return height;
	}
	
	//Returns the type of this graphical object
	public String getType() {
		return type;
	}
	
	//Returns the id of this graphical object
	public int getId() {
		return id;
	}
	
	//Returns the offset of this graphical object
	public Location getOffset() {
		return pos;
	}
	
	//Changes the offset of this graphical object to the specified value
	public void setOffset(Location value) {
		pos = value;
	}
	
	//Inserts pix into the binary search tree associated with this graphical object. 
	//Throws a DuplicatedKeyException if an error occurs when inserting the Pixel 
	//into the tree.
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		tree.put(tree.getRoot(), pix);
	}
	
	//additional helper method not in assignment, returns the BST
    private BinarySearchTree getTree() {
	return tree;
    }

    //Returns true if this graphical object
    //intersects the one specified in the parameter. It returns false otherwise
    public boolean intersects(GraphicalObject gobj)  {
	    BinarySearchTree BSTree = gobj.getTree();
		try {
			Pixel pixel = tree.smallest(tree.getRoot());
			while (pixel != null) {
				Location newLocation = new Location(this.getOffset().xCoord() + pixel.getLocation().xCoord() - gobj.getOffset().xCoord(), this.getOffset().yCoord() + pixel.getLocation().yCoord() - gobj.getOffset().yCoord());
				if (BSTree.get(BSTree.getRoot(), newLocation) != null) {
					return true;
				}
				pixel = tree.successor(tree.getRoot(), pixel.getLocation());
			}
			return false;
		} catch (EmptyTreeException e) {
			return false;
		}
	}
	
}

