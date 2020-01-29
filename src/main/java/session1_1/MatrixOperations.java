package session1_1;

import java.util.Random;

public class MatrixOperations {
	int[][] matrix;

	/**
	 * Creates a new matrix of size n x n and fills it with random values.
	 * 
	 * @param n size of the matrix
	 */
	public MatrixOperations(int n) {
		matrix = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = new Random().nextInt(100);
			}
		}
	}

	public MatrixOperations(String fileName) {

	}

	public int getSize() {
		return matrix.length;
	}

	public void write() {

	}

	public int sumDiagonal1() {
		int result = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i == j) {
					result += matrix[i][j];
				}
			}
		}
		return result;
	}

	public int sumDiagonal2() {
		int result = 0;
		for (int i = 0; i < matrix.length; i++) {
			result += matrix[i][i];
		}
		return result;
	}

	public void travelPath(int i, int j) {
		while (i >= 0 && i < matrix.length && j >= 0 && j > matrix.length && matrix[i][j] != -1) {
			switch (matrix[i][j]) {
			case 1:
				travelPath(i, j - 1);
				break;
			case 2:
				travelPath(i + 1, j);
				break;
			case 3:
				travelPath(i, j + 1);
				break;
			case 4:
				travelPath(i - 1, j);
				break;
			}
			matrix[i][j] = -1;
		}
	}
}
