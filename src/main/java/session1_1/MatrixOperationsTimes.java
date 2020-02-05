package session1_1;

import session0.MatrixOperations;

public class MatrixOperationsTimes {
	public static void main(String args[]) {
		System.out.println("DIAGONAL 1");
		calculateSumDiagonal1(args[0]);
		System.out.println("DIAGONAL 2");
		calculateSumDiagonal2(args[0]);
	}

	public static void calculateSumDiagonal1(String times) {
		int nTimes = Integer.parseInt(times);
		for (int n = 10; n < 10000; n *= 3) {
			MatrixOperations matrix = new MatrixOperations(n);
			long t1, t2;
			t1 = System.currentTimeMillis();
			for (int repetition = 1; repetition <= nTimes; repetition++) {
				matrix.sumDiagonal1();
			}
			t2 = System.currentTimeMillis();
			System.out.println(String.format("SIZE = %d ** TIME = %d ms", n, t2 - t1));
		}
	}

	public static void calculateSumDiagonal2(String times) {
		int nTimes = Integer.parseInt(times);
		for (int n = 10; n < 10000; n *= 3) {
			MatrixOperations matrix = new MatrixOperations(n);
			long t1, t2;
			t1 = System.currentTimeMillis();
			for (int repetition = 1; repetition <= nTimes; repetition++) {
				matrix.sumDiagonal2();
			}
			t2 = System.currentTimeMillis();
			System.out.println(String.format("SIZE = %d ** TIME = %d ms", n, t2 - t1));
		}
	}
}
