import java.io.*;
import java.util.*;

/*
 * 111688KB 332ms
 * 
 * 처음에 DP가 아니라 그리디인줄 알고 그리디로 풀었다가 바로 1틀함
 * 태그까고 보니까 DP길래 이걸 어떻게 DP로 풀지.. 곰곰히 생각해보니까 현재 문자를 제외한 나머지를 팰린드롬으로 만들어버리면
 * 그냥 반대쪽 끝에 자신을 붙이면 될 것이라고 생각했고 그러니까 식이 바로 나왔다.
 * 
 * dp[i][j]: i ~ j구간을 보았을 때 팰린드롬으로 만들 수 있는 최소 삽입 문자 횟수라고 하자.
 * 만일 i와 j가 동일하다면 현재 양 끝 기준에서는 팰린드롬의 조건을 만족하니 dp[i + 1][j - 1]이 곧 답이 된다.
 * 
 * 만일 다르다면 현재 양 끝 레벨에서 팰린드롬으로 만들어주기 위해 삽입이 필요하다.
 * i번째 문자를 고정하고 나머지 문자를 팰린드롬으로 만들어버린다면 전체 문자가 팰린드롬이 되기 위해서는 반대쪽 끝에 i번째 문자를 삽입하면 된다.
 * j번째 문자를 고정한다면 마찬가지로 나머지 문자를 팰린드롬으로 만들어버린다음 첫 번째에 j번째 문자를 삽입하면 된다.
 * 즉 dp[i + 1][j] + 1과 dp[i][j - 1] + 1의 최솟값이 답이 된다.
 */

public class Main {
	static int N;
	static String str;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.print(getAns(0, N - 1));
	}
	
	static int getAns(int s, int e) {
		if (s > e) return 0;
		if (s == e) return dp[s][e] = 0;
		if (dp[s][e] != -1) return dp[s][e];
		
		if (str.charAt(s) == str.charAt(e)) {
			return dp[s][e] = getAns(s + 1, e - 1);
		} else {
			return dp[s][e] = Math.min(getAns(s + 1, e), getAns(s, e - 1)) + 1;
		}
	}
}