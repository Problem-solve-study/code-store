// 76384KB, 388ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] candies = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                candies[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int c1 = i > 0 ? candies[i - 1][j] : 0;
                int c2 = j > 0 ? candies[i][j - 1] : 0;
                int c3 = i > 0 && j > 0 ? candies[i - 1][j - 1] : 0;
                candies[i][j] += Math.max(c1, Math.max(c2, c3));
            }
        }

        System.out.println(candies[n - 1][m - 1]);
    }
}
