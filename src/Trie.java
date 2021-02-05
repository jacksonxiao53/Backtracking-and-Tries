import java.util.*;
public class Trie {
	private TrieNode root; // root of trie
	
	public Trie() {
		root = new TrieNode();
	}
	/**
	 * Insert word from dictionary into trie. Consumes a word and a morse code representation of the word. 
	 * @param word - word from dictionary
	 * @param code - morse code representation of word from dictionary
	 */
	public void addWord(String word, String code) {
		HashMap<String,TrieNode> children = root.children;
		for (int i = 0; i< code.length();i++) {
			char c = code.charAt(i);
			String letter = String.valueOf(c);
			TrieNode newNode;
			if(children.containsKey(letter)) {
				newNode = children.get(letter);
			}
			else {
				newNode = new TrieNode(letter);
				children.put(letter,newNode);
			}
			children = newNode.children;
			if(i == code.length()-1) {
				newNode.words.add(word);
				//System.out.println(newNode.word);
				newNode.isWord = true;
			}
		}
	}
	

	/**
	 * Consume a morse code representation of a word and returns true if the trie has the word and false if it does not
	 * @param code - morse code reprsentation of a word
	 * @return - true or false
	 */
	public boolean hasWord(String code) {
		TrieNode node = search(code);
		if(node != null && node.isWord) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Will traverse the trie to find a given morse code. Will return the node if it is in the trie or null if it is not
	 * @param code - morse representation of word
	 * @return - node
	 */
	public TrieNode search(String code) {
		HashMap<String,TrieNode> children = root.children;
		TrieNode node = null;
		for(int i = 0; i<code.length();i++) {
			char c = code.charAt(i);
			String letter = String.valueOf(c);
			if(children.containsKey(letter)) {
				node = children.get(letter);
				children = node.children;
			}
			else {
				return null;
			}
		}
		return node;
	}
	
	/**
	 * Will call the search method and then return the ArrayList of possible words
	 * @param code - morse representation of word
	 * @return words - ArrayList of possible words
	 */
	public ArrayList<String> getWords(String code){
		TrieNode node = search(code);
		ArrayList<String> words = node.words;
		return words;
	}
}
