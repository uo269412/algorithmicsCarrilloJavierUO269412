package session1_2;

import java.util.Random;

public class Loop4 {

	public static void loop2(int n) {
		Random rn = new Random();
		@SuppressWarnings("unused")
		int cont=0;
		for (int i=1; i<=n; i++) //n^4
			for (int j=1; j<=n; j++) 
				for (int k=1; k<=n; k++)
					for(int l=1; l <= n; l++)
						cont += rn.nextInt();
	}
	
	public static void main(String arg[]) {
		long t1, t2;
		int nTimes = Integer.parseInt(arg[0]);
	
		for (int n=1; n<=Integer.MAX_VALUE; n*=2) {
			t1 = System.currentTimeMillis();
	 
			for (int repetitions=1; repetitions<=nTimes; repetitions++){
				loop2(n);
			} 
	 
			t2 = System.currentTimeMillis();
			System.out.println ("n="+n+ "**TIME=" +(t2-t1)+ " ** nTimes=" + nTimes);
		} //for	
	} //main
} //class