package session3.gilito;

/**
 * This class simulates the algorithm currently used by TIO GILITO and
 * challenges us to improve it for free (the usurer does not plan to pay us
 * anything)
 */
public class Gilito2 extends Gilito1 {

	public Gilito2(int n) {
		super(n);
	}

	@Override
	public int calculate() {
		if (getCoinsLength() % 2 == 0) {
			return calculate2(0, (getCoinsLength()-1)/ 2, getCoinsLength() - 1);
		} else {
			return 0;
		}
	}

	private int calculate2(int leftMin, int centre, int rightMax) {
		int res = balance(leftMin, centre, centre, rightMax);
		if (res == 1) {
			return calculate2(leftMin, (centre + leftMin)/2, centre);
		} else if (res == 2) {
			return calculate2(centre, (centre + rightMax)/2, rightMax);
		}
		return centre;
	}
	
	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]); //number of coins (size of the problem)
		Gilito2 gilito = new Gilito2(n);
		//let's simulate the n possible cases - false currency in each position
		for (int i=0; i<n; i++) { 
		    for (int j=0; j<n; j++) 
		    	gilito.setCoinWeight(j, REAL_WEIGHT); //authentic coin weight	    
		    gilito.setCoinWeight(i, FAKE_WEIGHT); //fake coin weight
		   gilito.calculate();
		}
		System.out.println("COINS="+n+" ***AVERAGE ENERGY="+ gilito.getUsedWatts()/n +" watts");
	} 

}