// 11616KB  72ms
import java.util.*;
import java.io.*;
/*
 * dp를 이용해서 푸는 문제.
 * 아직 dp가 익숙하지 않아 알고리즘을 어떻게 짜야할지 많이 헤멨다.
 * 또한 계단 수가 1일 때 잘 고려해서 실제 코테 대비해야할듯.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stair = new int[N];
        for(int i=0; i<N; i++){
            stair[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N+1];
        //dp[0]이 시작점
        dp[0] = 0;
        dp[1] = stair[0];
        //계단이 1개일 때 고려
        if(N>1) dp[2] = stair[0]+stair[1];

        for(int i=3; i<=N; i++){
            // 2칸 전에 값에서 1칸 뛰어넘고 올지 아니면 2칸 전을 뛰어넘고 1칸 전에와 현재 칸을 더할지 선택.
            dp[i] = Math.max(dp[i-2], dp[i-3]+stair[i-2])+stair[i-1];
        }

        System.out.println(dp[N]);
    }
}
