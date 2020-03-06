package session3.gilito;

import java.util.Random;

public class GilitoComparison {
	public static void main(String arg[]) {
		int nTimes = 1000;
		long t1Gilito1, t1Gilito2, t2Gilito1, t2Gilito2;
		int position1 = 0;
		int position2 = 0;
		for (int n = 10; n < Integer.MAX_VALUE; n *= 2) {
			Gilito1 gilito1 = new Gilito1(n);
			Gilito2 gilito2 = new Gilito2(n);
			for (int i = 0; i < n; i++) {
				gilito1.setCoinWeight(i, Gilito1.REAL_WEIGHT); // authentic coin weight
				gilito2.setCoinWeight(i, Gilito1.REAL_WEIGHT); // authentic coin weight
			}
			Random r = new Random();
			int posForFake = r.nextInt(n);
			gilito1.setCoinWeight(posForFake, Gilito1.FAKE_WEIGHT); // worst case (last coin is the fake one)
			gilito2.setCoinWeight(posForFake, Gilito1.FAKE_WEIGHT); // worst case (last coin is the fake one)

			t1Gilito1 = System.currentTimeMillis();
			for (int i = 0; i < nTimes; i++) {
				position1 = gilito1.calculate();
			}
			t2Gilito1 = System.currentTimeMillis();

			t1Gilito2 = System.currentTimeMillis();
			for (int i = 0; i < nTimes; i++) {
				position2 = gilito2.calculate();
			}
			t2Gilito2 = System.currentTimeMillis();

			System.out.println(String.format("GILITO1: NCOINS=%d FAKE_POSITION=%d ENERGY_USED=%d TIME=%d NTIMES=%d", n,
					position1, gilito1.getUsedWatts() / nTimes, t2Gilito1 - t1Gilito1, nTimes));

			System.out.println(String.format("GILITO2: NCOINS=%d FAKE_POSITION=%d ENERGY_USED=%d TIME=%d NTIMES=%d", n,
					position2, gilito2.getUsedWatts() / nTimes, t2Gilito2 - t1Gilito2, nTimes));

			System.out.println("TIME COMPARISON: " + ((t2Gilito1 - t1Gilito1) - (t2Gilito2 - t1Gilito2)));
			System.out.println("ENERGY COMPARISON: " + ((gilito1.getUsedWatts()) - (gilito2.getUsedWatts())));
			System.out.println("----------------------------------------------------------------------------------");
		}
	}
}
