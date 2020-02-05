package session1_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatrixOperations {
	int[][] matrix;
	Logger log = LoggerFactory.getLogger(MatrixOperations.class);
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

	/**
	 * Creates a matrix using data of the file provided as parameter
	 * 
	 * @param fileName of the file we want to read the matrix from
	 */
	public MatrixOperations(String fileName) {
		String line;
		String[] matrixData = null;
		String[] lineData = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("files/" + fileName + ".dat"));
			int lines = 0;
			while (reader.readLine() != null) {
				lines++;
			}
			reader.close();
			matrix = new int[lines][lines];
			BufferedReader file = new BufferedReader(new FileReader("files/" + fileName + ".dat"));
			int i = 0;
			while (file.ready()) {
				line = file.readLine();
				matrixData = line.split("\t");
				for (int k = 0; k < matrixData.length; k++) {
					lineData = matrixData[k].split(" ");
				}
				for (int j = 0; j < lineData.length; j++) {
					matrix[i][j] = Integer.parseInt(lineData[j]);
				}
				i++;
			}
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	/**
	 * Returns the matrix size
	 * 
	 * @return integer indicating the size of the matrix
	 */
	public int getSize() {
		return matrix.length;
	}

	/**
	 * Prints through the console all the matrix element
	 */
	public void write() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * Computes the summation of all the elements of the matrix diagonal. This
	 * implementation must iterate over all the matrix elements, but only sums
	 * appropriate elements
	 * 
	 * @return integer sum of the diagonal
	 */
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

	/**
	 * Computes the summation of all the elements of the matrix diagonal considering
	 * the elements of the main diagonal
	 * 
	 * @return integer sum of the diagonal
	 */
	public int sumDiagonal2() {
		int result = 0;
		for (int i = 0; i < matrix.length; i++) {
			result += matrix[i][i];
		}
		return result;
	}

	/**
	 * Given a matrix with integer numbers between 1 and 4, this method iterates
	 * through the matrix starting at position (i, j) according to the following
	 * number meanings: 1 – move up; 2 – move right; 3 – move down; 4 – move left.
	 * Traversed elements would be set to -1 value. The process will finish if it
	 * goes beyond the limits of the matrix or an already traversed position is
	 * reached
	 * 
	 * @param i starting number of the row for the method
	 * @param j starting number of the column for the method
	 */
	public void travelPath(int i, int j) {
		while (i >= 0 && i < matrix.length && j >= 0 && j < matrix.length && matrix[i][j] != -1) {
			switch (matrix[i][j]) {
			case 1:
				matrix[i][j] = -1;
				travelPath(i -1, j);
				break;
			case 2:
				matrix[i][j] = -1;
				travelPath(i, j + 1);
				break;
			case 3:
				matrix[i][j] = -1;
				travelPath(i + 1, j);
				break;
			case 4:
				matrix[i][j] = -1;
				travelPath(i, j - 1);		
				break;
			}
		}
	}
}
