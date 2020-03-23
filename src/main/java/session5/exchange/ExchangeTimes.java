package session5.exchange;

public class ExchangeTimes {

	public static void main(String[] args) {
		long t1, t2;
		String file = "src/main/java/session5/exchange/timesExample.txt";
		Exchange c1 = new Exchange(file);
		for (int n = 10000; n < Integer.MAX_VALUE; n *= 2) {
			t1 = System.currentTimeMillis();
			c1.getNumCoins(n);
			t2 = System.currentTimeMillis();
			System.out.println(String.format("EXCHANGE, n = " + n + " time = " + (t2 - t1)));

		}
	}

}
