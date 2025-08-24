// 19772KB 304ms

import java.io.*;
import java.util.*;

// DP
// 연속된 LIS 외에는 다 바깥쪽으로 빼면 정렬됨
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] dp;
  
    public static void main(String[] args) throws IOException {
        int n = nextInt();
        dp = new int[n + 1];

        int max = 0;
        for (int i = 0; i < n; i++) {
            int number = nextInt();

            dp[number] = dp[number - 1] + 1;

            if (dp[number] > max) {
                max = dp[number];
            }
        }

        System.out.print(n - max);
    }


    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
