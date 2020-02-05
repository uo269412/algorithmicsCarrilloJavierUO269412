package session1_1;

public class Vector4 {

	public static void main(String args[]) {
//		measureFillIn(args);
//		measureSum(args);
//		measureMaximum(args);
//		long seconds = (Integer.MAX_VALUE - System.currentTimeMillis()) *10^-3;
//		long minutes = seconds /60;
//		long hours = minutes/60;
//		long days = hours/24;
//		long months = days/30;
//		long years = months/12;
//		System.out.println(years);
//		int years2 = (int) months/12;
//		System.out.println(years2);
//		System.out.println(Integer.MAX_VALUE - years2);
	}



	private static void measureFillIn(String[] args) {
		int nTimes = Integer.parseInt(args[0]);
		for (int n = 10; n < Integer.MAX_VALUE; n *= 3) {
			long t1, t2;
			int[] v = new int[n];
			t1 = System.currentTimeMillis();
			for (int repetition = 1; repetition <= nTimes; repetition++) {
				Vector1.fillIn(v);
			}
			t2 = System.currentTimeMillis();
			System.out.println(String.format("[FILL IN] SIZE = %d ** TIME = %d ms", n, t2 - t1));
		}
		
	}

	private static void measureSum(String[] args) {
		int s = 0;
		int nTimes = Integer.parseInt(args[0]);
		for (int n = 10; n < Integer.MAX_VALUE; n *= 3) {
			int[] v = new int[n];
			Vector1.fillIn(v);

			long t1, t2;
			t1 = System.currentTimeMillis();
			for (int repetition = 1; repetition <= nTimes; repetition++) {
				s = Vector1.sum(v);
			}
			
			t2 = System.currentTimeMillis();
			
			System.out.println(String.format("[SUM] SIZE = %d ** SUM = %d TIME = %d ms", n, s, t2 - t1));
		}
	}
	
	private static void measureMaximum(String[] args) {
		int nTimes = Integer.parseInt(args[0]);
		for (int n = 10; n < Integer.MAX_VALUE; n *= 3) {
			int[] v = new int[n];
			Vector1.fillIn(v);
			long t1, t2;
			int[] m = new int[2];
			t1 = System.currentTimeMillis();
			for (int repetition = 1; repetition <= nTimes; repetition++) {
				Vector1.maximum(v, m);
			}	
			t2 = System.currentTimeMillis();
			System.out.println(String.format("[MAXIMUM] SIZE = %d ** MAXPOSITION = %d MAXNUMBER = %d TIME = %d ms", n, m[0], m[1], t2 - t1));
		}
		
	}
}
