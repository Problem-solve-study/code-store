//11836KB	80ms
import java.io.*;
import java.util.*;
/*
 * block마다 이전 block들에 따라 나올 수 있는 최소의 에너지값을 dp로 탐색.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] blocks = br.readLine().toCharArray();

        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                if(blocks[j]=='B' && blocks[i] == 'O' && dp[j]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[j]+(i-j)*(i-j), dp[i]);
                }else if(blocks[j]=='O' && blocks[i]=='J'&& dp[j]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[j]+(i-j)*(i-j), dp[i]);
                }else if(blocks[j]=='J' && blocks[i]=='B'&& dp[j]!=Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[j]+(i-j)*(i-j), dp[i]);
                }
            }
        }

        if(dp[N-1]==Integer.MAX_VALUE)  System.out.println(-1);
        else System.out.println(dp[N-1]);
    }

}
