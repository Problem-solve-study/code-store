import java.io.*;
import java.util.StringTokenizer;

/*
11560KB, 68ms

그래프 탐색 아니면 DP라고 생각했고 요즘 DP 배우고 있으니까 DP로 했다.
현재 값이 숫자라면 자신 주변에 존재하는 연산자를 찾고
다시 한 번 연산자 주변에 존재하는 피연산자를 찾아 연산하고 그 값을 기반으로 테이블을 갱신하는 과정을 반복
 */

public class Main {
    static int N;
    static char[][] map;
    static int[][] maxDp, minDp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        maxDp = new int[N][N];
        minDp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxDp[i][j] = -100_000_000;
                minDp[i][j] = 100_000_000;
            }
        }

        maxDp[0][0] = minDp[0][0] = map[0][0] - '0';
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) continue;
                if (Character.isDigit(map[i][j])) {
                    //왼쪽 피연산자가 존재하면
                    if (bc(i, j - 2)) {
                        char operator = map[i][j - 1];
                        maxDp[i][j] = Math.max(maxDp[i][j], calc(maxDp[i][j - 2], operator, map[i][j] - '0'));
                        minDp[i][j] = Math.min(minDp[i][j], calc(minDp[i][j - 2], operator, map[i][j] - '0'));
                    }

                    //위쪽 피연산자가 존재하면
                    if (bc(i - 2, j)) {
                        char operator = map[i - 1][j];
                        maxDp[i][j] = Math.max(maxDp[i][j], calc(maxDp[i - 2][j], operator, map[i][j] - '0'));
                        minDp[i][j] = Math.min(minDp[i][j], calc(minDp[i - 2][j], operator, map[i][j] - '0'));
                    }

                    //좌상단 피연산자가 존재하면
                    //좌상단 피연산자의 경우 →↓ 혹은 ↓→로 올 수 있으므로 양측 모두를 고려
                    if (bc(i - 1, j - 1)) {
                        char operator1 = map[i][j - 1];
                        char operator2 = map[i - 1][j];
                        maxDp[i][j] = Math.max(maxDp[i][j],
                                Math.max(calc(maxDp[i - 1][j - 1], operator1, map[i][j] - '0'),
                                        calc(maxDp[i - 1][j - 1], operator2, map[i][j] - '0'))
                        );
                        minDp[i][j] = Math.min(minDp[i][j],
                                Math.min(calc(minDp[i - 1][j - 1], operator1, map[i][j] - '0'),
                                        calc(minDp[i - 1][j - 1], operator2, map[i][j] - '0'))
                        );
                    }
                }
            }
        }

        System.out.print(maxDp[N - 1][N - 1] + " " + minDp[N - 1][N - 1]);
    }

    static int calc(int a, char c, int b) {
        if (c == '+') {
            return a + b;
        } else if (c == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < N);
    }
}