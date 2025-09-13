
/*
 * dpMax[i][j] = i, j 구간에서 최댓값
 * dpMin[i][j] = i, j 구간에서 최솟값
 * 
 * 곱하기가 있어서 최솟값 * 최솟값이 최댓값이 될 가능성이 있음.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num = (N + 1) >> 1;

        char[] opt = new char[N >> 1];
        int[][] dpMax = new int[num][num];
        int[][] dpMin = new int[num][num];

        String inStr = br.readLine();
        for (int i = 0; i < N; i += 2) {
            int t = i >> 1;
            Arrays.fill(dpMin[t], Integer.MAX_VALUE);
            Arrays.fill(dpMax[t], Integer.MIN_VALUE);
            dpMax[t][t] = inStr.charAt(i) - '0';
            dpMin[t][t] = inStr.charAt(i) - '0';
        }
        for (int i = 1; i < N; i += 2)
            opt[i >> 1] = inStr.charAt(i);

        for (int gap = 1; gap < num; gap++) {
            for (int i = 0, j = i + gap; j < num; i++, j++) {
                for (int k = i; k < j; k++) {
                    switch (opt[k]) {
                        case '+':
                            dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] + dpMax[k + 1][j]);
                            dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] + dpMin[k + 1][j]);
                            break;
                        case '-':
                            dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] - dpMin[k + 1][j]);
                            dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] - dpMax[k + 1][j]);
                            break;
                        case '*':
                            dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] * dpMax[k + 1][j]);
                            dpMax[i][j] = Math.max(dpMax[i][j], dpMin[i][k] * dpMin[k + 1][j]);
                            dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] * dpMin[k + 1][j]);
                            dpMin[i][j] = Math.min(dpMin[i][j], dpMax[i][k] * dpMin[k + 1][j]);
                            dpMin[i][j] = Math.min(dpMin[i][j], dpMin[i][k] * dpMax[k + 1][j]);
                    }
                }
            }
        }

        System.out.println(dpMax[0][num - 1]);
    }

}