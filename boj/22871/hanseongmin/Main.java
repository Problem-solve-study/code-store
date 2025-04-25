import java.io.*;
import java.util.*;

/*
13144KB, 156ms

DP의 느낌이 스멀스멀 올라와서 DP로 풀이
문제에서 제시된 조건을 그대로 점화식으로 옮기면 됨
다익스트라로도 풀 수 있다고 하는데 참신한듯
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        //dp[i]: 현재 돌까지 이동할 때 K의 최솟값
        long[] dp = new long[N];
        //K의 최솟값을 구할 것이니 초기값은 MAX로 설정
        Arrays.fill(dp, Long.MAX_VALUE);

        //가장 왼쪽에 있는 돌은 이미 도착해있으므로 dp[0]은 0임
        dp[0] = 0;
        //이제 각 돌마다 다음 돌로 움직였을 때의 최소 K를 구함
        for (int i = 0; i < N; i++) {
            //i번째 돌에서 j번째 돌로 움직임
            for (int j = i + 1; j < N; j++) {
                //dp 테이블은 최솟값을 구해야하므로 min 사용
                //i -> j로 움직일 때는 최대 k의 힘이 들어야 하므로
                //i까지 움직였을 때의 힘과 i -> j로 움직일 때의 힘 중 최댓값을 선택
                dp[j] = Math.min(dp[j], Math.max(dp[i], (long)(j - i) * (1 + Math.abs(arr[i] - arr[j]))));
            }
        }
        System.out.print(dp[N - 1]);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
