import java.io.*;

/*
 * 21124KB, 220ms
 * 
 * N의 제한이 10억이므로 O(N)으로도 시간초과가 나온다.
 * 이건 뭔가의 규칙을 찾아 O(1)로 풀라는 의도인 것 같아 규칙을 찾았다.
 * 1부터 200정도까지 수를 직접 뽑아서 오른쪽 끝에 있는 0의 개수를 세아보니 5의 거듭제곱들의 몫의 합으로 나옴을 알 수 있었다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		long T = nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			long N = nextInt();
			long res = 0;
			long d = 5;
			while (true) {
				if (N / d == 0) break;
				res += N / d;
				d *= 5;
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
