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
		return calculateUnderstandable(0, (getCoinsLength() - 1) / 2, getCoinsLength() / 2, getCoinsLength() - 1);
	}

//	private int calculate2(int leftMin, int leftMax, int rightMin, int rightMax) {
//		if (rightMax % 2 == 0) { // Checks when the sample is odd, compares the two last elements and if the case
//									// is given, ignores one of them
//			if (balance(rightMax - 1, rightMax - 1, rightMax, rightMax) == 3) {
//				rightMax--;
//			}
//			return (balance(rightMax - 1, rightMax - 1, rightMax, rightMax) == 2) ? rightMax : rightMax - 1;
//		}
//		int res = balance(leftMin, leftMax, rightMin, rightMax);
//		if (res == 1) { // less heavy on left
//			if (leftMin == (leftMin + leftMax - 1) / 2) { // When the sample is small, we get the coin
//				switch (balance(leftMin, leftMin, leftMax, leftMax)) {
//				case 2:
//					return leftMax;
//				case 1:
//					return leftMin;
//				}
//			}
//			return ((((rightMax + 1) / 2)) != 0) // Case given when the sample divided by two is not a even number
//					? calculate2(leftMin, (leftMax + leftMin) / 2, (leftMax + leftMin) / 2, leftMax)
//					: calculate2(leftMin, (leftMax + leftMin - 1) / 2, (leftMax + leftMin) / 2, leftMax);
//
//		} else if (res == 2) { //less heavy on right
//			if (rightMin == (rightMin + rightMax - 1) / 2) {
//				switch (balance(rightMin, rightMin, rightMax, rightMax)) {
//				case 2:
//					return rightMax;
//				case 3:
//					return rightMin;
//				}
//			}
//			return ((((rightMax + 1) / 2)) != 0) // Case given when the sample divided by two is not a even number
//					? calculate2(rightMin, (rightMin + rightMax) / 2, (rightMin + rightMax) / 2, rightMax)
//					: calculate2(rightMin, (rightMin + rightMax - 1) / 2, (rightMin + rightMax) / 2, rightMax);
//		}
//		return rightMin;
//	}

	/**
	 * More legible code for myself
	 * 
	 * @param leftMin
	 * @param leftMax
	 * @param rightMin
	 * @param rightMax
	 * @return
	 */
	@SuppressWarnings("unused")
	private int calculateUnderstandable(int leftMin, int leftMax, int rightMin, int rightMax) {
		if (rightMax % 2 == 0) {
			if (balance(rightMax - 1, rightMax - 1, rightMax, rightMax) == 3) {
				rightMax--;
				leftMax--;
			} else if (balance(rightMax - 1, rightMax - 1, rightMax, rightMax) == 2) {
				return rightMax;
			} else {
				return rightMax - 1;
			}
			System.out.println("RIGHTMAX IS NOW: " + rightMax);
		}
		System.out.println("leftMin: " + leftMin);
		System.out.println("leftMax: " + leftMax);
		System.out.println("rightMin: " + rightMin);
		System.out.println("RightMax: " + rightMax);
		int res = balance(leftMin, leftMax, rightMin, rightMax);
		System.out.println("the balance is:" + res);
		if (res == 1) {
			System.out.println("left");
			if (leftMin == (leftMin + leftMax - 1) / 2) {
				if (balance(leftMin, leftMin, leftMax, leftMax) == 2) {
					return leftMax;
				} else if ((balance(leftMin, leftMin, leftMax, leftMax) == 1)) {
					return leftMin;
				}
			}
			if ((rightMax + 1) / 2 %2 != 0) {
				return calculateUnderstandable(leftMin, (leftMax + leftMin) / 2, (leftMax + leftMin) / 2, leftMax);
			} else {
				System.out.println(leftMin);
				return calculateUnderstandable(leftMin, (leftMax + leftMin - 1) / 2, (leftMax + leftMin) / 2, leftMax);
			}

		} else if (res == 2) {
			System.out.println("right");
			if (rightMin == (rightMin + rightMax - 1) / 2) {
				if (balance(rightMin, rightMin, rightMax, rightMax) == 2) {
					return rightMax;
				} else if ((balance(rightMin, rightMin, rightMax, rightMax) == 1)) {
					return rightMin;
				}
			}
			if ((rightMax + 1) / 2 %2!= 0) {
				return calculateUnderstandable(rightMin, (rightMin + rightMax) / 2, (rightMin + rightMax) / 2, rightMax);

			} else {
				return calculateUnderstandable(rightMin, (rightMin + rightMax - 1) / 2, (rightMin + rightMax) / 2, rightMax);
			}

		}
		return rightMin;
	}

	public static void main(String arg[]) {
//		int n = Integer.parseInt(arg[0]); // number of coins (size of the problem)
//		Gilito2 gilito = new Gilito2(n);
//		// let's simulate the n possible cases - false currency in each position
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++)
//				gilito.setCoinWeight(j, REAL_WEIGHT); // authentic coin weight
//			gilito.setCoinWeight(i, FAKE_WEIGHT); // fake coin weight
//			gilito.calculate();
//		}
//		System.out.println("COINS=" + n + " ***AVERAGE ENERGY=" + gilito.getUsedWatts() / n + " watts");
//		   Gilito2 gilito2 = new Gilito2(4);
//		   gilito2.setCoinWeight(0, REAL_WEIGHT); 
//		   gilito2.setCoinWeight(1, REAL_WEIGHT); 
//		   gilito2.setCoinWeight(2, FAKE_WEIGHT); 
//		   gilito2.setCoinWeight(3, REAL_WEIGHT); 
//		   System.out.println("RESULT: " + gilito2.calculate());

		Gilito2 gilito2 = new Gilito2(9);
		gilito2.setCoinWeight(0, REAL_WEIGHT);
		gilito2.setCoinWeight(1, REAL_WEIGHT);
		gilito2.setCoinWeight(2, FAKE_WEIGHT);
		gilito2.setCoinWeight(3, REAL_WEIGHT);
		gilito2.setCoinWeight(4, REAL_WEIGHT);
		gilito2.setCoinWeight(5, REAL_WEIGHT);
		gilito2.setCoinWeight(6, REAL_WEIGHT);
		gilito2.setCoinWeight(7, REAL_WEIGHT);
		gilito2.setCoinWeight(8, REAL_WEIGHT);
		System.out.println("RESULT: " + gilito2.calculate());

	}
}
