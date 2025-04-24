// 11548KB 68ms

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 그냥 완탐
 * heap에 넣어서 정렬
 */
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] matrix = new char[r][c];
        PriorityQueue<String> heap = new PriorityQueue<>();

        for (int i = 0; i < r; i++) {
            matrix[i] = br.readLine().toCharArray();

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] != '#') {
                    sb.append(matrix[i][j]);
                    continue;
                }

                if (sb.length() > 1) {
                    heap.add(sb.toString());
                }
                sb = new StringBuilder();
            }

            if (sb.length() > 1) {
                heap.add(sb.toString());
            }
        }

        for (int j = 0; j < c; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                if (matrix[i][j] != '#') {
                    sb.append(matrix[i][j]);
                    continue;
                }

                if (sb.length() > 1) {
                    heap.add(sb.toString());
                }
                sb = new StringBuilder();
            }

            if (sb.length() > 1) {
                heap.add(sb.toString());
            }
        }

        System.out.print(heap.poll());
    }
}
