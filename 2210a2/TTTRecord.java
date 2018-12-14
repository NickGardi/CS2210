//This class represents one of the configurations 
//that will be stored in the dictionary along with its
//integer score and level. Each board configuration will 
//be represented as a string obtained by concatenating
//all characters placed on the board starting at the top 
//left position and moving from top to
//bottom and left to right

public class TTTRecord {
	
	private int score, level; 
	private String config; 
	
	
	public TTTRecord(String config, int score, int level) {
		this.config = config;
		this.score = score;
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public String getConfiguration() {
		return config;
	}

	public int getScore() {
		return score;
	}

}