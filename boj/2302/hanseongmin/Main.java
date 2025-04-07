import java.io.*;
import java.util.*;

/*
 * 11484KBm 64ms
 * 
 * dp[i]: 극장 좌석이 i개 있을 때 배치하는 경우의 수라고 하자.
 * 먼저 dp[i - 1]의 경우의 수에서 현재 i번째 좌석을 그냥 배치하는 방법이 있다.
 * i번째 좌석의 위치를 바꾸고 난 후도 배치해야하므로, i - 1번쨰 좌석과 i번째 좌석을 한 덩어리로 본다면 
 * 그 덩어리를 dp[i - 2]에 배치하면 된다.
 * 
 * 따라서 dp[i] = dp[i - 1] + dp[i - 2]인 피보나치 수열의 꼴로 나타나게 되며 이를 통해 dp 테이블을 채우고
 * vip 좌석이 아닌 연속된 좌석들의 길이의 곱을 출력하면 된다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		int[] dp = new int[41];
		dp[0] = dp[1] = 1; dp[2] = 2;
		for (int i = 3; i <= 40; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		int res = 1;
		int M = nextInt();
		int prevVip = 0;
		for (int i = 0; i < M; i++) {
			int vip = nextInt();
			res *= dp[vip - prevVip - 1];
			prevVip = vip;
		}
		res *= dp[N - prevVip];
		System.out.print(res);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}