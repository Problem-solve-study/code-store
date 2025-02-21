import java.io.*;
import java.util.*;

/*
 * 13228KB, 88ms
 * 
 * 순수 DP 플래티넘 문제. 매우 어려움. 테이블을 모델링하는 것까지는 쉽지만
 * 그 이후 점화식을 세우는 것이 매우 힘들었음
 * 현수 도움이 아니었으면 못풀었을듯
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int n;
    static int[] stones;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        n = nextInt();
        stones = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stones[i] = nextInt();
        }

        //n장소까지 보았을 때 철수의 최소 작업 횟수
        dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = stones[1] == stones[2] ? 1 : 2;
        for (int i = 3; i <= n; i++) {
        	//현재 장소에서 2번 연산을 수행하는 경우
        	dp[i] = dp[i - 1] + 1;
        	
        	//현재 장소 이전에서 1번 연산을 통해 작업 횟수가 줄어드는지 탐색
            int prev = stones[i];
            for (int j = i - 1; j >= 0; j--) {
            	prev = stones[j] - prev;
            	if (prev < 0) break;
            	//만일 이전 조약돌 값이 0이라면
            	//해당 위치에서 i번까지 1번 연산을 반복하는 것으로도
            	//모든 조약돌을 가져갈 수 있음
                if (prev == 0) {
                	dp[i] = Math.min(dp[i], i - j + dp[j - 1]);
                	break;
                }
            }
        }
        
        bw.write(String.valueOf(dp[n]));
        bw.flush();
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}