package session4.salesman;

import java.util.Random;

public class SalesmanTimes {
	public static void main(String arg[]) {
		int nTimes = 1000; 
		long t1, t2; 
		Random r = null;
		for (int n=10; n<Integer.MAX_VALUE; n*=2) {
			Salesman salesman = new Salesman(n, 500);		  
			t1=System.currentTimeMillis();
			for (int i=0; i<nTimes; i++) 
				r = new Random();
				int sourceNodepos = r.nextInt(n);
				salesman.greedy1(sourceNodepos);
			t2=System.currentTimeMillis();
			System.out.println(String.format("GREEDY1: N=%d TIME=%d NTIMES=%d", n, t2-t1, nTimes));
		}
	}  
}
