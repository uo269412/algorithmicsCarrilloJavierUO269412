package session6.boggle;

public class Dictionary {
	private DictionaryNode root;
	
	public Dictionary() {
		root = new DictionaryNode();
	}

    public void insert(String word) {
    	DictionaryNode node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.words[index] == null) {
            	DictionaryNode newNode = new DictionaryNode();
            	node.words[index] = newNode;
            	node = newNode;
            } else {
            	node = node.words[index];
            }
        }
        node.endOfWord = true;
    }
 
    public boolean search(String word) {
    	DictionaryNode node = searchNode(word);
        if(node == null) {
            return false;
        }
        return node.endOfWord;
    }
 
    public boolean startsWith(String prefix) {
    	DictionaryNode node = searchNode(prefix);
    	return !(node == null);
    }
 
    public DictionaryNode searchNode(String string){
    	DictionaryNode node = root;
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            int index = ch  -'a';
            if (node.words[index] != null){
            	node = node.words[index];
            } else {
                return null;
            }
        } 
        if(node == root) {
            return null;
        }
        return node;
    }
}
