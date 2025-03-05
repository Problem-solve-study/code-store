// 	11596KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    // 다음 노드가 연속할 수 있는지, 반드시 건너뛰어야 하는지
    static final int CONTINUE = 0;
    static final int SKIP = 1;
    static int n;
    static int[] numbers;
    static int[][] scores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(numbers[0]);
            return;
        }

        scores = new int[n][2];

        finsMax();

        System.out.println(Math.max(scores[n - 1][CONTINUE], scores[n - 1][SKIP]));
    }

    static void finsMax() {
        scores[0][CONTINUE] = numbers[0];
        scores[0][SKIP] = numbers[0];

        scores[1][CONTINUE] = numbers[1];
        scores[1][SKIP] = numbers[0] + numbers[1];
        
        for (int i = 2; i < n; i++) {
            scores[i][CONTINUE] = Math.max(scores[i - 2][CONTINUE] + numbers[i], scores[i - 2][SKIP] + numbers[i]);
            scores[i][SKIP] = scores[i - 1][CONTINUE] + numbers[i];
        }
    }
}
