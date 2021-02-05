# Backtracking-and-Tries

Objective: use backtracking and tries to decode messages in morse code.

Part 1: Morse Code

  - Consume a string of space-separated morse-encoded letters, translate the letters to regular English, and then print out the resulting word. 
  - If the letters do not form a word based on the contents of dictionary.txt, then print out nothing instead.
  
Part 2: Backtracking 

  - Implement a backtracking algorithm to recursively enumerate all the possible words that a string could represent. 
  - The program will consume a sequence of dots and dashes WITHOUT spaces, and then print out the candidates alphabetically.
  
Part 3: Try a Trie

  - Implement a Trie to enumerate all the possible words that a string could represent.
  - The program will consume a sequences of dots and dashes with spaces, and then print out the candidates alphabetically.
  
Part 4: Trie a Sentence

  - Consumes a Morse-encoded string, traverses your Morse Trie, and returns a list of possible sentences for the string.
  - In this version of the algorithm, when you encounter a match of one or more words before you have finished reading through the entire morse-encoded string, consider the additional possible paths where these words are part of a sentence.
  - Sort the candidates first by the number of words in each sentence, and then alphabetically. Only print out the candidates in the set with the fewest possible words. 

