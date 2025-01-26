import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer> fibonacci = new ArrayList<>();
    static int[][] dp = new int[20000][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        makeFibonacciSequence();
        dpInit();
        findMinMax(n);

        bw.write(String.format("%d %d", dp[n][0], dp[n][1]));
        bw.flush();

    }

    public static void makeFibonacciSequence() {
        //수열의 마지막항이 N 이하인 피보나치 수열 생성
        fibonacci.add(1); fibonacci.add(1);
        for (int i = 2; true; i++) {
            int next = fibonacci.get(i - 2) + fibonacci.get(i - 1);
            if (next <= n) {
                fibonacci.add(next);
            } else {
                break;
            }
        }
    }

    public static void dpInit() {
        //dp[n][0]: 인원수가 n일 때 치킨의 최솟값
        //dp[n][1]: 인원수가 n일 때 치킨의 최댓값
        dp[2][0] = 1; dp[2][1] = 1;
        dp[3][0] = 2; dp[3][1] = 2;
        dp[4][0] = 2; dp[4][1] = 2;
        dp[5][0] = 3; dp[5][1] = 3;
        dp[6][0] = 3; dp[6][1] = 4;
    }

    public static void findMinMax(int n) {
        if (dp[n][0] != 0) {
            return;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 2; fibonacci.get(i) <= n / 2; i++) {
            int value = fibonacci.get(i);
            findMinMax(n - value);
            findMinMax(value);

            min = Math.min(min, dp[n - value][0] + dp[value][0]);
            max = Math.max(max, dp[n - value][1] + dp[value][1]);
        }

        dp[n][0] = min;
        dp[n][1] = max;
    }
}