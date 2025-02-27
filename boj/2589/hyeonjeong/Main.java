// 	192412KB, 420ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static int n;
    static int m;
    static boolean[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) == 'L' ? true : false;
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]) {
                    int result = bfs(i, j);
                    if (result > max) {
                        max = result;
                    }
                }
            }
        }

        System.out.println(max);
    }

    static int bfs(int i, int j) {
        int[][] queue = new int[n * m][2];
        int front = 0, rear = 0;
        queue[rear][0] = i;
        queue[rear++][1] = j;

        boolean[][] visited = new boolean[n][m];
        visited[i][j] = true;

        int level = 0;
        while (front != rear) {
            int length = rear - front;
            for (int l = 0; l < length; l++) {
                int[] node = queue[front++];
    
                for (int[] delta : deltas) {
                    int ni = node[0] + delta[0];
                    int nj = node[1] + delta[1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] && visited[ni][nj] == false) {
                        visited[ni][nj] = true;
                        queue[rear][0] = ni;
                        queue[rear++][1] = nj;
                    }
                }
            }
            level++;
        }

        return level - 1;
    }
}
