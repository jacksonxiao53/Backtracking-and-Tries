import java.util.*;
public class TrieNode {
	String letter; // dot or dash
	HashMap<String,TrieNode> children = new HashMap<>(); // HashMap of children nodes
	ArrayList<String> words = new ArrayList<>(); // will store complete words of a dictionary
	boolean isWord; // set true if there is a word
	
	public TrieNode() {}
	
	public TrieNode(String letter) {
		this.letter = letter;
	}
}
