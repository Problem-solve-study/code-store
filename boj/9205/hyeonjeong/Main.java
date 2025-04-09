// 12508KB 88ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 * 현재 노드와 모든 정점의 거리가 20 * 50 이내면 방문 가능
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (; t > 0; t--) {
            int n = Integer.parseInt(br.readLine());

            int[][] spots = new int[n + 2][3];
            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                spots[i][0] = Integer.parseInt(st.nextToken());
                spots[i][1] = Integer.parseInt(st.nextToken());
                spots[i][2] = 0;        // visited
            }

            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(0);
            spots[0][2] = 1;

            boolean succeed = false;
            while (!queue.isEmpty()) {
                int ci = queue.poll();

                if (ci == n + 1) {
                    succeed = true;
                    break;
                }

                for (int ni = 0; ni < n + 2; ni++) {
                    if (spots[ni][2] == 1) {
                        continue;
                    }

                    if (Math.abs(spots[ci][0] - spots[ni][0]) + Math.abs(spots[ci][1] - spots[ni][1]) > 20 * 50) {
                        continue;
                    }

                    spots[ni][2] = 1;
                    queue.add(ni);
                }
            }

            if (succeed) {
                sb.append("happy\n");
                continue;
            }

            sb.append("sad\n");
        }

        System.out.println(sb);
    }
}
