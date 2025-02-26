// 11592 KB, 112 ms
/*
 * 0부터 N까지 정수 K개를 더해서 합이 N이 되는 경우의 수 구하기
 * 덧셈의 순서가 바뀌면 다른 경우.
 * 
 * 1 <= N <= 5,000, 1 <= K <= 5,000
 * 
 * 답을 1,000,000,000 나눈 나머지 출력
 * 
 * 숫자가 K개 있고, N 을 K 숫자에 분배하는 경우의 수
 * 중복 조합 H(K, N) = C(K+N-1, N)
 * 
 * 파스칼의 삼각형에 의해 nCr = n-1Cr-1+n-1Cr 이다.
 * r과 n-r중 작은 값을 선택해 계산
 * 1차원 dp 사용해 최적화
 */

import java.io.IOException;

public class BOJ13707_합분해2 {

	public static void main(String[] args) throws IOException {
		int N = readInt();
		int K = readInt();

		System.out.println(nCr(K + N - 1, N));
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}

	public static int nCr(int n, int r) {
	    r = Math.min(r, n - r);
	    
	    if (r == 0) 
	        return 1;
	    
	    int[] dp = new int[r + 1];
	    dp[0] = 1;
	    
	    for (int i = 1; i <= n; i++) 
	        for (int j = Math.min(i, r); j > 0; j--) 
	            dp[j] = (dp[j] + dp[j - 1]) % 1000000000;
	    
	    return dp[r];
	}


}
