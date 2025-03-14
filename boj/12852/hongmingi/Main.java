// 20276KB	132ms
import java.io.*;
import java.util.*;
/*
 * BFS와 DP로 풀 수 있는 문제지만 BFS는 여러 문제 풀어봤기 때문에 DP를 통해 구현해 보았다.
 * 다만 역추적을 추가했어야 했는데 역추적 문제는 처음이라 방법을 생각하는 것이 생각보다 까다로웠다.
 */
public class Main{
    // static int N, cnt, res;
    static int[] dp, backIdx;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        backIdx = new int[N+1];
        Arrays.fill(dp, 100000);
        dp[N] = 0;
        
        for(int i=N; i>0; i--){
            if(i%3==0 && dp[i/3]>dp[i]+1){
                dp[i/3] = dp[i]+1;
                backIdx[i/3] = i;
            }
            if(i%2==0 && dp[i/2]>dp[i]+1){
                dp[i/2] = dp[i]+1;
                backIdx[i/2] = i;
            }
            if(dp[i-1]>dp[i]+1){
                dp[i-1] = dp[i]+1;
                backIdx[i-1] = i;
            }
        }

        System.out.println(dp[1]);        
        backPrint(0);
    }

    static void backPrint(int idx){
        if(idx!=N){
            backPrint(backIdx[idx]);
            System.out.print(backIdx[idx]+" ");
        }
        return;
    }
}
