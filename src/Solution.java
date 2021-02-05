import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.String;
import java.util.*;
public class Solution {
	HashMap<String,String> morseMap = new HashMap<>(); // HashMap with letter as key and morse code as value
	ArrayList<String> dictionary = new ArrayList<>(); //ArrayList of words of an English dictionary
	HashMap<String,Integer> sentenceMap = new HashMap<>(); //All possible sentences for part 4
	
	public Solution(String mFile, String dFile) {
		morseMap = readMorse(mFile);
		dictionary = readDict(dFile);
	}
	/**
	 * Consumes a string of space-separated morse-encoded letters, translate the letters to regular English, 
	 * and then print out the resulting word.
	 * @param morsed - a string of space-separated morse-encode letters
	 * @return none
	 */
	 public void handleSpacedLetters(String morsed) {
	        String[] parts = morsed.split(" ");
	        StringBuilder word = new StringBuilder();
	        for (String part : parts) {
	        	for (Map.Entry<String,String> entry : morseMap.entrySet()) {
	        		if(entry.getValue().equals(part)) {
	        			word.append(entry.getKey());
	        		}
	        	}
	        }
	        if(dictionary.contains(word.toString())) {
	        	System.out.println(word);
	        }
	        else {
	        	System.out.println();
	        }
	        
	        
	    }
	 /**
	  * Recursive backtracking algorithm that consume a sequence of dots and dashes without spaces, and then 
	  * print out the possible words.
	  * @param morsed - a sequence of dots and dashes without spaces
	  * @param candidate - the current sequence of dots and dashes
	  * @param word - the current translation of the candidate 
	  * @param len - the length of the original sequence of dots and dashes 
	  * @return solutions - an ArrayList of possible words
	  */
	 public ArrayList<String> handleWord(String morsed,String candidate, String word, int len){
		 ArrayList<String> solutions = new ArrayList<>();
	    	if(candidate.length() == len) {
	    		if(dictionary.contains(word)) {
	    			solutions.add(word);
	    			return solutions;
	    		}
	    		else {
	    			return solutions;
	    		}
	    	}
	    	else {
	    		for(int i = 1; i<=morsed.length();i++) {
	    			String nextCode = morsed.substring(0,i);
	    			for(Map.Entry<String,String> entry: morseMap.entrySet()) {
	    				if(entry.getValue().equals(nextCode)) {
	    					ArrayList<String> solution = handleWord(morsed.substring(i,morsed.length()),candidate.concat(nextCode),word.concat(entry.getKey()),len);
	    					solutions.addAll(solution);
	    				}
	    			}
	    		}
	    	}
	    	return solutions;    
	 
    }
	    /**
	     * Consumes a string with sequences of dots and dashes separated spaces, and then print out all 
	     * combination of the two sequences alphabetically. A trie is implemented to rule out combination of letters
	     * that do not have a valid word
	     * @param morsed - a string with two sequences of dots and dashes separated by a space
	     * @param trie - 
	     * @param index - value is 0 and keep track of which sequence is being explore
	     * @param prevWords - keep track of the previous word printed
	     */
	    public void handleSpacedWords(String morsed,Trie trie, int index,ArrayList<String> prevWords) {
	    	String parts[] = morsed.split(" ");
	    	if(index == parts.length) {
	    		for(String word: prevWords) {
	    			System.out.print(word + " ");
	    		}
	    		System.out.println();
	    		return;
	    	}
	    	else {
	    		ArrayList<String> wordList = trie.getWords(parts[index]);
	    		for(int i = 0; i<wordList.size();i++) {
	    			prevWords.add(wordList.get(i));
	    			handleSpacedWords(morsed,trie,index+1,prevWords);
	    			prevWords.remove(prevWords.size()-1);
	    		}
	    	}
	    }
	    
