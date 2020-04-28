package session7.boggle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import session6.boggle.Boggle;

public class BoggleAllBacktrackingRepetitions extends Boggle{
	
	int wordRepetitions = 1;
	int cellRepetitions = 1;
	int[][] markNumbers;
	TreeMap<String, Integer> newSolutions;

	public BoggleAllBacktrackingRepetitions(String dictionaryFileName, String tableFileName) {
		super(dictionaryFileName, tableFileName);
	}
	
	public BoggleAllBacktrackingRepetitions(String dictionaryFileName, int tableSize) {
		super(dictionaryFileName, tableSize);
	}

	public BoggleAllBacktrackingRepetitions(String dictionaryFileName, int numberOfTimesAWordCanBeConcanetaded,
			int numberOfTimesACellCanBeRepeated, String tableFileName) {
		super(dictionaryFileName, tableFileName);
		this.wordRepetitions = numberOfTimesAWordCanBeConcanetaded;
		this.cellRepetitions = numberOfTimesAWordCanBeConcanetaded;
		initializeMarks(numberOfTimesACellCanBeRepeated);
		newSolutions = new TreeMap<String, Integer>();
	}

	public void initializeMarks(int repetitionValue) {
		markNumbers = new int[getTable().length][getTable().length];
		for (int i = 0; i < markNumbers.length; i++) {
			for (int j = 0; j < markNumbers.length; j++) {
				markNumbers[i][j] = 0;
			}
		}
	}
	
	@Override
	public void findSolutions() {	
		if (wordRepetitions == 1 && cellRepetitions == 1) {
			for (int i = 0; i < table.length; i++) {
				for (int j = 0; j < table.length; j++) {
					backtrackingSolutionsOld("", i, j);
				}
			}
			Collections.sort(solutions);
		} else {
			for (int i = 0; i < table.length; i++) {
				for (int j = 0; j < table.length; j++) {
					backtrackingSolutionsNewScenarios("", i, j);
				}
			}
			computeNewPoints();
			solutions = new ArrayList<String>(newSolutions.keySet());
		}
		
	}
	
	private void computeNewPoints() {
		for (Map.Entry<String, Integer> entry : newSolutions.entrySet()) {
			totalPoints += entry.getKey().length()^entry.getValue() + entry.getKey().length()*entry.getValue();
		}
		
	}

	protected void backtrackingSolutionsOld(String solution, int i, int j) {
		mark[i][j] = true;
		solution += table[i][j].toLowerCase();
		if (dictionary.search(solution) && !solutions.contains(solution)) {
			solutions.add(solution);
			totalPoints += solution.length();
		}
		if (dictionary.startsWith(solution)) {
			for (int horizontalMovement = i - 1; horizontalMovement <= i + 1; horizontalMovement++) {
				for (int verticalMovement = j - 1; verticalMovement <= j + 1; verticalMovement++) {
					if (valuesAreInTable(horizontalMovement, verticalMovement)
							&& !mark[horizontalMovement][verticalMovement]
							&& solution.length() <= pointsPerLength.length) {
						backtrackingSolutions(solution, horizontalMovement, verticalMovement);
					}
				}
			}
		}
		solution = solution.substring(0, solution.length() - table[i][j].length());
		mark[i][j] = false;
	}
	
	protected void backtrackingSolutionsNewScenarios(String solution, int i, int j) {
		markNumbers[i][j]++;
		solution += table[i][j].toLowerCase();
		if (dictionary.search(solution)) {
			if (!newSolutions.containsKey(solution)) {
				newSolutions.put(solution, 1);
			} else if (newSolutions.get(solution) < wordRepetitions) {
				newSolutions.replace(solution, newSolutions.get(solution) + 1);
			}
		}
		if (dictionary.startsWith(solution)) {
			for (int horizontalMovement = i - 1; horizontalMovement <= i + 1; horizontalMovement++) {
				for (int verticalMovement = j - 1; verticalMovement <= j + 1; verticalMovement++) {
					if (valuesAreInTable(horizontalMovement, verticalMovement)
							&& markNumbers[horizontalMovement][verticalMovement] < cellRepetitions
							&& solution.length() <= pointsPerLength.length) {
						backtrackingSolutions(solution, horizontalMovement, verticalMovement);
					}
				}
			}
		}
		solution = solution.substring(0, solution.length() - table[i][j].length());
		markNumbers[i][j]--;
	}
}
