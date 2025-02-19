import java.io.*;
import java.util.*;

/*
98020KB, 520ms

day + t >= n + 1이라면 무조건 선택 불가
엄청 냅색스럽게 생겼는데 냅색으로 접근하자니 메모리가 너무 커진다. 일반적인 DP로 접근해야하는 듯
i일을 선택하면 i + t일부터 다음껄 선택할 수 있다.

dp[i] = i일 시점에서 얻을 수 있는 수익의 최댓값으로 정의하고
1일차부터 차례대로 보면서 DP 테이블을 갱신해준다.
테이블이 차례대로 갱신되는게 아니므로 i + t시점부터 50번째 이전의 테이블 값을 보며 제일 큰 값을 찾고 해당 값을 기준으로 갱신한다.
150만 * 50으로 시간내에 풀 수 있다.
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[][] arr = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
        	arr[i] = new int[] {nextInt(), nextInt()};
        }
        
        int res = 0;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
        	int t = arr[i][0];
        	int p = arr[i][1];
        	if (i + t - 1 > n) continue;
        	for (int j = 1; j <= 50; j++) {
        		if (i - j < 0) break;
        		dp[i + t - 1] = Math.max(dp[i + t - 1], dp[i - j] + p);
        	}
        	res = Math.max(res, dp[i + t - 1]);
        }
        
        bw.write(String.valueOf(res));
        bw.flush();
    }
    
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}