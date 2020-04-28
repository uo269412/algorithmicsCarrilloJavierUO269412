package session7.boggle;

public class BoggleRepetitionsTimes {

	public static void main(String arg[]) {
		long t1, t2;
		String dictionaryFileName = "src/main/java/session6/boggle/dictionary80368.txt";
		for (int n=1; n<Integer.MAX_VALUE; n+=10) {			
		   	BoggleAllBacktrackingRepetitions problem = new BoggleAllBacktrackingRepetitions(dictionaryFileName, n);	//In this case, instead of a board, a indicate the size and a random board should be created
		   	t1 = System.currentTimeMillis();
	    	problem.findSolutions();
	    	t2 = System.currentTimeMillis();
	    	//System.out.println(String.format("Size: %d - Elapsed time: %s ms - Points: %d", n, t2-t1, problem.getTotalPoints()));
	    	System.out.println(String.format("%d - %s", n, t2-t1));
		 }    
	} 
}  