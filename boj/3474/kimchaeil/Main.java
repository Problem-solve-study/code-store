//문제: BOJ 3474번 교수가 된 현우
//메모리: 20792 KB
//시간: 192 ms

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = nextInt();
		while(T-->0) {
			int n = nextInt();
			int five = 0;
			while(n!=0) {
				n/=5;
				five+=n;
			}
			sb.append(five).append('\n');
		}
		System.out.println(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
