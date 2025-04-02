
// 11596KB 64ms
import java.io.*;
import java.util.*;

// 완탐 !
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int result = 1;
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (data[i][0] < data[j][0] && data[i][1] < data[j][1]) {
                    result++;
                }
            }
            sb.append(result).append(" ");
        }
        System.out.print(sb);
    }
}