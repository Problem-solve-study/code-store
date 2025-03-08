// 11728 KB, 84 ms
/*
 * N개의 정수 배열 A
 * 주어진 식의 최댓값.
 * N 최대 8 이므로 모든 경우 8!
 * 매개 변수로 이전 요소와 현재까지 계산한 식을 넘겨줘서 결과 계산 줄이기
 */

import java.io.IOException;

public class Main {
    static int N, max;
    static int[] arr;
    static boolean[] select;

    public static void main(String[] args) throws IOException {
        N = readInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
        }

        select = new boolean[N];
        for (int i = 0; i < N; i++) {
            select[i] = true;
            func(0, arr[i], 1);
            select[i] = false;
        }
        System.out.println(max);
    }

    static void func(int sum, int prev, int cnt) {
        if (cnt == N) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (select[i])
                continue;
            select[i] = true;
            func(sum + Math.abs(arr[i] - prev), arr[i], cnt + 1);
            select[i] = false;
        }
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        boolean negative = false;
        if (c == 45) {
            negative = true;
            c = System.in.read();
        }
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return negative ? -n : n;
    }
}
