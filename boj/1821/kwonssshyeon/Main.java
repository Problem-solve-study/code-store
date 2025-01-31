// 17028KB	544ms
import java.util.*;
import java.io.*;

public class Main {
    static int n,f;
    static boolean[] visited;
    static int[] coef;
    static int[] answer;
    static int[][] dp; 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        
        
        dp = new int[n+1][n+1];
        coef = new int[n];
        for (int i=0; i<n; i++) {
            coef[i] = calcCoef(n-1, i);
        }

        answer = new int[n];
        Arrays.fill(answer, n);
        visited = new boolean[n];
        dfs(new int[n], 0);

        for (int i=0; i<n; i++) {
            System.out.print(answer[i]+" ");
        }
    }


    static void dfs(int[] order, int idx) {
        if (idx == n) {
            int sum = 0;
            for (int i=0; i<n; i++) {
                sum += (order[i]*coef[i]);
            }
            if (sum == f) {
                if (Arrays.compare(order, answer) < 0) {
                    answer = Arrays.copyOf(order, n);
                }
            }
            
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[idx] = i+1;
                dfs(order, idx + 1);
                visited[i] = false;
            }
        }
    }


   
    static int calcCoef(int n, int r) {
        if (r == 0 || n == r) {
            return 1;
        }
        if (dp[n][r] != 0) {
            return dp[n][r];
        }
        return dp[n][r] = calcCoef(n - 1, r - 1) + calcCoef(n - 1, r);
    }
}