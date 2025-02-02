// 	44564KB	272ms

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] K;
    static int[] DP;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = new int[N+1];
        DP = new int[N+1];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken(); st.nextToken();
            K[i] = Integer.parseInt(st.nextToken());
        }
        DP[1] = K[1];
        for (int i=2; i<=N; i++) DP[i] = Math.max(DP[i-1], DP[i-2] + K[i]);
        System.out.println(DP[N]);
    }
}
