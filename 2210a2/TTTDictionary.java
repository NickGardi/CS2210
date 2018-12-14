import java.util.LinkedList; 


//Dictionary using a hash table with seperate chaining

public class TTTDictionary implements TTTDictionaryADT {
	
	
	//initializing hash table
	private LinkedList<TTTRecord>[] hTable;
	
	//dictionary constructor
	public TTTDictionary(int size){
		
		hTable = new LinkedList[size];
		int i = 0;
		while( i < size){
	         hTable[i] = new LinkedList<TTTRecord>(); 
	         i++;
		}
	}
	
	//returns the number of objects in the dictionary
	public int numElements() {
			return hTable.length + 1;
	}
	
	//Removes the record with the given key config from the dictionary
	public void remove(String config) throws InexistentKeyException {

		TTTRecord record = get(config); 		//initialize record
		if (record == null) {			// if the record is null throw exception
			throw new InexistentKeyException();
		} else {
			int key = hash(config);		//get the key from hash()
			hTable[key].remove(record);	//remove the record
			record = null;
		}
	}
	//hash function to get a key fron the config string, returns the int key
	private int hash(String config) {
		
		int stringSize = config.length();
		int size = hTable.length;
		int hValue = config.charAt(0);
		
		int i = 0;
		while (i<stringSize){
			hValue = (hValue * 45 + config.charAt(i)) % size;  //hash function, the key will always be <= to the size of the hashtable
			i++;
		}
		return (hValue);
	}

	//inserts the given record in the dictionary, returns 1 or 0 if there was a collision or not
	public int put(TTTRecord record) throws DuplicatedKeyException {
		String config = record.getConfiguration();	//initialize the config
		if (get(config) != null){		//config is in the dictionary throw exception
			throw new DuplicatedKeyException();
		}
		
		int index = hash(config);		//get the hashed index
		
		if (!hTable[index].isEmpty()) {		//if the table isnt empty at that index,
			hTable[index].add(record);		// add and return 1 because there was a collision
			return 1;
		}else{
			hTable[index].add(record); 		// add and return 0 as there was no collision
			return 0;
		}
	}
	
	//returns the TTTRecord stored in the dictionary given the config/key
	public TTTRecord get(String config) {

		int key = hash(config);
		
		if (hTable[key] != null) { 			//if key is not null
			for (TTTRecord record : hTable[key]) {		//iterate through the table
				if ((record.getConfiguration()).equals(config)) {		//return the config that matches
					return record;
				}
			}
		}
		return null;		//return null if config isnt found
	}
	
}


