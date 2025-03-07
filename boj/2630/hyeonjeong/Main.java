// 	19980KB 224ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[][] matrix;
    static int cnt0 = 0;
    static int cnt1 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int result = search(0, 0, n);
        count(result);

        System.out.println(cnt0);
        System.out.println(cnt1);
    }

    static int search(int i, int j, int length) {
        if (length == 1) {
            return matrix[i][j];
        }

        int nextLength = length >> 1;
        int result1 = search(i, j, nextLength);
        int result2 = search(i + nextLength, j, nextLength);
        int result3 = search(i, j + nextLength, nextLength);
        int result4 = search(i + nextLength, j + nextLength, nextLength);
        if (result1 == result2 && result2 == result3 && result3 == result4) {
            return result1;
        }

        count(result1);
        count(result2);
        count(result3);
        count(result4);

        return -1;
    }

    static void count(int result) {
        switch (result) {
            case 0: cnt0++; break;
            case 1: cnt1++; break;
            default: break;
        }
    }
}
