// 11612kb 76ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MAX_GROUP = 13 * 13 + 12 * 12;       // 25 * 25의 최대 단지 개수
    static int n;
    static int[][] matrix;
    static int[] groupSize;
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        groupSize = new int[MAX_GROUP + 2];        // 0, 1 제외 단지 크기

        char[] input;
        for (int i = 0; i < n; i++) {
            input = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = input[j] - '0';
            }
        }

        int groupIdx = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dfs(i, j, groupIdx++);
                }
            }
        }
        
        System.out.println(groupIdx - 2);
        Arrays.sort(groupSize);
        for (int i = 0; i < groupSize.length; i++) {
            if (groupSize[i] == 0) {
                continue;
            }
            System.out.println(groupSize[i]);
        }
    }

    static void dfs(int i, int j, int groupIdx) {
        matrix[i][j] = groupIdx;
        groupSize[groupIdx]++;

        for (int[] delta : deltas) {
            int ni = i + delta[0];
            int nj = j + delta[1];
            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                if (matrix[ni][nj] == 1) {
                    dfs(ni, nj, groupIdx);
                }
            }
        }
    }
}
