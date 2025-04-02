
//11592KB 68ms
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] list = new int[N][2];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int level = 1;
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                if (list[i][0] < list[j][0] && list[i][1] < list[j][1]) {
                    level++;
                }
            }
            sb.append(level + " ");
        }
        System.out.println(sb.toString());
    }
}