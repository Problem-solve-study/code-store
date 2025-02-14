// 67760kb 168ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static int[][] matrix;
    static int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            matrix = new int[N][M];
            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                matrix[i][j] = 1;
            }

            System.out.println(solve());
        }
    }

    static int solve() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {
                    search(i, j);
                    count++;

                    for (int mi = 0; mi < N; mi++) {
                        System.out.println(Arrays.toString(matrix[mi]));
                    }
                    System.out.println("=====");
                }
            }
        }

        return count;
    }

    static void search(int si, int sj) {
        int[][] queue = new int[N * M][2];
        int front = 0;
        int rear = 0;

        matrix[si][sj] = 0;
        queue[rear][0] = si;
        queue[rear++][1] = sj;

        while (front != rear) {
            int i = queue[front][0];
            int j = queue[front++][1];

            for (int[] delta : deltas) {
                int ni = i + delta[0];
                int nj = j + delta[1];
                if (ni >= 0 && ni < N && nj >= 0 && nj < M && matrix[ni][nj] == 1) {
                    matrix[ni][nj] = 0;
                    queue[rear][0] = ni;
                    queue[rear++][1] = nj;
                }
            }
        }
    }
}
