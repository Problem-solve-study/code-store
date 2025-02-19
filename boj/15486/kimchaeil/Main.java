//문제: 15486번 퇴사

/*
 * 수익이 누적되면서 최대를 구하는 문제라서 DP로 접근했다.
 */

import java.io.*;

public class Main {
	static StreamTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);
		
		int n = nextInt();
		int[] dp = new int[n+1];
		
		for(int i=0;i<n;i++) {
			dp[i+1]=Math.max(dp[i], dp[i+1]); //i일에 상담 안하기
			int nextDay = i+nextInt(); //i일에 상담할 경우 다음으로 상담할 수 있는 날
			int pay = nextInt(); //i일에 상담했을때 받는 금액
			if(nextDay<=n) {
				dp[nextDay] = Math.max(dp[nextDay], dp[i]+pay); //i일에 상담했을 때 수익과 이미 계산된 nextday까지의 최대 수익 비교
			}
		}
		
		System.out.println(dp[n]);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}