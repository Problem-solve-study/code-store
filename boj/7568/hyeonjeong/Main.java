// 11608KB 68ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N이 50이길래 N^2 돌렸습니다.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] people = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            people[i][0] = x;
            people[i][1] = y;
        }

        StringBuilder sb = new StringBuilder();
        int count;
        for (int i = 0; i < n; i++) {
            count = 0;
            for (int j = 0; j < n; j++) {
                if (people[j][0] > people[i][0] && people[j][1] > people[i][1]) {
                    count++;
                }
            }

            sb.append(count + 1).append(' ');
        }

        System.out.println(sb);
    }
}
