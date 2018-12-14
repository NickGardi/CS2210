//This class implements an ordered dictionary using a binary search tree. Each node of the tree will store a
//Pixel object; the attribute Location of the Pixel will be its key. In your binary search tree only the
//internal nodes will store information. The leaves are nodes (leaves are not null) that do not store
//any data
public class BinarySearchTree implements BinarySearchTreeADT {

	private BinaryNode root;

	//constructor
	public BinarySearchTree() {
		root = new BinaryNode();
	}
	
	//helper method not in assignment, returns a node object given the node and key;
	//verifies that the node and key are valid
    private BinaryNode find(BinaryNode r, Location key){
        if(r.isLeaf()){
            return r;
        }
        else{
            int test = r.getData().getLocation().compareTo(key);
            if(test == 0)
                return r;
            if(test > 0)
                return find(r.getLeft(), key);
            if(test < 0)
                return find(r.getRight(), key);
        }
        return null;
    }
	
	//Returns the Pixel storing the given key, if
	//the key is stored in the tree; returns null otherwise
	public Pixel get(BinaryNode r, Location key) {
		Location BinaryNodeKey;
		if (r.isLeaf())
			return null;
		BinaryNodeKey = r.getData().getLocation();
		if (BinaryNodeKey.compareTo(key) == 0)
			return r.getData();
		if (key.compareTo(BinaryNodeKey) < 0)
			return get(r.getLeft(), key);
		else
			return get(r.getRight(), key);
	}

	//Inserts the given data in the tree if no data item with the same key is already there. If a node already stores the
	//same key, the algorithm throws a DuplicatedKeyException
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		Location x, y;
		if (r.isLeaf() != true) {
			y = data.getLocation();
			x = r.getData().getLocation();
			if(y.compareTo(x) == 0)
				throw (new DuplicatedKeyException("Duplicated key"));
			else if(y.compareTo(x) < 0)
				put(r.getLeft(), data);
			else
				put(r.getRight(), data);
		}else{
			r.setData(data);
			r.setLeft(new BinaryNode());
			r.getLeft().setParent(r);
			r.setRight(new BinaryNode());
			r.getRight().setParent(r);
		}
	}
	
	//Removes the data item with the given key, if the key is stored in the tree; throws an InexistentKeyException
	//otherwise.
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		BinaryNode nodeX = find(r, key);
		if(nodeX == null || nodeX.isLeaf())
			throw new InexistentKeyException("Not in dictionary");
		if((nodeX.getLeft()).isLeaf()) { 
			(nodeX.getRight()).setParent(nodeX.getParent());
			if(nodeX.getParent() == null) 
				root = nodeX.getRight(); 
			else if((nodeX.getParent()).getLeft() == nodeX)
				(nodeX.getParent()).setLeft(nodeX.getRight());
			else{
				(nodeX.getParent()).setRight(nodeX.getRight());
			}
		}
		if((nodeX.getRight()).isLeaf()) {
			(nodeX.getLeft()).setParent(nodeX.getParent());
			if(nodeX.getParent() == null)
				root = nodeX.getLeft();
			else if ((nodeX.getParent()).getLeft() == nodeX)
				(nodeX.getParent()).setLeft(nodeX.getLeft());
			else
				(nodeX.getParent()).setRight(nodeX.getLeft());
		}else{
			BinaryNode nodeY = nodeX.getRight();
			while (nodeY.isLeaf() != true){
				nodeY = nodeY.getLeft();
			}
			nodeY = nodeY.getParent();
			Pixel pixel2 = nodeY.getData();
			nodeX.setData(pixel2);
			if (nodeY.getParent() != nodeX) { 
				(nodeY.getParent()).setLeft(nodeY.getRight());
				(nodeY.getRight()).setParent(nodeY.getParent());
			}else{
				nodeX.setRight(nodeY.getRight());
				nodeY.getRight().setParent(nodeX);
			}
		}
	}

	//Returns the Pixel with the smallest
	//key larger than the given one 
	//Returns null if the given key has no successor
	public Pixel successor(BinaryNode r, Location key) {
		if (r.isLeaf()){
			return null;
		}
		BinaryNode nodeX = find(r, key);
		if (nodeX == null){
			return null;
		}
		if (!nodeX.isLeaf() && !(nodeX.getRight()).isLeaf()) {
			BinaryNode nodeY = nodeX.getRight();
			while (!nodeY.isLeaf()){
				nodeY = nodeY.getLeft();
			}
			nodeY = nodeY.getParent();
			return nodeY.getData();
		}else{
			if(nodeX.getParent() == null)
				return null;
			BinaryNode nodeY = nodeX.getParent();
			while((nodeY != null) && (nodeY.getLeft() != nodeX)){
				nodeX = nodeY;
				nodeY = nodeY.getParent();
			}
			if (nodeY == null){
				return null;
			}
			else{
				return nodeY.getData();
			}
		}
	}

	//Returns the Pixel with the
	//largest key smaller than the given one
	//Returns null if the given key has no predecessor.
	public Pixel predecessor(BinaryNode r, Location key) {
		if (r.isLeaf()){
			return null;
		}
		BinaryNode nodeX = find(r, key);
		if (nodeX == null){
			return null;
		}
		if (!nodeX.isLeaf() && !(nodeX.getLeft()).isLeaf()) {
			BinaryNode nodeY = nodeX.getLeft();
			while(!nodeY.isLeaf()){
				nodeY = nodeY.getRight();
			}
			nodeY = nodeY.getParent();
			return nodeY.getData();
		}else{
			if(nodeX.getParent() == null)
				return null;
			BinaryNode nodeY = nodeX.getParent();
			while((nodeY != null) && (nodeY.getRight() != nodeX)){
				nodeX = nodeY;
				nodeY = nodeY.getParent();
			}
			if(nodeY == null){
				return null;
			}
			else{
				return nodeY.getData();
			}
		}
	}

	//Returns the Pixel with the smallest key. 
	//Throws an EmptyTreeException if the tree does not contain any data
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()){
			throw new EmptyTreeException("EmptyTreeException");
		}
		else {
			BinaryNode node = r;
			while (node.isLeaf() != true){
				node = node.getLeft();
			}
			node = node.getParent();
			return node.getData();
		}
	}

	//Returns the Pixel with the largest key. 
	//Throws an EmptyTreeException if the tree does not contain any data
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if (r.isLeaf()){
			throw new EmptyTreeException("EmptyTreeException");
		}
		else{
			BinaryNode node = r;
			while (!node.isLeaf()){
				node = node.getRight();
			}
			node = node.getParent();
			return node.getData();
		}
	}
	
	// Returns the root of the binary search tree
	public BinaryNode getRoot(){
		return root;
	}	

}