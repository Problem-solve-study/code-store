// 12332KB 84ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int n;
    static int m;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = input[j] - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<List<Integer>> queue = new ArrayDeque<>();

        
        int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        queue.add(Arrays.asList(0, 0));

        int level = 1;
        while (queue.size() > 0) {
            int currLevelCnt = queue.size();
            for (int cnt = 0; cnt < currLevelCnt; cnt++) {
                List<Integer> node = queue.poll();

                for (int[] delta : deltas) {
                    int ni = node.get(0) + delta[0];
                    int nj = node.get(1) + delta[1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && matrix[ni][nj] == 1 && !visited[ni][nj]) {
                        if (ni == n - 1 &&  nj == m - 1) {
                            return ++level;
                        }

                        visited[ni][nj] = true;
                        queue.add(Arrays.asList(ni, nj));
                    }
                }
            }

            level++;
        }

        return 0;
    }
}