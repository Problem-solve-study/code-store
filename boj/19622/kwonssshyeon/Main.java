// 46860KB 348ms
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] meetings;
    static int[] people;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        meetings = new int[n+1][2];
        people = new int[n+1];
        for (int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
            people[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1];
        dp[1] = people[1];
        for (int i=2; i<=n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + people[i]);
        }
        System.out.print(dp[n]);
    }
}