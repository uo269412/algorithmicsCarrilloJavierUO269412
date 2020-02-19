package session2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {

	public Insertion(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {		
		for (int i = 1; i < elements.length; i++) {
			int pivot = elements[i];
			int splitOrdered = i-1;
			
			while (splitOrdered >= 0 && pivot < elements[splitOrdered]) {
				elements[splitOrdered+1] = elements[splitOrdered];
				splitOrdered--;
			}
			elements[splitOrdered+1] = pivot;
		}
	}

	@Override
	public String getName() {
		return "Insertion";
	}
}