	    /**
	     * Consumes a Morse-encoded string, traverses a Morse Trie, and updates a HashMap of possible sentences for the string.
	     * The length of the sentence is the value and the sentence is the key.
	     * @param morsed - morsed-encoded string without any spaces
	     * @param finalCode - keep track of parts of the morsed-encoded string that has been explored
	     * @param sentence - sentence that is being form
	     * @param trie - a trie of dictionary with dots and dashes as nodes 
	     * @param len - length of the original morsed-encoded string 
	     */
	    public void handleSentence(String morsed,String finalCode, String sentence, Trie trie, int len ) {
	    	if(len == finalCode.length()) {
	    		//System.out.println(sentence);
				String[] parts = sentence.split(" ");
				int sentenceLength = parts.length;
				sentenceMap.put(sentence,sentenceLength);
			}
			else {
				for(int i = 1; i<= morsed.length();i++ ) {
					String nextCode = morsed.substring(0,i);
					if(trie.hasWord(nextCode)) {
						ArrayList<String> words = trie.getWords(nextCode);
						for (String word: words) {
							sentence = sentence + " " + word;
							//System.out.println(sentence);
							handleSentence(morsed.substring(i,morsed.length()),finalCode.concat(nextCode),sentence,trie,len);
							sentence= sentence.substring(0, sentence.lastIndexOf(" "));
							
						}
					}
				}
			}
	    }
	    /**
	     * Read in a file of alphabet and their respected code into a HashMap
	     * @param filename - name of the txt file
	     * @return - HashMap with the letter as the key and the respected morse code as the value
	     */
	    public static HashMap<String,String> readMorse(String filename){
	    	HashMap<String,String> morse = new HashMap<>();       
	        try {            
	            BufferedReader reader = new BufferedReader(new FileReader(filename));            
	            String line;            
	            while ((line = reader.readLine()) != null) {    
	                String[] parts = line.split(" ");
	                morse.put(parts[0],parts[1]);           
	            }            
	            reader.close();        
	        } 
	        catch (Exception e) {            
	            System.err.format("Exception occurred trying to read '%s'.", filename);            
	            e.printStackTrace();        
	        }        
	        return morse;  
	    }
	    /**
	     * Read in a file of words in an English dictionary into an ArrayList
	     * @param filename 0 name of the txt file
	     * @return - An ArrayList of words in a dictionary 
	     */
	    public static ArrayList<String> readDict(String filename){
	    	ArrayList<String> dictonary = new ArrayList<>();      
	        try {            
	            BufferedReader reader = new BufferedReader(new FileReader(filename));            
	            String line;            
	            while ((line = reader.readLine()) != null) {    
	                dictonary.add(line);           
	            }            
	            reader.close();        
	        } 
	        catch (Exception e) {            
	            System.err.format("Exception occurred trying to read '%s'.", filename);            
	            e.printStackTrace();        
	        }        
	        return dictonary;  
	    }
	    /**
	     * Takes in a string and encode the word into morse code
	     * @param word - word in the dictionary
	     * @return - a String that represents the morse code of the given string
	     */
	    public String encode(String word) {
	    	StringBuilder code = new StringBuilder();
	    	for(int i = 0; i<word.length();i++) {
	    		char c = word.charAt(i);
	    		String letter = String.valueOf(c);
	    		code.append(morseMap.get(letter));
	    	}
	    	return code.toString();
	    }


	    public static void main(String[] args) {
	        // Get input from stdin
	        Scanner scanner = new Scanner(System.in);
	        String command = scanner.nextLine();
	        // Parse the style and morsed code value
	        String[] parts = command.split(":");
	        String style = parts[0].trim();
	        String morsed = parts[1].trim();
	        Solution s = new Solution("morse.txt","dictionary.txt");
	        
	        switch (style) {
	            case "Spaced Letters":
	                s.handleSpacedLetters(morsed);
	                break;
	            case "Word":
	            	int len = morsed.length();
	                ArrayList<String> words = s.handleWord(morsed,"","",len);
	                Collections.sort(words);
	                for(String word : words) {
	                	System.out.println(word);
	                }
	                break;
	            case "Spaced Words":
	            	Trie trie = new Trie();
	    	    	for (String word : s.dictionary) {
	    	    		String code = s.encode(word);
	    	    		trie.addWord(word,code);
	    	    	}
	    	    	ArrayList<String> prevArray = new ArrayList<>();
	                s.handleSpacedWords(morsed,trie,0,prevArray);
	                break;
	            case "Sentence":
	            	Trie trie2 = new Trie();
	            	for(String word: s.dictionary) {
	            		String code2 = s.encode(word);
	            		trie2.addWord(word,code2);
	            	}
	            	int length = morsed.length();
	                s.handleSentence(morsed,"","",trie2,length);
	                
	                //find the minimum length for sentences in the HashMap
	                int minLength = 100;
	                for(Map.Entry<String,Integer> entry : s.sentenceMap.entrySet()) {
	                	if(entry.getValue() < minLength) {
	                		minLength = entry.getValue();
	                	}
	                }
	                //add all sentences with the minimum length into an ArrayList
	                ArrayList<String> finalSolution = new ArrayList<>();
	                for(Map.Entry<String,Integer> entry : s.sentenceMap.entrySet()) {
	                	if(entry.getValue() == minLength) {
	                		finalSolution.add(entry.getKey());
	                	}
	                }
	                //Sort ArrayList in natural order and print it 
	                Collections.sort(finalSolution);
	                for(String sentence : finalSolution) {
	                	System.out.println(sentence.trim());
	                }
	                
	                
	                break;
	        }
	        
	    }

}
