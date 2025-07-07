import java.io.*;
import java.util.*;

/**
 * 13120KB	132ms
 * 건너는게 가능"했던" 돌에서 다음 돌까지 건너는 게 가능한지 확인하기
 * 5000 * 5000 N^2로 가능
 *  */ 

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] dp = new boolean[N+1];    
        int[] stone = new int[N+1];
        dp[1] = true;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            stone[i] = Integer.parseInt(st.nextToken());
        }

        for(int j = 2; j <= N; j++){
            for(int i = 1; i < j; i++){
                if(dp[i]){
                    int w = (j - i) * (1 + Math.abs(stone[i] - stone[j]));
                    if(w > K) continue;
                    else dp[j] = true;
                }
            }
        }

        if(dp[N]) System.out.println("YES");
        else System.out.println("NO");
    }
}
