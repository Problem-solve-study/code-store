/*
 * 보도블록 N개
 * 
 * 스타트 집 1번, 링크 집 N번.
 * 
 * 점프해서 간다.
 * 
 * 한 번 k칸 점프 하는데 필요한 에너지는 k*k
 * 
 * BOJ를 외치면서 만나러 간다.
 * 
 * N <= 1,000
 * 
 * 에너지 양의 최솟값.
 * 
 * dp[i][0] 은 i번째에 현재 문자 B로 오는 최솟값, dp[i이전][2]에서 옴
 * dp[i][1] 은 O로 오는 최솟값, 값이 있는 dp[i이전][0]에서 옴
 * dp[i][2] 는 J 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12026_BOJ거리 {
	static final int MAX = 9_999_999;
	static int N, min = MAX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String inStr = br.readLine();
		int[][] dp = new int[N][3];
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], MAX);
		if (inStr.charAt(0) == 'B')
			dp[0][0] = 0;
		else if (inStr.charAt(0) == 'O')
			dp[0][1] = 0;
		else if (inStr.charAt(0) == 'J')
			dp[0][2] = 0;
		for (int i = 1; i < N; i++) {
			int t = inStr.charAt(i);
			for (int j = 0; j < N - 1; j++) {
				if (t == 'B')
					dp[i][0] = Math.min(dp[i][0], dp[j][2] + (i - j) * (i - j));
				else if (t == 'O')
					dp[i][1] = Math.min(dp[i][1], dp[j][0] + (i - j) * (i - j));
				else if (t == 'J')
					dp[i][2] = Math.min(dp[i][2], dp[j][1] + (i - j) * (i - j));
			}
		}
		if (inStr.charAt(N - 1) == 'B')
			min = Math.min(min, dp[N - 1][0]);
		else if (inStr.charAt(N - 1) == 'O')
			min = Math.min(min, dp[N - 1][1]);
		else if (inStr.charAt(N - 1) == 'J')
			min = Math.min(min, dp[N - 1][2]);
		System.out.println(min != MAX ? min : -1);
	}

}
