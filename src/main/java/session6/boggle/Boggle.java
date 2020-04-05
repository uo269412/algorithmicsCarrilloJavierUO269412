package session6.boggle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Boggle {

	List<String> solutions;
	String[][] table;
	boolean[][] mark;
	Hashtable<String, Boolean> dictionary;
	int[] pointsPerLength = { 0, 0, 1, 2, 5, 7, 9, 12, 15, 19, 24 };
	int totalPoints = 0;

	public Boggle(String dictionaryFileName, String tableFileName) {
		solutions = new ArrayList<String>();
		loadDictionary(dictionaryFileName);
		loadTable(tableFileName);
	}

	public Boggle(String dictionaryFileName, int tableSize) {
		loadDictionary(dictionaryFileName);
		table = new String[tableSize][tableSize];
		mark = new boolean [tableSize][tableSize];
		Random r = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				table[i][j] = String.valueOf(alphabet.charAt(r.nextInt() * alphabet.length()));
			}
		}
	}

	public List<String> getSolutions() {
		return solutions;
	}

	public long getTotalPoints() {
		return totalPoints;
	}

	public void printTable() {
		System.out.println("TABLE");
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void printSolutions() {
		System.out.println("SOLUTIONS");
		for (String element : solutions) {
			System.out.println(element);
		}
	}

	public void findSolutions() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				backtrackingSolutions("", i, j);
			}
		}
		Collections.sort(solutions);
	}

	private void backtrackingSolutions(String solution, int i, int j) {
		mark[i][j] = true;
		solution += table[i][j].toLowerCase();
		if (dictionary.containsKey(solution) && !(dictionary.get(solution) && solutions.contains(solution))) {
			solutions.add(solution);
			totalPoints+= pointsPerLength[solution.length()];
			dictionary.replace(solution, true);
		}
		for (int horizontalMovement = i - 1; horizontalMovement <= i + 1; horizontalMovement++) {
			for (int verticalMovement = j - 1; verticalMovement <= j + 1; verticalMovement++) {
				if (valuesAreInTable(horizontalMovement, verticalMovement)
						&& !mark[horizontalMovement][verticalMovement] && solution.length() <= pointsPerLength.length) {

					backtrackingSolutions(solution, horizontalMovement, verticalMovement);
				}
			}
		}
		solution = solution.substring(0, solution.length() - table[i][j].length());
		mark[i][j] = false;
	}

	private boolean valuesAreInTable(int horizontalMovement, int verticalMovement) {
		return (horizontalMovement >= 0 && horizontalMovement < table.length && verticalMovement >= 0
				&& verticalMovement < table.length);
	}

	private void loadTable(String tableFileName) {
		BufferedReader file = null;
		String line;
		try {
			file = new BufferedReader(new FileReader(tableFileName));
			line = file.readLine();
			int sizeOfTable = Integer.parseInt(line);
			table = new String[sizeOfTable][sizeOfTable];
			mark = new boolean[sizeOfTable][sizeOfTable];
			int i = 0;
			while ((line = file.readLine()) != null) {
				String values[] = line.split(" ");
				for (int j = 0; j < values.length; j++) {
					table[i][j] = values[j];
				}
				i++;
			}
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + tableFileName);
		} catch (IOException e) {
			System.out.println("File reading error: " + tableFileName);
		}
	}

	private void loadDictionary(String dictionaryFileName) {
		dictionary = new Hashtable<String, Boolean>();
		BufferedReader file = null;
		String line;
		try {
			file = new BufferedReader(new FileReader(dictionaryFileName));
			while ((line = file.readLine()) != null) {
				dictionary.put(line, false);
			}
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + dictionaryFileName);
		} catch (IOException e) {
			System.out.println("File reading error: " + dictionaryFileName);
		}
	}
}
