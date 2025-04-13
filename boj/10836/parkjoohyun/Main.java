//Memory 	44756KB
//Time 	440ms

import java.io.IOException;

public class Main{
	
	static int n, t;
	static int[] fixSum;
	public static void main(String[] args) throws IOException {
		input();
		updateGrow();
		print();
	}
	
	static void print() {
		StringBuilder sb= new StringBuilder();
		StringBuilder pattern = new StringBuilder();
		
		
		int limit = 2*n-1;
		for(int j=n;j<limit;j++) {
			pattern.append(fixSum[j]).append(' ');
		}
		pattern.append('\n');
		for(int i=n-1;i>-1;i--) {
			sb.append(fixSum[i]).append(' ');
			sb.append(pattern);
		}
		System.out.println(sb);
	}
	
	static void updateGrow() {
		int limit = n*2-1;
		for(int i=1;i<limit;i++) {
			fixSum[i] += fixSum[i-1];
		}
	}
	
	static void sumGrow() throws IOException {
		int limit = nextInt(); //zero
		fixSum[limit]++;
		limit += nextInt();
		fixSum[limit]++;
		nextInt();
	}
	
	static void input() throws IOException {
		n = nextInt();
		t = nextInt();
		fixSum = new int[n*2];
		int l = 2*n-1;
		fixSum[0] =1;
		for(int i=0;i<t;i++) {
			sumGrow();
		}
	}
	static int nextInt() throws IOException {
		int c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}