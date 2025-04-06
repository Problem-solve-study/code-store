/**
 * 	11524KB	64ms
 * 지금은 1등이긴 하지만, 또 뺏기겠죠...?
 * 
 * [사고흐름]
 * 또 수학문제인가보네 -> 아 DP구나
 * 숫자가 쓰인 격자에만 DP를 적용해서 구하면 될거라고 생각했습니다.
 * 
 * [알고리즘 설명]
 * maxDP와 minDP는 숫자가 쓰여진 각 격자까지 계산했을 때의 최대/최소를 저장합니다.
 * 이번에는 바텀업이 쉬워보여서, 바텀업으로 구현했습니다.
 * 
 * 현재 격자와 관련있는 격자는 다음 세 가지입니다.
 * - (-2, 0)
 * - (0, -2)
 * - (-1, -1)
 * 
 * 연관된 격자의 DP값과 현재 격자의 값, 그 사이의 연산자로 현재 격자의 최대/최솟값을 구해 DP에 저장합니다.
 */

import java.io.*;

public class Main {
    static int N;
    static char[][] map;
    static int[][] minDP;
    static int[][] maxDP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        minDP = new int[N][N];
        maxDP = new int[N][N];

        for (int r=0; r<N; ++r) {
            String line = br.readLine();
            for (int c=0; c<N; ++c) {
                map[r][c] = line.charAt(c << 1);
            }
        }
        maxDP[0][0] = minDP[0][0] = map[0][0]-'0';
        for (int c=2; c<N; c+=2) {
            maxDP[0][c] = minDP[0][c] = calc(minDP[0][c-2], map[0][c]-'0', map[0][c-1]);
        }
        for (int r=1; r<N; ++r) {
            for (int c=0; c<N; ++c) {
                if (((r+c)&1) == 0) {
                    int cur = map[r][c]-'0';
                    minDP[r][c] = Integer.MAX_VALUE;
                    maxDP[r][c] = Integer.MIN_VALUE;
                    if (isValid(r-2, c)) {
                        minDP[r][c] = Math.min(minDP[r][c], calc(minDP[r-2][c], cur, map[r-1][c]));
                        maxDP[r][c] = Math.max(maxDP[r][c], calc(maxDP[r-2][c], cur, map[r-1][c]));
                    }
                    if (isValid(r, c-2)) {
                        minDP[r][c] = Math.min(minDP[r][c], calc(minDP[r][c-2], cur, map[r][c-1]));
                        maxDP[r][c] = Math.max(maxDP[r][c], calc(maxDP[r][c-2], cur, map[r][c-1]));
                    }
                    if (isValid(r-1, c-1)) {
                        minDP[r][c] = Math.min(minDP[r][c], calc(minDP[r-1][c-1], cur, map[r][c-1]));
                        minDP[r][c] = Math.min(minDP[r][c], calc(minDP[r-1][c-1], cur, map[r-1][c]));
                        maxDP[r][c] = Math.max(maxDP[r][c], calc(maxDP[r-1][c-1], cur, map[r][c-1]));
                        maxDP[r][c] = Math.max(maxDP[r][c], calc(maxDP[r-1][c-1], cur, map[r-1][c]));
                    }
                }
            }
        }
        System.out.println(maxDP[N-1][N-1] + " " + minDP[N-1][N-1]);
    }

    static boolean isValid(int r, int c) {
        return 0<=r&&r<N && 0<=c&&c<N;
    }

    static int calc(int a, int b, char opr) {
        if (opr == '+') return a+b;
        else if (opr == '-') return a-b;
        else return a*b;
    }
}