package session5.exchange;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exchange {
	int[] coinsWeHave;
	int[][] exchangeTable;
	boolean[][] booleanTable;
	int[] exchange;

	public static void main(String arg[]) {
		String file = "src/main/java/session5/exchange/example.txt";
		Exchange c1 = new Exchange(file);
		c1.getNumCoins(14);
	}

	public Exchange(String fileName) {
		BufferedReader file = null;
		String line;
		try {
			file = new BufferedReader(new FileReader(fileName));
			line = file.readLine();
			int numberOfAvailableCoins = Integer.parseInt(line);
			line = file.readLine();
			String values[] = line.split("\t");
			coinsWeHave = new int[numberOfAvailableCoins + 1];
			for (int j = 1; j < numberOfAvailableCoins + 1; j++) {
				coinsWeHave[j] = Integer.parseInt(values[j - 1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (IOException e) {
			System.out.println("File reading error: " + fileName);
		}
	}

	public int getNumCoins(int amount) {
		int numberOfAvailableCoins = coinsWeHave.length - 1;
		exchangeTable = new int[numberOfAvailableCoins + 1][amount + 1];
		booleanTable = new boolean[numberOfAvailableCoins + 1][amount + 1];

		for (int k = 1; k < amount + 1; k++) {
			exchangeTable[1][k] = k;
			if (k <= numberOfAvailableCoins) {
				exchangeTable[k][1] = 1;
			}
			booleanTable[1][k] = true;
		}

		for (int i = 2; i < numberOfAvailableCoins + 1; i++) {
			for (int j = 2; j < amount + 1; j++) {
				if (j < coinsWeHave[i]) {
					exchangeTable[i][j] = exchangeTable[i - 1][j];
				} else if (j == coinsWeHave[i]) {
					exchangeTable[i][j] = 1;
					booleanTable[i][j] = true;
				} else if (j > coinsWeHave[i]) {
					exchangeTable[i][j] = Math.min(exchangeTable[i - 1][j], 1 + exchangeTable[i][j - coinsWeHave[i]]);
					if (exchangeTable[i][j] != exchangeTable[i - 1][j]) {
						booleanTable[i][j] = true;
					}
				}
			}
		}

		exchange = new int[numberOfAvailableCoins];
		int i = numberOfAvailableCoins;
		int j = amount;
		while (j >= 1) {
			if (booleanTable[i][j] == false) {
				i--;
				if (i == 0) {
					j--;
				}
			} else {
				exchange[i - 1] = exchange[i - 1] + 1;
				j = j - coinsWeHave[i];
			}
		}
		return exchangeTable[coinsWeHave.length - 1][amount];
	}

	public int[] getBestCoins() {
		return exchange;
	}

}