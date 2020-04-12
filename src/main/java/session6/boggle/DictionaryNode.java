package session6.boggle;

public class DictionaryNode {

	DictionaryNode[] words = new DictionaryNode[26];
	boolean endOfWord;
	
	public DictionaryNode() {
		endOfWord = false;
		for (int i = 0; i < 26; i++) {
			words[i] = null;
		}
		
	}
}
